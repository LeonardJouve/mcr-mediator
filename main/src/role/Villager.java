package role;

import mediator.MediatorState;
import player.Player;

public class Villager extends Role {
    public Villager(Player player, MediatorState mediatorState) {
        super(player, mediatorState);
        
    }

    protected void activate() {}
}
