package mediator;


import player.Player;

import java.util.ArrayList;
import java.util.List;

public class MediatorState implements Mediator {
    private Mediator mediator;
    private List<Player> players;

    public MediatorState() {
        this.mediator = new NormalMediator(this);
        this.players = new ArrayList<Player>();
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public int getMinPlayers() {
        return mediator.getMinPlayers();
    }

    public int getMaxPlayers() {
        return mediator.getMaxPlayers();
    }

    public void playTurn() {
        mediator.playTurn();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void assignRoles() {
        mediator.assignRoles();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}
