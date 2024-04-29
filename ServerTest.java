import java.io.IOException;
import java.net.UnknownHostException;

public class ServerTest implements Runnable{

    private Thread t;
    private String threadName;
    ServerTest(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {

        System.out.println("Running" + threadName);
        try {
            for (int i = 4; i > 0; i--) {
                System.out.println("Thread " + threadName + ", " + i);
                //Let the thread sleep for a while
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted");
        }
        System.out.println("Thread " + threadName + " existing");
    }

    public void start () {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    public static void main(String[] args) {
        ServerTest R1 = new ServerTest("Thread-1");
        R1.start();

        ServerTest R2 = new ServerTest("Thread-1");
        R2.start();
    }
     /*
    public static void main(String[] args) throws UnknownHostException,
            IOException, ClassNotFoundException, InterruptedException {
        Thread t = new Thread(new Server());
        Thread t2 = new Thread(new Server());

        t.start();
        t2.start();

        // wait for threads to end
        try {
            t.join();
            t2.join();
        }
        catch (Exception e) {
            System.out.println("Interrupted");
        }
    }

     */
}

