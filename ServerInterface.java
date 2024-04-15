import java.io.*;
import java.net.*;
import java.util.*;
/**
* Server
*
* Handles client connections and requests
*
* @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
* Harshil Shah, Benjamin Ascano
* @version April 14th, 2024
*/
public interface ServerInterface {
    void createUser(String payload) throws BadInputException;

    void login(String payload);

    void sendMessage(String payload);

    void viewChats(String payload);

    void sendMessageInChat(String payload);

    void deleteMessage(String payload);

    void addFriend(String payload);

    void removeFriend(String payload);

    void blockUser(String payload);

    void unblockUser(String payload);

    void deleteUser(String payload);
}

