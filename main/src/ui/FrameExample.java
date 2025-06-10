package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameExample {
    private JFrame frame;

    public FrameExample() {
        // Create the frame
        frame = new JFrame("Frame Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a button
        JButton button = new JButton("Change Content");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeContent();
            }
        });

        // Set initial content
        frame.getContentPane().add(button);
        frame.setVisible(true);
    }

    private void changeContent() {
        // Change the content pane
        JLabel newLabel = new JLabel("Content Changed!");
        frame.getContentPane().removeAll();
        frame.getContentPane().add(newLabel);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        new FrameExample();
    }
}