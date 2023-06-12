package uk.co.mistyknives.kickrpc.pusher;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.inject.internal.util.Objects;
import com.jagrosh.discordipc.entities.Callback;
import com.jagrosh.discordipc.entities.RichPresence;
import com.pusher.client.*;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.connection.*;

import lombok.*;

import uk.co.mistyknives.kickrpc.*;
import uk.co.mistyknives.kickrpc.util.*;
import uk.co.mistyknives.kickrpc.util.api.KickAPI;
import uk.co.mistyknives.kickrpc.util.api.impl.livestream.Livestream;
import uk.co.mistyknives.kickrpc.util.api.impl.livestream.tree.LivestreamCategory;
import uk.co.mistyknives.kickrpc.util.api.impl.streamer.Streamer;
import uk.co.mistyknives.kickrpc.util.api.impl.user.User;

import javax.swing.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

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
public class PusherClient {

    public final String PUSHER_CLUSTER = "us2", PUSHER_KEY = "eb1d5f283081a78b932c";

    private String currentCategory, currentTitle;

    private final Config config = KickRPC.getInstance().getConfig();

    private Pusher pusher;

    private User user;

    private Livestream livestream;

    private Streamer streamer;

    public Thread holdThread;

    public PusherClient() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(streamer != null) {
                    try {
                        streamer = KickAPI.getStreamer(KickRPC.getInstance().getConfig().getUsername());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    if (streamer.getLivestream() == null) return;
                    if (currentTitle == null || currentCategory == null) return;

                    livestream = streamer.getLivestream();
                    LivestreamCategory category = livestream.getCategories().stream().findFirst().get();

                    if (!Objects.equal(livestream.getSessionTitle().toLowerCase(), currentTitle.toLowerCase()) || !Objects.equal(category.getName().toLowerCase(), currentCategory.toLowerCase()))
                        if (pusher != null) {
                            onEvent(new PusherEvent("App\\Events\\StreamerIsLive", "", "", ""));
                        }
                }
            }
        }, 0, 60000 * 2);
    }

    public void setup() throws IOException {
        PusherOptions options = new PusherOptions().setCluster(PUSHER_CLUSTER);
        this.pusher = new Pusher(PUSHER_KEY, options);

        this.pusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {
                if(change.getCurrentState() == ConnectionState.CONNECTED) {
                    System.out.println("Pusher is Connected");
                }
            }
            @Override
            public void onError(String message, String code, Exception e) {
                JOptionPane.showMessageDialog(null, "There was an error processing a request from '%s':\n%s\n \nPlease contact Misty#0666 on Discord for further assistance".formatted("Pusher", e.getMessage()), "KickRPC v4.0.0 - Error", JOptionPane.ERROR_MESSAGE);
                KickRPC.getInstance().shutdown();
            }
        }, ConnectionState.ALL);

        streamer = KickAPI.getStreamer(config.getUsername());
        livestream = streamer.getLivestream();

        this.pusher.subscribe("channel." + streamer.getId()).bindGlobal(this::onEvent);

        KickRPC.getInstance().setPusherSetup(true);

        this.holdThread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(Long.MAX_VALUE);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        this.holdThread.start();
    }

    private void onEvent(PusherEvent event) {
        String type = event.getEventName();

        try {
            switch (type) {
                case "App\\Events\\StreamerIsLive":
                    user = KickAPI.getUser(config.getUsername());
                    Streamer streamer = KickAPI.getStreamer(user.getUsername());
                    livestream = streamer.getLivestream();

                    if (livestream == null) return;

                    LivestreamCategory category = livestream.getCategories().stream().findFirst().get();

                    JsonObject buttonObj = new JsonObject();
                    buttonObj.addProperty("label", "Watch");
                    buttonObj.addProperty("url", "https://kick.com/" + user.getUsername());
                    JsonArray buttons = new JsonArray();
                    buttons.add(buttonObj);

                    RichPresence.Builder builder = new RichPresence.Builder()
                            .setState("Playing %s".formatted(category.getName()))
                            .setDetails(livestream.getSessionTitle())
                            .setLargeImage(livestream.getThumbnail().getUrl())
                            .setStartTimestamp(config.getEnableStreamTime().equals("true") ? System.currentTimeMillis() : 0)
                            .setButtons(buttons);

                    KickRPC.getInstance().getDiscord().sendRichPresence(builder.build());

                    currentCategory = category.getName();
                    currentTitle = livestream.getSessionTitle();

                    System.out.println("Updated Presence to: ");
                    System.out.println("Game     - " + currentCategory);
                    System.out.println("Title    - " + currentTitle);

                    KickRPC.getInstance().getSystemTray().displayMessage("Displaying Stream", "Title: %s\nGame: %s".formatted(currentTitle, currentCategory));
                    break;
                case "App\\Events\\StopStreamBroadcast":
                    KickRPC.getInstance().getDiscordBackend().clear("Stopped Streaming, Clearing RPC");
                    KickRPC.getInstance().getSystemTray().displayMessage("Clearing Stream", "Stream no longer detected, clearing RPC.");
                    return;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "There was an error processing a request from '%s':\n%s\n \nPlease contact Misty#0666 on Discord for further assistance".formatted("Pusher", e.getMessage()), "KickRPC v4.0.0 - Error", JOptionPane.ERROR_MESSAGE);
            KickRPC.getInstance().shutdown();
        }
    }

    public void checkIfLive() {
        if(livestream != null) {
            this.onEvent(new PusherEvent("App\\Events\\StreamerIsLive", "", "", ""));
        }
    }

    public void shutdown() {
        this.holdThread.interrupt();
    }

    public Pusher getClient() {
        return pusher;
    }
}
