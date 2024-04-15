import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
/**
 * MessageTest
 *
 * Run tests for message class
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version April 1st, 2024
 */

public class MessageTest {

    @Test
    public void testMessageConstructor() throws BadInputException {
        User sender = new User("John Doe", "johndoe", "password123", "johndoe@example.com");
        User receiver = new User("Jane Smith", "janesmith", "password456", "janesmith@example.com");
        String content = "Hello, Jane!";

        ArrayList<User> receivers = new ArrayList<>();
        receivers.add(receiver);

        Message message = new Message(sender, receivers, content);

        assertNotNull(message);
        assertEquals(sender, message.getSender());
        assertEquals(receivers, message.getReceivers());
        assertEquals(content, message.getContent());
        assertNotNull(message.getTimestamp());
        assertTrue(message.getExactTime() > 0);
    }

    @Test
    public void testSameDM() throws BadInputException {
        User sender1 = new User("John Doe", "johndoe", "password123", "johndoe@example.com");
        User receiver1 = new User("Jane Smith", "janesmith", "password456", "janesmith@example.com");
        User sender2 = new User("Alice", "alice", "password789", "alice@example.com");
        User receiver2 = new User("Bob", "bob", "password101", "bob@example.com");

        ArrayList<User> receivers1 = new ArrayList<>();
        receivers1.add(receiver1);
        ArrayList<User> receivers2 = new ArrayList<>();
        receivers2.add(receiver2);

        Message message1 = new Message(sender1, receivers1, "Hello, Jane!");
        Message message2 = new Message(sender2, receivers2, "Hey, Bob!");
        Message message3 = new Message(sender1, receivers2, "Hi, Bob!");
        Message message4 = new Message(receiver1, receivers1, "Hello, John!");

        assertTrue(message1.sameDM(message4)); // Same conversation
        assertFalse(message1.sameDM(message2)); // Different conversation
        assertFalse(message1.sameDM(message3)); // Different conversation
    }

    @Test
    public void testToString() throws BadInputException {
        User sender = new User("John Doe", "johndoe", "password123", "johndoe@example.com");
        User receiver = new User("Jane Smith", "janesmith", "password456", "janesmith@example.com");
        String content = "Hello, Jane!";

        ArrayList<User> receivers = new ArrayList<>();
        receivers.add(receiver);


        Message message = new Message(sender, receivers, content);
        System.out.println(message.toString());
        String expectedString = "Message{sender=johndoe, receivers=[janesmith], timestamp='"
                + message.getTimestamp() + "', exactTime=" + message.getExactTime() + ", content='Hello, Jane!'" + '}';
        assertEquals(expectedString, message.toString());
    }
}
