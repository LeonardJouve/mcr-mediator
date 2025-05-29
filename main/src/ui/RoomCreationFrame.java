package ui;

import player.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class RoomCreationFrame extends JFrame {
    private final JTextField nameField;
    private final JButton okButton;     // bouton pour ajouter un joueur avec son nom
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

        this.add(this.nameField);
        this.add(this.okButton);
        this.add(this.startButton);

        this.nameField.setBounds(100, 100, 100, 20);
        this.okButton.setBounds(250, 100, 100, 20);
        this.startButton.setBounds(50, 50, 80, 30);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 800);
    }

    void addPlayer() {
        // ajouter un joueur depuis le namefield
        String name = nameField.getText();

        if (name.isEmpty()) {
            System.out.println("Le nom est vide");
            return;
        }

        players.add(new Player(name));

        // si il y assez de joueur, on permet de commencer
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
