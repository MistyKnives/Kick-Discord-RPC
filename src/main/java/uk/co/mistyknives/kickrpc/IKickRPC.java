package uk.co.mistyknives.kickrpc;

import com.jagrosh.discordipc.IPCClient;
import com.pusher.client.Pusher;
import uk.co.mistyknives.kickrpc.discord.DiscordClient;
import uk.co.mistyknives.kickrpc.pusher.PusherClient;
import uk.co.mistyknives.kickrpc.tray.SystemTrayInterface;
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
public interface IKickRPC {

    KickRPC setup() throws Exception;
    KickRPC load() throws Exception;
    void shutdown();

    // Config
    Config getConfig();
    Config updateConfig(Config newConfig);

    IPCClient getDiscord();
    DiscordClient getDiscordBackend();

    Pusher getPusher();
    PusherClient getPusherBackend();

    SystemTrayInterface getSystemTray();
}
