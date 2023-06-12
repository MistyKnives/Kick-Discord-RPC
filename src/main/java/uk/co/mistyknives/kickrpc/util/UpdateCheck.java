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

    public static String latest = "";

    public static boolean isLatest() {
        try {
            URL url = new URL("https://mistyknives.co.uk/projects/kickrpc/version");
            URLConnection connection = url.openConnection();
            String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

            latest = version;
            return version.equalsIgnoreCase("v4.0.2");
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
