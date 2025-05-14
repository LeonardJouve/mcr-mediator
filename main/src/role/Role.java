package role;

import mediator.Mediator;
import player.Player;

public abstract class Role {
    private boolean isAlive;
    private Mediator mediator;
    private final Player player;

    public Role(Player player, Mediator mediator) {
        this.isAlive = true;
        this.mediator = mediator;
        this.player = player;
    }

    protected abstract void activate();

    public void play() {
        if (!this.isAlive) {
            return;
        }

        this.activate();
    }

    public void kill() {
        this.isAlive = false;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}
