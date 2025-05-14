package role;

import mediator.Mediator;
import player.Player;

public class Seer extends Role {
    public Seer(Player player, Mediator mediator) {
        super(player, mediator);
    }

    public void activate() {
        // Select a player to watch his card
    }
}
