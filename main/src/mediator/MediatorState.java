package mediator;


import player.Player;

import java.util.ArrayList;
import java.util.List;

public class MediatorState {
    private Mediator gameMediator;
    private final List<Player> players;

    public MediatorState() {
        this.gameMediator = new NormalMediator(this);
        this.players = new ArrayList<>();
    }

    public void setGameMediator(Mediator gameMediator) {
        this.gameMediator = gameMediator;
    }

    public int getMinPlayers() {
        return gameMediator.getMinPlayers();
    }

    public int getMaxPlayers() {
        return gameMediator.getMaxPlayers();
    }

    public void playTurn() {
        gameMediator.playTurn();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean start() {
        if (!gameMediator.start()) {
            return false;
        }

        while (!gameMediator.isGameOver()){
            gameMediator.playTurn();
        }

        System.out.println("Fin de partie");
        return true;
    }
}
