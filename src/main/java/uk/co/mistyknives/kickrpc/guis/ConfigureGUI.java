package uk.co.mistyknives.kickrpc.guis;

import uk.co.mistyknives.kickrpc.KickRPC;
import uk.co.mistyknives.kickrpc.util.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class ConfigureGUI extends JFrame {

    private JPanel mainPanel;

    private JPanel usernamePanel;
    private JLabel usernameTextLabel, fakeUsernameLabel;
    private JTextField usernameField;

    private JPanel discordClientPanel;
    private JLabel discordClientTextLabel, fakeDiscordClientLabel;
    private JTextField discordClientField;

    private JPanel streamTimePanel;
    private JLabel streamTimeTextLabel, fakeStreamTimeLabel;
    private JComboBox<String> streamTimeDropdown;

    private JPanel buttonPanel;
    private JButton saveButton;

    public ConfigureGUI() {
        setTitle("KickRPC v4.0.0 - Configuration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();

        setLayout(new BorderLayout());
        addComponents();

        pack();
        setIconImage(new ImageIcon(KickRPC.class.getResource("/images/logo.jpg")).getImage());
    }

    public void initComponents() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        usernameTextLabel = new JLabel("What is your Kick Username? ");
        fakeUsernameLabel = new JLabel();
        usernameField = new JTextField(15);
        usernameField.setText(Config.isAlreadySetup() ? KickRPC.getInstance().getConfig().getUsername() : "");

        streamTimePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        streamTimeTextLabel = new JLabel("Want to display your Stream Time? ");
        fakeStreamTimeLabel = new JLabel();
        streamTimeDropdown = new JComboBox<>();
        streamTimeDropdown.addItem("false");
        streamTimeDropdown.addItem("true");
        streamTimeDropdown.setSelectedItem(Config.isAlreadySetup() ? KickRPC.getInstance().getConfig().getEnableStreamTime().toLowerCase() : "False");

        discordClientPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        discordClientTextLabel = new JLabel("Discord Client ID (You don't need to change): ");
        fakeDiscordClientLabel = new JLabel();
        discordClientField = new JTextField(Config.isAlreadySetup() ? KickRPC.getInstance().getConfig().getClientId() : "1099615872777195530", 11);

        saveButton = new JButton("Save");
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.addActionListener(e -> {
            if(usernameField.getText().equalsIgnoreCase("") || discordClientField.getText().equalsIgnoreCase("")) {
                saveButton.setEnabled(true);
                return;
            }

            String username = usernameField.getText();
            String discordClientId = discordClientField.getText();
            String displayStreamTime = streamTimeDropdown.getSelectedItem().toString().toLowerCase();

            Config.save(new Config(username, discordClientId, displayStreamTime));
            setVisible(false);

            KickRPC.getInstance().getDiscordBackend().shutdown();
            KickRPC.getInstance().getPusherBackend().shutdown();

            try {
                KickRPC.getInstance().load();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void addComponents() {
        streamTimePanel.add(streamTimeTextLabel);
        streamTimePanel.add(streamTimeDropdown);
        streamTimePanel.add(fakeStreamTimeLabel);

        mainPanel.add(streamTimePanel, BorderLayout.CENTER);

        usernamePanel.add(usernameTextLabel);
        usernamePanel.add(usernameField);
        usernamePanel.add(fakeUsernameLabel);

        mainPanel.add(usernamePanel, BorderLayout.CENTER);

        discordClientPanel.add(discordClientTextLabel);
        discordClientPanel.add(discordClientField);
        discordClientPanel.add(fakeDiscordClientLabel);

        mainPanel.add(discordClientPanel, BorderLayout.CENTER);

        mainPanel.add(saveButton);

        add(mainPanel);
    }
}