package uk.co.mistyknives.kickrpc.util;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

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
public class CustomTheme {

    public static void setup() {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());

            UIManager.put("Component.focusWidth", 2);
            UIManager.put("Panel.background", new Color(11, 14, 15));
            UIManager.put("Component.focusColor", new Color(0, 128, 0));
            UIManager.put("Component.borderColor", new Color(0, 128, 0));
            UIManager.put("ComboBox.buttonBackground", new ColorUIResource(83, 252, 24));
            UIManager.put("ComboBox.buttonDarkShadowColor", new ColorUIResource(83, 252, 24));
            UIManager.put("ComboBox.buttonHighlightColor", new ColorUIResource(83, 252, 24));
            UIManager.put("ComboBox.buttonShadowColor", new ColorUIResource(83, 252, 24));
            UIManager.put("ComboBox.selectionBackground", new ColorUIResource(63, 90, 58));
            UIManager.put("ComboBox.selectionForeground", new ColorUIResource(255, 255, 255));

        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }



    }
}
