import java.util.ArrayList;

/**
 * An interface of data
 *
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Harshil, Zonglin, Arav, Eesha, Benjamin
 * @version April 1st, 2024
 */
public interface Data {
    ArrayList<User> getUsers();

    void setUsers(ArrayList<User> users);

    void readFile() throws BadInputException;

    boolean searchUser(String username);

    boolean addUser(String info) throws BadInputException;

    String viewUser(String username);

    boolean editUser(String oldInfo, String newInfo) throws BadInputException;

    boolean removeUser(String username);
}
