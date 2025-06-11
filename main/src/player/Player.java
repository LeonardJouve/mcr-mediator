package player;

/**
 * Represents a player in the game.
 * Each player has a name.
 */
public class Player {
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    /**
     * Getter for the name of the player.
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the player.
     * @return the name of the player.
     */
    public String toString() {
        return name;
    }
}
