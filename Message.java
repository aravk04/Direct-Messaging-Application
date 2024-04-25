import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

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
    private String sender;
    private ArrayList<String> receivers;
    private String content;
    private String timestamp;
    private long exactTime;

    public Message(String sender, ArrayList<String> receivers, String content) {
        this.sender = sender;
        this.receivers = receivers;
        this.content = content;
        this.timestamp = LocalDate.now().format(DateTimeFormatter.ofPattern("dd LLLL yyyy")) + " "
                + LocalTime.now().format(DateTimeFormatter.ofPattern("HH[:mm]"));
        this.exactTime = Instant.now().toEpochMilli();
    }

    // Getters
    public String getSender() {
        return sender;
    }

    public ArrayList<String> getReceivers() {
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

    public String createFile(String sender, ArrayList<String> receivers) throws FileNotFoundException, IOException {
        String fileName = "";
        receivers.add(sender);
        Collections.sort(receivers);
        for (String username : receivers) {
            fileName += username + "-";
        }
        fileName = fileName.substring(0, fileName.length() - 1) + ".csv";
        System.out.println("filename in message: " + fileName);
        receivers.remove(sender);

        return fileName;
    }

    @Override
    public String toString() {
        String out = sender + ",";

        String receiversString = "";
        if (!receivers.isEmpty()) {
            receiversString = receivers.get(0);
            for (int i = 1; i < receivers.size(); i++) {
                receiversString += ";" + receivers.get(i);
            }
        }

        out += receiversString;
        out = out + "," +  timestamp + "," + exactTime + "," + content;

        return out;
    }
}
