package mediator;

import java.util.List;
import java.util.stream.Stream;

import player.Player;
import role.Role;
import ui.GameDisplay;

public interface Mediator {
    /**
     * Starts the game.
     */
    void start();

    /**
     * Gets all remaining living roles
     * @return a Stream of all remaining living roles in the game.
     */
    Stream<Role> getRolesAlive();

    /**
     * Assign a role to a list of players
     * @param players the players we want to assign a role to.
     */
    void assignRoles(List<Player> players);

    /**
     * Gets the minimum number of players needed for the game to start.
     * @return the minimum number of players needed for the game to start.
     */
    int getMinPlayers();

    /**
     * Gets the maximum number of players allowed for the game to start.
     * @return the maximum number of players allowed for the game to start.
     */
    int getMaxPlayers();

    boolean isGameOver();

    void displayRole(Role role);

    List<Role> getVictims();

    boolean askHeal();

    boolean askKill();

    Role selectRole(List<Role> roles, String reason);

    GameDisplay getGameDisplay();

    void displayVictims();

    void kill(Role role);

    void heal(Role role);

    void displayCurrentPlayer(Role role);
}
