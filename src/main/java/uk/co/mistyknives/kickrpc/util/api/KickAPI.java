package uk.co.mistyknives.kickrpc.util.api;

import co.casterlabs.rakurai.json.Rson;
import lombok.*;
import okhttp3.*;

import uk.co.mistyknives.kickrpc.util.api.impl.livestream.Livestream;
import uk.co.mistyknives.kickrpc.util.api.impl.streamer.Streamer;
import uk.co.mistyknives.kickrpc.util.api.impl.user.User;

import java.io.IOException;
import java.net.URL;

/**
 * Copyright MistyKnives © 2022-2023
 * <br>
 * ---------------------------------------
 * <br>
 * All Projects are located on my GitHub
 * <br>
 * Please provide credit where due :)
 * <br>
 * ---------------------------------------
 * <br>
 * https://github.com/MistyKnives
 */
public class KickAPI {

    public static final OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor((chain) -> chain.proceed(chain.request().newBuilder().removeHeader("Accept-Encoding").build())).build();

    public static User getUser(String username) throws IOException {
        String response = sendHttpRequest("user", username);
        return Rson.DEFAULT.fromJson(response, User.class);
    }

    public static Streamer getStreamer(String username) throws IOException {
        String response = sendHttpRequest("streamer", username);
        return Rson.DEFAULT.fromJson(response, Streamer.class);
    }

    private static String sendHttpRequest(String type, String username) throws IOException {
        Request.Builder builder = null;

        switch(type) {
            case "streamer" -> builder = new Request.Builder().url("https://kick.com/api/v1/channels/" + username);
            case "user"     -> builder = new Request.Builder().url("https://kick.com/api/v1/users/" + username);
        }

        try (Response response = client.newCall(builder.build()).execute()) {
            if(response.code() != 200) {

                try (Response updatedResponse = client.newCall(builder.build()).execute()) {
                    return updatedResponse.body().string();
                }
            } else {
                return response.body().string();
            }
        }
    }
}