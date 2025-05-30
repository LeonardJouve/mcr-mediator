package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// singleton class server

public class Server {
    private static Server instance;
    private final static int port = 2999;
    private Server(){}
    private ServerSocket serverSocket;
    private Socket clientSocket;


    public Server getInstance(){
        if(instance == null){
            instance = new Server();
        }
        return instance;
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        /*
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String greeting = in.readLine();
        if ("hello server".equals(greeting)) {
            out.println("hello client");
        }
        else {
            out.println("unrecognised greeting");
        }

         */
    }

}
