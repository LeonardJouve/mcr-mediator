package ui;

import mediator.NormalMediator;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameFrame extends JFrame {
    private final MainFrame mainFrame;

    public GameFrame(MainFrame mainFrame, List<Player> players) {
        this.mainFrame = mainFrame;

        this.mainFrame.getMediatorState().setGameMediator(new NormalMediator(mainFrame.getMediatorState()));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        int cols = 3;
        for (int i = 0; i < players.size(); i++) {
            String playerName = players.get(i).getName();

            int row = i / cols;
            int col = i % cols;

            gbc.gridx = col;
            gbc.gridy = row * 2;
            gbc.anchor = GridBagConstraints.CENTER;

            JLabel nameLabel = new JLabel(playerName, SwingConstants.CENTER);
            panel.add(nameLabel, gbc);

            gbc.gridy = row * 2 + 1; // Ligne en dessous pour le bouton
            JButton chooseButton = new JButton("Choisir");
            chooseButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Joueur choisi : " + playerName);
            });
            panel.add(chooseButton, gbc);
        }

        // https://www.geeksforgeeks.org/java-jscrollpane/ si vraiment il y a beaucoup de joueurs
        JScrollPane scrollPane = new JScrollPane(panel);
        this.add(scrollPane);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 800);
    }
}
