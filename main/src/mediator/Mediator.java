package mediator;

import java.util.List;
import player.Player;

public interface Mediator {
    void playTurn();

    List<Player> getPlayers();
    void assignRoles();


    int getMinPlayers();
    int getMaxPlayers();

    // pour commencer la partie. Retourne false si pas assez de joueurs
    boolean start();
}
