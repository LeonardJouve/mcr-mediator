package role;

import mediator.Mediator;
import player.Player;

public class Villager extends Role {
    public Villager(Player player, Mediator mediator) {
        super(player, mediator);
    }

    public void activate() {}

    public String getRoleName() {
        return "Villager";
    }
}
