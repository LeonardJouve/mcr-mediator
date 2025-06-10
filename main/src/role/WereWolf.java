package role;

import mediator.Mediator;
import player.Player;

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
