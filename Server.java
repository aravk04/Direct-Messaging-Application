import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.util.*;

/**
 * Server
 * <p>
 * Handles client connections and requests
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version April 14th, 2024
 */
public class Server implements Runnable {
    private static Map<String, User> users = new HashMap<>();
    private static Map<String, ArrayList<String>> chats = new HashMap<>();
    private static final String INFILE = "input.txt";
    private static Database database;
    private static MessageDatabase messsageDatabase;
    private static final String chatsFile = "chats.csv";


    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started on port 12345");

            //read user's data from database
            database = new Database(INFILE);
            messsageDatabase = new MessageDatabase();
            Map<String, User> usersList = new HashMap<>();
            for (User u : database.getUsers()) {
                usersList.put(u.getUsername(), u);
            }

            // read chats from message database
            try (BufferedReader reader = new BufferedReader(new FileReader(chatsFile))) {
                String line = reader.readLine();
                String chatId = "";
                ArrayList<String> participantChats = new ArrayList<String>();
                while (line != null) {
                    chatId = line;
                    // now add each message to chats
                    //String chatFilename = chatId.replaceAll("-", ",");
                    String[] chatUsers = chatId.split("-");
                    Arrays.sort(chatUsers);
                    String chatFilename = "";
                    for (int i = 0; i < chatUsers.length; i++) {
                        chatFilename += chatUsers[i] + "-";
                    }
                    chatFilename = chatFilename.substring(0, chatFilename.length() - 1);
                    chatFilename += ".csv";
                    System.out.println("chatFilename in Server: " + chatFilename);
                    try (BufferedReader reader1 = new BufferedReader(new FileReader(chatFilename))) {
                        String message = reader1.readLine();
                        while (message != null) {
                            participantChats.add(message);
                            message = reader1.readLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    chats.put(chatId, participantChats);
                    line = reader.readLine();
                }

            } catch (IOException e) {
                // file has not been created yet or some other error
                e.printStackTrace();
            }
            System.out.println("Chats intialized: " + chats);
            users = usersList;

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadInputException e) {
            throw new RuntimeException(e);
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
            } catch (IOException | BadInputException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleRequest(String request) throws BadInputException, FileNotFoundException, IOException {
            String command = request.substring(0, 3);
            System.out.println("This is the command: " + command);
            String payload = request.substring(3);
            System.out.println("payload:" + payload);

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
                // view chat log
                case "vcl":
                    viewChatLog(payload);
                    break;
                case "edt":
                    editUser(payload);
                    break;
                case "vUr":
                    viewUser(payload);
                    break;
                default:
                    out.println("Invalid command");
            }
        }

        //create new user account and extract information
        private void createUser(String payload) throws BadInputException {
            String[] parts = payload.split(",,,");
            String name = parts[0].substring(0);
            String[] restOfPayload = parts[1].split(",");
            String password = restOfPayload[0];
            String email = restOfPayload[1];
            String username = restOfPayload[2];
            //check if username is same or not
            if (!users.containsKey(username)) {
                User user = new User(name, username, password, email);
                users.put(username, user);
                user.addFriend("defaultFriend1");
                user.blockUser("defaultBlock1");
                System.out.println(user.toString());
                database.addUser(user.toString());
                out.println("True");
                System.out.println("A: ");
            } else {
                out.println("False");
            }
        }

        // checks if username exists and if the password matches it
        private void login(String payload) {
            String[] parts = payload.split(",");
            String username = parts[0];
            String password = parts[1];
            //System.out.println("Key=" + key + ", Value=" + value);
            //System.out.println("user's password=" + value.getPassword());
            boolean randomString = false;

            //test
            for (Map.Entry<String, User> entry : users.entrySet()) {
                String key = entry.getKey();
                User value = entry.getValue();

                if (key.equals(username) && value.getPassword().equals(password)) {
                    this.username = username;
                    randomString = true;
                    System.out.println("user successfully logged in!");
                    out.println("True");
                    System.out.println("B: ");
                    //out.println();
                    //out.flush();
                    break;
                }
            }
            if (!randomString) {
                out.println("S");
            }
        }

        // send message to receiver and creates a chat also checks and makes sure they are not blocked
        private void sendMessage(String payload) throws FileNotFoundException, IOException {
            String sender = payload.substring(0, payload.indexOf(","));
            String receivers = payload.substring(payload.indexOf(",") + 1, payload.lastIndexOf(";"));
            String message = payload.substring(payload.lastIndexOf(";") + 1);

            System.out.println("sender:" + sender);
            System.out.println("receivers:" + receivers);
            System.out.println("message:" + message);

            String[] receiverList = receivers.split(";");

            ArrayList<String> receiverInArrayList = new ArrayList<>(Arrays.asList(receiverList));

            boolean success = false;
            ArrayList<String> receiveArrayList = new ArrayList<>(Arrays.asList(receiverList));
            System.out.println("Arrays.asList(receiverList)" + Arrays.asList(receiverList));
            Message m = new Message(sender, receiveArrayList, message);
            // add message to files
            messsageDatabase.addMessage(m.createFile(sender, receiveArrayList), m);

            for (String receiver : receiverList) {
                if (users.containsKey(receiver) && !users.get(receiver).isBlocked(sender)) {
                    String chatId = createChat(sender, receiverInArrayList);
                    writeToChat(chatId, m.getSender() + "," + receivers + "," +
                            m.getTimestamp() + "," + m.getExactTime() + "," + m.getContent());
                    System.out.println("C: ");
                    //System.out.println("message is successfully sent");
                    success = true;
                }
            }
            if (success) {
                out.println("True");
            } else {
                out.println("S");
            }
        }

        //gets username and checks which chats the user is in
        private void viewChats(String payload) {
            String username = payload;
            ArrayList<String> chatIds = new ArrayList<>();
            /*
            for (Map.Entry<String, ArrayList<String>> entry : chats.entrySet()) {
                if (entry.getValue().contains(username)) {
                    chatIds.add(entry.getKey());
                }
            }
             */
            for (String key : chats.keySet()) {
                if (key.contains(payload)) {
                    chatIds.add(key);
                }
            }
            //String[] listOfChatId = String.join(",", chatIds).split(",");

            for (String s : chatIds) {
                out.println(s);
                System.out.println("chat ( " + s);
            }
            out.println("stop");
        }

        private void viewChatLog(String payload) throws IOException {
            String chat = messsageDatabase.getChat(payload);
            System.out.println(chat);
            if (chat == null) {
                out.println("error");
            } else {
                out.println(chat);
                out.println("stop");
            }

        }

        // sends message within a chat
        //String sendMessage = "msg" + username + "," + recievers + message;
        private void sendMessageInChat(String payload) {
            String sender = payload.substring(0, payload.indexOf(","));
            payload = payload.substring(payload.indexOf(",") + 1);
            //Chat name
            String chatId = payload.substring(0, payload.indexOf(","));
            payload = payload.substring(payload.indexOf(",") + 1);
            String message = payload;

            //initialize the receiverList
            String[] receiverArray = chatId.split("-");
            ArrayList<String> receiveArrayList = new ArrayList<>();
            String receivers = "";
            for (String s : receiverArray) {
                if (!s.equals(sender)) {
                    receiveArrayList.add(s);
                    receivers = s + ";";
                }
            }

            System.out.println("sender:" + sender);
            System.out.println("Arrays.asList(receiveArrayList)" + Arrays.asList(receiveArrayList));
            System.out.println("message:" + message);

            Message m = new Message(sender, receiveArrayList, message);
            System.out.println("msv chatid: " + chatId);

            writeToChat(chatId, m.getSender() + "," + receivers + "," +
                    m.getTimestamp() + "," + m.getExactTime() + "," + m.getContent());
            List<String> messages = chats.get(chatId);
            System.out.println(messages);
            messsageDatabase.updateChatLog(chatId, messages);
        }

        //deletes username in specific chat and check username and line number of message to delete
        private void deleteMessage(String payload) {
            System.out.println(payload);
            String[] parts = payload.split(",");
            String username = parts[0];
            int lineNumber = Integer.parseInt(parts[1]);

            String chatId = getChatIdForUser(username);
            System.out.println("Chat id after dms " + chatId);
            if (chatId != null) {
                List<String> messages = chats.get(chatId);
                System.out.println(messages);
                System.out.println(lineNumber);
                System.out.println(messages.size());
                // line numbers are from 1 - messages.size()
                if (lineNumber >= 1 && lineNumber <= messages.size()) {
                    messages.remove(lineNumber - 1);
                    messsageDatabase.updateChatLog(chatId, messages);
                    out.println("True");
                    System.out.println("D: ");
                } else {
                    out.println("False");
                }
            } else {
                out.println("False");
            }
        }

        //add friend to the user friend list if they are friends
        private void addFriend(String payload) throws BadInputException {
            String username = payload;
            boolean randomString = false;
            for (Map.Entry<String, User> entry : users.entrySet()) {
                String key = entry.getKey();
                User value = entry.getValue();
                //System.out.println("Key= " + key + ", Value= " + value);
                if (key.equals(username)) {
                    users.get(this.username).addFriend(username);
                    database.rewriteFile();
                    database = new Database(INFILE);
                    out.println("True");
                    System.out.println("E: ");
                    randomString = true;
                    break;
                }
            }
            if (!randomString) {
                out.println("S");
            }

        }

        // remove friend from user list if they exist
        private void removeFriend(String payload) throws BadInputException {
            String username = payload;
            boolean randomString = false;
            for (Map.Entry<String, User> entry : users.entrySet()) {
                String key = entry.getKey();
                User value = entry.getValue();
                //System.out.println("Key= " + key + ", Value= " + value);

                if (key.equals(username)) {
                    users.get(this.username).removeFriend(username);
                    database.rewriteFile();
                    database = new Database(INFILE);
                    out.println("True");
                    System.out.println("F: ");
                    randomString = true;
                    break;
                }

            }
            if (!randomString) {
                out.println("S");
            }
        }

        // block user parses through the usernames and checks if they exist and adds them to the blocked list
        private void blockUser(String payload) throws BadInputException {
            String username = payload;
            boolean randomString = true;

            for (Map.Entry<String, User> entry : users.entrySet()) {
                String key = entry.getKey();
                User value = entry.getValue();
                //System.out.println("Key= " + key + ", Value= " + value);
                if (key.equals(username)) {
                    users.get(this.username).blockUser(username);
                    database.rewriteFile();
                    database = new Database(INFILE);
                    out.println("True");
                    System.out.println("G: ");
                    randomString = true;
                    break;
                }
            }
            if (!randomString) {
                out.println("S");
            }
        }

        // unblocks user and removes them from the blocked list if they are there
        private void unblockUser(String payload) throws BadInputException {
            String username = payload;
            boolean randomString = true;

            for (Map.Entry<String, User> entry : users.entrySet()) {
                String key = entry.getKey();
                User value = entry.getValue();
                //System.out.println("Key= " + key + ", Value= " + value);
                if (key.equals(username)) {
                    users.get(this.username).unblockUser(username);
                    database.rewriteFile();
                    database = new Database(INFILE);
                    out.println("True");
                    System.out.println("H: ");
                    break;
                }
            }
        }

        // parses usernames and deletes the user and any chats they were part of
        private void deleteUser(String payload) {
            String username = payload.substring(0);
            if (users.containsKey(username)) {
                users.remove(username);
                database.removeUser(username);
                // Remove the user from all chats
                for (Map.Entry<String, ArrayList<String>> entry : chats.entrySet()) {
                    entry.getValue().remove(username);
                }
            }

        }

        private void editUser(String payload) throws BadInputException { //edt + name + password + email

            String[] info = payload.split(";");
            for (Map.Entry<String, User> entry : users.entrySet()) {
                String key = entry.getKey();
                User value = entry.getValue();
                //System.out.println("Key= " + key + ", Value= " + value);
                if (key.equals(username)) {

                    String oldInfo = users.get(this.username).toString();
                    User tempUser = users.get(this.username);
                    tempUser.updateName(info[0]);
                    tempUser.setPassword(info[1]);
                    tempUser.setEmailAddress(info[2]);

                    String newInfo = tempUser.toString();

                    database.editUser(oldInfo, newInfo);
                    database.rewriteFile();
                    database = new Database(INFILE);

                    out.println("True");
                    System.out.println("I: ");
                    break;
                }
            }
        }

        private void viewUser(String payload) { //vUr + username

            boolean success = false;
            if (payload.toUpperCase().equals("EXIT")) {
                out.println("EXIT");
            } else {
                for (Map.Entry<String, User> entry : users.entrySet()) {
                    String key = entry.getKey();
                    User value = entry.getValue();
                    System.out.println("Key= " + key + ", Value= " + value);
                    if (key.equals(payload)) {
                        out.println("True" + value.retrieveName() +
                                ";" + value.getEmailAddress() + ";" + value.getUsername());
                        System.out.println("J: ");
                        success = true;
                        break;
                    }
                    System.out.println("success " + success);
                }
            }
            System.out.println("success OUT of the loop " + success);
            if (!success) {
                out.println("S");
            }
        }

        // creates unique chat between users
        public String createChat(String sender, ArrayList<String> receivers) throws FileNotFoundException, IOException {
            String fileName = "";
            receivers.add(sender);
            Collections.sort(receivers);
            for (String username : receivers) {
                fileName += username + "-";
            }
            fileName = fileName.substring(0, fileName.length() - 1);
            System.out.println("filename in message: " + fileName);
            receivers.remove(sender);

            if (!chats.containsKey(fileName)) {
                // add new unique chat to a file to save after server dies
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(chatsFile, true))) {
                    writer.newLine();
                    writer.write(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ArrayList<String> participants = new ArrayList<>();
                // participants.add(user1);
                // participants.add(user2);
                chats.put(fileName, participants);
            }
            return fileName;
        }


        /*
        private String createChat(String user1, String user2) {
            // needs to be alphabetized to check for uniqueness
            String chatId;
            if (user1.compareTo(user2) < 0) {
                chatId = user1 + "-" + user2;
            } else {
                chatId = user2 + "-" + user1;
            }

            System.out.println(chatId);
            if (!chats.containsKey(chatId)) {
                // add new unique chat to a file to save after server dies
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(chatsFile, true))) {
                    writer.write(chatId);
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ArrayList<String> participants = new ArrayList<>();
                // participants.add(user1);
                // participants.add(user2);
                chats.put(chatId, participants);
            }
            return chatId;
        }

         */

        // iterates through the chats to find the chat with the given username
        private String getChatIdForUser(String username) {
            // for (Map.Entry<String, ArrayList<String>> entry : chats.entrySet()) {
            //     if (entry.getValue().contains(username)) {
            //         return entry.getKey();
            //     }
            // }
            for (String key : chats.keySet()) {
                if (key.contains(username)) {
                    return key;
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

