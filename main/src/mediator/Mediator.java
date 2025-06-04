package mediator;

import java.util.List;
import java.util.stream.Stream;

import player.Player;
import role.Role;

public interface Mediator {
    void playTurn();

    Stream<Role> getRolesAlive();
    void assignRoles(List<Player> players);


    int getMinPlayers();
    int getMaxPlayers();

    boolean isGameOver();

    void displayRole(Role role);
}
