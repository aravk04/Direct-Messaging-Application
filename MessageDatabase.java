import java.util.ArrayList;
import java.io.*;
import java.util.Collections;

/**
 * MessageDatabase
 *
 * Add messages to files
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version April 1, 2024
 */
public class MessageDatabase implements MessageData {

    private ArrayList<String> fileList = new ArrayList<>();
    public MessageDatabase() {

    }

    public boolean addMessage(String fileName, Message m) throws FileNotFoundException, IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String receivers = "";
            for (String r : m.getReceivers()) {
                receivers += r + ";";
            }

            if (!fileList.contains(fileName)) {
                fileList.add(fileName);
            }

            writer.write(m.getSender() + "," + receivers + "," +
                    m.getTimestamp() + "," + m.getExactTime() + "," + m.getContent());
            writer.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public boolean deleteMessage(String fileName, Message m) throws FileNotFoundException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            ArrayList<Message> messages= new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                // read next line
                int countCommas = 0;
                int messageIndex = 0;
                for(int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ',') {
                        countCommas++;
                    }
                    if (countCommas == 4) {
                        messageIndex = i;
                    }
                }
                String message = line.substring(messageIndex + 1);
                String data = line.substring(0,messageIndex);
                String[] dataParts = data.split(",");
                String[] receiversArr = dataParts[1].split(";");
                ArrayList<String> receivers = new ArrayList<>();
                Collections.addAll(receivers, receiversArr);
                Message readMessage = new Message(dataParts[0],receivers,message);
                messages.add(readMessage);
                line = reader.readLine();
            }
            // go through each line in the file and make a new message object for each line
            // while loop going throuh file:
            // while end
            // go through array list of messages and check if sameDM to your parameter m
            // if you find it remove and return true

            for (int i = 0; i < messages.size(); i++) {
                if (m.sameDM(messages.get(i))) {
                    messages.remove(messages.get(i));
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }


    }


    public ArrayList<String> getChats(String username) {
        ArrayList<String> chats = new ArrayList<>();

        for (int i = 0; i < fileList.size(); i++) {
            if (fileList.get(i).contains(username)) {
                chats.add(fileList.get(i).substring(0, fileList.get(i).length() - 4));
            }
        }

        return chats;
    }
}
