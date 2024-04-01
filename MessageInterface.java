/**
 * MessageInterface
 *
 * Interface for message class
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version March 31, 2024
 */
import java.util.ArrayList;

public interface MessageInterface {
    User getSender();
    ArrayList<User> getReceivers();
    String getContent();
    String getTimestamp();
    long getExactTime();
    boolean sameDM(MessageInterface m);
    String toString();
}
