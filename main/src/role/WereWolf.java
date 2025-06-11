package role;

import mediator.Mediator;
import player.Player;

/**
 * WereWolf hides in the village and tries to eliminate the villagers.
 * It does not have any special abilities.
 */
public class WereWolf extends Role {
    public WereWolf(Player player, Mediator mediator) {
       super(player, mediator);
    }

    public void activate() {}

    public String getRoleName() {
        return "WereWolf";
    }

    public boolean isWereWolf() {
        return true;
    }
}
