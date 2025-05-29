package GUI;

import javax.swing.*;
import java.awt.*;

public class GUI {
    class CercleDansFenetre extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dessiner un cercle
            g.setColor(Color.BLUE); // Couleur du cercle
            g.fillOval(this.getX(), this.getY(), 100, 100); // (x, y, largeur, hauteur)
        }
    }

    private JFrame gameFrame;
    private JFrame startMenuFrame;

    private int playerNb;

    private final static int GAME_WINDOW_WIDTH = 1000;
    private final static int GAME_WINDOW_HEIGHT = 1000;
    public GUI(){
        startMenuFrame = new JFrame();
        startMenuFrame.setSize(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);

        JLabel titleLabel = new JLabel("Les loups garou");
        titleLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        titleLabel.setBounds(250, 50, 200, 50);
        startMenuFrame.add(titleLabel);

        JButton addButton = new JButton("Start");
        addButton.setBounds(250, 100, 100, 50);
        addButton.addActionListener(e -> {
            createGameFrame(7);
        });
        startMenuFrame.add(addButton);

        startMenuFrame.setLayout(null);
        startMenuFrame.setVisible(true);
        startMenuFrame.setLocationRelativeTo(null);
        //startMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createGameFrame(int numberOfPlayers){   // later, maybe give something else when relevant
        playerNb = numberOfPlayers;

        gameFrame = new JFrame();
        gameFrame.setSize(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
        CercleDansFenetre cercle = new CercleDansFenetre();
        cercle.setBounds(600, 50, 200, 50);

        //gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(cercle);
        //cercle.paintComponent(gameFrame.getGraphics());

        startMenuFrame.setVisible(false);
        gameFrame.setVisible(true);
        gameFrame.setLocationRelativeTo(null); // Centrer la fenêtre
    }
}


