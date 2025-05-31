package network.client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    private Socket socket = null;
    private BufferedReader in = null;
    //private DataOutputStream out = null;
    private BufferedWriter out;
    // Constructor to put IP address and port

    private final static String ENDLINE = "\n";

    public Client(String addr, int port, String name){
        System.out.println("NOUVEAU ! Vanish extra fresh SANS ARNAQUE!");
        // Establish a connection
        try {
            socket = new Socket(addr, port);
            System.out.println("Connected");

            // Takes input from terminal
            //in = new DataInputStream(System.in);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            // Sends output to the socket
            //out = new DataOutputStream(socket.getOutputStream());
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));


            String res = in.readLine();
            System.out.println("Response: " + res);
            System.out.println("On continue ?");
            sendMessage(name);

        }
        catch (IOException i) {
            System.out.println(i);
            return;
        }

        // String to read message from input
        String m = "";

        // Keep reading until "Over" is input
        while (!m.equals("Over")) {
            try {
                m = in.readLine();
                out.write(m);
            }
            catch (IOException i) {
                System.out.println(i);
            }
        }

        // Close the connection
        try {
            in.close();
            out.close();
            socket.close();
        }
        catch (IOException i) {
            System.out.println(i);
        }
    }

    private void sendMessage(String msg){
        System.out.println("Sending: " + msg);
        try{
            System.out.println("[CLient] Sending: " + msg);
            out.write(msg + ENDLINE);
            out.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public Client(String addr, int port)
    {
        this(addr, port, "placeholderName");
    }

    public static void main(String[] args) {
        new Client("127.0.0.1", 2999);
    }
}
