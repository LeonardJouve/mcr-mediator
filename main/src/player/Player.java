package player;

import role.Role;

import java.net.Socket;

public class Player {
    private static int nextId = 0;

    private final int id;
    private final String name;
    private Role role;
    private final Socket socket;

    public Player(String name, Socket socket) {
        this.id = ++nextId;
        this.name = name;
        this.socket = socket;
        this.role = null;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
