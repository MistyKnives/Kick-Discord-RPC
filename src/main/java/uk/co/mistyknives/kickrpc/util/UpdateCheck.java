package uk.co.mistyknives.kickrpc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

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
public class UpdateCheck {

    public static boolean isLatest() {
        try {
            URL url = new URL("https://raw.githubusercontent.com/MistyKnives/Kick-Discord-RPC/main/version.txt");
            URLConnection connection = url.openConnection();
            return new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine().equalsIgnoreCase("v3.0.0");
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static String getLatest() {
        try {
            URL url = new URL("https://raw.githubusercontent.com/MistyKnives/Kick-Discord-RPC/main/version.txt");
            URLConnection connection = url.openConnection();
            return new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
