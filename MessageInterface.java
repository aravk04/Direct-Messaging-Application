import java.util.ArrayList;

public interface MessageInterface {
    User getSender();
    ArrayList<User> getReceivers();
    String getContent();
    String getTimestamp();
    long getExactTime();
    boolean sameDM(MessageInterface m);
    String toString();
}

