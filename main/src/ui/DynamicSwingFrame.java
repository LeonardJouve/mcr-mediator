package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;

public class DynamicSwingFrame extends JFrame {
    private static final String WAIT_PANEL = "wait";
    private static final String MESSAGE_PANEL = "message";
    private static final String CHOICE_PANEL = "choice";

    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Panels
    private JPanel waitPanel;
    private JPanel messagePanel;
    private JPanel choicePanel;

    // Components for message panel
    private JLabel messageLabel;
    private JButton okButton;

    // Components for choice panel
    private JList<String> choiceList;
    private JButton selectButton;

    // Callback for choice selection
    private Consumer<String> choiceCallback;

    public DynamicSwingFrame() {
        setTitle("Dynamic Content Frame");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        createWaitPanel();
        createMessagePanel();
        createChoicePanel();

        mainPanel.add(waitPanel, WAIT_PANEL);
        mainPanel.add(messagePanel, MESSAGE_PANEL);
        mainPanel.add(choicePanel, CHOICE_PANEL);

        getContentPane().add(mainPanel);

        showWaitScreen();
    }

    private void createWaitPanel() {
        waitPanel = new JPanel(new BorderLayout());
        JLabel waitLabel = new JLabel("En attente d'événement...", SwingConstants.CENTER);
        waitLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        waitPanel.add(waitLabel, BorderLayout.CENTER);
    }

    private void createMessagePanel() {
        messagePanel = new JPanel(new BorderLayout(10, 10));
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        okButton = new JButton("Ok");
        okButton.addActionListener(e -> showWaitScreen());

        messagePanel.add(messageLabel, BorderLayout.CENTER);
        JPanel south = new JPanel();
        south.add(okButton);
        messagePanel.add(south, BorderLayout.SOUTH);
    }

    private void createChoicePanel() {
        choicePanel = new JPanel(new BorderLayout(10, 10));
        choiceList = new JList<>();
        choiceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        choiceList.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(choiceList);

        selectButton = new JButton("Sélectionner");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = choiceList.getSelectedValue();
                if (selected != null && choiceCallback != null) {
                    choiceCallback.accept(selected);
                }
                showWaitScreen();
            }
        });

        choicePanel.add(scrollPane, BorderLayout.CENTER);
        JPanel south = new JPanel();
        south.add(selectButton);
        choicePanel.add(south, BorderLayout.SOUTH);
    }

    // Méthodes publiques pour changer de contenu
    public void showWaitScreen() {
        cardLayout.show(mainPanel, WAIT_PANEL);
    }

    public void showMessage(String message) {
        messageLabel.setText(message);
        cardLayout.show(mainPanel, MESSAGE_PANEL);
    }

    public void showChoices(List<String> options, Consumer<String> callback) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String opt : options) {
            listModel.addElement(opt);
        }
        choiceList.setModel(listModel);
        this.choiceCallback = callback;
        cardLayout.show(mainPanel, CHOICE_PANEL);
    }

    public static void main(String[] args) {
        // Exemple d'utilisation
        SwingUtilities.invokeLater(() -> {
            DynamicSwingFrame frame = new DynamicSwingFrame();
            frame.setVisible(true);

            // Après 2 secondes, afficher un message
            //new Timer(2000, e -> frame.showMessage("Bonjour, ceci est un message !")).start();

            // Après 5 secondes, afficher des choix
            new Timer(5000, e -> frame.showChoices(
                    List.of("Option 1", "Option 2", "Option 3"),
                    selection -> System.out.println("Vous avez sélectionné : " + selection)
            )).start();
        });
    }
}
