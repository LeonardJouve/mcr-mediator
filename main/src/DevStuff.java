
//class to fake data while dev is on going


import player.Player;
import java.util.ArrayList;

public class DevStuff {
    public ArrayList<Player> players = new ArrayList<>();

    public DevStuff() {
        players.add(new Player("Alizée"));
        players.add(new Player("Guillermo"));
        players.add(new Player("Felicia"));
        players.add(new Player("Olivier"));
        players.add(new Player("Ousmane"));
        players.add(new Player("Alireza"));
        players.add(new Player("Fabien"));
        players.add(new Player("Elise"));
    }
}
