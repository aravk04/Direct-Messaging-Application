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
            //test userlist
            System.out.print("username in userlist: ");

            for (User u : userList) {
                System.out.print(u.getUsername() + ";");
            }

            for (int i = 0; i < userList.size(); i++) {
                for (int j = 0; j < friends.get(i).length; j++) {

                    //test
                    System.out.println("friends.get(" + i + ")[" + j + "]= " + friends.get(i)[j]);
                    System.out.println(searchUser(friends.get(i)[j]));

                    boolean validFriends = false;
                    for (User u : userList) {
                        if (u.getUsername().equals(friends.get(i)[j])) {
                            validFriends = true;
                        }
                    }

                    if (validFriends) {
                        for (int k = 0; k < userList.size(); k++) {
                            if (userList.get(k).getUsername().equals(friends.get(i)[j])) {
                                userList.get(i).addFriend(userList.get(k).getUsername());
                            }
                        }
                    } else {
                        throw new BadInputException("Invalid Data: " + friends.get(i)[j] + " not found");
                    }

                }
            }

            for (int i = 0; i < userList.size(); i++) {
                for (int j = 0; j < blocked.get(i).length; j++) {

                    boolean validBlocked = false;
                    for (User u : userList) {
                        if (u.getUsername().equals(blocked.get(i)[j])) {
                            validBlocked = true;
                        }
                    }

                    if (validBlocked) {
                        for (int k = 0; k < userList.size(); k++) {
                            if (userList.get(k).getUsername().equals(blocked.get(i)[j])) {
                                userList.get(i).blockUser(userList.get(k).getUsername());
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
                //System.out.println("userData: " + userData);
                if (!userData.isEmpty()) {
                    if (lineIsBlank) {
                        bw.newLine();
                        lineIsBlank = false;
                    }
                    bw.write(userData + "\n");
                    //test
                    //System.out.println("userData of " + userData.split(",")[0] + " is successfully added");
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
        User newUser = createUser(newInfo);
        //System.out.println("editing... the users size is " + users.size());
        //System.out.println("the newUser data is: " + newUser.toString());
        boolean isEdited = false;
        int index = -1;

        for (int i = 0; i < users.size(); i++) {
            //System.out.println("print each line =" + users.get(i).toString());
            //System.out.println("oldInfo =" + oldInfo);

            if (users.get(i).toString().equals(oldInfo)) {

                index = i;
                break;

            }

        }
        if (index == -1) {
            System.out.println("There is no user that found to be matching the input");
            return isEdited;
        }


        //System.out.println("the name of the user you want to modified is: " + oldInfo.split(",")[0]);

        for (int i = 0; i < users.size(); i++) {

            //Edit the line of every line that contains the old user's name
            //If the name is in the friendList
            for (int j = 0; j < users.get(i).getFriends().size(); j++) {

                String friend = users.get(i).getFriends().get(j);
                //System.out.println("friend name=" + friend.retrieveName());

                if (friend.contains(oldInfo.split(",")[0])) {
                    users.get(i).removeFriend(users.get(index).getUsername());
                    users.get(i).addFriend(newUser.getUsername());
                    rewriteFile();
                    //System.out.println("friends data of " + users.get(i).retrieveName() + " is successfully updated");

                }
            }

            //Edit the line of every line that contains the old user's name
            //If the name is in the blocked
            for (int j = 0; j < users.get(i).getBlocked().size(); j++) {

                String block = users.get(i).getBlocked().get(j);
                //System.out.println("blocked name=" + block.retrieveName());

                if (block.contains(oldInfo.split(",")[0])) {
                    users.get(i).unblockUser(users.get(index).getUsername());
                    users.get(i).blockUser(newUser.getUsername());
                    rewriteFile();
                    //System.out.println("blocked data of " + users.get(i).retrieveName() + " is successfully updated");
                }
            }
        }
        //Edit the line of the user himself/herself
        users.set(index, newUser);
        rewriteFile();
        System.out.println("userData of " + newInfo.split(",")[0] + " is successfully updated");
        isEdited = true;

        return isEdited;

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
                            user.addFriend(users.get(k).getUsername());
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
                            user.blockUser(users.get(k).getUsername());
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

        //System.out.println(users.size());

        for (User u : users) {
            //System.out.println("realname: " + realName);
            //System.out.println("u.getRealName" + u.getRealName());

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
