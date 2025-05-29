package network.server;

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

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void send(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Entrez votre nom:");
            name = in.readLine();
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
