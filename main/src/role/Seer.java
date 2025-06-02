package role;

import mediator.MediatorState;
import player.Player;

import java.nio.file.Path;

public class Seer extends Role {
    private final static Path GRAPHIC = Path.of("../../../images/seer.png");

    public Seer(Player player, MediatorState mediatorState) {
        super(player, mediatorState);
    }

    public void activate() {
        // Select a player to watch his card
    }

    public String getRoleName() {
        return "Seer";
    }

    public boolean isWereWolf() {
        return false;
    }

    public Path getGraphic() {
        return GRAPHIC;
    }
}
