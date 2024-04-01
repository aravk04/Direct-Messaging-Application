import java.util.ArrayList;
import java.io.*;
import java.util.List;

public class Test {
    public static void main(String[] args) throws FileNotFoundException, IOException, BadInputException {
       User e = new User("Eesha Faruqi", "efaruqi", "00003062", "efaruqi@gmail.com"); 
       User e1 = new User("Eesha1 Faruqi", "efaruqi1", "00003062", "efaruqi1@gmail.com"); 
       Message m = new Message(e, new ArrayList<>(List.of(e1)), "hello eesha1");
       e.sendMessage(m);
    }
}
