import java.util.ArrayList;
/**
 * UserInterface
 *
 * Interface for the User class
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version March 31, 2024
 */
public interface UserInterface {
    boolean addFriend(String u);
    boolean blockUser(String u);
    boolean removeFriend(String u);
    String getName();
    ArrayList<String> getFriends();
    ArrayList<String> getBlocked();
    String getPassword();
    String getEmailAddress();
    String getUsername();
    void setName(String name);
    void setEmailAddress(String emailAddress);
    void setPassword(String password);
}