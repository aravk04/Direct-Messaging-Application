/**
 * MessageDatabaseTest
 *
 * Run tests for MessageDatabase class
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version March 31, 2024
 */
import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;
import java.util.ArrayList;

public class MessageDatabaseTest {

    @Test
    public void testAddMessage() {
        User sender = new User("Eesha Faruqi", "efaruqi", "00003062", "efaruqi@gmail.com");
        User receiver = new User("Eesha1 Faruqi", "efaruqi1", "00003063", "efaruqi1@gmail.com");

        ArrayList<User> receivers = new ArrayList<>();
        receivers.add(receiver);
        Message message = new Message(sender, receivers, "Test message content");  // test message

        MessageDatabase database = new MessageDatabase();

        String fileName = "test_messages.txt";
        try {
            assertTrue(database.addMessage(fileName, message));

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("efaruqi,efaruqi1") && line.contains("Test message content")) {
                    found = true;
                    break;
                }
            }
            reader.close();
            assertTrue("Message not found in the file", found);
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }

        // Clean up test file
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }
}

