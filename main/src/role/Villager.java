package role;

import mediator.Mediator;
import player.Player;

/**
 * The Villager is a basic role in the game.
 * It does not have any special abilities.
 * Its goal is to survive and help the village win.
 */
public class Villager extends Role {
    public Villager(Player player, Mediator mediator) {
        super(player, mediator);
    }

    public void activate() {}

    public String getRoleName() {
        return "Villager";
    }
}
