import java.util.ArrayList;
import java.io.*;
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
            for (User r : m.getReceivers()) {
                receivers += r.getUsername() + ";";
            }

            if (!fileList.contains(fileName)) {
                fileList.add(fileName);
            }

            writer.write(m.getSender().getUsername() + "," + receivers + "," +
                    m.getTimestamp() + "," + m.getExactTime() + "," + m.getContent());
            writer.newLine();
            return true;
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
