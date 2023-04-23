package uk.co.mistyknives.kickrpc;

import com.google.gson.Gson;

import uk.co.mistyknives.kickrpc.discord.Discord;
import uk.co.mistyknives.kickrpc.obs.OBS;
import uk.co.mistyknives.kickrpc.util.Config;

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
public class Main {

    private static Config config;

    public static void main(String[] args) {
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(Main.class.getResourceAsStream("/config.json")))) {
            config = new Gson().fromJson(reader, Config.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new OBS().createOBSRemoteController();
        Discord.load();
        while(true) {}
    }

    public static Config getConfig() {
        return config;
    }
}