package role;

import mediator.Mediator;

public abstract class Role {
    private boolean isAlive;
    private Mediator mediator;

    public Role() {
        this.isAlive = true;
        this.mediator = null;
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
