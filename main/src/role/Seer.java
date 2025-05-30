package role;

import mediator.MediatorState;
import player.Player;

public class Seer extends Role {
    public Seer(Player player, MediatorState mediatorState) {
        super(player, mediatorState);
    }

    public void activate() {
        // Select a player to watch his card
    }

    @Override
    public String toString() {
        return "Seer";
    }
}
