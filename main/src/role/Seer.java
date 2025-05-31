package role;

import mediator.MediatorState;
import player.Player;

import java.util.ArrayList;
import java.util.List;

public class Seer extends Role {
    public Seer(Player player, MediatorState mediatorState) {
        super(player, mediatorState);
    }

    public void activate() {
        // Select a player to watch his card
    }

    public String getRoleName() {
        return "Seer";
    }
}
