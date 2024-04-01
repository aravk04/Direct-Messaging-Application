import java.util.ArrayList;
public class User extends Thread implements UserInterface{
    // Variables
    private String name;
    private ArrayList<User> friends;
    private ArrayList<User> blocked;
    private String password;
    private String emailAddress;
    private String username;

    // Constructor
    public User(String name, String username, String password, String emailAddress) throws BadInputException {
        // Validate username
        if (username == null || username.isEmpty()) {
            throw new BadInputException("Username cannot be empty");
        }
        //check email
        if (!isValidEmailAddress(emailAddress)) {
            throw new BadInputException("Invalid email address format");
        }
        //check password
        if (password == null || password.length() < 8) {
            throw new BadInputException("Password must be at least 8 characters long");
        }

        this.name = name;
        this.username = username;
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
                        if (this.getPassword().equals(this.getPassword())) {
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
        return username;
    }
    public String retrieveName() {
        return name;
    }

    // Setters

    public void updateName(String name) {
        this.name = name;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private boolean isValidEmailAddress(String email) {
        //checks if the email follows the basic pattern
        return email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    @Override
    public String toString() {
        String output = name + ",";
        for (int i = 0; i < friends.size(); i++) {
            output = output + friends.get(i).getName() + ";";
        }
        output += ",";

        for (int i = 0; i < blocked.size(); i++) {
            output = output + blocked.get(i).getName() + ";";
        }

        output = output + "," + password + "," + emailAddress + "," + username;

        return  output;
    }
}
