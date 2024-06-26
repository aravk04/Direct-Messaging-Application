import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * Client
 * <p>
 * The client that works the server
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version April 29th 2024
 */

public class Client implements Runnable {
    private Socket socket;
    private BufferedReader bfr;
    private PrintWriter pw;

    public Client() {

    }

    @Override
    public void run() {
        try {
            socket = new Socket("localhost", 4422);
            bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());

        } catch (IOException i) {
            System.out.println("Could not connect");
        }
    }

    public boolean createUser(String name, String username, String emailAddress, String password) {
        try {
            String info = String.format("cre%s,,,%s,%s,%s", name, password, emailAddress, username);
            pw.write(info);
            pw.println();
            pw.flush();

            return bfr.readLine().equals("True");

        } catch (IOException i) {
            System.out.println("IOException in createUser");
            return false;
        }
    }

    public boolean login(String username, String password) {
        try {
            String info = String.format("log%s,%s", username, password);
            pw.write(info);
            pw.println();
            pw.flush();

            return bfr.readLine().equals("True");

        } catch (IOException i) {
            System.out.println("IOException in login");
            return false;
        }
    }

    public boolean message(String username, String recievers, String message) {
        try {
            String info = "msg" + username + "," + recievers + ";" + message;
            pw.write(info);
            pw.println();
            pw.flush();

            return bfr.readLine().equals("True");

        } catch (IOException i) {
            System.out.println("IOException in message(Receivers Variant)");
            return false;
        }
    }

    public boolean message1(String chat, String username, String message) {
        try {
            String info = "msv" + username + "," + chat + "," + message;
            pw.write(info);
            pw.println();
            pw.flush();

            return bfr.readLine().equals("True");

        } catch (IOException i) {
            System.out.println("IOException in message(Receivers Variant)");
            return false;
        }
    }

    public boolean deleteMessage(String username, int lineNum) {
        try {
            String info = "dms" + username + "," + lineNum;
            pw.write(info);
            pw.println();
            pw.flush();

            return bfr.readLine().equals("True");

        } catch (IOException i) {
            System.out.println("IOException in deleteMessage");
            return false;
        }
    }

    public boolean editFriends(boolean add, String username) {
        try {
            if (add) {
                String info = "add" + username;
                pw.write(info);
                pw.println();
                pw.flush();

                return bfr.readLine().equals("True");

            } else {
                String info = "rem" + username;
                pw.write(info);
                pw.println();
                pw.flush();

                return bfr.readLine().equals("True");

            }
        } catch (IOException i) {
            System.out.println("IOException in editFriends");
            return false;
        }
    }

    public boolean editFriends(boolean add, String username, String user) {
        try {
            if (add) {
                String info = "add" + user + "," + username;
                pw.write(info);
                pw.println();
                pw.flush();

                return bfr.readLine().equals("True");

            } else {
                String info = "rem" + user + "," + username;
                pw.write(info);
                pw.println();
                pw.flush();

                return bfr.readLine().equals("True");

            }
        } catch (IOException i) {
            System.out.println("IOException in editFriends");
            return false;
        }
    }

    public boolean editBlocked(boolean block, String username, String user) {
        try {
            if (block) {
                String info = "blo" + user + "," + username;
                pw.write(info);
                pw.println();
                pw.flush();

                return bfr.readLine().equals("True");

            } else {
                String info = "unb" + user + "," + username;
                pw.write(info);
                pw.println();
                pw.flush();

                return bfr.readLine().equals("True");

            }
        } catch (IOException i) {
            System.out.println("IOException in editBlocked");
            return false;
        }
    }

    public void deleteAccount(String username) {
        String info = "del" + username;
        pw.write(info);
        pw.println();
        pw.flush();
    }

    public ArrayList<String> viewChats(String username) {
        try {
            String info = "vie" + username;
            pw.write(info);
            pw.println();
            pw.flush();

            ArrayList<String> list = new ArrayList<>();
            String line = bfr.readLine();
            while (!line.equals("stop")) {
                list.add(line);
                line = bfr.readLine();
            }

            return list;

        } catch (IOException i) {
            System.out.println("IOException in viewChats");
            return null;
        }
    }

    public ArrayList<String> viewChatLog(String chat) {
        try {
            String info = "vcl" + chat;
            pw.write(info);
            pw.println();
            pw.flush();

            ArrayList<String> list = new ArrayList<>();
            String line = bfr.readLine();
            while (!line.equals("stop")) {
                list.add(line);
                line = bfr.readLine();
            }

            return list;

        } catch (IOException i) {
            System.out.println("IOException in viewChats");
            return null;
        }
    }

    public boolean editProfile(String username, String name, String password, String email) {
        try {
            String newInfo = "edt" + username + ";" + name + ";" + password + ";" + email;
            pw.write(newInfo);
            pw.println();
            pw.flush();

            return bfr.readLine().equals("True");

        } catch (IOException i) {
            System.out.println("IOException in editProfile");
            return false;
        }
    }

    public String viewUser(String username, boolean personal) {
        try {
            String newInfo = "vUr" + personal + "," + username;
            pw.write(newInfo);
            pw.println();
            pw.flush();

            if (bfr.readLine().equals("True")) {
                return bfr.readLine();
            } else {
                return "Failure";
            }

        } catch (IOException i) {
            System.out.println("IOException in viewUser");
            return "Failure";
        }
    }
    public String getFriends(String username) {
        try {
            pw.write("gFr" + username);
            pw.println();
            pw.flush();

            String response = bfr.readLine();
            if (response.equals("")) {
                return "Failure";
            } else {
                return response;
            }

        } catch (IOException i) {
            System.out.println("IOException in getFriends");
            return "Failure";
        }
    }

    public String getBlocked(String username) {
        try {
            pw.write("gBl" + username);
            pw.println();
            pw.flush();

            String response = bfr.readLine();
            if (response.equals("")) {
                return "Failure";
            } else {
                return response;
            }

        } catch (IOException i) {
            System.out.println("IOException in getFriends");
            return "Failure";
        }
    }
}