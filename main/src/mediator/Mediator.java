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

    /**
     * Gets a boolean that represents whether the game is over or not.
     * @return that represents whether the game is over or not.
     */
    boolean isGameOver();

    /**
     * Display a role in the game.
     * @param role the role to display.
     */
    void displayRole(Role role);

    /**
     * Gets the list of the dead roles.
     * @return reference to the list of the dead roles.
     */
    List<Role> getVictims();

    /**
     * Asks whether to heal.
     * @return true if heal, false otherwise.
     */
    boolean askHeal();

    /**
     * Asks whether to kill.
     * @return true if kill, false otherwise.
     */
    boolean askKill();


    /**
     * Selects a role among many
     * @param roles list of roles among which to select
     * @param reason the reason for which to select the role
     * @return the selected role
     */
    Role selectRole(List<Role> roles, String reason);

    /**
     * Getter for the class that displays the game.
     * @return ref to the instance that displays the game.
     */
    GameDisplay getGameDisplay();


    /**
     * Displays the dead players of the game.
     */
    void displayVictims();

    /**
     * Kills a given role.
     * @param role the role to kill.
     */
    void kill(Role role);

    /**
     * Heals a given role.
     * @param role the role to heal.
     */
    void heal(Role role);

    /**
     * Displays the current player
     * @param role role of the current player.
     */
    void displayCurrentPlayer(Role role);
}
