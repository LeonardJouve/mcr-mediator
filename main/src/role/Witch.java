package role;

import mediator.MediatorState;
import player.Player;

public class Witch extends Role {
    public Witch(Player player, MediatorState mediatorState) {
        super(player, mediatorState);
    }

    public void activate() {
        // Show victim and select if you want to heal or kill
    }
}
