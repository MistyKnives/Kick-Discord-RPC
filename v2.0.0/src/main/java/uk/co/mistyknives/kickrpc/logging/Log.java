package uk.co.mistyknives.kickrpc.logging;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import uk.co.mistyknives.kickrpc.util.ConsoleColors;
import uk.co.mistyknives.kickrpc.util.DateTime;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

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
public class Log {

    public static void info(String message) {
        Date date = Date.from(Instant.now());
        AnsiConsole.systemInstall();
        System.out.println(Ansi.ansi().render(ConsoleColors.MAGENTA_BRIGHT + DateTime.formatTime(date) + " " + ConsoleColors.GREEN_BRIGHT + "[INFO] " + ConsoleColors.RESET + " \u00BB %s".formatted(message)));AnsiConsole.systemUninstall();
    }

    public static void error(String message) {
        Date date = Date.from(Instant.now());
        AnsiConsole.systemInstall();
        System.out.println(Ansi.ansi().render(ConsoleColors.MAGENTA_BRIGHT + DateTime.formatTime(date) + " " + ConsoleColors.RED_BRIGHT + "[ERROR] " + ConsoleColors.RESET + " \u00BB %s".formatted(message)));
    }
}
