package role;

import mediator.Mediator;
import player.Player;

import java.util.List;

public class Seer extends Role {
    public Seer(Player player, Mediator mediator) {
        super(player, mediator);
    }

    public void activate() {
        // Select a player to watch his card
        List<Role> roles = this.mediator.getRolesAlive().toList();
        Role role = this.mediator.selectRole(roles, "voir sa carte");
        this.mediator.displayRole(role);
    }

    public String getRoleName() {
        return "Seer";
    }
}
