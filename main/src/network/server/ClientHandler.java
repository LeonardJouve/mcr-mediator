package network.server;

import mediator.MediatorState;
import mediator.SimpleGameMediator;
import player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket socket;
    private Server server;
    private PrintWriter out;
    private BufferedReader in;
    private String name;

    private Player player;

    private final boolean isCreator;

    public ClientHandler(Socket socket, Server server, boolean isCreator) {
        this.socket = socket;
        this.server = server;
        this.isCreator = isCreator;
    }


    public void send(String message) {
        out.println(message);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Entrez votre nom:");
            name = in.readLine();
            setPlayer(new Player(name));
            if (server.getMediatorState() == null){


                out.println("Vous êtes le créateur. Entrer 'start' quand la partie doit commencer");
                // éventuellement lire un ACK ?
            }
            server.broadcast(name + " a rejoint la partie");

            String line;
            while ((line = in.readLine()) != null) {
                server.processAction(line, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
