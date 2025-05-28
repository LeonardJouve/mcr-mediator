package ui;

import mediator.MediatorState;

import javax.swing.*;

public class MainFrame extends JFrame {
    private JFrame currentFrame;
    private final MediatorState mediatorState;

    public MainFrame(MediatorState mediatorState) {
        this.mediatorState = mediatorState;
        this.currentFrame = new RoomCreationFrame(this);
        this.add(this.currentFrame);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    void setCurrentFrame(JFrame currentFrame) {
        this.currentFrame = currentFrame;
    }

    MediatorState getMediatorState() {
        return mediatorState;
    }
}
