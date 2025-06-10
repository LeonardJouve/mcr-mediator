package ui.graphical;

import com.sun.tools.javac.Main;
import ui.UserInput;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final GraphicalDisplay display;

    public MainFrame() {
        super("Jeu Loup-Garou");
        this.display = new GraphicalDisplay(new GraphicalInput(this));
        setupUI();
    }

    public MainFrame(UserInput userInput) {
        super("Jeu Loup-Garou");
        this.display = new GraphicalDisplay(userInput);
        setupUI();
    }

    private void setupUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(display.getLogComponent(), BorderLayout.CENTER);
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(controlPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    public GraphicalDisplay getDisplay() {
        return display;
    }
}