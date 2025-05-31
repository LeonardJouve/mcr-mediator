package mediator;


import player.Player;

import java.util.ArrayList;
import java.util.List;

public class MediatorState implements Mediator {
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

    @Override
    public boolean isGameOver() {
        return gameMediator.isGameOver();
    }

    public void playTurn() {
        gameMediator.playTurn();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void assignRoles() {
        gameMediator.assignRoles();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public boolean start() {
        if (!gameMediator.start()) {
            return false;
        }
        int loopTurn = 0;   // temporaire
        while (!gameMediator.isGameOver()){
            gameMediator.playTurn();
            if (++loopTurn > 5) break;
        }

        System.out.println("Fin de partie");
        return true;
    }
}
