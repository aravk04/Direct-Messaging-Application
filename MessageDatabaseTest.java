/**
 * MessageDatabaseTest
 * <p>
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
            User sender = new User("John Doe", "johndoe", "password123", "johndoe@example.com");
            ArrayList<User> receivers = new ArrayList<>();
            receivers.add(new User("Jane Smith", "janesmith", "password456", "janesmith@example.com"));
            Message message = new Message(sender, receivers, "Hello, Jane!");
            MessageDatabase database = new MessageDatabase();
            assertTrue(database.addMessage(fileName, message));


            BufferedReader br = new BufferedReader(new FileReader(tempFile));
            String line;

            ArrayList<String> lineList = new ArrayList<>(); //already created (extracted from file)
            while ((line = br.readLine()) != null) {

                lineList.add(line);

            }


            for (int i = 0; i < lineList.size(); i++) {
                String tempLine = lineList.get(i);
                //System.out.println("tempLine: " + tempLine);

                String senderInString = tempLine.substring(0, tempLine.indexOf(","));
                tempLine = tempLine.substring(tempLine.indexOf(",") + 1);
                //System.out.println("tempLine: " + tempLine);

                tempLine = tempLine.substring(tempLine.indexOf(",") + 1);
                //System.out.println("tempLine: " + tempLine);

                String timestamp = tempLine.substring(0, tempLine.indexOf(","));
                //System.out.println("timestamp: " + timestamp);

                tempLine = tempLine.substring(tempLine.indexOf(",") + 1);
                //System.out.println("tempLine: " + tempLine);

                long exactTime = Long.parseLong(tempLine.substring(0, tempLine.indexOf(",")));
                //System.out.println("exactTime: " + tempLine.substring(0, tempLine.indexOf(",")));

                tempLine = tempLine.substring(tempLine.indexOf(",") + 1);
                //System.out.println("tempLine: " + tempLine);

                String content = tempLine;
                System.out.println(content);


                //test
                assertEquals(sender.getUsername(), senderInString);
                assertEquals(message.getContent(), content);
                assertNotNull(timestamp);
                assertNull(br.readLine());
                br.close();
                tempFile.delete();
            }



        } catch (IOException | BadInputException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}