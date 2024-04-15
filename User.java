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
    private ArrayList<User> friends;
    private ArrayList<User> blocked;
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
        this.friends = new ArrayList<User>();
        this.blocked = new ArrayList<User>();
    }
    
    // Add friend to list

    public boolean addFriend(User u) {

        if ((u != null && !friends.contains(u)) && (!blocked.contains(u))) {
            friends.add(u);
            return true;
        }
        return false;
    }

    // Block a user
    public boolean blockUser(User u) {


        if ((u != null && !blocked.contains(u))) {
            blocked.add(u);
            this.removeFriend(u);
            return true;
        }
        return false;
    }
    // unblock a user
    public boolean unblockUser(User u) {
        if (u != null && blocked.contains(u)) {
            blocked.remove(u);
            return true;
        }
        return false;
    }


    // Remove friend from list
    public boolean removeFriend(User u) {
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

    public ArrayList<User> getFriends() {
        return new ArrayList<User>(friends);
    }

    public ArrayList<User> getBlocked() {
        return new ArrayList<User>(blocked);
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
            output = output + friends.get(i).getUsername() + ";";
        }
        output += ",";

        for (int i = 0; i < blocked.size(); i++) {
            System.out.println();
            output = output + blocked.get(i).getUsername() + ";";
        }

        output = output + "," + password + "," + emailAddress + "," + userName;

        return  output;
    }
}
