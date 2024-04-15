import java.io.*;
import java.net.*;
import java.util.*;
/**
 * Server
 *
 * Handles client connections and requests
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version April 14th, 2024
 */
public class Server implements Runnable {
    private static Map<String, User> users = new HashMap<>();
    private static Map<String, ArrayList<String>> chats = new HashMap<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started on port 12345");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private String username;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String request;
                while ((request = in.readLine()) != null) {
                    handleRequest(request);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleRequest(String request) {
            String command = request.substring(0, 3);
            String payload = request.substring(3);

            switch (command) {
                case "cre":
                    createUser(payload);
                    break;
                case "log":
                    login(payload);
                    break;
                case "msg":
                    sendMessage(payload);
                    break;
                case "vie":
                    viewChats(payload);
                    break;
                case "msv":
                    sendMessageInChat(payload);
                    break;
                case "dms":
                    deleteMessage(payload);
                    break;
                case "add":
                    addFriend(payload);
                    break;
                case "rem":
                    removeFriend(payload);
                    break;
                case "blo":
                    blockUser(payload);
                    break;
                case "unb":
                    unblockUser(payload);
                    break;
                case "del":
                    deleteUser(payload);
                    break;
                default:
                    out.println("Invalid command");
            }
        }
        //create new user account and extract information
        private void createUser(String payload) {
            String[] parts = payload.split(",,,");
            String name = parts[0].substring(3);
            String password = parts[1];
            String email = parts[2];
            String username = parts[3];
            //check if username is same or not
            if (!users.containsKey(username)) {
                User user = new User(name, password, email, username);
                users.put(username, user);
                out.println("True");
            } else {
                out.println("False");
            }
        }
        // checks if username exists and if the password matches it
        private void login(String payload) {
            String[] parts = payload.split(",");
            String username = parts[0].substring(3);
            String password = parts[1];

            if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
                this.username = username;
                out.println("True");
            } else {
                out.println("False");
            }
        }
        // send message to receiver and creates a chat also checks and makes sure they are not blocked
        private void sendMessage(String payload) {
            String sender = payload.substring(0, payload.indexOf(","));
            String receivers = payload.substring(payload.indexOf(",") + 1, payload.lastIndexOf(","));
            String message = payload.substring(payload.lastIndexOf(",") + 1);

            String[] receiverList = receivers.split(";");
            boolean success = true;

            for (String receiver : receiverList) {
                if (users.containsKey(receiver) && !users.get(receiver).isBlocked(sender)) {
                    String chatId = createChat(sender, receiver);
                    writeToChat(chatId, sender + ": " + message);
                } else {
                    success = false;
                }
            }

            out.println(success);
        }
        //gets username and checks which chats the user is in
        private void viewChats(String payload) {
            String username = payload.substring(3);
            Set<String> chatIds = new HashSet<>();

            for (Map.Entry<String, ArrayList<String>> entry : chats.entrySet()) {
                if (entry.getValue().contains(username)) {
                    chatIds.add(entry.getKey());
                }
            }

            out.println(String.join(",", chatIds));
            out.println("stop");
        }
        // sends message within a chat
        private void sendMessageInChat(String payload) {
            String[] parts = payload.split(",");
            String chatId = parts[0].substring(3);
            String message = parts[1];

            writeToChat(chatId, username + ": " + message);
        }
        //deletes username in specific chat and check username and line number of message to delete
        private void deleteMessage(String payload) {
            String[] parts = payload.split(",");
            String username = parts[0].substring(3);
            int lineNumber = Integer.parseInt(parts[1]);

            String chatId = getChatIdForUser(username);
            if (chatId != null) {
                List<String> messages = chats.get(chatId);
                if (lineNumber >= 0 && lineNumber < messages.size()) {
                    messages.remove(lineNumber);
                    out.println("True");
                } else {
                    out.println("False");
                }
            } else {
                out.println("False");
            }
        }
        //add friend to the user friend list if they are friends
        private void addFriend(String payload) {
            String username = payload.substring(3);
            if (users.containsKey(username)) {
                users.get(this.username).addFriend(username);
                out.println("True");
            } else {
                out.println("False");
            }
        }
        // remove friend from user list if they exist
        private void removeFriend(String payload) {
            String username = payload.substring(3);
            if (users.containsKey(username)) {
                users.get(this.username).removeFriend(username);
                out.println("True");
            } else {
                out.println("False");
            }
        }
        // block user parses through the usernames and checks if they exist and adds them to the blocked list
        private void blockUser(String payload) {
            String username = payload.substring(3);
            if (users.containsKey(username)) {
                users.get(this.username).blockUser(username);
                out.println("True");
            } else {
                out.println("False");
            }
        }

        // unblocks user and removes them from the blocked list if they are there
        private void unblockUser(String payload) {
            String username = payload.substring(3);
            if (users.containsKey(username)) {
                users.get(this.username).unblockUser(username);
                out.println("True");
            } else {
                out.println("False");
            }
        }
        // parses usernames and deletes the user and any chats they were part of
        private void deleteUser(String payload) {
            String username = payload.substring(3);
            if (users.containsKey(username)) {
                users.remove(username);
                // Remove the user from all chats
                for (Map.Entry<String, ArrayList<String>> entry : chats.entrySet()) {
                    entry.getValue().remove(username);
                }
            }
        }
        // creates unique chat between users
        private String createChat(String user1, String user2) {
            String chatId = user1 + "-" + user2;
            if (!chats.containsKey(chatId)) {
                ArrayList<String> participants = new ArrayList<>();
                participants.add(user1);
                participants.add(user2);
                chats.put(chatId, participants);
            }
            return chatId;
        }
        // iterates through the chats to find the chat with the given username
        private String getChatIdForUser(String username) {
            for (Map.Entry<String, ArrayList<String>> entry : chats.entrySet()) {
                if (entry.getValue().contains(username)) {
                    return entry.getKey();
                }
            }
            return null;
        }
        // writes a message and adds it to the chat if it exists
        private void writeToChat(String chatId, String message) {
            ArrayList<String> chat = chats.get(chatId);
            if (chat != null) {
                chat.add(message);
            }
        }
    }
}



