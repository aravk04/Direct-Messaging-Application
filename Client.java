import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client implements Runnable {
    public Client() {

    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            Socket socket = new Socket("localhost", 12345);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            String username;

            do {
                System.out.println("What would you like to do?\n1. Sign up\n2. Log in\n3. Quit Program");
                if (scanner.hasNextInt()) {
                    int signOption = scanner.nextInt();
                    scanner.nextLine();

                    if (signOption == 1) {
                        boolean valid = false;
                        do {
                            System.out.println("What would like your name to be?");
                            String name = scanner.nextLine();
                            System.out.println("What would like your username to be?");
                            username = scanner.nextLine();
                            System.out.println("What would like your password to be?");
                            String password = scanner.nextLine();
                            System.out.println("What is your email address?");
                            String emailAddress = scanner.nextLine();
                            String info = String.format("sig%s,,,%s,%s,%s", name, password, emailAddress, username);
                            pw.write(info);
                            pw.println();
                            pw.flush();

                            if (bfr.readLine().equals("True")) {
                                valid = true;
                            }

                            if (!valid) {
                                System.out.println("Username already taken. Please try again!");
                            }

                        } while (!valid);

                        do {
                            System.out.println("What would you like to do now " + username + "?\n1. Send Message\n2. View Message" +
                                    "\n3. Add Friend\n4. Remove friend\n5. Block users\n6. Unblock users\n7. Delete Account\n8. Logout");
                            if (scanner.hasNextInt()) {
                                int userOption = scanner.nextInt();
                                valid = false;
                                scanner.nextLine();
                                if (userOption == 1) {
                                    do {
                                        String recievers = "";
                                        String add;
                                        do {
                                            System.out.println("What user do you want to send the message to? (username)");
                                            recievers += scanner.nextLine() + ";";
                                            System.out.println("Do you want to add more users (Y/N) ?");
                                            add = scanner.nextLine();
                                        } while (add.toUpperCase().contains("Y"));
                                        System.out.println("What message do you want to send");
                                        String message = scanner.nextLine();
                                        String sendMessage = "msg" + username + recievers + message;
                                        pw.write(sendMessage);
                                        pw.println();
                                        pw.flush();

                                        if (bfr.readLine().equals("True")) {
                                            valid = true;
                                        }

                                        if (!valid) {
                                            System.out.println("One of the receivers was not found or has you blocked");
                                        }

                                    } while (!valid);
                                } else if (userOption == 2) {
                                    pw.write("vie");
                                    pw.println();
                                    pw.flush();

                                    ArrayList<String> list= new ArrayList<>();
                                    String line = bfr.readLine();
                                    while (line != null) {
                                        
                                    }

                                } else if (userOption == 3) {
                                    do {
                                        System.out.println("Who do you want to add? (username)");
                                        String add = "add" + scanner.nextLine();
                                        if (add.equals("EXIT")) {
                                            break;
                                        }
                                        pw.write(add);
                                        pw.println();
                                        pw.flush();

                                        if (bfr.readLine().equals("True")) {
                                            valid = true;
                                        }

                                        if (!valid) {
                                            System.out.println("Please enter a valid username or type EXIT to leave");
                                        }
                                    } while (!valid);
                                } else if (userOption == 4) {
                                    do {
                                        System.out.println("Who do you want to remove? (username)");
                                        String remove = "rem" + scanner.nextLine();
                                        pw.write(remove);
                                        pw.println();
                                        pw.flush();

                                        if (bfr.readLine().equals("True")) {
                                            valid = true;
                                        }

                                        if (!valid) {
                                            System.out.println("Please enter a valid username or type EXIT to leave");
                                        }
                                    } while (!valid);
                                } else if (userOption == 5) {
                                    do {
                                        System.out.println("Who do you want to block? (username)");
                                        String block = "blo" + scanner.nextLine();
                                        pw.write(block);
                                        pw.println();
                                        pw.flush();

                                        if (bfr.readLine().equals("True")) {
                                            valid = true;
                                        }

                                        if (!valid) {
                                            System.out.println("Please enter a valid username or type EXIT to leave");
                                        }
                                    } while (!valid);
                                } else if (userOption == 6) {
                                    do {
                                        System.out.println("Who do you want to unblock? (username)");
                                        String unblock = "unb" + scanner.nextLine();
                                        pw.write(unblock);
                                        pw.println();
                                        pw.flush();

                                        if (bfr.readLine().equals("True")) {
                                            valid = true;
                                        }

                                        if (!valid) {
                                            System.out.println("Please enter a valid username or type EXIT to leave");
                                        }
                                    } while (!valid);
                                } else if (userOption == 7) {
                                    String delete = "del" + username;
                                    pw.write(delete);
                                    pw.println();
                                    pw.flush();
                                    break;
                                } else if (userOption == 8) {
                                    break;
                                } else {
                                    System.out.println("Please select a valid Option");
                                }
                            } else {
                                System.out.println("Please select a valid Option");

                            }
                        } while (true);

                    } else if (signOption == 2) {
                        boolean valid = false;
                        int i = 0;
                        do {
                            i++;
                            System.out.println("What is your username");
                            username = scanner.nextLine();
                            System.out.println("What is your password");
                            String password = scanner.nextLine();
                            String info = String.format("log%s,%s", username, password);
                            pw.write(info);
                            pw.println();
                            pw.flush();

                            if (bfr.readLine().equals("True")) {
                                valid = true;
                            }

                            if (!valid) {
                                System.out.println("Username or Password is incorrect. Please Try again!");
                                System.out.println("" + (5 - i) + "attempts remaining...");
                            }

                            if (i >= 5) {
                                System.out.println("Ran out of attempts. Returning to main menu.");
                                break;
                            }

                        } while (!valid);

                        if (!valid) {
                            break;
                        }

                        do {
                            System.out.println("What would you like to do now " + username + "?\n1. Send Message\n2. View Message" +
                                    "\n3. Add Friend\n4. Remove friend\n5. Block users\n6. Unblock users\n7. Delete Account\n8. Logout");
                            if (scanner.hasNextInt()) {
                                int userOption = scanner.nextInt();
                                valid = false;
                                scanner.nextLine();
                                if (userOption == 1) {
                                    do {
                                        String recievers = "";
                                        String add;
                                        do {
                                            System.out.println("What user do you want to send the message to? (username)");
                                            recievers += scanner.nextLine() + ";";
                                            System.out.println("Do you want to add more users (Y/N) ?");
                                            add = scanner.nextLine();
                                        } while (add.toUpperCase().contains("Y"));
                                        System.out.println("What message do you want to send");
                                        String message = scanner.nextLine();
                                        String sendMessage = "msg" + username + recievers + message;
                                        pw.write(sendMessage);
                                        pw.println();
                                        pw.flush();

                                        if (bfr.readLine().equals("True")) {
                                            valid = true;
                                        }

                                        if (!valid) {
                                            System.out.println("One of the receivers was not found or has you blocked");
                                        }

                                    } while (!valid);
                                } else if (userOption == 2) {

                                } else if (userOption == 3) {
                                    do {
                                        System.out.println("Who do you want to add? (username)");
                                        String add = "add" + scanner.nextLine();
                                        if (add.equals("EXIT")) {
                                            break;
                                        }
                                        pw.write(add);
                                        pw.println();
                                        pw.flush();

                                        if (bfr.readLine().equals("True")) {
                                            valid = true;
                                        }

                                        if (!valid) {
                                            System.out.println("Please enter a valid username or type EXIT to leave");
                                        }
                                    } while (!valid);
                                } else if (userOption == 4) {
                                    do {
                                        System.out.println("Who do you want to remove? (username)");
                                        String remove = "rem" + scanner.nextLine();
                                        pw.write(remove);
                                        pw.println();
                                        pw.flush();

                                        if (bfr.readLine().equals("True")) {
                                            valid = true;
                                        }

                                        if (!valid) {
                                            System.out.println("Please enter a valid username or type EXIT to leave");
                                        }
                                    } while (!valid);
                                } else if (userOption == 5) {
                                    do {
                                        System.out.println("Who do you want to block? (username)");
                                        String block = "blo" + scanner.nextLine();
                                        pw.write(block);
                                        pw.println();
                                        pw.flush();

                                        if (bfr.readLine().equals("True")) {
                                            valid = true;
                                        }

                                        if (!valid) {
                                            System.out.println("Please enter a valid username or type EXIT to leave");
                                        }
                                    } while (!valid);
                                } else if (userOption == 6) {
                                    do {
                                        System.out.println("Who do you want to unblock? (username)");
                                        String unblock = "unb" + scanner.nextLine();
                                        pw.write(unblock);
                                        pw.println();
                                        pw.flush();

                                        if (bfr.readLine().equals("True")) {
                                            valid = true;
                                        }

                                        if (!valid) {
                                            System.out.println("Please enter a valid username or type EXIT to leave");
                                        }
                                    } while (!valid);
                                } else if (userOption == 7) {
                                    String delete = "del" + username;
                                    pw.write(delete);
                                    pw.println();
                                    pw.flush();
                                    break;
                                } else if (userOption == 8) {
                                    break;
                                } else {
                                    System.out.println("Please select a valid Option");
                                }
                            } else {
                                System.out.println("Please select a valid Option");

                            }
                        } while (true);

                    } else if (signOption == 3) {
                        System.out.println("Goodbye!");
                        break;
                    } else {
                        System.out.println("Please select a valid Option");
                    }
                } else {
                    System.out.println("Please select a valid Option");
                }
            } while (true);
        } catch (IOException i) {
            System.out.println("Could not connect");
        }
    }
}
