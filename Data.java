import java.util.ArrayList;

public interface Data {
    void readFile() throws IncorrectInfoException;
    void writeFile();
    boolean searchUser(String username);
    boolean addUser(String info);
    String viewUser(String username);
    boolean editUser(String oldInfo, String newInfo);
}
