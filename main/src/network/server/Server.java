package network.server;

import mediator.MediatorState;
import mediator.SimpleGameMediator;
import network.client.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Server {
    private static Server instance;
    private int port = 2999;

    private Server() {}

    private ServerSocket serverSocket;
    private List<ClientHandler> clients = new ArrayList<>();
    private DataInputStream in = null;

    private MediatorState mediatorState;
    private GameHandler gameHandler;

    private ClientHandler creator;      // le client qui va devoir start la game. Le premier à rejoindre est le creator

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started");

        System.out.println("Waiting for a client ...");

        while (clients.size() < 5) {
            ClientHandler handler;
            Socket socket = serverSocket.accept();
            if (gameHandler == null) {       // le premier à rejoindre devra envoyer "start" pour que ça start
                MediatorState m = new MediatorState();
                SimpleGameMediator gm = new SimpleGameMediator(m);
                m.setGameMediator(gm);
                gameHandler = new GameHandler(m);

                handler = new ClientHandler(socket, this, true);
            } else {
                handler = new ClientHandler(socket, this, false);
            }
            clients.add(handler);
            new Thread(handler).start();
        }
    }
    public synchronized void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.send(message);       // nécéssite que le client soit entrain d'écouter ?
        }
    }

    public void processAction(String line, ClientHandler clientHandler) {
        if (clientHandler == null) {
            return;
        }

        if (Objects.equals(line, "start") && clientHandler == creator) {
            //mediatorState.start();
            new Thread(gameHandler).start();
        }
    }

    public MediatorState getMediatorState() {
        return mediatorState;
    }

    public void setMediatorState(MediatorState mediatorState) {
        this.mediatorState = mediatorState;
    }

    public static void main(String[] args) {
        try {
            Server.getInstance().start();
        } catch (IOException e) {
            System.out.println("Server failed to start : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
