package uk.co.mistyknives.kickrpc.discord;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.entities.pipe.PipeStatus;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;

import uk.co.mistyknives.kickrpc.KickRPC;

import javax.swing.*;

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

    public void setup() {
        try {
            ipcClient = new IPCClient(Long.parseLong(KickRPC.getInstance().getConfig().getClientId()));
            ipcClient.setListener(new DiscordListener());
            ipcClient.connect();

            KickRPC.getInstance().setDiscordSetup(true);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "There was an error processing a request from '%s':\n%s\n \nPlease contact Misty#0666 on Discord for further assistance".formatted("Discord", exception.getMessage()), "KickRPC v4.0.0 - Error", JOptionPane.ERROR_MESSAGE);
            KickRPC.getInstance().shutdown();

            exception.printStackTrace();
        }
    }

    public void clear(String message) {
        this.shutdown();
        this.setup();

        System.out.println(message);
    }

    public void shutdown() {
        try {
            if(ipcClient != null && ipcClient.getStatus() == PipeStatus.CONNECTED) {
                ipcClient.close();
                ipcClient = null;
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "There was an error processing a request from '%s':\n%s\n\nPlease contact Misty#0666 on Discord for further assistance".formatted("Discord", exception.getMessage()), "KickRPC v4.0.0 - Error", JOptionPane.ERROR_MESSAGE);
            KickRPC.getInstance().shutdown();

            exception.printStackTrace();
        }
    }

    public IPCClient getClient() {
        return ipcClient;
    }
}