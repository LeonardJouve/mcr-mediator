package network.server;

import mediator.MediatorState;
import mediator.SimpleGameMediator;
import player.Player;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable{
    private Socket socket;
    private Server server;
    //private PrintWriter out;
    private BufferedWriter out;
    private BufferedReader in;
    private String name;

    private final static String ENDLINE = "\n";

    private Player player;

    private final boolean isCreator;

    public ClientHandler(Socket socket, Server server, boolean isCreator) {
        this.socket = socket;
        this.server = server;
        this.isCreator = isCreator;
    }

    public void send(String message) {
        //out.w(message);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    // pour débogguer
    public void sendMessage(String message) {
        try {
            System.out.println("[ClientHandler] sending : " + message);
            out.write(message + ENDLINE);
            out.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String readClientResponse(){
        String res = "";
        try {
            res = in.readLine();
            System.out.println("Response: " + res);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public void run() {
        try {
            System.out.println("VANISH ! Dites non aux tâches !");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            //out = new PrintWriter(socket.getOutputStream(), true);

            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

            // out.println("Entrez votre nom:");
            sendMessage("Entrez votre nom:");
            name = in.readLine();
            System.out.println("Client " + name + " connected");
            setPlayer(new Player(name));
            if (server.getMediatorState() == null){
                server.setMediatorState(new MediatorState());

                do {
                    sendMessage("CREATOR");
                    while (!readClientResponse().equals("start")){
                        sendMessage("INVALID");
                    };
                } while (!server.getGameHandler().getMediatorState().start());
                System.out.println("WENT THROUGH ^^");
                //out.println("Vous êtes le créateur. Entrer 'start' quand la partie doit commencer");

                // éventuellement lire un ACK ?
            }
            // server.broadcast(name + " a rejoint la partie");

            String line;
            while ((line = in.readLine()) != null) {
                server.processAction(line, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
