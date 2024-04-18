import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client implements Runnable {
    public Client() {

    }

    @Override
    public void run() {
        String recievers = "";
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
                            String info = String.format("%s,,,%s,%s,%s", name, password, emailAddress, username);
                            pw.write("cre" + info);
                            pw.println();
                            pw.flush();

                            if (bfr.readLine().equals("True")) {
                                valid = true;
                            }
                            else {
                                valid = false;
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
                                        String add = "";
                                        do {
                                            System.out.println("What user do you want to send the message to? (username)");
                                            recievers += scanner.nextLine() + ";";
                                            do {
                                                System.out.println("Do you want to add more users (Y/N) ?");
                                                add = scanner.nextLine();
                                                if ((!add.toUpperCase().equals("Y")) && (!add.toUpperCase().equals("N"))) {
                                                    System.out.println("Please select Y or N");
                                                }
                                            } while ((!add.toUpperCase().equals("Y")) && (!add.toUpperCase().equals("N")));
                                        } while (!add.toUpperCase().equals("N"));
                                        System.out.println("What message do you want to send");
                                        String message = scanner.nextLine();
                                        String sendMessage = "msg" + username + "," + recievers + message;
                                        //System.out.println("sendMessage: " + sendMessage);
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
                                    boolean logOut = false;
                                    pw.write("vie" + username);
                                    pw.println();
                                    pw.flush();

                                    ArrayList<String> list = new ArrayList<>();
                                    String line = bfr.readLine();
                                    while (!line.equals("stop")) {
                                        list.add(line);
                                        line = bfr.readLine();
                                    }

                                    if (list.size() == 0) {
                                        System.out.println("You are not in any chats yet");
                                    } else {
                                        System.out.println("Please select one of the following chats to view:");
                                        for (int i = 0; i < list.size(); i ++) {
                                            int j = i + 1;
                                            System.out.println(j + ". " + list.get(i));
                                        }

                                        if (scanner.hasNextInt()) {
                                            int choice = scanner.nextInt();
                                            scanner.nextLine();

                                            if ((choice - 1) < list.size()) {
                                                String chat = list.get(choice - 1) + ".csv";
                                                pw.write(chat);
                                                pw.println();
                                                pw.flush();

                                                ArrayList<String> msgList = new ArrayList<>();
                                                String message = bfr.readLine();
                                                while (!message.equals("stop")) {
                                                    list.add(message);
                                                    System.out.println(message);
                                                    message = bfr.readLine();
                                                }

                                                do {
                                                    System.out.println("What would you like to do?\n1. Send Message\n2. Delete Message\n3. Go Back\n4. Log Out");
                                                    if (scanner.hasNextInt()) {
                                                        int vChoice = scanner.nextInt();
                                                        scanner.nextLine();

                                                        if (vChoice == 1) {
                                                            System.out.println("Enter your message:");
                                                            String msg = scanner.nextLine();

                                                            pw.write("msv" + chat + "," + msg);
                                                            pw.println();
                                                            pw.flush();

                                                        } else if (vChoice == 2) {
                                                            // first view the chat and print out all the current file contents
                                                            chat = list.get(choice - 1);
                                                            chat = chat.replaceAll("-", ",");
                                                            System.out.println(chat);
                                                            pw.write("vcl" + chat);
                                                            pw.println();
                                                            pw.flush();

                                                            String response = bfr.readLine();
                                                            int lineNum = 1;
                                                            while (!response.equals("stop")) {
                                                                System.out.println("(" + lineNum + ") " + response);
                                                                response = bfr.readLine();
                                                                lineNum ++;
                                                            }
                                                            boolean isNum = false;
                                                            lineNum = -1;
                                                            do {
                                                                System.out.println("Enter the line number you want to delete:");
                                                                if (scanner.hasNextInt()) {
                                                                    isNum = true;
                                                                    lineNum = scanner.nextInt();
                                                                    scanner.nextLine();
                                                                }
                                                            } while (!isNum);

                                                            String out = "dms" + username + "," + lineNum;
                                                            pw.write(out);
                                                            pw.println();
                                                            pw.flush();

                                                            if (bfr.readLine().equals("True")) {
                                                                System.out.println("Successfully deleted");
                                                            } else {
                                                                System.out.println("Could not delete");
                                                            }
                                                        } else if (vChoice == 3) {
                                                            break;
                                                        } else if (vChoice == 4) {
                                                            logOut = true;
                                                            break;
                                                        } else {
                                                            System.out.println("Please select a valid Option");
                                                        }

                                                    } else {
                                                        System.out.println("Please select a valid Option");
                                                    }
                                                } while (true);

                                                if (logOut) {
                                                    break;
                                                }

                                            } else {
                                                System.out.println("Please select a valid Option");
                                            }
                                        } else {
                                            System.out.println("Please select a valid Option");
                                        }
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
                                    String choice = "";
                                    while ((!choice.toUpperCase().equals("Y")) && (!choice.toUpperCase().equals("N"))) {
                                        System.out.println("Are you sure you want to delete your account (Y/N)?");
                                        choice = scanner.nextLine();
                                        if ((!choice.toUpperCase().equals("Y")) && (!choice.toUpperCase().equals("N"))) {
                                            System.out.println("Please select Y or N");
                                        }
                                    }

                                    if (choice.equals("Y")) {
                                        String delete = "del" + username;
                                        pw.write(delete);
                                        pw.println();
                                        pw.flush();
                                        break;
                                    }
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
                        int attempt = 0;
                        do {
                            attempt++;
                            System.out.println("What is your username");
                            username = scanner.nextLine();
                            System.out.println("What is your password");
                            String password = scanner.nextLine();
                            String info = String.format("log%s,%s", username, password);
                            pw.write(info);
                            pw.println();
                            pw.flush();
                            String response = bfr.readLine();

                            if (response.equals("True")) {
                                valid = true;
                            }

                            if (!valid) {
                                System.out.println("Username or Password is incorrect. Please Try again!");
                                System.out.println("" + (5 - attempt) + "attempts remaining...");
                            }

                            if (attempt >= 5) {
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
                                        String add = "";
                                        do {
                                            System.out.println("What user do you want to send the message to? (username)");
                                            recievers += scanner.nextLine() + ";";
                                            do {
                                                System.out.println("Do you want to add more users (Y/N) ?");
                                                add = scanner.nextLine();
                                                if ((!add.toUpperCase().equals("Y")) && (!add.toUpperCase().equals("N"))) {
                                                    System.out.println("Please select Y or N");
                                                }
                                            } while ((!add.toUpperCase().equals("Y")) && (!add.toUpperCase().equals("N")));
                                        } while (!add.toUpperCase().equals("N"));
                                        System.out.println("What message do you want to send");
                                        String message = scanner.nextLine();
                                        String sendMessage = "msg" + username + "," + recievers + message;
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
                                    boolean logOut = false;
                                    pw.write("vie" + username);
                                    pw.println();
                                    pw.flush();

                                    ArrayList<String> list = new ArrayList<>();
                                    String line = bfr.readLine();
                                    while (!line.equals("stop")) {
                                        list.add(line);
                                        line = bfr.readLine();
                                    }

                                    if (list.size() == 0) {
                                        System.out.println("You are not in any chats yet");
                                    } else {
                                        System.out.println("Please select one of the following chats to view:");
                                        for (int i = 0; i < list.size(); i ++) {
                                            int j = i + 1;
                                            System.out.println(j + ". " + list.get(i));
                                        }

                                        if (scanner.hasNextInt()) {
                                            int choice = scanner.nextInt();
                                            scanner.nextLine();

                                            if ((choice - 1) < list.size()) {
                                                String chat = list.get(choice - 1);
                                                //chat = chat.replaceAll("-", ",");
                                                pw.write("vcl" + chat);
                                                pw.println();
                                                pw.flush();

                                                String response = bfr.readLine();
                                                while (!response.equals("stop")) {
                                                    System.out.println(response);
                                                    response = bfr.readLine();
                                                }


                                                do {
                                                    System.out.println("What would you like to do?\n1. Send Message\n2. Delete Message\n3. Go Back\n4. Log Out");
                                                    if (scanner.hasNextInt()) {
                                                        int vChoice = scanner.nextInt();
                                                        scanner.nextLine();

                                                        if (vChoice == 1) {
                                                            System.out.println("Enter your message:");
                                                            String msg = scanner.nextLine();

                                                            pw.write("msv" + chat + "," + msg);
                                                            pw.println();
                                                            pw.flush();

                                                        } else if (vChoice == 2) {
                                                            // first view the chat and print out all the current file contents
                                                            chat = list.get(choice - 1);
                                                            chat = chat.replaceAll("-", ",");

                                                            pw.write("vcl" + chat);
                                                            pw.println();
                                                            pw.flush();

                                                            response = bfr.readLine();
                                                            int lineNum = 1;
                                                            while (!response.equals("stop") && !response.equals("")) {
                                                                System.out.println("(" + lineNum + ") " + response);
                                                                response = bfr.readLine();
                                                                lineNum ++;
                                                            }
                                                            // read stop line
                                                            bfr.readLine();
                                                            boolean isNum = false;
                                                            lineNum = -1;
                                                            do {
                                                                System.out.println("Enter the line number you want to delete:");
                                                                if (scanner.hasNextInt()) {
                                                                    isNum = true;
                                                                    lineNum = scanner.nextInt();
                                                                    scanner.nextLine();
                                                                }
                                                            } while (!isNum);

                                                            String out = "dms" + username + "," + lineNum;
                                                            pw.write(out);
                                                            pw.println();
                                                            pw.flush();
                                                            response = bfr.readLine();
                                                            System.out.println(response);
                                                            if (response.equals("True")) {
                                                                System.out.println("Successfully deleted");
                                                            } else {
                                                                System.out.println("Could not delete");
                                                            }
                                                        } else if (vChoice == 3) {
                                                            break;
                                                        } else if (vChoice == 4) {
                                                            logOut = true;
                                                            break;
                                                        } else {
                                                            System.out.println("Please select a valid Option");
                                                        }

                                                    } else {
                                                        System.out.println("Please select a valid Option");
                                                    }
                                                } while (true);

                                                if (logOut) {
                                                    break;
                                                }

                                            } else {
                                                System.out.println("Please select a valid Option");
                                            }
                                        } else {
                                            System.out.println("Please select a valid Option");
                                        }
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
                                    String choice = "";
                                    while ((!choice.toUpperCase().equals("Y")) && (!choice.toUpperCase().equals("N"))) {
                                        System.out.println("Are you sure you want to delete your account (Y/N)?");
                                        choice = scanner.nextLine();
                                        if ((!choice.toUpperCase().equals("Y")) && (!choice.toUpperCase().equals("N"))) {
                                            System.out.println("Please select Y or N");
                                        }
                                    }

                                    if (choice.equals("Y")) {
                                        String delete = "del" + username;
                                        pw.write(delete);
                                        pw.println();
                                        pw.flush();
                                        break;
                                    }
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
