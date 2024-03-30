public class Database implements Dat {
    private String input;
    private String output;
    private ArrayList<User> users;

    public Database(String input, String output) {
        this.input = input;
        this.output = output;
        this.users = null;
    }
    public boolean editUser(String oldInfo, String newInfo) {
        for (u : users) {
            if (u.toString.equals(oldInfo)) {
                try {
                    User newUser = new User(newInfo);
                    u = newUser;
                    return true
                } catch(IncorrectInfoException e) {
                    return false;
                }
            }
        }
        return false;
    }
}
