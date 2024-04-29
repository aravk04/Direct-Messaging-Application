import java.io.IOException;
import java.net.UnknownHostException;
/**
 * ClientTest
 *
 * client testing
 *
 * @author Harshil, Zonglin, Arav, Eesha, Benjamin
 * @version April 1st, 2024
 */
public class ClientTest {
    public static void main(String[] args) throws UnknownHostException,
            IOException, ClassNotFoundException, InterruptedException {
        Thread t = new Thread(new Client());
        //Thread t2 = new Thread(new Client());

        t.start();
        //t2.join();

    }
}
