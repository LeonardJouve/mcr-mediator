package mediator;

import player.Player;
import role.*;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class NormalMediator implements Mediator {
    private List<Villager> villagers;
    private List<WereWolf> wereWolves;
    private Witch witch;
    private Seer seer;

    ServerSocket serverSocket;

    public NormalMediator(List<Player> players) {
        villagers = new ArrayList<>();
        wereWolves = new ArrayList<>();
        witch = null;
        seer = null;

        assignRoles(players);
    }

    private void assignRoles(List<Player> players) {
        Role[] primaryRoles = {new WereWolf(), new Witch(), new Villager(), new WereWolf(), new Villager(), new Seer(), new WereWolf()};
        Role[] otherRoles = {new Villager(), new Villager(), new Villager(), new WereWolf()};

        for (int i = 0; i < players.size(); ++i) {
            Role role = i >= primaryRoles.length ? otherRoles[i % otherRoles.length] : primaryRoles[i];
            switch (role) {
                case WereWolf w -> wereWolves.add(w);
                case Villager v -> villagers.add(v);
                case Seer s -> seer = s;
                case Witch w -> witch = w;
                default -> throw new IllegalStateException("Unexpected value: " + role);
            }
        }
    }

    public void playTurn() {}
}
