package role;

import mediator.MediatorState;
import player.Player;

import java.nio.file.Path;

public class WereWolf extends Role {
    private final static Path GRAPHIC = Path.of("../../../images/were_wolf.png");

    public WereWolf(Player player, MediatorState mediatorState) {
       super(player, mediatorState);

    }

    public void activate() {
        // Select a player to kill
    }

    public String getRoleName() {
        return "WereWolf";
    }

    public boolean isWereWolf() {
        return true;
    }

    @Override
    public Path getGraphic() {
        return GRAPHIC;
    }
}
