package uk.co.mistyknives.kickrpc.discord;

import com.google.gson.JsonObject;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.User;

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
public class DiscordListener implements IPCListener {

    @Override
    public void onPacketSent(IPCClient ipcClient, Packet packet) {}

    @Override
    public void onPacketReceived(IPCClient ipcClient, Packet packet) {}

    @Override
    public void onActivityJoin(IPCClient ipcClient, String s) {}

    @Override
    public void onActivitySpectate(IPCClient ipcClient, String s) {}

    @Override
    public void onActivityJoinRequest(IPCClient ipcClient, String s, User user) {}

    @Override
    public void onReady(IPCClient ipcClient) {
        Log.info("Logged in as %s#%s".formatted(ipcClient.getCurrentUser().getName(), ipcClient.getCurrentUser().getDiscriminator()));
    }

    @Override
    public void onClose(IPCClient ipcClient, JsonObject jsonObject) {}

    @Override
    public void onDisconnect(IPCClient ipcClient, Throwable throwable) {}
}
