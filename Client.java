import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Client implements Runnable {
    private Socket socket;
    private BufferedReader bfr;
    private PrintWriter pw;

    public Client() {

    }

    @Override
    public void run() {
        try {
            socket = new Socket("localhost", 12345);
            bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());
            System.out.println("INITIALZING PW\n\n\n");
            
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

    public boolean validUser(String username) throws IOException {
        String info = "vlu" + username;
        pw.write(info);
        pw.println();
        pw.flush();
        
        return bfr.readLine().equals("True");
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

    public boolean message(String chat, String message) {
        try {
            chat = chat.replace("-", ",");
            String info = "msv" + chat + "," + message;
            info = info.replace("\n", "");
            pw.write(info);
            pw.println();
            pw.flush();

            return bfr.readLine().equals("True");

        } catch (IOException i) {
            System.out.println("IOException in message(Chat Variant)");
            return false;
        }
    }

    public boolean deleteMessage(String chatId, int lineNum) {
        try {
            String info = "dms" + chatId + "," + lineNum;
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

    public boolean editBlocked(boolean block, String username) {
        try {
            if (block) {
                String info = "blo" + username;
                pw.write(info);
                pw.println();
                pw.flush();

                return bfr.readLine().equals("True");

            } else {
                String info = "unb" + username;
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
            
            String[] split = chat.split("-");
            Arrays.sort(split);
            String info = "vcl" + split[0] + "-" + split[1];
            pw.write(info);
            pw.println();
            pw.flush();

            ArrayList<String> list = new ArrayList<>();
            String line = bfr.readLine();
            if (line.equals("error")) {
                // return empty list
                return list;
            }
            while (!line.equals("stop")) {
                split = line.split(",");
                line =  split[0] + ": " + split[4] + " " + split[2] + "\n";
                list.add(line);
                line = bfr.readLine();
            }

            return list;

        } catch (IOException i) {
            System.out.println("IOException in viewChats");
            return null;
        }
    }

    public boolean editProfile(String name, String password, String email) {
        try {
            String newInfo = "edt" + name + ";" + password + ";" + email;
            pw.write(newInfo);
            pw.println();
            pw.flush();

            return bfr.readLine().equals("True");

        } catch (IOException i) {
            System.out.println("IOException in editProfile");
            return false;
        }
    }

    public boolean viewUser(String username) {
        try {
            String newInfo = "vUr" + username;
            pw.write(newInfo);
            pw.println();
            pw.flush();

            return bfr.readLine().equals("True");

        } catch (IOException i) {
            System.out.println("IOException in viewUser");
            return false;
        }
    }
}
