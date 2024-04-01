import static org.junit.Assert.*;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

public class DatabaseTest {

    // Test reading from a file
    @Test
    public void testReadFile() {
        try {
            // Create a temporary file with sample user data
            File tempFile = File.createTempFile("temp", ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            writer.write("John Doe, johndoe1; johndoe2, , password123, johndoe@example.com, johndoe");
            writer.newLine();
            writer.write("Jane Smith, janesmith1; janesmith2, johndoe, password456, janesmith@example.com, janesmith");
            writer.close();

            Database database = new Database("", tempFile.getAbsolutePath());
            database.readFile();

            ArrayList<User> users = database.getUsers();
            assertEquals(2, users.size());

            // Check if the users are correctly read
            User user1 = users.get(0);
            assertEquals("John Doe", user1.getName());
            assertEquals("johndoe", user1.getUsername());
            assertEquals("password123", user1.getPassword());
            assertEquals("johndoe@example.com", user1.getEmailAddress());
            assertTrue(user1.getFriends().contains(new User("", "johndoe1", "", "")));
            assertTrue(user1.getFriends().contains(new User("", "johndoe2", "", "")));

            User user2 = users.get(1);
            assertEquals("Jane Smith", user2.getName());
            assertEquals("janesmith", user2.getUsername());
            assertEquals("password456", user2.getPassword());
            assertEquals("janesmith@example.com", user2.getEmailAddress());
            assertTrue(user2.getFriends().contains(new User("", "janesmith1", "", "")));
            assertTrue(user2.getFriends().contains(new User("", "janesmith2", "", "")));

            // Delete the temporary file
            tempFile.delete();
        } catch (IOException e) {
            fail("Exception occurred while testing readFile: " + e.getMessage());
        } catch (IncorrectInfoException e) {
            fail("IncorrectInfoException occurred while testing readFile: " + e.getMessage());
        } catch (BadInputException e) {
            throw new RuntimeException(e);
        }
    }

    // Test writing to a file
    @Test
    public void testWriteFile() {
        try {
            // Create a temporary file
            File tempFile = File.createTempFile("temp", ".txt");
            String filePath = tempFile.getAbsolutePath();

            // Create a database and write user data to the file
            Database database = new Database("", filePath);
            database.addUser("John Doe, johndoe1; johndoe2, , password123, johndoe@example.com, johndoe");
            database.addUser("Jane Smith, janesmith1; janesmith2, johndoe, password456, janesmith@example.com, janesmith");
            database.rewriteFile();

            // Read from the file to check if the data is written correctly
            BufferedReader reader = new BufferedReader(new FileReader(tempFile));
            assertEquals("John Doe, johndoe1; johndoe2, , password123, johndoe@example.com, johndoe", reader.readLine());
            assertEquals("Jane Smith, janesmith1; janesmith2, johndoe, password456, janesmith@example.com, janesmith", reader.readLine());

            // Delete the temporary file
            tempFile.delete();
        } catch (IOException e) {
            fail("Exception occurred while testing writeFile: " + e.getMessage());
        }
    }

    // Test adding a user
    @Test
    public void testAddUser() {
        try {
            // Create a temporary file
            File tempFile = File.createTempFile("temp", ".txt");
            String filePath = tempFile.getAbsolutePath();

            // Create a database and add a user
            Database database = new Database("", filePath);
            assertTrue(database.addUser("John Doe, johndoe1; johndoe2, , password123, johndoe@example.com, johndoe"));

            // Check if the user is added correctly
            database.readFile();
            ArrayList<User> users = database.getUsers();
            assertEquals(1, users.size());
            User user = users.get(0);
            assertEquals("John Doe", user.getName());
            assertEquals("johndoe", user.getUsername());
            assertEquals("password123", user.getPassword());
            assertEquals("johndoe@example.com", user.getEmailAddress());
            assertTrue(user.getFriends().contains(new User("", "johndoe1", "", "")));
            assertTrue(user.getFriends().contains(new User("", "johndoe2", "", "")));

            // Delete the temporary file
            tempFile.delete();
        } catch (IOException e) {
            fail("Exception occurred while testing addUser: " + e.getMessage());
        } catch (IncorrectInfoException | BadInputException e) {
            fail("IncorrectInfoException occurred while testing addUser: " + e.getMessage());
        }
    }

    // Test editing a user
    @Test
    public void testEditUser() {
        try {
            // Create a temporary file
            File tempFile = File.createTempFile("temp", ".txt");
            String filePath = tempFile.getAbsolutePath();

            // Create a database and add a user
            Database database = new Database("", filePath);
            database.addUser("John Doe, johndoe1; johndoe2, , password123, johndoe@example.com, johndoe");

            // Edit the user's information
            assertTrue(database.editUser("John Doe, johndoe1; johndoe2, , password123, johndoe@example.com, johndoe",
                    "Jane Smith, janesmith1; janesmith2, , password456, janesmith@example.com, janesmith"));

            // Check if the user information is edited correctly
            database.readFile();
            ArrayList<User> users = database.getUsers();
            assertEquals(1, users.size());
            User editedUser = users.get(0);
            assertEquals("Jane Smith", editedUser.getName());
            assertEquals("janesmith", editedUser.getUsername());
            assertEquals("password456", editedUser.getPassword());
            assertEquals("janesmith@example.com", editedUser.getEmailAddress());
            assertTrue(editedUser.getFriends().contains(new User("", "janesmith1", "", "")));
            assertTrue(editedUser.getFriends().contains(new User("", "janesmith2", "", "")));

            // Delete the temporary file
            tempFile.delete();
        } catch (IOException e) {
            fail("Exception occurred while testing editUser: " + e.getMessage());
        } catch (IncorrectInfoException | BadInputException e) {
            fail("IncorrectInfoException occurred while testing editUser: " + e.getMessage());
        }
    }

    // Test searching for a user
    @Test
    public void testSearchUser() {
        try {
            // Create a temporary file
            File tempFile = File.createTempFile("temp", ".txt");
            String filePath = tempFile.getAbsolutePath();

            // Create a database and add a user
            Database database = new Database("", filePath);
            database.addUser("John Doe, johndoe1; johndoe2, , password123, johndoe@example.com, johndoe");

            // Search for the added user
            assertTrue(database.searchUser("johndoe"));

            // Search for a non-existing user
            assertFalse(database.searchUser("nonexistinguser"));

            // Delete the temporary file
            tempFile.delete();
        } catch (IOException e) {
            fail("Exception occurred while testing searchUser: " + e.getMessage());
        }
    }

    // Test viewing a user
    @Test
    public void testViewUser() {
        try {
            // Create a temporary file
            File tempFile = File.createTempFile("temp", ".txt");
            String filePath = tempFile.getAbsolutePath();

            // Create a database and add a user
            Database database = new Database("", filePath);
            database.addUser("John Doe, johndoe1; johndoe2, , password123, johndoe@example.com, johndoe");

            // View the added user
            String userInfo = database.viewUser("johndoe");
            assertNotNull(userInfo);
            assertEquals("John Doe, johndoe1; johndoe2, , password123, johndoe@example.com, johndoe", userInfo);

            // View a non-existing user
            assertNull(database.viewUser("nonexistinguser"));

            // Delete the temporary file
            tempFile.delete();
        } catch (IOException e) {
            fail("Exception occurred while testing viewUser: " + e.getMessage());
        }
    }
}
