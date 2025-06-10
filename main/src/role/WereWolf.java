package role;

import mediator.Mediator;
import player.Player;

public class WereWolf extends Role {
    public WereWolf(Player player, Mediator mediator) {
       super(player, mediator);

    }

    protected void activate() {}

    public String getRoleName() {
        return "WereWolf";
    }

}
