package role;

import mediator.Mediator;
import player.Player;

import java.util.stream.Stream;

public class WereWolf extends Role {
    public WereWolf(Player player, Mediator mediator) {
       super(player, mediator);

    }

    protected void activate() {
        // Select a player to kill
        Stream<Role> roles = this.mediator.getRolesAlive();

    }

    public String getRoleName() {
        return "WereWolf";
    }

}
