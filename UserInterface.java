/**
 * UserInteerface
 *
 * Interface for User
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version March 31, 2024
 */
import java.util.ArrayList;
public interface UserInterface {
    boolean addFriend(User u);
    boolean blockUser(User u);
    boolean removeFriend(User u);
    boolean unblockUser(User u);
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
