package player;

public class Player {
    private static int nextId = 0;

    private final int id;
    private final String name;

    public Player(String name) {
        this.id = ++nextId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return name;
    }
}
