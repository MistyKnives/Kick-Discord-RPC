package uk.co.mistyknives.kickrpc.util;

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
public class Logger {

    public static void log(LogType type, String message) {
        System.out.printf("[%s]%s| %s%n", type.getValue(), type == LogType.OBS ? "     " : " ", message);
    }
}
