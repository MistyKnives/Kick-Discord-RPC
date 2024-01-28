package uk.co.mistyknives.kickrpc.util;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

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

    private static final String GITHUB_API_URL = "https://api.github.com/repos/{owner}/{repo}/releases/latest";
    private static final String OWNER = "MistyKnives";
    private static final String REPO = "Kick-Discord-RPC";

    @SneakyThrows
    public static String getLatestVersion() {
        URL url = new URL(GITHUB_API_URL.replace("{owner}", OWNER).replace("{repo}", REPO));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Parse JSON response to get the latest version
            return parseLatestVersion(response.toString());
        } catch (IOException e) {
            return getVersion();
        } finally {
            connection.disconnect();
        }
    }

    public static String getVersion() {
        return "v4.0.2";
    }

    private static String parseLatestVersion(String jsonResponse) {
        // You can use a JSON parsing library like Jackson or Gson for a more robust solution
        // For simplicity, we'll use a simple String manipulation here
        int startIndex = jsonResponse.indexOf("\"tag_name\":\"") + 12;
        int endIndex = jsonResponse.indexOf("\"", startIndex);

        return jsonResponse.substring(startIndex, endIndex);
    }

    public static boolean isLatest(String currentVersion, String latestVersion) {
        String[] currentParts = currentVersion.split("\\.");
        String[] latestParts = latestVersion.split("\\.");

        for (int i = 0; i < currentParts.length; i++) {
            int current = Integer.parseInt(currentParts[i]);
            int latest = Integer.parseInt(latestParts[i]);

            if (current < latest) {
                return true;
            } else if (current > latest) {
                return false;
            }
        }

        return false;
    }
}
