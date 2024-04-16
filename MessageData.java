import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * MessageData
 *
 * interface for MessageDatabase
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version April 1, 2024
 */

public interface MessageData {
    boolean addMessage(String fileName, Message m) throws FileNotFoundException, IOException;
    boolean deleteMessage(String fileName, String username, int lineNum) throws FileNotFoundException, IOException;
    String getChat(String filename) throws IOException;
    public ArrayList<String> getChats(String username);
    public boolean updateChatLog(String chatId, List<String> messages);
}