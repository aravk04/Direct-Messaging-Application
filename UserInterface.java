import java.util.ArrayList;



public interface UserInterface {
    // Methods to be implemented by classes that implement this interface

    // Add a friend to the user's friend list
    boolean addFriend(User u);

    // Block a user
    boolean blockUser(User u);

    // Remove a friend from the user's friend list
    boolean removeFriend(User u);

    // Getters

    // Get the user's name
    String getName();

    // Get the list of user's friends
    ArrayList<User> getFriends();

    // Get the list of users blocked by this user
    ArrayList<User> getBlocked();

    // Get the user's password
    String getPassword();

    // Get the user's email address
    String getEmailAddress();

    // Get the user's username
    String getUsername();

    // Setters

    // Set the user's name
    void setName(String name);

    // Set the user's email address
    void setEmailAddress(String emailAddress);

    // Set the user's password
    void setPassword(String password);
}
