package player;

import network.server.ClientHandler;

public class Player extends ClientHandler {
    private static int nextId = 0;

    private final int id;
    private final String name;

    public Player(String name) {
        super();
        this.id = ++nextId;
        this.name = name;
    }
}
