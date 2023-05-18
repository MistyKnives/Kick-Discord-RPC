package uk.co.mistyknives.kickrpc;

import com.google.common.base.Objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.jagrosh.discordipc.entities.RichPresence;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;

import lombok.SneakyThrows;

import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.livestream.Livestream;
import uk.co.mistyknives.kick4j.livestream.trees.LivestreamCategory;
import uk.co.mistyknives.kick4j.streamer.Streamer;
import uk.co.mistyknives.kick4j.user.User;

import uk.co.mistyknives.kickrpc.discord.DiscordClient;
import uk.co.mistyknives.kickrpc.logging.Log;
import uk.co.mistyknives.kickrpc.util.Config;

import java.util.Timer;
import java.util.TimerTask;

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

    public final String PUSHER_CLUSTER = "us2", PUSHER_KEY = "eb1d5f283081a78b932c";

    private String currentCategory, currentTitle;

    private final Config config = KickRPC.getConfig();

    private Pusher pusher;

    private Thread holdThread;

    private User user;

    private Livestream livestream;

    public KickAPI() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Streamer streamer = Kick4J.getInstance().getStreamer(KickRPC.getConfig().getUsername());
                if(streamer.getLivestream() == null) return;

                if(currentTitle == null || currentCategory == null) return;

                Livestream livestream = Kick4J.getInstance().getLivestream(config.getUsername());
                LivestreamCategory category = livestream.getCategories().stream().findFirst().get();

                if(!Objects.equal(livestream.getSessionTitle().toLowerCase(), currentTitle.toLowerCase()) || !Objects.equal(category.getName().toLowerCase(), currentCategory.toLowerCase()))
                    if(pusher != null) {
                        onEvent(new PusherEvent("App\\Events\\StreamerIsLive", "", "", ""));
                    }
            }
        }, 0, 3 * 60000);
    }

    public void setup() {
        PusherOptions options = new PusherOptions().setCluster(PUSHER_CLUSTER);
        this.pusher = new Pusher(PUSHER_KEY, options);

        this.pusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {
                if(change.getCurrentState() == ConnectionState.CONNECTED) {
                    Log.info("Pusher is Connected");
                }
            }
            @Override
            public void onError(String message, String code, Exception e) {
                Log.error("Connection error");
            }
        }, ConnectionState.ALL);

        Streamer streamer = Kick4J.getInstance().getStreamer(KickRPC.getConfig().getUsername());

        this.pusher.subscribe("channel." + streamer.getId())
                .bindGlobal(this::onEvent);

        if(streamer.getLivestream() != null) {
            this.onEvent(new PusherEvent("App\\Events\\StreamerIsLive", "", "", ""));
        }

        this.holdThread = new Thread(() -> {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        this.holdThread.start();
    }

    @SneakyThrows
    private void onEvent(PusherEvent event) {
        String type = event.getEventName();

        switch(type) {
            case "App\\Events\\StreamerIsLive":
                user = Kick4J.getInstance().getUser(config.getUsername());
                livestream = Kick4J.getInstance().getLivestream(user.getUsername());
                LivestreamCategory category = livestream.getCategories().stream().findFirst().get();

                JsonObject buttonObj = new JsonObject();
                buttonObj.addProperty("label", "Watch");
                buttonObj.addProperty("url", "https://kick.com/" + user.getUsername());
                JsonArray buttons = new JsonArray();
                buttons.add(buttonObj);

                RichPresence.Builder builder = new RichPresence.Builder()
                        .setState("Playing %s".formatted(category.getName()))
                        .setDetails(livestream.getSessionTitle())
                        .setLargeImage(livestream.getThumbnail().stream().findFirst().get().getUrl())
                        .setStartTimestamp(config.isDisplayElapsed() ? System.currentTimeMillis() : 0)
                        .setButtons(buttons);

                DiscordClient.getClient().sendRichPresence(builder.build());

                currentCategory = category.getName();
                currentTitle = livestream.getSessionTitle();

                Log.info("Updated Presence to: ");
                Log.info("Game     - " + currentCategory);
                Log.info("Title    - " + currentTitle);
                break;
            case "App\\Events\\StopStreamBroadcast":
                KickRPC.getDiscordClient().clear("Stopped Streaming, Clearing RPC");
                return;
        }
    }
}
