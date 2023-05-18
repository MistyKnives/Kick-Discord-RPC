package uk.co.mistyknives.kickrpc.util;

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
public class DateTime {

    public static String formatTime(Date date) {
        String hours = String.valueOf(date.getHours());
        String minutes = String.valueOf(date.getMinutes());
        String seconds = String.valueOf(date.getSeconds());

        hours = date.getHours() < 10 ? "0" + hours : hours;
        minutes = date.getMinutes() < 10 ? "0" + minutes : minutes;
        seconds = date.getSeconds() < 10 ? "0" + seconds : seconds;

        return hours + ":" + minutes + ":" + seconds;
    }
}
