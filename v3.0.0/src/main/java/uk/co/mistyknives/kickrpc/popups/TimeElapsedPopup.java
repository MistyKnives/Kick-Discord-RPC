package uk.co.mistyknives.kickrpc.popups;

import lombok.Data;

import javax.swing.*;
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
@Data
public class TimeElapsedPopup extends JFrame {

    private JSlider slider;
    private JButton button;
    private boolean value;

    public TimeElapsedPopup() {
        super("Display your stream time?");
        setLayout(new FlowLayout());

        setSize(320, 100);

        slider = new JSlider(JSlider.HORIZONTAL, 0, 1, 0);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(e -> value = slider.getValue() == 1);

        add(slider);

        button = new JButton("Save");

        button.addActionListener(e -> dispose());

        add(button);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
