import java.util.ArrayList;

public interface Data {
    ArrayList<User> getUsers();
    void setUsers(ArrayList<User> users);
    void readFile() throws IncorrectInfoException;
    void writeFile();
    boolean searchUser(String username);
    boolean addUser(String info);
    String viewUser(String username);
    boolean editUser(String oldInfo, String newInfo);
    boolean removeUser(String username);
}
