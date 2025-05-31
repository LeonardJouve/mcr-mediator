package player;

import java.io.IOException;
import java.util.List;

public class AutoPlayedPlayer extends Player {
    public AutoPlayedPlayer(String name) {
        super(name);
    }

    public Player chooseAmongPlayers(List<Player> players) {
        Player randomPlayer;
        do {
            int max = players.size() - 1;
            int min = 0;
            int randomWithMathRandom = (int) ((Math.random() * (max - min)) + min); // 2.1 from : https://www.baeldung.com/java-generating-random-numbers
            randomPlayer = players.get(randomWithMathRandom);
            // System.out.println(this + " chooses " + randomPlayer);
        } while (randomPlayer == this);

        return randomPlayer;
    }
}
