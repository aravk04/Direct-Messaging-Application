/**
 * MessageData
 *
 * interface for MessageDatabase
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version April 1, 2024
 */
import java.io.FileNotFoundException;
import java.io.IOException;

public interface MessageData {
    boolean addMessage(String fileName, Message m) throws FileNotFoundException, IOException;
}