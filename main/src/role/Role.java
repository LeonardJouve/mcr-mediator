package role;

import mediator.MediatorState;
import player.Player;

import java.util.List;

public abstract class Role {
    private boolean isAlive;
    private boolean isAsleep;
    private MediatorState mediatorState;
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

    public int getId() {
        return id;
    }

    public abstract String getRoleName();


    public boolean isAlive() {
        return isAlive;
    }
    
    protected abstract void activate();

    public void sendGameInformation(String message){
        System.out.println(this + " reçoit l'information: " + message);
    }

    // choisir un joueur parmi plusieurs choix. Nécéssaire de pouvoir le faire puisqu'on connaît rarement les rôles
    public Player choosePlayer(List<Player> l) {
        Player chosen = getPlayer().chooseAmongPlayers(l);
        System.out.println(this + " choses: " + chosen);  // log temporaire
        return chosen;
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
