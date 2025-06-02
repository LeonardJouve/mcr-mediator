package role;

import mediator.MediatorState;
import player.Player;

import java.nio.file.Path;

public class Villager extends Role {
    private final static Path GRAPHIC = Path.of("../../../images/villager.png");

    public Villager(Player player, MediatorState mediatorState) {
        super(player, mediatorState);

    }

    public void activate() {}

    public String getRoleName() {
        return "Villager";
    }

    public boolean isWereWolf() {
        return false;
    }

    public Path getGraphic() {
        return GRAPHIC;
    }
}
