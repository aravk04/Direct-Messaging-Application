import java.io.*;
import java.net.*;
/**
 * Client
 *
 * Client class
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version April 1st, 2024
 */

public class Client {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT)) {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your username: ");
            String username = reader.readLine();
            out.writeObject(username);

            new Thread(() -> {
                try {
                    String message;
                    while ((message = (String) in.readObject()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }).start();

            String input;
            while ((input = reader.readLine()) != null) {
                out.writeObject(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
