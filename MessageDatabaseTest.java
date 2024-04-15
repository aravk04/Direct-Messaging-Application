/**
 * MessageDatabaseTest
 *
 * Run tests for MessageDatabase class
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version April 1, 2024
 */
import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;
import java.util.ArrayList;

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
}