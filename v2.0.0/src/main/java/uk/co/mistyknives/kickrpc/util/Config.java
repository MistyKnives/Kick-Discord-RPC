package uk.co.mistyknives.kickrpc.util;

import com.google.gson.Gson;

import lombok.Data;
import uk.co.mistyknives.kickrpc.Client;
import uk.co.mistyknives.kickrpc.logging.Log;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

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
@Data
public class Config {

    private String username, host, password, clientID;

    private boolean displayElapsed;

    private int port;

    public static Config setup() {
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(Client.class.getClassLoader().getResourceAsStream("config.json")))) {
            Log.info("Loaded Config");
            return new Gson().fromJson(reader, Config.class);
        } catch (IOException e) {
            Log.error(e.getMessage());
            return null;
        }
    }
}
