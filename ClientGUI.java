import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientGUI extends JFrame {
    private Client clientInstance;
    private JTextField usernameField, passwordField;
    private JTextArea messageArea;
    private JButton sendButton, signUpButton, logInButton;

    public ClientGUI() {
        setTitle("Chat Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create an instance of the Client class
        clientInstance = new Client();

        // Set up the GUI components
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JTextField();
        signUpButton = new JButton("Sign Up");
        logInButton = new JButton("Log In");

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
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
            // Add code to handle sign-up logic here
        }
    }

    private class LogInListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            // Add code to handle log-in logic here
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
            // Add code to handle sending messages here
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
