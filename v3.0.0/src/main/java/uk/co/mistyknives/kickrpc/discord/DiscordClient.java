package uk.co.mistyknives.kickrpc.discord;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;

import uk.co.mistyknives.kickrpc.logging.Log;

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
public class DiscordClient {

    private static IPCClient ipcClient;

    public void setup() {
        try {
            ipcClient = new IPCClient(1099615872777195530L);
            ipcClient.setListener(new DiscordListener());
            ipcClient.connect();
        } catch (NoDiscordClientException exception) {
            exception.printStackTrace();
        }
    }

    public void clear(String message) {
        try {
            ipcClient.close();
            ipcClient = new IPCClient(1099615872777195530L);
            ipcClient.connect();

            Log.info(message);
        } catch (NoDiscordClientException exception) {
            Log.error(exception.getMessage());
        }
    }

    public static IPCClient getClient() {
        return ipcClient;
    }
}
