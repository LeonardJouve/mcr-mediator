package role;

import mediator.MediatorState;
import player.Player;

public abstract class Role {
    private boolean isAlive;
    private MediatorState mediatorState;
    private final Player player;

    public Role(Player player, MediatorState mediatorState) {
        this.isAlive = true;
        this.mediatorState = mediatorState;
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
}
