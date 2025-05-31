
//class to fake data while dev is on going


import player.*;
import java.util.ArrayList;

public class DevStuff {
    public ArrayList<Player> players = new ArrayList<>();

    public DevStuff() {
        players.add(new AutoPlayedPlayer("Alizée"));
        players.add(new AutoPlayedPlayer("Guillermo"));
        players.add(new AutoPlayedPlayer("Felicia"));
        players.add(new AutoPlayedPlayer("Olivier"));
        players.add(new AutoPlayedPlayer("Ousmane"));
        players.add(new AutoPlayedPlayer("Alireza"));
        players.add(new AutoPlayedPlayer("Fabien"));
        players.add(new AutoPlayedPlayer("Elise"));
    }
}
