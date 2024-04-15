import java.util.ArrayList;
/**
 * MessageInterface
 *
 * Interface for message class
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version March 31, 2024
 */
public interface MessageInterface {
    String getSender();
    ArrayList<String> getReceivers();
    String getContent();
    String getTimestamp();
    long getExactTime();
    String toString();
}
