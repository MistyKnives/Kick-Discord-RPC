package uk.co.mistyknives.kickrpc.discord;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;

import uk.co.mistyknives.kickrpc.KickRPC;

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

    private IPCClient ipcClient;
    private boolean isConnected;

    public void setup() {
        try {
            ipcClient = new IPCClient(Long.parseLong(KickRPC.getInstance().getConfig().getClientId()));
            ipcClient.setListener(new DiscordListener());
            ipcClient.connect();

            isConnected = true;

            KickRPC.getInstance().setDiscordSetup(true);
        } catch (NoDiscordClientException exception) {
            exception.printStackTrace();
        }
    }

    public void clear(String message) {
        try {
            if (ipcClient != null && isConnected) {
                ipcClient.close();
                ipcClient = null;
                isConnected = false;
            }

            ipcClient = new IPCClient(Long.parseLong(KickRPC.getInstance().getConfig().getClientId()));
            ipcClient.connect();

            isConnected = true;

            System.out.println(message);
        } catch (NoDiscordClientException exception) {
            exception.printStackTrace();
        }
    }

    public void shutdown() {
        try {
            if (ipcClient != null && isConnected) {
                ipcClient.close();
                ipcClient = null;
                isConnected = false;
            }
        } catch (IllegalStateException exception) {
            exception.printStackTrace();
        }
    }

    public IPCClient getClient() {
        return ipcClient;
    }
}