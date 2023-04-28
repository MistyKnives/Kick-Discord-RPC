package uk.co.mistyknives.kickrpc.logging;

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
    DISCORD("Discord", " "), OBS("OBS", "     ");

    private final String value;
    private final String spacing;

    LogType(String value, String spacing) {
        this.value = value;
        this.spacing = spacing;
    }

    public String getValue() {
        return value;
    }

    public String getSpacing() {
        return spacing;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
