package uk.co.mistyknives.kickrpc;

import uk.co.mistyknives.kickrpc.discord.DiscordClient;
import uk.co.mistyknives.kickrpc.logging.Log;
import uk.co.mistyknives.kickrpc.obs.OBSWebsocket;
import uk.co.mistyknives.kickrpc.util.Config;

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
public class Client {

    private static Config config;

    private static DiscordClient discordClient;

    private static OBSWebsocket obsWebsocket;

    public static void main(String[] args) {
        Log.info("\n   ██╗  ██╗██╗ ██████╗██╗  ██╗     ██████╗ ██████╗  ██████╗\n" +
                "   ██║ ██╔╝██║██╔════╝██║ ██╔╝     ██╔══██╗██╔══██╗██╔════╝\n" +
                "   █████╔╝ ██║██║     █████╔╝█████╗██████╔╝██████╔╝██║     \n" +
                "   ██╔═██╗ ██║██║     ██╔═██╗╚════╝██╔══██╗██╔═══╝ ██║     \n" +
                "   ██║  ██╗██║╚██████╗██║  ██╗     ██║  ██║██║     ╚██████╗\n" +
                "   ╚═╝  ╚═╝╚═╝ ╚═════╝╚═╝  ╚═╝     ╚═╝  ╚═╝╚═╝      ╚═════╝\n");

        config = Config.setup();

        discordClient = new DiscordClient();
        discordClient.setup();

        obsWebsocket = new OBSWebsocket();
        obsWebsocket.setup();
    }

    public static Config getConfig() {
        return config;
    }

    public static DiscordClient getDiscordClient() {
        return discordClient;
    }

    public static OBSWebsocket getOBSWebsocket() {
        return obsWebsocket;
    }
}
