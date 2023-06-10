package uk.co.mistyknives.kickrpc.util;

import com.google.gson.*;
import lombok.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    private String username, clientId, enableStreamTime;

    public static Config load() {
        String filePath = "config.json";

        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(reader, Config.class);
        } catch (IOException e) {

            return null;
        }
    }

    public static boolean isAlreadySetup() {
        if(!new File("config.json").exists()) return false;

        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("config.json")));
            JsonElement jsonElement = JsonParser.parseString(jsonString);
            String value = jsonElement.getAsJsonObject().get("username").getAsString();
            return !value.equalsIgnoreCase("{YOUR_USERNAME}");
        } catch (IOException e) {
            System.err.println("Failed to read the JSON file: " + e.getMessage());
            return false;
        }
    }

    public static Config save(Config config) {
        String filePath = "config.json";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(config);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
            System.out.println("Successfully saved the Config object to the JSON file.");
            return config;
        } catch (IOException e) {
            System.err.println("Failed to write the JSON file: " + e.getMessage());
            return null;
        }
    }
}
