import java.util.ArrayList;
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String receivers = "";
            for (String r : m.getReceivers()) {
                receivers += r + ";";
            }

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

                String[] data = line.split(",");
                if (lineNum == currLineNum && data[0].equals(username)) {
                    removed = true;
                    continue;
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
