package uk.co.mistyknives.kickrpc.logging;

import uk.co.mistyknives.kickrpc.util.ConsoleColors;
import uk.co.mistyknives.kickrpc.util.DateTime;

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
        System.out.println(ConsoleColors.PURPLE_BRIGHT + DateTime.formatTime(date) + " " + ConsoleColors.GREEN_BRIGHT + "[INFO] " + ConsoleColors.RESET + " » %s".formatted(message));
    }

    public static void error(String message) {
        Date date = Date.from(Instant.now());
        System.out.println(ConsoleColors.PURPLE_BRIGHT + DateTime.formatTime(date) + " " + ConsoleColors.RED_BRIGHT + "[ERROR] " + ConsoleColors.RESET + " » %s".formatted(message));
    }
}
