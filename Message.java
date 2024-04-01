import java.time.*;
import java.time.format.DateTimeFormatter;

public class Message {
    private User sender;
    private User receiver;
    private String content;
    private String timestamp;

    private long exactTime;

    public Message(User sender, User receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = LocalDate.now().format(DateTimeFormatter.ofPattern("dd LLLL yyyy")) + " "
                + LocalTime.now().format(DateTimeFormatter.ofPattern("HH[:mm]"));
        this.exactTime = Instant.now().toEpochMilli();
    }

    // Getters
    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
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

    public boolean sameDM(Message m) {
        if (m.getSender().equals(this.getSender()) || m.getSender().equals(this.getReceiver())) {
            if ((m.getReceiver().equals(this.getSender()) || m.getReceiver().equals(this.getReceiver()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender.getUsername() +
                ", receiver=" + receiver.getUsername() +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", exactTime=" + exactTime +
                '}';
    }

}


