import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 * Message
 *
 * Used for users to communicate includes things
 * like sender, receiver, time, etc.
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version March 31, 2024
 */

public class Message implements MessageInterface {
    private User sender;
    private ArrayList<User> receivers;
    private String content;
    private String timestamp;
    private long exactTime;

    public Message(User sender, ArrayList<User> receivers, String content) {
        this.sender = sender;
        this.receivers = receivers;
        this.content = content;
        this.timestamp = LocalDate.now().format(DateTimeFormatter.ofPattern("dd LLLL yyyy")) + " "
                + LocalTime.now().format(DateTimeFormatter.ofPattern("HH[:mm]"));
        this.exactTime = Instant.now().toEpochMilli();
    }

    // Getters
    public User getSender() {
        return sender;
    }

    public ArrayList<User> getReceivers() {
        return receivers;
    }

    public String getContent() {
        return content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public long getExactTime() {
        return exactTime;
    }

    @Override
    public boolean sameDM(MessageInterface m) {
        return false;
    }

    public boolean sameDM(Message m) {
        for (User receiver : m.getReceivers()) {
            if (receiver.equals(this.getSender()) || this.receivers.contains(receiver)) {
                for (User thisReceiver : this.receivers) {
                    if (thisReceiver.equals(m.getSender()) || m.getReceivers().contains(thisReceiver)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String receiversString = "";
        if (!receivers.isEmpty()) {
            receiversString = receivers.get(0).getUsername();
            for (int i = 1; i < receivers.size(); i++) {
                receiversString += ", " + receivers.get(i).getUsername();
            }
        }

        return "Message{" +
                "sender=" + sender.getUsername() +
                ", receivers=[" + receiversString + "]" +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", exactTime=" + exactTime +
                '}';
    }
}