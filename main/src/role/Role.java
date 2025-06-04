package role;

import mediator.Mediator;
import player.Player;

import java.util.List;
import java.util.Random;

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


    // choisir un joueur parmi plusieurs choix. Nécéssaire de pouvoir le faire puisqu'on connaît rarement les rôles
    public Role vote(List<Role> l) {
        Random r = new Random();
        int randomWithRandom = r.nextInt(l.size());  // Choix aléatoire, a changer pour choix volontaire
        return l.get(randomWithRandom);
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
}
