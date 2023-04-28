package uk.co.mistyknives.kickrpc.obs;

import com.google.common.base.Objects;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.jagrosh.discordipc.entities.RichPresence;

import io.obswebsocket.community.client.OBSRemoteController;
import io.obswebsocket.community.client.message.event.outputs.StreamStateChangedEvent;

import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.livestream.Livestream;
import uk.co.mistyknives.kick4j.livestream.trees.LivestreamCategory;
import uk.co.mistyknives.kick4j.user.User;

import uk.co.mistyknives.kickrpc.Client;
import uk.co.mistyknives.kickrpc.discord.DiscordClient;
import uk.co.mistyknives.kickrpc.logging.Log;
import uk.co.mistyknives.kickrpc.util.Config;

import java.net.URISyntaxException;
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
public class OBSWebsocket {

    private final Config config = Client.getConfig();

    public static boolean isLive = false;
    private String currentCategory, currentTitle;

    public OBSWebsocket() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(!isLive) return;

                if(currentTitle == null || currentCategory == null) return;

                Livestream livestream = Kick4J.getInstance().getLivestream(config.getUsername());
                LivestreamCategory category = livestream.getCategories().stream().findFirst().get();

                if(!Objects.equal(livestream.getSessionTitle().toLowerCase(), currentTitle.toLowerCase()) || !Objects.equal(category.getName().toLowerCase(), currentCategory.toLowerCase()))
                    setPresenceData();
            }
        }, 0, 2 * 60000);
    }

    public void setup() {
        OBSRemoteController.builder().autoConnect(true).host(config.getHost()).port(config.getPort()).password(config.getPassword()).lifecycle().and().registerEventListener(StreamStateChangedEvent.class, this::onStreamStateChanged).build();
        Log.info("Connected to OBS Websocket");
    }

    public void onStreamStateChanged(StreamStateChangedEvent event) {
        switch (event.getOutputState()) {
            case "OBS_WEBSOCKET_OUTPUT_STARTED" -> {
                isLive = true;

                setPresenceData();
            }

            case "OBS_WEBSOCKET_OUTPUT_STOPPED" -> {
                isLive = false;

                Client.getDiscordClient().clear("No longer Streaming, Clearing Rich Presence...");
            }

        }
    }

    public void setPresenceData() {
        User user = Kick4J.getInstance().getUser(config.getUsername());
        Livestream livestream = Kick4J.getInstance().getLivestream(config.getUsername());
        LivestreamCategory category = livestream.getCategories().stream().findFirst().get();

        JsonObject buttonObj = new JsonObject();
        buttonObj.addProperty("label", "Watch");
        buttonObj.addProperty("url", "https://kick.com/" + config.getUsername());
        JsonArray buttons = new JsonArray();
        buttons.add(buttonObj);

        RichPresence.Builder builder = new RichPresence.Builder()
                .setState("Playing %s".formatted(category.getName()))
                .setDetails(livestream.getSessionTitle())
                .setLargeImage(livestream.getThumbnail().stream().findFirst().get().getUrl())
                .setStartTimestamp(config.isDisplayElapsed() ? System.currentTimeMillis() : 0)
                .setButtons(buttons);

        DiscordClient.getClient().sendRichPresence(builder.build());

        currentCategory = category.getName();
        currentTitle = livestream.getSessionTitle();

        Log.info("Updated Presence to: ");
        Log.info("Game     - " + currentCategory);
        Log.info("Title    - " + currentTitle);
        Log.info("Streamer - " + user.getUsername());
    }
}