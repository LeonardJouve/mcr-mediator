package ui;

import player.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class RoomCreationFrame extends JFrame {
    private final JTextField nameField;
    private final JButton okButton;
    private final List<Player> players;
    private final JButton startButton;
    private final MainFrame mainFrame;

    public RoomCreationFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.nameField = new JTextField(15);
        this.okButton = new JButton("OK");
        this.startButton = new JButton("Start");
        this.startButton.setEnabled(false);
        this.players = new ArrayList<>();

        this.okButton.addActionListener(e -> addPlayer());
        this.startButton.addActionListener(e -> start());
    }

    void addPlayer() {
        String name = nameField.getText();
        players.add(new Player(name));

        if (players.size() >= mainFrame.getMediatorState().getMinPlayers()) {
            startButton.setEnabled(true);
        }

        if (players.size() >= mainFrame.getMediatorState().getMaxPlayers()) {
            okButton.setEnabled(false);
        }
    }

    void start() {
        mainFrame.setCurrentFrame(new GameFrame(mainFrame));
    }
}
