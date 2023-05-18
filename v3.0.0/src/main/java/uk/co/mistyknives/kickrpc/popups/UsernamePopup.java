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
public class UsernamePopup extends JFrame {

    private String username;

    private JTextField textField;
    private JButton button;

    public UsernamePopup() {
        super("Enter Your Kick Username");
        setLayout(new FlowLayout());

        setSize(320, 75);

        textField = new JTextField(20);
        add(textField);

        button = new JButton("Save");

        button.addActionListener(e -> {
            if(textField.getText().equalsIgnoreCase("")) {
                button.setEnabled(true);
                return;
            }

            setUsername(textField.getText());
            dispose();
        });

        add(button);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
