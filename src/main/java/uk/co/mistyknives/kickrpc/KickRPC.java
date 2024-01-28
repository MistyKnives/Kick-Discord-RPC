package uk.co.mistyknives.kickrpc;

import com.jagrosh.discordipc.IPCClient;

import com.pusher.client.Pusher;

import lombok.Getter;
import uk.co.mistyknives.kickrpc.discord.DiscordClient;
import uk.co.mistyknives.kickrpc.guis.ConfigureGUI;
import uk.co.mistyknives.kickrpc.pusher.PusherClient;

import uk.co.mistyknives.kickrpc.tray.*;
import uk.co.mistyknives.kickrpc.util.*;

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
public class KickRPC implements IKickRPC {

    @Getter
    private static KickRPC instance;

    public Config config;

    public IPCClient discordClient;
    public DiscordClient discordBackend;

    public Pusher pusherClient;
    public PusherClient pusherBackend;

    public ConfigureGUI configGUI;

    private SystemTrayInterface systemTray;

    private boolean discordSetup = false, pusherSetup = false;

    @Override
    public KickRPC setup() throws Exception {
        instance = this;

        CustomTheme.setup();

        config = Config.load();
        configGUI = new ConfigureGUI();

        systemTray = SystemTrayFactory.createSystemTray();
        systemTray.addTrayIcon(SystemTrayFactory.getIcon(), "KickRPC v4.0.2");

        Thread shutdownHook = new Thread(this::shutdown);
        Runtime.getRuntime().addShutdownHook(shutdownHook);

        if(Config.isAlreadySetup()) {
            System.out.println("config is already loaded!");
            load();
            return this;
        }

        configGUI.setVisible(true);

        return this;
    }

    @Override
    public KickRPC load() throws Exception {
        // Update config again just in case it's been updated
        config = Config.load();

        if(UpdateCheck.isLatest("4.0.2", UpdateCheck.getLatestVersion())) systemTray.displayMessage("Out of Date", "%s is no longer supported\nPlease consider updating!".formatted(UpdateCheck.getVersion()));

        // Discord
        this.discordBackend = new DiscordClient();
        this.discordBackend.setup();
        this.discordClient = this.discordBackend.getClient();

        // Pusher
        this.pusherBackend = new PusherClient();
        this.pusherBackend.setup();
        this.pusherClient = this.pusherBackend.getClient();

        if(discordSetup && pusherSetup) {
            systemTray.displayMessage("Configuration", "Pusher: Connected\n" +
                            "Discord: " +
                            discordClient.getCurrentUser().getName() + "#" +
                            discordClient.getCurrentUser().getDiscriminator() + "\n\n" +
                            "Ready to Detect Your Stream!");
        }

        this.pusherBackend.checkIfLive();

        return this;
    }

    @Override
    public void shutdown() {
        // Discord
        if(this.discordBackend != null) discordBackend.shutdown();

        // Pusher
        if(this.pusherBackend != null) pusherBackend.shutdown();

        systemTray.removeTrayIcon();

        Config.save(this.getConfig());

        Runtime.getRuntime().halt(1);
    }

    @Override
    public Config getConfig() {
        return this.config;
    }

    @Override
    public Config updateConfig(Config newConfig) {
        this.config = newConfig;
        return Config.save(newConfig);
    }

    @Override
    public IPCClient getDiscord() {
        return this.discordClient;
    }

    @Override
    public DiscordClient getDiscordBackend() {
        return discordBackend;
    }

    @Override
    public Pusher getPusher() {
        return this.pusherClient;
    }

    @Override
    public PusherClient getPusherBackend() {
        return this.pusherBackend;
    }

    @Override
    public SystemTrayInterface getSystemTray() {
        return systemTray;
    }

    public void setDiscordSetup(boolean discordSetup) {
        this.discordSetup = discordSetup;
    }

    public void setPusherSetup(boolean pusherSetup) {
        this.pusherSetup = pusherSetup;
    }
}