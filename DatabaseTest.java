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
 * @author Harshil, Zonglin
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
                assertEquals("Zonglin Jia,Chenfeng Lyu;Er Yue;Dude;,Yulei Yang;MajiaQi;," +
                                "12345678,zonglin.2017@outlook.com,zj133",
                        database.viewUser("zj133"));

            } catch (Exception e) {
                fail("There should be no exception thrown for valid input");
            }
        }


        @Test(timeout = 1000)
        public void testAddUser() {
            try {
                Database database = new Database(INFILE);
                database.addUser("Mitch Daniels,fri1;,block2;,00000000,MitchDaniel@purdue.edu,Daniel123");
                assertEquals("Mitch Daniels,fri1;,block2;,00000000,MitchDaniel@purdue.edu,Daniel123",
                        database.viewUser("Daniel123"));

            } catch (Exception e) {
                fail("There should be no exception thrown for valid input");
            }
        }



        @Test(timeout = 1000)
        public void testRemoveUser() {
            try {
                Database database = new Database(INFILE);
                database.removeUser("zj133");

                assertNull(database.viewUser("zj133"));

            } catch (Exception e) {
                fail("There should be no exception thrown for valid input");
            }
        }


    }


}