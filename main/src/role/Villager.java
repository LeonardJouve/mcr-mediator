package role;

import mediator.Mediator;
import player.Player;

/**
 * The Villager is a basic role in the game.
 * It does not have any special abilities.
 * Its goal is to survive and help the village win.
 */
public class Villager extends Role {
    /**
     * Constructor for the Villager
     */
    public Villager(Player player, Mediator mediator) {
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
        return "Villager";
    }
}
