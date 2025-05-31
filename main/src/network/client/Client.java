package network.client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    private Socket socket = null;
    private BufferedReader in = null;
    private BufferedReader stdIn = null;
    //private DataOutputStream out = null;
    private BufferedWriter out;
    // Constructor to put IP address and port

    private final static String ENDLINE = "\n";

    public Client(String addr, int port, String name){
        // Establish a connection
        try {
            socket = new Socket(addr, port);
            System.out.println("Connected");
            // data from user
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            // data from server
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            // Sends output to the socket
            //out = new DataOutputStream(socket.getOutputStream());
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

            String lastResponse = "";

            // on nous demande le nom
            readServerResponse();
            sendMessage(name);
            lastResponse = readServerResponse();

            // lire la reponse du serveur

            // si "Wait for instructions"
            // envoyer ack, et faire un readline

            // si "Send start when ready"
            // lire l'input user en attendant qu'il écrive start

            if (lastResponse.equals("CREATOR")) {
                System.out.println("Vous êtes le créateur. Entrer 'start' quand assez de joueurs sont présents pour que la partie puisse commencer");

                do {
                    lastResponse = readUserResponse();
                    sendMessage(lastResponse);
                } while (lastResponse.equals("INVALID"));
            }

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
        try{
            System.out.println("[CLient] Sending: " + msg);
            out.write(msg + ENDLINE);
            out.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private String readServerResponse(){
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

    private String readUserResponse(){
        String res = "";
        try {
            System.out.print(":>");
            res = stdIn.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

    public Client(String addr, int port)
    {
        this(addr, port, "placeholderName");
    }

    public static void main(String[] args) {
        new Client("127.0.0.1", 2999);
    }
}
