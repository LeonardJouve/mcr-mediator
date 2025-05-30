package network.server;

import mediator.MediatorState;

public class GameHandler implements Runnable {
    private MediatorState mediatorState;

    public GameHandler(MediatorState mediatorState) {
        this.mediatorState = mediatorState;
    }

    @Override
    public void run() {
        System.out.println("La partie va commencer ... ");
    }
}
