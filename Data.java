public interface Data {
    String searchUser(String info);
    boolean addUser(String info);
    boolean viewUser(String info);
    boolean editUser(String oldInfo, String newInfo);
    boolean outputData(String info);
}
