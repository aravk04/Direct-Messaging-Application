import java.io.*;
import java.util.ArrayList;

public class Database implements Data {
    private String file;
    private  ArrayList<User> users;

    public Database(String file) {
        this.file = file;
        this.users = null;
        try {
            readFile();
        } catch (IncorrectInfoException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void readFile() throws IncorrectInfoException {
        ArrayList<User> userList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 6) {
                    String name = userData[0].trim();
                    String[] friendNames = userData[1].split(";");
                    String[] blockedNames = userData[2].split(";");
                    String password = userData[3].trim();
                    String emailAddress = userData[4].trim();
                    String username = userData[5].trim();
                    User user = new User(name, username, password, emailAddress);
                    for (String friendsUserName : friendNames) {
                        User friend = new User("", friendsUserName, "", "");
                        user.addFriend(friend);
                    }
                    for (String blockedUserName : blockedNames) {
                        User blockedUser = new User("", blockedUserName, "", "");
                        user.blockUser(blockedUser);
                    }
                    userList.add(user);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
            this.users = userList;
        } catch (IOException | BadInputException e) {
            throw new IncorrectInfoException("Invalid Data");
        }
    }

    public void rewriteFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.file))) {
            for (User u : users) {
                bw.write(u.toString());
            }
        } catch (IOException e) {
            try {
                throw new IncorrectInfoException("Invalid Data");
            } catch (IncorrectInfoException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean addUser(String info) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.file, true))) {
            if (users != null) {
                for (User u : users) {
                    if (u.toString().equals(info)) {
                        return false;
                    }
                }
                bw.write(info);
                return true;
            }
        } catch (IOException e) {
            try {
                throw new IncorrectInfoException("Invalid Data");
            } catch (IncorrectInfoException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean editUser(String oldInfo, String newInfo) {
        try {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).toString().equals(oldInfo)) {
                    String[] userData = newInfo.split(",");
                    String name = userData[0].trim();
                    String[] friendNames = userData[1].split(";");
                    String[] blockedNames = userData[2].split(";");
                    String password = userData[3].trim();
                    String emailAddress = userData[4].trim();
                    String username = userData[5].trim();
                    User newUser = new User(name, username, password, emailAddress);
                    for (String friendsUserName : friendNames) {
                        User friend = new User("", friendsUserName, "", "");
                        newUser.addFriend(friend);
                    }
                    for (String blockedUserName : blockedNames) {
                        User blockedUser = new User("", blockedUserName, "", "");
                        newUser.blockUser(blockedUser);
                    }
                    users.set(i, newUser);
                    rewriteFile();
                    return true;
                }
            }
        } catch (BadInputException e) {
            return false;
        }
        return false;
    }

    public boolean searchUser(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public String viewUser(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u.toString();
            }
        }
        return null;
    }

    public boolean removeUser(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                users.remove(u);
                return true;
            }
        }
        return false;
    }
}