package uk.co.mistyknives.kickrpc.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.*;
import uk.co.mistyknives.kickrpc.KickRPC;
import uk.co.mistyknives.kickrpc.logging.Log;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
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
@AllArgsConstructor
public class Config {

    private String username;

    private boolean displayElapsed;

    public static boolean isAlreadySetup() {
        File file = new File("config.json");
        if(!file.exists()) {
            Config.save(new Config("{YOUR_USERNAME}", false));
        }

        try (FileReader reader = new FileReader("config.json")) {
            Gson gson = new Gson();
            Config config = gson.fromJson(reader, Config.class);

            return !config.getUsername().equalsIgnoreCase("{YOUR_USERNAME}");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Config setup() {
        try (FileReader reader = new FileReader("config.json")) {
            Gson gson = new Gson();
            Log.info("Config Loaded");
            return gson.fromJson(reader, Config.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void save(Config config) {
        Gson gson = new Gson();
        String json = gson.toJson(config);

        try (FileWriter writer = new FileWriter("config.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
