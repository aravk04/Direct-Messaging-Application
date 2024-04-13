import java.io.*;
import java.net.*;
import java.util.*;
/**
 * Server
 *
 * Server class
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version April 13th, 2024
 */
public class Server {
    private static final int PORT = 12345;
    private static final Map<String, ObjectOutputStream> clients = new HashMap<>();
    private static final Object lock = new Object();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addClient(String username, ObjectOutputStream out) {
        synchronized (lock) {
            clients.put(username, out);
        }
    }

    public static void removeClient(String username) {
        synchronized (lock) {
            clients.remove(username);
        }
    }

    public static void sendMessage(String sender, String receiver, String message) {
        synchronized (lock) {
            ObjectOutputStream out = clients.get(receiver);
            if (out != null) {
                try {
                    out.writeObject(sender + ": " + message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("User " + receiver + " not found.");
            }
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String username;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            username = (String) in.readObject();
            Server.addClient(username, out);
            System.out.println("User " + username + " connected");

            String message;
            while ((message = (String) in.readObject()) != null) {
                String[] parts = message.split(" ", 2);
                Server.sendMessage(username, parts[0], parts[1]);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Server.removeClient(username);
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("User " + username + " disconnected");
        }
    }
}

