package role;

import mediator.Mediator;
import player.Player;

import java.util.List;

public abstract class Role {
    private boolean isAlive;
    protected Mediator mediator;
    private final Player player;

    private static int nextId = 1;
    private final int id;

    public Role(Player player, Mediator mediator) {
        this.id = nextId++;
        this.isAlive = true;

        this.mediator = mediator;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getId() {
        return id;
    }

    public abstract String getRoleName();


    public boolean isAlive() {
        return isAlive;
    }
    
    protected abstract void activate();

    
    public Role vote(List<Role> roles) {
        this.mediator.displayCurrentPlayer(this);
        return this.mediator.selectRole(roles, "voter contre lui");
    }

    public void kill() {
        this.isAlive = false;
    }

    public void heal() {
        this.isAlive = true;
    }
}
