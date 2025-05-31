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
     * Choisir un joueur parmi plusieurs. Attention, en l'état il pourrait se choisir lui-même.
     * @param players liste de choix possible
     * @return le joueur choisit
     */
    public Player chooseAmongPlayers(List<Player> players) {
        // System.out.println("AutoPlayer is choosing ...");
        Player randomPlayer;
        System.out.println("Size : " + players.size());
        int randomWithRandom = random.nextInt(players.size()); // Génère un nombre aléatoire entre 0 et players.size() - 1
        randomPlayer = players.get(randomWithRandom);
        // System.out.println(this + " chooses " + randomPlayer);


        return randomPlayer;
    }
}
