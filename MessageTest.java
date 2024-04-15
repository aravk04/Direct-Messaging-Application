import org.junit.Test;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * MessageTest
 *
 * Run tests for message class
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version April 15th, 2024
 */
public class MessageTest {

    @Test
    public void testConstructor() {
        String sender = "johndoe";
        ArrayList<String> receivers = new ArrayList<>(Arrays.asList("janesmith", "bobjohnson"));
        String content = "Hello, everyone!";
        Message message = new Message(sender, receivers, content);

        assertEquals(sender, message.getSender());
        assertEquals(receivers, message.getReceivers());
        assertEquals(content, message.getContent());

        String expectedTimestamp = LocalDate.now().format(DateTimeFormatter.ofPattern("dd LLLL yyyy")) + " "
                + LocalTime.now().format(DateTimeFormatter.ofPattern("HH[mm]"));
        assertEquals(expectedTimestamp, message.getTimestamp());

        long expectedExactTime = Instant.now().toEpochMilli();
        assertTrue(Math.abs(expectedExactTime - message.getExactTime()) < 1000); // Allow 1 second difference
    }

    @Test
    public void testCreateFile() throws FileNotFoundException, IOException {
        String sender = "johndoe";
        ArrayList<String> receivers = new ArrayList<>(Arrays.asList("janesmith", "bobjohnson"));
        String expectedFileName = "bobjohnson,janesmith,johndoe.csv";

        Message message = new Message(sender, receivers, "Hello, everyone!");
        String fileName = message.createFile(sender, receivers);

        assertEquals(expectedFileName, fileName);
    }

    @Test
    public void testToString() {
        String sender = "johndoe";
        ArrayList<String> receivers = new ArrayList<>(Arrays.asList("janesmith", "bobjohnson"));
        String content = "Hello, everyone!";
        Message message = new Message(sender, receivers, content);

        String expectedString = "johndoe,bobjohnson;janesmith," + message.getTimestamp() + "," + message.getExactTime() + ",Hello, everyone!";
        assertEquals(expectedString, message.toString());
    }

    @Test
    public void testGetters() {
        String sender = "johndoe";
        ArrayList<String> receivers = new ArrayList<>(Arrays.asList("janesmith", "bobjohnson"));
        String content = "Hello, everyone!";
        Message message = new Message(sender, receivers, content);

        assertEquals(sender, message.getSender());
        assertEquals(receivers, message.getReceivers());
        assertEquals(content, message.getContent());

        String expectedTimestamp = LocalDate.now().format(DateTimeFormatter.ofPattern("dd LLLL yyyy")) + " "
                + LocalTime.now().format(DateTimeFormatter.ofPattern("HH[mm]"));
        assertEquals(expectedTimestamp, message.getTimestamp());

        long expectedExactTime = Instant.now().toEpochMilli();
        assertTrue(Math.abs(expectedExactTime - message.getExactTime()) < 1000); // Allow 1 second difference
    }
}
