import java.io.*;
import java.util.ArrayList;
/**
 * A Database that implements Data -- Project5——Phase1
 *
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Harshil, Zonglin, Arav, Eesha, Benjamin
 * @version April 1st, 2024
 */
public class Database implements Data {
    private String file;
    private ArrayList<User> users;

    public Database(String file) {
        this.file = file;
        this.users = new ArrayList<>();
        try {
            readFile();
        } catch (BadInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void readFile() throws BadInputException {
        ArrayList<User> userList = new ArrayList<>();
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
                    for (String friendName : friendNames) {
                        User friend = new User(friendName, "none",
                                "00000000", "default.email@outlook.com");
                        user.addFriend(friend);
                    }
                    for (String blockedName : blockedNames) {
                        User blockedUser = new User(blockedName, "none1",
                                "00000000", "default.email@outlook.com");
                        user.blockUser(blockedUser);
                    }
                    userList.add(user);
                } else {
                    System.out.println("Invalid data format: " + line);
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
            for (User u : users) {

                bw.write(u.toString());
                bw.newLine();

            }
        } catch (IOException e) {
            try {
                throw new BadInputException("Invalid Data");
            } catch (BadInputException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean addUser(String info) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.file, true))) {
            if (users != null) {
                String[] userData = info.split(",");
                String username = userData[5];
                for (User u : users) {
                    if (u.getUsername().equals(username)) {
                        return false;
                    }
                }
                bw.newLine();
                bw.write(info);

                if (userData.length == 6) {
                    String name = userData[0].trim();
                    String[] friendNames = userData[1].split(";");
                    String[] blockedNames = userData[2].split(";");
                    String password = userData[3].trim();
                    String emailAddress = userData[4].trim();
                    User newUser = new User(name, username, password, emailAddress);
                    for (String friendsName : friendNames) {
                        User friend = new User(friendsName, "none",
                                "00000000", "default.email@outlook.com");
                        newUser.addFriend(friend);
                    }
                    for (String blockedName : blockedNames) {
                        User blockedUser = new User(blockedName, "none1",
                                "00000000", "default.email@outlook.com");
                        newUser.blockUser(blockedUser);
                    }
                    users.add(newUser);
                }
                return true;
            }
        } catch (IOException e) {
            try {
                throw new BadInputException("Invalid Data");
            } catch (BadInputException ex) {
                ex.printStackTrace();
            }
        } catch (BadInputException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean editUser(String oldInfo, String newInfo) throws BadInputException {
        for (int i = 0; i < users.size(); i++) {
            //Assume there is this user
            String[] oldUserData = oldInfo.split(",");
            User newUser;
            if (oldUserData.length == 6) {
                String name = oldUserData[0].trim();
                String[] friendNames = oldUserData[1].split(";");
                String[] blockedNames = oldUserData[2].split(";");
                String password = oldUserData[3].trim();
                String emailAddress = oldUserData[4].trim();
                String username = oldUserData[5].trim();
                newUser = new User(name, username, password, emailAddress);
                for (String friendName : friendNames) {
                    User friend = new User(friendName, "none",
                            "00000000", "default.email@outlook.com");
                    newUser.addFriend(friend);
                }
                for (String blockedName : blockedNames) {
                    User blockedUser = new User(blockedName, "none1",
                            "00000000", "default.email@outlook.com");
                    newUser.blockUser(blockedUser);
                }
            } else {
                System.out.println("Invalid newInfo");
                return false;
            }
            //User initialization stop

            if (users.get(i).toString().equals(newUser.toString())) {

                String[] userData = newInfo.split(",");
                String name = userData[0].trim();
                String[] friendNames = userData[1].split(";");
                String[] blockedNames = userData[2].split(";");
                String password = userData[3].trim();
                String emailAddress = userData[4].trim();
                String username = userData[5].trim();
                newUser = new User(name, username, password, emailAddress);
                for (String friendName : friendNames) {
                    User friend = new User(friendName, "none",
                            "00000000", "default.email@outlook.com");
                    newUser.addFriend(friend);
                }
                for (String blockedName : blockedNames) {
                    User blockedUser = new User(blockedName, "none1",
                            "00000000", "default.email@outlook.com");
                    newUser.blockUser(blockedUser);
                }

                users.set(i, newUser);
                rewriteFile();
                return true;
            }
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

            //System.out.println("getUsername" + u.getUsername());
            if (u.getUsername().equals(username)) {
                return u.toString();
            }
        }
        return null;
    }

    public boolean removeUser(String username) {

        int indexToRemove = -1;

        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);

            if (u.getUsername().equals(username)) {
                indexToRemove = i;
                break;
            }
        }


        if (indexToRemove >= 0) {
            users.remove(indexToRemove);

            rewriteFile();
            return true;
        } else {
            return false;
        }
    }
}