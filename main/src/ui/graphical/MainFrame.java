package ui.graphical;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final GraphicalDisplay display;

    public MainFrame() {
        super("Jeu Loup-Garou");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        this.display = new GraphicalDisplay(new GraphicalInput(this));
        setupUI();
    }

    private void setupUI() {
        // Layout principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Zone de log avec défilement
        mainPanel.add(display.getLogComponent(), BorderLayout.CENTER);

        // Panel de contrôle
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public GraphicalDisplay getDisplay() {
        return display;
    }
}