import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements Runnable {
    private Client client;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new ClientGUI());
    }

    public ClientGUI() {

    }

    @Override
    public void run() {
        client = new Client();

        JFrame startFrame = new JFrame();
        startFrame.setTitle("Welcome to Our Social Media Platform");

        // Content pane for the start frame
        Container startContent = startFrame.getContentPane();
        JLabel programName = new JLabel("Faceless Book");
        programName.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        programName.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Start labels for spacing
        JLabel startLabel1 = new JLabel(" ");
        startLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 200));
        startLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel startLabel2 = new JLabel(" ");
        startLabel2.setFont(new Font("Times New Roman", Font.PLAIN, 200));
        startLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel startLabel3 = new JLabel(" ");
        startLabel3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        startLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set layout for start content
        startContent.setLayout(new BorderLayout());

        // Configure start frame
        startFrame.setSize(400, 800);
        startFrame.setLocationRelativeTo(null);
        startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startFrame.setVisible(true);

        // Buttons for login and sign up
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel for start screen
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.PAGE_AXIS));
        startPanel.add(startLabel1, BorderLayout.CENTER);
        startPanel.add(programName, BorderLayout.CENTER);
        startPanel.add(startLabel2, BorderLayout.CENTER);
        startPanel.add(loginButton, BorderLayout.CENTER);
        startPanel.add(startLabel3, BorderLayout.CENTER);
        startPanel.add(signUpButton, BorderLayout.CENTER);

        // Add start panel to start content
        startContent.add(startPanel, BorderLayout.CENTER);

        // Login frame configuration
        JFrame loginFrame = new JFrame();
        loginFrame.setTitle("Faceless Book");

        // Content pane for login frame
        Container loginContent = loginFrame.getContentPane();
        loginContent.setLayout(new BorderLayout());

        // Configure login frame
        loginFrame.setSize(400, 800);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Buttons for login frame
        JButton loginEnter = new JButton("Enter");
        loginEnter.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        loginEnter.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backLogin = new JButton("Back");
        backLogin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        backLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLogin = new JLabel("Log In");
        titleLogin.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        titleLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Labels and fields for login
        JLabel loginLabel1 = new JLabel("Enter your Username:");
        loginLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        loginLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel loginLabel2 = new JLabel(" ");
        loginLabel2.setFont(new Font("Times New Roman", Font.PLAIN, 100));
        loginLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel loginLabel3 = new JLabel("Enter your Password:");
        loginLabel3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        loginLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel loginLabel4 = new JLabel(" ");
        loginLabel4.setFont(new Font("Times New Roman", Font.PLAIN, 100));
        loginLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel loginLabel5 = new JLabel(" ");
        loginLabel5.setFont(new Font("Times New Roman", Font.PLAIN, 100));
        loginLabel5.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel loginLabel6 = new JLabel(" ");
        loginLabel6.setFont(new Font("Times New Roman", Font.PLAIN, 100));
        loginLabel6.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel loginLabel7 = new JLabel(" ");
        loginLabel7.setFont(new Font("Times New Roman", Font.PLAIN, 100));
        loginLabel7.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel loginLabel8 = new JLabel(" ");
        loginLabel8.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        loginLabel8.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField userLogin = new JTextField(10);
        userLogin.setPreferredSize(new Dimension(100, 30));
        userLogin.setMaximumSize(userLogin.getPreferredSize());

        JTextField passLogin = new JTextField(10);
        passLogin.setPreferredSize(new Dimension(100, 30));
        passLogin.setMaximumSize(userLogin.getPreferredSize());

        // Panel for login screen
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
        loginPanel.add(loginLabel7, BorderLayout.CENTER);
        loginPanel.add(titleLogin, BorderLayout.CENTER);
        loginPanel.add(loginLabel4, BorderLayout.CENTER);
        loginPanel.add(loginLabel1, BorderLayout.CENTER);
        loginPanel.add(userLogin, BorderLayout.CENTER);
        loginPanel.add(loginLabel2, BorderLayout.CENTER);
        loginPanel.add(loginLabel3, BorderLayout.CENTER);
        loginPanel.add(passLogin, BorderLayout.CENTER);
        loginPanel.add(loginLabel5, BorderLayout.CENTER);
        loginPanel.add(loginEnter, BorderLayout.CENTER);
        loginPanel.add(loginLabel8, BorderLayout.CENTER);
        loginPanel.add(backLogin, BorderLayout.CENTER);
        loginPanel.add(loginLabel6, BorderLayout.CENTER);

        // Add login panel to login frame
        loginFrame.add(loginPanel);

        // Sign up frame configuration
        JFrame signUpFrame = new JFrame();
        signUpFrame.setTitle("Faceless Book");

        // Content pane for sign up frame
        Container signUpContent = signUpFrame.getContentPane();
        signUpContent.setLayout(new BorderLayout());

        // Configure sign up frame
        signUpFrame.setSize(400, 800);
        signUpFrame.setLocationRelativeTo(null);
        signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Buttons for sign up frame
        JButton signUpEnter = new JButton("Enter");
        signUpEnter.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        signUpEnter.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backSign = new JButton("Back");
        backSign.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        backSign.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel signLabel = new JLabel(" ");
        signLabel.setFont(new Font("Times New Roman", Font.PLAIN, 100));
        signLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel signTitle = new JLabel("Sign Up");
        signTitle.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        signTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel signLabel1 = new JLabel(" ");
        signLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 100));
        signLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameSign = new JLabel("Enter your Name:");
        nameSign.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        nameSign.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel signLabel2 = new JLabel(" ");
        signLabel2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        signLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailSign = new JLabel("Enter your Email:");
        emailSign.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        emailSign.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel signLabel3 = new JLabel(" ");
        signLabel3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        signLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userSign = new JLabel("Enter your username:");
        userSign.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        userSign.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel signLabel4 = new JLabel(" ");
        signLabel4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        signLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel passSign = new JLabel("Enter your password:");
        passSign.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        passSign.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel signLabel5 = new JLabel(" ");
        signLabel5.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        signLabel5.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel signLabel6 = new JLabel(" ");
        signLabel6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        signLabel6.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nameText = new JTextField(10);
        nameText.setPreferredSize(new Dimension(100, 30));
        nameText.setMaximumSize(nameText.getPreferredSize());
        nameText.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField emailText = new JTextField(10);
        emailText.setPreferredSize(new Dimension(100, 30));
        emailText.setMaximumSize(emailText.getPreferredSize());
        emailText.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField userText = new JTextField(10);
        userText.setPreferredSize(new Dimension(100, 30));
        userText.setMaximumSize(userText.getPreferredSize());
        userText.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField passText = new JTextField(10);
        passText.setPreferredSize(new Dimension(100, 30));
        passText.setMaximumSize(passText.getPreferredSize());
        passText.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel for sign up screen
        JPanel signPanel = new JPanel();
        signPanel.setLayout(new BoxLayout(signPanel, BoxLayout.PAGE_AXIS));
        signPanel.add(signLabel, BorderLayout.CENTER);
        signPanel.add(signTitle, BorderLayout.CENTER);
        signPanel.add(signLabel1, BorderLayout.CENTER);
        signPanel.add(nameSign, BorderLayout.CENTER);
        signPanel.add(nameText, BorderLayout.CENTER);
        signPanel.add(signLabel2, BorderLayout.CENTER);
        signPanel.add(emailSign, BorderLayout.CENTER);
        signPanel.add(emailText, BorderLayout.CENTER);
        signPanel.add(signLabel3, BorderLayout.CENTER);
        signPanel.add(userSign, BorderLayout.CENTER);
        signPanel.add(userText, BorderLayout.CENTER);
        signPanel.add(signLabel4, BorderLayout.CENTER);
        signPanel.add(passSign, BorderLayout.CENTER);
        signPanel.add(passText, BorderLayout.CENTER);
        signPanel.add(signLabel5, BorderLayout.CENTER);
        signPanel.add(signUpEnter, BorderLayout.CENTER);
        signPanel.add(signLabel6, BorderLayout.CENTER);
        signPanel.add(backSign, BorderLayout.CENTER);

        // Add sign up panel to sign up frame
        signUpFrame.add(signPanel);

        // ActionListener for buttons
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == loginButton) {
                    startFrame.setVisible(false);
                    loginFrame.setVisible(true);
                } else if (e.getSource() == signUpButton) {
                    startFrame.setVisible(false);
                    signUpFrame.setVisible(true);
                } else if (e.getSource() == loginEnter) {
                    String username = userLogin.getText();
                    String password = passLogin.getText();
                    if (client.login(username, password)) {
                        loginFrame.setVisible(false);
                        userLogin.setText("");
                        passLogin.setText("");
                        // Display the main menu after successful login
                        displayMainMenu();
                    } else {
                        JOptionPane.showMessageDialog(null, "Username or Password was Incorrect",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (e.getSource() == signUpEnter) {
                    String username = userText.getText();
                    String password = passText.getText();
                    String name = nameText.getText();
                    String email = emailText.getText();
                    if (password.length() > 7) {
                        if (client.createUser(name, username, email, password)) {
                            signUpFrame.setVisible(false);
                            userText.setText("");
                            passText.setText("");
                            nameText.setText("");
                            emailText.setText("");
                            // Display the main menu after successful sign up
                            displayMainMenu();
                        } else {
                            JOptionPane.showMessageDialog(null, "Username was already taken",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Password must be at least 8 characters",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (e.getSource() == backLogin) {
                    loginFrame.setVisible(false);
                    startFrame.setVisible(true);
                } else if (e.getSource() == backSign) {
                    signUpFrame.setVisible(false);
                    startFrame.setVisible(true);
                }
            }
        };

        // Add action listener to buttons
        loginButton.addActionListener(actionListener);
        signUpButton.addActionListener(actionListener);
        loginEnter.addActionListener(actionListener);
        signUpEnter.addActionListener(actionListener);
        backLogin.addActionListener(actionListener);
        backSign.addActionListener(actionListener);
    }

    // Method to display the main menu
    private void displayMainMenu() {
        JFrame mainMenuFrame = new JFrame();
        mainMenuFrame.setTitle("Main Menu");

        // Content pane for the main menu frame
        Container mainMenuContent = mainMenuFrame.getContentPane();

        // Configure main menu frame
        mainMenuFrame.setSize(400, 800);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Components for the main menu
        JTextField messageField = new JTextField();
        JTextField recipientField = new JTextField();
        JButton sendButton = new JButton("Send");
        JButton editProfileButton = new JButton("Edit Profile");
        JComboBox<String> groupChatDropdown = new JComboBox<>();
        JButton viewGroupChatButton = new JButton("View Group Chat");
        JTextField userSearchField = new JTextField();
        JButton searchUserButton = new JButton("Search User");
        JButton logOutButton = new JButton("Log Out");

        // Panel for the main menu
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridLayout(4, 2));
        mainMenuPanel.add(new JLabel("Message Text Field:"));
        mainMenuPanel.add(messageField);
        mainMenuPanel.add(new JLabel("Recipient Text Field:"));
        mainMenuPanel.add(recipientField);
        mainMenuPanel.add(sendButton);
        mainMenuPanel.add(editProfileButton);
        mainMenuPanel.add(groupChatDropdown);
        mainMenuPanel.add(viewGroupChatButton);
        mainMenuPanel.add(userSearchField);
        mainMenuPanel.add(searchUserButton);
        mainMenuPanel.add(logOutButton);

        // Add main menu panel to main menu content
        mainMenuContent.add(mainMenuPanel);

        // Display the main menu frame
        mainMenuFrame.setVisible(true);
    }
}

