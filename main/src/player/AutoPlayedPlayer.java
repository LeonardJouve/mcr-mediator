package player;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class AutoPlayedPlayer extends Player {
    private final Random random = new Random();
    public AutoPlayedPlayer(String name) {
        super(name);
    }

    /**
     * Choisir un joueur parmi plusieurs. Ne se choisit pas lui-même (à priori pas de situation où on voudrait faire ça
     * @param players liste de choix possible
     * @return le joueur choisit
     */
    public Player chooseAmongPlayers(List<Player> players) {
        Player randomPlayer;
        do {
            int randomWithRandom = random.nextInt(players.size()); // Génère un nombre aléatoire entre 0 et players.size() - 1
            randomPlayer = players.get(randomWithRandom);
        } while (randomPlayer.getName().equals(getName()));

        return randomPlayer;
    }
}
