package player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// représentation d'un joueur du point de vue du serveur
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

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
}
