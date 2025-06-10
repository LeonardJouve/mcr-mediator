package role;

import mediator.Mediator;
import player.Player;

import java.util.List;

/**
 * Role represents a role during a game of The Werewolves of Millers Hollow.
 * Possible roles could be : https://www.murderama.fr/blog/tous-les-roles-du-loup-garou-guide-complet
 */
public abstract class Role {
    private boolean isAlive;
    protected Mediator mediator;
    private final Player player;

    private static int nextId = 1;
    private final int id;

    /**
     * Constructor for class Role
     * @param player the player that will play this role
     * @param mediator the mediator of this role
     */
    public Role(Player player, Mediator mediator) {
        this.id = nextId++;
        this.isAlive = true;

        this.mediator = mediator;
        this.player = player;
    }

    /**
     * Getter for the player attribute of the instance.
     * @return reference to the player attribute of the instance.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Getter for the id attribute of the instance.
     * @return value of the id attribute of the instance.
     */
    public int getId() {
        return id;
    }

    /**
     * Abstract getter for the name of the role
     * @return the name of the concrete role.
     */
    public abstract String getRoleName();


    /**
     * Getter for the isAlive attribute of the instance.
     * @return true if the role is alive, false otherwise.
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Activates the action of the concrete role.
     */
    public abstract void activate();

    /**
     *
     * @param roles
     * @return
     */
    public Role vote(List<Role> roles) {
        this.mediator.displayCurrentPlayer(this);
        return this.mediator.selectRole(roles, "voter contre lui");
    }

    /**
     * Kills the role (meaning setting the isAlive attribute to false).
     */
    public void kill() {
        this.isAlive = false;
    }

    /**
     * Heals the role (meaning setting the isAlive attribute to true).
     */
    public void heal() {
        this.isAlive = true;
    }

    /**
     * Returns a value to indicate if the role is a were wolf or not.
     * @return false by default
     */
    public boolean isWereWolf() {
        return false;
    }
}
