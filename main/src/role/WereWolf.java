package role;

import mediator.Mediator;
import player.Player;

/**
 * WereWolf hides in the village and tries to eliminate the villagers.
 * It does not have any special abilities.
 */
public class WereWolf extends Role {
    /**
     * Constructor for the WereWolf
     */
    public WereWolf(Player player, Mediator mediator) {
       super(player, mediator);
    }
    /**
     * {@inheritDoc}
     */
    public void activate() {}
    /**
     * {@inheritDoc}
     */
    public String getRoleName() {
        return "WereWolf";
    }

    /**
     * {@inheritDoc}
     * Here's since a WereWolf is a WereWolf, it returns true
     */
    public boolean isWereWolf() {
        return true;
    }
}
