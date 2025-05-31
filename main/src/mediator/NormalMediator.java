package mediator;

import player.Player;
import role.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

// mediator pour l'exécution d'une partie ?

public class NormalMediator implements Mediator {
    private final MediatorState mediatorState;
    private final List<Villager> villagers;
    private final List<WereWolf> wereWolves;
    private Witch witch;
    private Seer seer;

    private final static List<BiFunction<Player, MediatorState, Role>> primaryRoles = List.of(
            WereWolf::new, Witch::new, Villager::new, WereWolf::new,
            Villager::new, Seer::new, WereWolf::new
    );


    private final static List<BiFunction<Player, MediatorState, Role>> otherRoles = List.of(
            Villager::new, Villager::new, Villager::new, WereWolf::new
    );

    public NormalMediator(MediatorState mediatorState) {
        this.mediatorState = mediatorState;
        this.villagers = new ArrayList<>();
        this.wereWolves = new ArrayList<>();
        this.witch = null;
        this.seer = null;
    }

    public List<Player> getPlayers() {
        return mediatorState.getPlayers();
    }

    public void assignRoles() {
        List<Player> players = getPlayers();
        Collections.shuffle(players);

        for (int i = 0; i < players.size(); ++i) {
            BiFunction<Player, MediatorState, Role> constructor = i >= primaryRoles.size() ?
                    otherRoles.get(i % otherRoles.size()) :
                    primaryRoles.get(i);
            Role role = constructor.apply(players.get(i), mediatorState);

            switch (role) {
                case WereWolf w -> wereWolves.add(w);
                case Villager v -> villagers.add(v);
                case Seer s -> seer = s;
                case Witch w -> witch = w;
                default -> throw new IllegalStateException("Unexpected role: " + role);
            }
        }
    }

    public void playTurn() {

    }

    public int getMinPlayers() {
        return 2;
    }

    public int getMaxPlayers() {
        return 10;
    }

    @Override
    public boolean isGameOver() {
        return true;
    }

    @Override
    public boolean start() {
        return false;
    }
}
