package role;

import mediator.MediatorState;
import network.server.ClientHandler;
import player.Player;

public abstract class Role {
    private boolean isAlive;
    private boolean isAsleep;
    private MediatorState mediatorState;
    private final Player player;




    public Role(Player player, MediatorState mediatorState) {
        this.isAlive = true;
        this.isAsleep = true;
        this.mediatorState = mediatorState;
        this.player = player;
    }

    protected abstract void activate();

    public void sendGameInformation(String message){
        return ;
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

    public void setAsleep(boolean isAsleep) {
        this.isAsleep = isAsleep;
    }

    public boolean isAsleep() {
        return isAsleep;
    }

    public String toString(){
        return "";
    }
}
