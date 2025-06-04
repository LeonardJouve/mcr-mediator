package role;

import mediator.Mediator;
import player.Player;

import java.util.List;
import java.util.Random;

public class Seer extends Role {
    public Seer(Player player, Mediator mediator) {
        super(player, mediator);
    }

    public void activate() {

        // Select a player to watch his card
        Random rand = new Random();
        List<Role> players = this.mediator.getRolesAlive().toList();
        Role p = players.get(rand.nextInt(players.size()));
        this.mediator.displayRole(p);
        // Wait for ok
    }

    public String getRoleName() {
        return "Seer";
    }
}
