package network.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static Server instance;
    private int port = 2999;

    private Server() {}

    private ServerSocket serverSocket;
    private List<ClientHandler> clients = new ArrayList<ClientHandler>();
    private DataInputStream in = null;

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started");

        System.out.println("Waiting for a client ...");
        while (clients.size() < 5) {
            Socket socket = serverSocket.accept();
            ClientHandler handler = new ClientHandler(socket, this);
            clients.add(handler);
            new Thread(handler).start();
        }
    }

    public synchronized void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.send(message);
        }
    }

    public void processAction(String line, ClientHandler clientHandler) {

    }
}
