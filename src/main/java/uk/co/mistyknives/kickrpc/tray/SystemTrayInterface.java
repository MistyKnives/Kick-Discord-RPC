package uk.co.mistyknives.kickrpc.tray;

import java.awt.*;

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
public interface SystemTrayInterface {
    void addTrayIcon(Image image, String tooltip);
    void removeTrayIcon();
    void displayMessage(String title, String message);
}