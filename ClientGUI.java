import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * ClientGUI 
 *
 * Graphical interface for app
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version April 22nd, 2024
 */
public class ClientGUI extends JFrame {
    private Client clientInstance;
    private JTextField usernameField, passwordField, nameField, emailField, recipientField;
    private JTextArea messageArea;
    private JButton sendButton, signUpButton, logInButton;
    private Socket socket;
    private BufferedReader bfr;
    private PrintWriter pw;
    private String username;

    public ClientGUI() {
        setTitle("Chat Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create an instance of the Client class
        clientInstance = new Client();

        // Set up the GUI components
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel recipientLabel = new JLabel("Recipient:");
        usernameField = new JTextField();
        passwordField = new JTextField();
        nameField = new JTextField();
        emailField = new JTextField();
        recipientField = new JTextField();
        signUpButton = new JButton("Sign Up");
        logInButton = new JButton("Log In");

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);
        inputPanel.add(recipientLabel);
        inputPanel.add(recipientField);
        inputPanel.add(signUpButton);
        inputPanel.add(logInButton);

        messageArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(messageArea);
        messageArea.setEditable(false);

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(scrollPane, BorderLayout.CENTER);
        JTextField messageField = new JTextField();
        sendButton = new JButton("Send");
        messagePanel.add(messageField, BorderLayout.NORTH);
        messagePanel.add(sendButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.NORTH);
        add(messagePanel, BorderLayout.CENTER);

        // Set up event handlers
        signUpButton.addActionListener(new SignUpListener());
        logInButton.addActionListener(new LogInListener());
        sendButton.addActionListener(new SendMessageListener(messageField));

        pack();
        setLocationRelativeTo(null);
    }

    private class SignUpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String name = nameField.getText();
            String email = emailField.getText();

            try {
                socket = new Socket("localhost", 12345);
                bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                pw = new PrintWriter(socket.getOutputStream());

                String info = String.format("%s,,,%s,%s,%s", name, password, email, username);
                pw.write("cre" + info);
                pw.println();
                pw.flush();

                String response = bfr.readLine();
                if (response.equals("True")) {
                    JOptionPane.showMessageDialog(ClientGUI.this, "Sign-up successful!");
                } else {
                    JOptionPane.showMessageDialog(ClientGUI.this, "Username already taken. Please try again!");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class LogInListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            username = usernameField.getText();
            String password = passwordField.getText();

            try {
                socket = new Socket("localhost", 12345);
                bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                pw = new PrintWriter(socket.getOutputStream());

                String info = String.format("log%s,%s", username, password);
                pw.write(info);
                pw.println();
                pw.flush();

                String response = bfr.readLine();
                if (response.equals("True")) {
                    JOptionPane.showMessageDialog(ClientGUI.this, "Log-in successful!");
                } else {
                    JOptionPane.showMessageDialog(ClientGUI.this, "Invalid username or password.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class SendMessageListener implements ActionListener {
        private JTextField messageField;

        public SendMessageListener(JTextField messageField) {
            this.messageField = messageField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String message = messageField.getText();
            String recipient = recipientField.getText();

            try {
                if (socket == null || socket.isClosed()) {
                    socket = new Socket("localhost", 12345);
                    bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    pw = new PrintWriter(socket.getOutputStream());
                }

                String sendMessage = "msg" + username + "," + recipient + message;
                pw.write(sendMessage);
                pw.println();
                pw.flush();

                String response = bfr.readLine();
                if (response.equals("True")) {
                    messageArea.append("Message sent to " + recipient + ": " + message + "\n");
                } else {
                    JOptionPane.showMessageDialog(ClientGUI.this, "Failed to send message.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            messageField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClientGUI gui = new ClientGUI();
                gui.setVisible(true);
            }
        });
    }
}
