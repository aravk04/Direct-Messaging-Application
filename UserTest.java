import static org.junit.Assert.*;
import org.junit.Test;

public class UserTest {

    @Test
    public void testConstructorWithValidInputs() {
        try {
            User user = new User("John Doe", "johndoe", "password123", "johndoe@example.com");
            assertEquals("John Doe", user.retrieveName());
            assertEquals("johndoe", user.getUsername());
            assertEquals("password123", user.getPassword());
            assertEquals("johndoe@example.com", user.getEmailAddress());
        } catch (Exception e) {
            fail("Exception should not be thrown for valid inputs");
        }
    }

    @Test
    public void testConstructorWithInvalidUsername() {
        try {
            User user = new User("John Doe", "", "password123", "johndoe@example.com");
            fail("Exception should be thrown for empty username");
        } catch (BadInputException e) {
            assertEquals("Username cannot be empty", e.getMessage());
        } catch (Exception e) {
            fail("BadInputException should be thrown for empty username");
        }
    }

    @Test
    public void testConstructorWithInvalidEmailAddress() {
        try {
            User user = new User("John Doe", "johndoe", "password123", "invalid-email");
            fail("Exception should be thrown for invalid email address");
        } catch (BadInputException e) {
            assertEquals("Invalid email address format", e.getMessage());
        } catch (Exception e) {
            fail("BadInputException should be thrown for invalid email address");
        }
    }

    @Test
    public void testConstructorWithInvalidPassword() {
        try {
            User user = new User("John Doe", "johndoe", "pass", "johndoe@example.com");
            fail("Exception should be thrown for password less than 8 characters");
        } catch (BadInputException e) {
            assertEquals("Password must be at least 8 characters long", e.getMessage());
        } catch (Exception e) {
            fail("BadInputException should be thrown for password less than 8 characters");
        }
    }

    @Test
    public void testAddFriend() throws BadInputException {
        User user1 = new User("John Doe", "johndoe", "password123", "johndoe@example.com");
        User user2 = new User("Jane Smith", "janesmith", "password456", "janesmith@example.com");

        assertTrue(user1.addFriend(user2));
        assertTrue(user1.getFriends().contains(user2));
    }

    @Test
    public void testBlockUser() throws BadInputException {
        User user1 = new User("John Doe", "johndoe", "password123", "johndoe@example.com");
        User user2 = new User("Jane Smith", "janesmith", "password456", "janesmith@example.com");

        assertTrue(user1.blockUser(user2));
        assertTrue(user1.getBlocked().contains(user2));
    }

    @Test
    public void testRemoveFriend() throws BadInputException {
        User user1 = new User("John Doe", "johndoe", "password123", "johndoe@example.com");
        User user2 = new User("Jane Smith", "janesmith", "password456", "janesmith@example.com");

        user1.addFriend(user2);
        assertTrue(user1.removeFriend(user2));
        assertFalse(user1.getFriends().contains(user2));
    }
    public void testUnblockUser() throws BadInputException {
        User user1 = new User("John Doe", "johndoe", "password123", "johndoe@example.com");
        User user2 = new User("Jane Smith", "janesmith", "password456", "janesmith@example.com");
        user1.blockUser(user2);
        assertTrue(user1.getBlocked().contains(user2));
        assertTrue(user1.unblockUser(user2));
        assertFalse(user1.getBlocked().contains(user2));
    }

    @Test
    public void testEquals() throws BadInputException {
        User user1 = new User("John Doe", "johndoe", "password123", "johndoe@example.com");
        User user2 = new User("John Doe", "johndoe", "password123", "johndoe@example.com");
        User user3 = new User("Jane Smith", "janesmith", "password456", "janesmith@example.com");

        assertTrue(user1.equals(user2));
        assertFalse(user1.equals(user3));
    }
}


