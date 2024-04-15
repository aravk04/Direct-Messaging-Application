import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) throws IOException, BadInputException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What would you like to do?\n1. Sign up\n2. Log in");
        int signOption = scanner.nextInt();
        scanner.nextLine();

        if (signOption == 1) {
            System.out.println("What would like your name to be?\n(your name can be modified)");
            String name = scanner.nextLine();
            System.out.println("What would like your username to be?\n(your username cannot be modified)");
            String userName = scanner.nextLine();
            System.out.println("What would like your password to be?");
            String password = scanner.nextLine();
            System.out.println("What is your email address?");
            String emailAddress = scanner.nextLine();
            User user = new User(name, userName, password, emailAddress);

        }

    }
}
