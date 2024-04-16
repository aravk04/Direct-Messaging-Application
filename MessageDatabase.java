import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.util.Collections;

/**
 * MessageDatabase
 *
 * Add messages to files
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version April 1, 2024
 */
public class MessageDatabase implements MessageData {

    private ArrayList<String> fileList = new ArrayList<>();
    public MessageDatabase() {

    }

    public boolean addMessage(String fileName, Message m) throws FileNotFoundException, IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String receivers = "";
            for (String r : m.getReceivers()) {
                receivers += r + ";";
            }
            receivers = receivers.substring(0,receivers.length()-1);
            if (!fileList.contains(fileName)) {
                fileList.add(fileName);
            }

            writer.write(m.getSender() + "," + receivers + "," +
                    m.getTimestamp() + "," + m.getExactTime() + "," + m.getContent());
            writer.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public boolean deleteMessage(String fileName, String username, int lineNum) throws FileNotFoundException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            ArrayList<String> rewriteLines = new ArrayList<>();
            boolean removed = false;
            String line = reader.readLine();
            int currLineNum = 1;
            while (line != null) {
                // read next line
                line = line + "\n";
                String[] data = line.split(",");
                if (lineNum == currLineNum) {
                    if (!data[0].equals(username)) {
                        // user cannot delete someone else's message
                        return false;
                    }
                    removed = true;
                } else {
                    rewriteLines.add(line);
                }
                line = reader.readLine();
                currLineNum++;
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
                for (int i = 0; i < rewriteLines.size(); i++) {
                    writer.write(rewriteLines.get(i));
                }
                writer.close();

            }

           if (removed) {
               return true;
           } else {
               return false;
           }

        } catch (IOException e) {
            return false;
        }
    }

    public String getChat(String filename) throws IOException {
        String fileContent = "";
        // alphabatize file name
        String[] users = filename.split(",");
        Arrays.sort(users);
        filename = "";
        for (int i = 0; i < users.length; i++) {
            filename += users[i] + ",";
        }
        // remove last comma
        filename = filename.substring(0, filename.length() - 1);
        filename += ".csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            while (line != null) {
                fileContent = fileContent + line + "\n";
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }


    public ArrayList<String> getChats(String username) {
        ArrayList<String> chats = new ArrayList<>();

        for (int i = 0; i < fileList.size(); i++) {
            if (fileList.get(i).contains(username)) {
                chats.add(fileList.get(i).substring(0, fileList.get(i).length() - 4));
            }
        }
        return chats;
    }
}