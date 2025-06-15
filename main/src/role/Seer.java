package role;

import mediator.Mediator;
import player.Player;

import java.util.List;

/**
 * The Seer can watch the card of another player.
 */
public class Seer extends Role {
    /**
     * Constructor for the Seer
     */
    public Seer(Player player, Mediator mediator) {
        super(player, mediator);
    }

    /**
     * {@inheritDoc}
     * Here, activates the seer action
     */
    public void activate() {
        // Select a player to watch his card
        List<Role> roles = this.mediator.getRolesAlive().toList();
        Role role = this.mediator.selectRole(roles, "voir sa carte");
        this.mediator.displayRole(role);
    }

    /**
     * {@inheritDoc}
     */
    public String getRoleName() {
        return "Seer";
    }
}
