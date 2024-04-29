import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;
import java.util.ArrayList;
/**
 * MessageDatabaseTest
 *
 * Run tests for MessageDatabase class
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version April 29th, 2024
 */
public class MessageDatabaseTest {
    @Test
    public void testAddMessage() {
        try {
            // temp file for test
            File tempFile = File.createTempFile("temp", ".txt");
            String fileName = tempFile.getAbsolutePath();
            User u = new User("John Doe", "johndoe", "password123", "johndoe@example.com");
            String sender = u.getUsername();
            ArrayList<String> receivers = new ArrayList<>();
            receivers.add(new User("Jane Smith", "janesmith", "password456", "janesmith@example.com").getUsername());
            Message message = new Message(sender, receivers, "Hello Jane!");
            MessageDatabase database = new MessageDatabase();
            assertTrue(database.addMessage(fileName, message));
            BufferedReader br = new BufferedReader(new FileReader(tempFile));
            String line = br.readLine();
            assertNotNull(line);
            String[] parts = line.split(",");
            assertEquals(sender, parts[0]);
            assertEquals("", parts[1].trim());
            assertEquals(message.getContent(), parts[2]);
            assertNotNull(parts[3]);
            assertNull(br.readLine());
            br.close();
            tempFile.delete();
        } catch (IOException | BadInputException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
     @Test
    public void testDeleteMessage() throws IOException, BadInputException {
        MessageDatabase db = new MessageDatabase();
        User sender = new User("John Doe", "johndoe", "password123", "john@example.com");
        User receiver = new User("Jane Smith", "janesmith", "password456", "jane@example.com");

        ArrayList<String> receivers = new ArrayList<>();
        receivers.add(receiver.getUsername());

        Message message1 = new Message(sender.getUsername(), receivers, "Hello");
        Message message2 = new Message(sender.getUsername(), receivers, "World");
        String fileName = message1.createFile(sender.getUsername(), receivers);

        db.addMessage(fileName, message1);
        db.addMessage(fileName, message2);

        assertTrue(db.deleteMessage(fileName, sender.getUsername(), 1));
        assertFalse(db.deleteMessage(fileName, receiver.getUsername(), 1)); // User cannot delete someone else's message
    }

    @Test
    public void testGetChats() throws IOException, BadInputException {
        MessageDatabase db = new MessageDatabase();
        User sender = new User("John Doe", "johndoe", "password123", "john@example.com");
        User receiver1 = new User("Jane Smith", "janesmith", "password456", "jane@example.com");
        User receiver2 = new User("Bob Johnson", "bobjohnson", "password789", "bob@example.com");

        ArrayList<String> receivers1 = new ArrayList<>();
        receivers1.add(receiver1.getUsername());
        Message message1 = new Message(sender.getUsername(), receivers1, "Hello, Jane!");
        String fileName1 = message1.createFile(sender.getUsername(), receivers1);
        db.addMessage(fileName1, message1);

        ArrayList<String> receivers2 = new ArrayList<>();
        receivers2.add(receiver2.getUsername());
        Message message2 = new Message(sender.getUsername(), receivers2, "Hello, Bob!");
        String fileName2 = message2.createFile(sender.getUsername(), receivers2);
        db.addMessage(fileName2, message2);

        ArrayList<String> chats = db.getChats(sender.getUsername());
        assertTrue(chats.contains(fileName1.substring(0, fileName1.length() - 4)));
        assertTrue(chats.contains(fileName2.substring(0, fileName2.length() - 4)));
        assertEquals(2, chats.size());
    }

    @Test
    public void testFileNotFound() throws IOException, BadInputException {
        MessageDatabase db = new MessageDatabase();
        User sender = new User("John Doe", "johndoe", "password123", "john@example.com");
        User receiver = new User("Jane Smith", "janesmith", "password456", "jane@example.com");

        ArrayList<String> receivers = new ArrayList<>();
        receivers.add(receiver.getUsername());

        Message message = new Message(sender.getUsername(), receivers, "Hello, world!");
        String fileName = "nonexistent.txt";

        assertFalse(db.addMessage(fileName, message));
        assertFalse(db.deleteMessage(fileName, sender.getUsername(), 1));
    }
}
