package uk.co.mistyknives.kickrpc.obs;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.jagrosh.discordipc.entities.RichPresence;

import io.obswebsocket.community.client.OBSRemoteController;
import io.obswebsocket.community.client.message.event.outputs.StreamStateChangedEvent;

import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.livestream.Livestream;
import uk.co.mistyknives.kick4j.livestream.trees.LivestreamCategory;
import uk.co.mistyknives.kickrpc.Main;
import uk.co.mistyknives.kickrpc.discord.Discord;
import uk.co.mistyknives.kickrpc.util.Config;
import uk.co.mistyknives.kickrpc.util.LogType;

import static uk.co.mistyknives.kickrpc.util.Logger.log;

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
public class OBS {

    private OBSRemoteController obsRemoteController;

    public void createOBSRemoteController() {
        this.obsRemoteController = OBSRemoteController.builder().autoConnect(true).host(Main.getConfig().getHost()).port(Main.getConfig().getPort()).password(Main.getConfig().getPassword())
                .lifecycle().withControllerDefaultLogging(false).withCommunicatorDefaultLogging(false).and().registerEventListener(StreamStateChangedEvent.class, this::onStreamStateChanged)
                .build();

        log(LogType.OBS, "Connected to OBS Websocket!");
    }

    private void onStreamStateChanged(StreamStateChangedEvent event) {
        switch (event.getOutputState()) {
            case "OBS_WEBSOCKET_OUTPUT_STARTED" -> {
                Config config = Main.getConfig();
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

                Discord.getClient().sendRichPresence(builder.build());
            }
            case "OBS_WEBSOCKET_OUTPUT_STOPPED" -> {
                Discord.clearRichPresence();
            }
        }
    }
}
