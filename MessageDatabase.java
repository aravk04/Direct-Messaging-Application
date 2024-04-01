/**
 * MessageDatabase
 *
 * Add messages to files
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version March 31, 2024
 */
import java.util.ArrayList;
import java.io.*;
public class MessageDatabase implements MessageData {
    public MessageDatabase() {

    }

    public boolean addMessage(String fileName, Message m) throws FileNotFoundException, IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String receivers = "";
            for (User r : m.getReceivers()) {
                receivers += r.getUsername() + ",";
            }
            writer.write(m.getSender().getUsername() + "," +  "," + m.getContent() + "," + m.getTimestamp());
            writer.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}