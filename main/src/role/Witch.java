package role;

import mediator.Mediator;
import player.Player;

public class Witch extends Role {
    public Witch(Player player, Mediator mediator) {
        super(player, mediator);
    }

    public void activate() {
        // Show victim and select if you want to heal or kill
    }

    public String getRoleName() {
        return "Witch";
    }
}
