package role;

import mediator.MediatorState;
import player.Player;

import java.nio.file.Path;
import java.util.List;

public abstract class Role {
    private boolean isAlive;
    private final MediatorState mediatorState;
    private final Player player;

    private static int nextId = 1;
    private final int id;

    public Role(Player player, MediatorState mediatorState) {
        this.id = nextId++;
        this.isAlive = true;

        this.mediatorState = mediatorState;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public int getId() {
        return id;
    }

    public abstract String getRoleName();

    public boolean isAlive() {
        return isAlive;
    }
    
    public abstract void activate();

    // choisir un joueur parmi plusieurs choix. Nécéssaire de pouvoir le faire puisqu'on connaît rarement les rôles
    public Role chooseRole(List<Role> roles) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void play() {
        if (!this.isAlive) {
            return;
        }

        this.activate();
    }

    public void kill() {
        this.isAlive = false;
    }

    public String toString(){
        return getRoleName() + "#" + getId() + " (" + getPlayer().getName() + ")";
    }

    public abstract Path getGraphic();

    public abstract boolean isWereWolf();
}
