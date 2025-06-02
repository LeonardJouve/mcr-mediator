package role;

import mediator.MediatorState;
import player.Player;

import java.nio.file.Path;

public class Witch extends Role {
    private final static Path GRAPHIC = Path.of("../../../images/witch.png");

    public Witch(Player player, MediatorState mediatorState) {
        super(player, mediatorState);
    }

    public void activate() {
        // Show victim and select if you want to heal or kill
    }

    public String getRoleName() {
        return "Witch";
    }

    public boolean isWereWolf() {
        return false;
    }

    @Override
    public Path getGraphic() {
        return GRAPHIC;
    }
}
