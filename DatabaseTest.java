import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

/**
 * A framework to run public test cases for Database.java -- Project5——Phase1
 *
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Harshil, Zonglin, Eesha, Benjamin, Arav
 * @version April 1st, 2024
 */

@RunWith(Enclosed.class)
public class DatabaseTest {

    public static void main(String[] args) {

        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }

    }



    public static class TestCase {

        private static final String INFILE = "input.txt";


        @Test(timeout = 1000)
        public void testConstructorWithValidInputs() {
            try {
                Database database = new Database(INFILE);
                //System.out.println("viewUser result: " + database.viewUser("zj133"));
                assertEquals(
                        "Zonglin Jia,cl47;ey533;wang001;,yl13;mq05;xiao157;," +
                                "12345678,jia133@purdue.edu,zj133",
                        database.viewUser("zj133"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @Test(timeout = 1000)
        public void testAddUser() {
            try {
                Database database = new Database(INFILE);
                database.addUser("Mitch Daniels,defaultFriend1;," +
                        "defaultBlock2;,00000000,MitchDaniel@purdue.edu,Daniel123");
                assertEquals("Mitch Daniels,defaultFriend1;," +
                                "defaultBlock2;,00000000,MitchDaniel@purdue.edu,Daniel123",
                        database.viewUser("Daniel123"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @Test(timeout = 1000)
        public void testRemoveUser() {
            try {
                Database database = new Database(INFILE);
                database.removeUser("Daniel123");

                assertNull(database.viewUser("Daniel123"));

            } catch (Exception e) {
                fail("There should be no exception thrown for valid input");
            }
        }



        @Test(timeout = 1000)
        public void testEditUser() {
            try {

                Database database = new Database(INFILE);

                String oldInfo = database.viewUser("zj133");

                String newInfo = "Zonglin Jia,cl47;ey533;wang001;,yl13;mq05;xiao157;," +
                        "12345678,jia133@purdue.edu,zj133";

                database.editUser(oldInfo, newInfo);
                assertEquals(newInfo, database.viewUser("zj133"));
                database.editUser(newInfo, oldInfo); //set the input.txt to default


                oldInfo = "MajiaQi,yl13;,defaultBlock1;,12345678,majiaqi.2024@gmail.com,mq05";
                newInfo = "MajiaQi,yl13;,defaultBlock2;,12345678,majiaqi.2024@gmail.com,mq05";

                database.editUser(oldInfo, newInfo);
                assertEquals(newInfo, database.viewUser("mq05"));
                database.editUser(newInfo, oldInfo); //set the input.txt to default

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
