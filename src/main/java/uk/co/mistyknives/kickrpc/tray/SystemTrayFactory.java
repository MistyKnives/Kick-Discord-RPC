package uk.co.mistyknives.kickrpc.tray;

import uk.co.mistyknives.kickrpc.KickRPC;
import uk.co.mistyknives.kickrpc.tray.impl.WindowsSystemTray;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

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
public class SystemTrayFactory {
    private static final String ICON_PATH = "images/logo.jpg";

    public static SystemTrayInterface createSystemTray() {
        String system = System.getProperty("os.name").toLowerCase();

        if (system.startsWith("win")) return new WindowsSystemTray();
        else throw new UnsupportedOperationException("System tray is not supported on this platform.");
    }

    public static Image getIcon() {
        try {
            InputStream inputStream = KickRPC.class.getClassLoader().getResourceAsStream(ICON_PATH);
            if (inputStream != null) {
                return ImageIO.read(inputStream);
            } else {
                throw new IllegalStateException("Tray icon image not found: " + ICON_PATH);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load tray icon image: " + e.getMessage());
        }
    }
}