package player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// représentation d'un joueur du point de vue du serveur
public class Player {
    private static int nextId = 0;
    private BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
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

    public Player chooseAmongPlayers(List<Player> players) {
        // Afficher les objets avec leur index
        for (int i = 0; i < players.size(); i++) {
            System.out.println(i + ": " + players.get(i));
        }

        int indexChoisi = -1;
        boolean choixValide = false;

        // Demander à l'utilisateur de choisir un index
        while (!choixValide) {
            System.out.print("Entrer l'index du joueur à choisir : ");
            try {
                String input = stdin.readLine();
                indexChoisi = Integer.parseInt(input);
                if (indexChoisi >= 0 && indexChoisi < players.size()) {
                    choixValide = true;
                } else {
                    System.out.println("Index invalide. Veuillez réessayer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre entier.");
            } catch (IOException e) {
                System.out.println("Erreur de lecture de l'entrée.");
            }
        }

        return players.get(indexChoisi);
    }
}
