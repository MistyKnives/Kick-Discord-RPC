package uk.co.mistyknives.kickrpc;

import org.fusesource.jansi.AnsiConsole;
import uk.co.mistyknives.kickrpc.discord.DiscordClient;
import uk.co.mistyknives.kickrpc.logging.Log;
import uk.co.mistyknives.kickrpc.popups.TimeElapsedPopup;
import uk.co.mistyknives.kickrpc.popups.UsernamePopup;
import uk.co.mistyknives.kickrpc.util.Config;
import uk.co.mistyknives.kickrpc.util.UpdateCheck;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class KickRPC {

    private static Config config;

    private static DiscordClient discordClient;

    private static KickAPI kickAPI;

    private static UsernamePopup usernamePopup;
    private static TimeElapsedPopup timeElapsedPopup;

    public static void main(String[] args) {
        if(Config.isAlreadySetup()) {
            setup();
            return;
        }

        usernamePopup = new UsernamePopup();
        usernamePopup.getButton().addActionListener(e -> {
            if(usernamePopup.getTextField().getText().equalsIgnoreCase("")) {
                usernamePopup.getButton().setEnabled(true);
                return;
            }

            timeElapsedPopup = new TimeElapsedPopup();
            timeElapsedPopup.getButton().addActionListener(e1 -> {
                Config.save(new Config(usernamePopup.getUsername(), timeElapsedPopup.isValue()));
                setup();
            });
        });
    }

    private static void setup() {
        System.setProperty("webdriver.firefox.logfile", "/dev/null");

        Thread shutdownHook = new Thread(AnsiConsole::systemUninstall);
        Runtime.getRuntime().addShutdownHook(shutdownHook);

        Log.info(" ");
        System.out.println("\n   ██╗  ██╗██╗ ██████╗██╗  ██╗     ██████╗ ██████╗  ██████╗\n" +
                "   ██║ ██╔╝██║██╔════╝██║ ██╔╝     ██╔══██╗██╔══██╗██╔════╝\n" +
                "   █████╔╝ ██║██║     █████╔╝█████╗██████╔╝██████╔╝██║     \n" +
                "   ██╔═██╗ ██║██║     ██╔═██╗╚════╝██╔══██╗██╔═══╝ ██║     \n" +
                "   ██║  ██╗██║╚██████╗██║  ██╗     ██║  ██║██║     ╚██████╗\n" +
                "   ╚═╝  ╚═╝╚═╝ ╚═════╝╚═╝  ╚═╝     ╚═╝  ╚═╝╚═╝      ╚═════╝\n");

        boolean latest = UpdateCheck.isLatest();
        if(!latest) Log.error("Your KickRPC is not up to date (%s), please update it if you want the latest features!".formatted(UpdateCheck.getLatest()));

        config = Config.setup();

        discordClient = new DiscordClient();
        discordClient.setup();

        kickAPI = new KickAPI();
        kickAPI.setup();
    }

    public static Config getConfig() {
        return config;
    }

    public static DiscordClient getDiscordClient() {
        return discordClient;
    }
}
