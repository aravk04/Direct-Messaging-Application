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
    boolean addFriend(User u);
    boolean blockUser(User u);
    boolean removeFriend(User u);
    String getName();
    ArrayList<User> getFriends();
    ArrayList<User> getBlocked();
    String getPassword();
    String getEmailAddress();
    String getUsername();
    void setName(String name);
    void setEmailAddress(String emailAddress);
    void setPassword(String password);
}