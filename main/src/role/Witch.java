package role;

import mediator.Mediator;
import player.Player;

import java.util.List;

/**
 * The witch is a role in the game that can heal one player and kill another.
 */
public class Witch extends Role {
    private boolean canHeal;
    private boolean canKill;

    public Witch(Player player, Mediator mediator) {
        super(player, mediator);
        this.canHeal = true;
        this.canKill = true;
    }

    public void activate() {
        // Show victim
        List<Role> victims = this.mediator.getVictims();
        this.mediator.displayVictims();

        // use heal potion
        if (this.canHeal && this.mediator.askHeal()) {
            Role role = this.mediator.selectRole(victims, "le soigner");
            this.mediator.heal(role);
            this.canHeal = false;
        }

        // use poison potion
        if (this.canKill && this.mediator.askKill()) {
            Role role = this.mediator.selectRole(this.mediator.getRolesAlive().toList(), "le tuer");
            this.mediator.kill(role);
            this.canKill = false;
        }
    }

    public String getRoleName() {
        return "Witch";
    }
}
