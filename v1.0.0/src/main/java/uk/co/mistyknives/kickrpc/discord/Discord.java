package uk.co.mistyknives.kickrpc.discord;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;

import uk.co.mistyknives.kickrpc.discord.events.EventReady;

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
public class Discord {

    private static IPCClient ipcClient;

    public static void load() {
        try {
            ipcClient = new IPCClient(1094344214864736398L);
            ipcClient.setListener(new EventReady());
            ipcClient.connect();
        } catch (NoDiscordClientException exception) {
            exception.printStackTrace();
        }
    }

    public static void clearRichPresence() {
        ipcClient.close();
        load();
    }

    public static IPCClient getClient() {
        return ipcClient;
    }
}
