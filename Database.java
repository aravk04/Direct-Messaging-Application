import java.io.*;
import java.util.ArrayList;

/**
 * A Database that implements Data -- Project5——Phase1
 *
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Harshil, Zonglin
 * @version April 1st, 2024
 */

public class Database implements Data {
    private String file;
    private ArrayList<User> users;

    public Database(String file) throws BadInputException {
        this.file = file;
        this.users = new ArrayList<>();
        readFile();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void readFile() throws BadInputException {
        ArrayList<User> userList = new ArrayList<>();
        ArrayList<String[]> friends = new ArrayList<>();
        ArrayList<String[]> blocked = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 6) {
                    String name = userData[0];
                    String[] friendNames = userData[1].split(";");
                    String[] blockedNames = userData[2].split(";");
                    String password = userData[3];
                    String emailAddress = userData[4];
                    String username = userData[5];
                    User user = new User(name, username, password, emailAddress);
                    friends.add(friendNames);
                    blocked.add(blockedNames);
                    userList.add(user);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }

            for (int i = 0; i < userList.size(); i++) {
                for (int j = 0; j < friends.get(i).length; j++) {
                    if (searchUser(friends.get(i)[j])) {
                        for (int k = 0; k < userList.size(); k++) {
                            if (userList.get(k).getUsername().equals(friends.get(i)[j])) {
                                userList.get(i).addFriend(userList.get(k));
                            }
                        }
                    } else {
                        throw new BadInputException("Invalid Data: " + friends.get(i)[j] + " not found");
                    }
                }
            }

            for (int i = 0; i < userList.size(); i++) {
                for (int j = 0; j < blocked.get(i).length; j++) {
                    if (searchUser(blocked.get(i)[j])) {
                        for (int k = 0; k < userList.size(); k++) {
                            if (userList.get(k).getUsername().equals(blocked.get(i)[j])) {
                                userList.get(i).blockUser(userList.get(k));
                            }
                        }
                    } else {
                        throw new BadInputException("Invalid Data: " + blocked.get(i)[j] + " not found");
                    }
                }
            }

            this.users = userList;
        } catch (IOException e) {
            throw new BadInputException("Invalid Data in the constructor");
        } catch (BadInputException e) {
            throw new RuntimeException(e);
        }
    }

    public void rewriteFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.file))) {

            boolean lineIsBlank = false;
            for (User u : users) {
                String userData = u.toString();
                if (!userData.isEmpty()) {
                    if (lineIsBlank) {
                        bw.newLine();
                        lineIsBlank = false;
                    }
                    bw.write(userData);
                } else {
                    lineIsBlank = true;
                }


            }

        } catch (IOException e) {
            try {
                throw new BadInputException("Invalid Data");
            } catch (BadInputException ex) {
                ex.printStackTrace();
            }
        }
    }


    public boolean addUser(String info) throws BadInputException {
        try {
            User user = createUser(info);

            if (searchUser(user.getUsername())) {
                return false;
            } else {
                users.add(user);
                rewriteFile();
                return true;
            }
        } catch (BadInputException e) {
            throw new BadInputException("Invalid Data");
        }
    }

    public boolean editUser(String oldInfo, String newInfo) throws BadInputException {
        for (int i = 0; i < users.size(); i++) {
            User newUser = createUser(newInfo);

            if (users.get(i).toString().equals(oldInfo)) {
                users.set(i, newUser);
                rewriteFile();
                return true;
            }
        }
        return false;
    }

    private User createUser(String userInfo) throws BadInputException {
        String[] userData = userInfo.split(",");
        if (userData.length == 6) {
            String name = userData[0].trim();
            String[] friendNames = userData[1].split(";");
            String[] blockedNames = userData[2].split(";");
            String password = userData[3].trim();
            String emailAddress = userData[4].trim();
            String username = userData[5].trim();
            User user = new User(name, username, password, emailAddress);
            for (int j = 0; j < friendNames.length; j++) {
                if (searchUser(friendNames[j])) {
                    for (int k = 0; k < users.size(); k++) {
                        if (users.get(k).getUsername().equals(friendNames[j])) {
                            user.addFriend(users.get(k));
                        }
                    }
                } else {
                    throw new BadInputException("Invalid Data: " + friendNames[j] + " not found");
                }
            }
            for (int j = 0; j < blockedNames.length; j++) {
                if (searchUser(blockedNames[j])) {
                    for (int k = 0; k < users.size(); k++) {
                        if (users.get(k).getUsername().equals(blockedNames[j])) {
                            user.blockUser(users.get(k));
                        }
                    }
                } else {
                    throw new BadInputException("Invalid Data: " + blockedNames[j] + " not found");
                }
            }
            return user;
        } else {
            throw new BadInputException("Invalid Data");
        }
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
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);

            if (u.getUsername().equals(username)) {
                users.remove(i);
                rewriteFile();
                return true;
            }
        }

        return false;
    }
}
