package mediator.alternative;

import role.Role;
import role.WereWolf;

import java.util.List;

/**
 * Interface for mediators that change how werewolves and villagers turn happen.
 */
public interface WeatherMediator {
    /**
     * The werewolves' turn where they can vote to eliminate a player.
     * @param wereWolves the list of werewolves participating in the turn.
     * @param aliveRoles list of available targets that can be eliminated.
     */
    void wereWolvesTurn(List<WereWolf> wereWolves, List<Role> aliveRoles);

    /**
     * The villagers' turn where they can vote to eliminate a player.
     * @param aliveRoles list of available targets that can be eliminated.
     */
    void villagersTurn(List<Role> aliveRoles);

    /**
     * Trigger a change of weather mediator.
     */
    void trigger();
}
