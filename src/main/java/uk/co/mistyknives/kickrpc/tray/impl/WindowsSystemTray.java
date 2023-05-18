package uk.co.mistyknives.kickrpc.tray.impl;

import uk.co.mistyknives.kickrpc.KickRPC;
import uk.co.mistyknives.kickrpc.guis.ConfigureGUI;
import uk.co.mistyknives.kickrpc.tray.SystemTrayInterface;

import java.awt.*;
import java.util.Arrays;

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
public class WindowsSystemTray implements SystemTrayInterface {
    private TrayIcon trayIcon;

    @Override
    public void addTrayIcon(Image image, String tooltip) {
        if (SystemTray.isSupported()) {
            SystemTray systemTray = SystemTray.getSystemTray();

            PopupMenu popupMenu = new PopupMenu();
            MenuItem configureItem = new MenuItem("Configure");
            MenuItem exitMenuItem = new MenuItem("Exit");

            configureItem.addActionListener(e -> Arrays.stream(ConfigureGUI.getWindows()).findFirst().get().setVisible(true));
            exitMenuItem.addActionListener(e -> { this.removeTrayIcon(); KickRPC.getInstance().shutdown(); });

            popupMenu.add(configureItem);
            popupMenu.add(exitMenuItem);

            trayIcon = new TrayIcon(image, tooltip, popupMenu);
            trayIcon.setImageAutoSize(true);

            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("Failed to add tray icon: " + e.getMessage());
            }
        } else {
            throw new UnsupportedOperationException("System tray is not supported on this platform.");
        }
    }

    @Override
    public void removeTrayIcon() {
        if (trayIcon != null) {
            SystemTray systemTray = SystemTray.getSystemTray();
            systemTray.remove(trayIcon);
        }
    }

    @Override
    public void displayMessage(String title, String message) {
        SystemTray tray = SystemTray.getSystemTray();
        TrayIcon trayIcon = tray.getTrayIcons()[0];

        trayIcon.displayMessage(title, message, TrayIcon.MessageType.NONE);
    }
}