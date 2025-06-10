package mediator;

import java.util.List;
import java.util.stream.Stream;

import player.Player;
import role.Role;
import ui.GameDisplay;

public interface Mediator {
    void start();

    Stream<Role> getRolesAlive();
    void assignRoles(List<Player> players);

    int getMinPlayers();
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
