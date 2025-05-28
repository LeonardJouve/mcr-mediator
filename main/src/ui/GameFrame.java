package ui;

import mediator.NormalMediator;

import javax.swing.*;

public class GameFrame extends JFrame {
    private final MainFrame mainFrame;

    public GameFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        this.mainFrame.getMediatorState().setMediator(new NormalMediator(mainFrame.getMediatorState()));
    }
}
