package mediator;

import player.Player;
import role.*;

import java.util.List;
import java.util.function.BiFunction;

public class NormalMediator extends Mediator {
    private final static int MIN_PLAYERS = 8;
    private final static int MAX_PLAYERS = 18;

    private final static List<BiFunction<Player, MediatorState, Role>> PRIMARY_ROLES = List.of(
        WereWolf::new, Villager::new, WereWolf::new, Witch::new,
        Villager::new, Seer::new, WereWolf::new
    );

    private final static List<BiFunction<Player, MediatorState, Role>> SECONDARY_ROLES = List.of(
        Villager::new, Villager::new, Villager::new, WereWolf::new
    );

    public NormalMediator(MediatorState mediatorState) {
        super(mediatorState);
    }

    public int getMinPlayers() {
        return MIN_PLAYERS;
    }

    public int getMaxPlayers() {
        return MAX_PLAYERS;
    }

    protected List<BiFunction<Player, MediatorState, Role>> getPrimaryRoles() {
        return PRIMARY_ROLES;
    }

    protected List<BiFunction<Player, MediatorState, Role>> getSecondaryRoles() {
        return SECONDARY_ROLES;
    }

    public void playTurn() {
        super.playTurn();
    }
}
