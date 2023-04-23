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
public enum LogType {
    DISCORD("Discord"), OBS("OBS");

    private final String value;

    LogType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
