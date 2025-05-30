package role;

import mediator.MediatorState;
import player.Player;

public class WereWolf extends Role {
    public WereWolf(Player player, MediatorState mediatorState) {
       super(player, mediatorState);

    }

    protected void activate() {
        // Select a player to kill
    }

    @Override
    public String toString() {
        return "WereWolf";
    }
}
