import static org.junit.Assert.*;
import org.junit.Test;

public class MessageTest {

    @Test
    public void testMessageConstructor() throws BadInputException {
        User sender = new User("John Doe", "johndoe", "password123", "johndoe@example.com");
        User receiver = new User("Jane Smith", "janesmith", "password456", "janesmith@example.com");
        String content = "Hello, Jane!";

        Message message = new Message(sender, receiver, content);

        assertNotNull(message);
        assertEquals(sender, message.getSender());
        assertEquals(receiver, message.getReceiver());
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

        Message message1 = new Message(sender1, receiver1, "Hello, Jane!");
        Message message2 = new Message(sender2, receiver2, "Hey, Bob!");
        Message message3 = new Message(sender1, receiver2, "Hi, Bob!");
        Message message4 = new Message(receiver1, sender1, "Hello, John!");

        assertTrue(message1.sameDM(message4)); // Same conversation
        assertFalse(message1.sameDM(message2)); // Different conversation
        assertFalse(message1.sameDM(message3)); // Different conversation
    }

    @Test
    public void testToString() throws BadInputException {
        User sender = new User("John Doe", "johndoe", "password123", "johndoe@example.com");
        User receiver = new User("Jane Smith", "janesmith", "password456", "janesmith@example.com");
        String content = "Hello, Jane!";

        Message message = new Message(sender, receiver, content);

        String expectedString = "Message{sender=johndoe, receiver=janesmith, content='Hello, Jane!', timestamp='" + message.getTimestamp() + "', exactTime=" + message.getExactTime() + '}';
        assertEquals(expectedString, message.toString());
    }
}
