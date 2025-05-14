package mediator;

import player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Mediator {
    private final List<Player> players = new ArrayList<>();

    public Mediator(List<Player> players) {
        this.players.addAll(players);
    }


}
