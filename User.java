import java.util.ArrayList;
import java.io.*;
import java.io.File;
import java.util.Collections;
/**
 * User
 *
 * Represents user and contains information
 * about them
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version March 31, 2024
 */
public class User extends Thread implements UserInterface {
    // Variables
    private String realName;
    private ArrayList<String> friends;
    private ArrayList<String> blocked;
    private String password;
    private String emailAddress;
    private String userName;

    // Constructor
    public User(String realName, String userName, String password, String emailAddress) throws BadInputException {
        // Validate username
        if (userName == null || userName.isEmpty()) {
            throw new BadInputException("Username cannot be empty");
        }
        //check password
        if (password == null || password.length() < 8) {
            throw new BadInputException("Password must be at least 8 characters long");
        }

        this.realName = realName;
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.friends = new ArrayList<String>();
        this.blocked = new ArrayList<String>();
    }

    // Check if a user is blocked
    public boolean isBlocked(String username) {
        return blocked.contains(username);
    }

    // Add friend to list
    public boolean addFriend(String username) {

        if ((username != null && !friends.contains(username)) && (!blocked.contains(username))) {
            friends.add(username);
            return true;
        }
        return false;
    }

    // Block a user
    public boolean blockUser(String u) {
        if ((u != null && !blocked.contains(u))) {
            blocked.add(u);
            this.removeFriend(u);
            return true;
        }
        return false;
    }
    // unblock a user
    public boolean unblockUser(String u) {

        for (int i = 0; i < blocked.size(); i++) {
            System.out.println("u = " + u);
            System.out.println("blocked.get(i) = " + blocked.get(i));
            System.out.println("equal?" + blocked.get(i).equals(u));
            if (blocked.get(i).equals(u)) {
                blocked.remove(i);
                return true;
            }
        }
        return false;
    }


    // Remove friend from list
    public boolean removeFriend(String u) {
        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).equals(u)) {
                friends.remove(i);
                return true;
            }
        }
        return false;
    }

    //check if they have the same username
    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User u = (User) o;
            if (u.retrieveName().equals(this.retrieveName())) {
                if (u.getEmailAddress().equals(this.getEmailAddress())) {
                    if (u.getUsername().equals(this.getUsername())) {
                        if (u.getPassword().equals(this.getPassword())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Getters

    public ArrayList<String> getFriends() {
        return new ArrayList<String>(friends);
    }

    public ArrayList<String> getBlocked() {
        return new ArrayList<String>(blocked);
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUsername() {
        return userName;
    }
    public String retrieveName() {
        return realName;
    }

    // Setters

    public void updateName(String name) {
        this.realName = name;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.userName = username;
    }
    @Override
    public String toString() {
        String output = realName + ",";
        for (int i = 0; i < friends.size(); i++) {
            output = output + friends.get(i) + ";";
        }
        output += ",";

        for (int i = 0; i < blocked.size(); i++) {
            System.out.println();
            output = output + blocked.get(i) + ";";
        }

        output = output + "," + password + "," + emailAddress + "," + userName;

        return  output;
    }
}
