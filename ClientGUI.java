import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * ClientGUI
 * <p>
 * The GUI that implements the function
 *
 * @author Eesha Faruqi, Arav Kolli, Zonglin Jia,
 * Harshil Shah, Benjamin Ascano
 * @version April 29,th 2024
 */

public class ClientGUI extends JFrame implements Runnable {
    private Client client;
    private String user;
    private String viewing;
    private ArrayList<String> chats;
    private JTextArea messageArea = new JTextArea();
    private ArrayList<String> chatLog;
    private JScrollPane scrollPane = new JScrollPane(messageArea);
    private JScrollPane chatsScrollPane = new JScrollPane();
    private JFrame mainMenuFrame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JPanel chatPanel = new JPanel();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new ClientGUI());
    }

    public ClientGUI() {

    }

    @Override
    public void run() {
        client = new Client();
        client.run();

        JFrame startFrame = new JFrame();
        startFrame.setTitle("Welcome to Our Social Media Platform");

        Container startContent = startFrame.getContentPane();
        JLabel programName = new JLabel("Faceless Book");
        programName.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        programName.setAlignmentX(Component.CENTER_ALIGNMENT);

        startContent.setLayout(new BorderLayout());

        startFrame.setSize(400, 800);
        startFrame.setLocationRelativeTo(null);
        startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startFrame.setVisible(true);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.PAGE_AXIS));
        startPanel.add(Box.createVerticalStrut(200));
        startPanel.add(programName, BorderLayout.CENTER);
        startPanel.add(Box.createVerticalStrut(200));
        startPanel.add(loginButton, BorderLayout.CENTER);
        startPanel.add(Box.createVerticalStrut(20));
        startPanel.add(signUpButton, BorderLayout.CENTER);

        startContent.add(startPanel, BorderLayout.CENTER);

        JFrame loginFrame = new JFrame();
        loginFrame.setTitle("Faceless Book");

        Container loginContent = loginFrame.getContentPane();
        loginContent.setLayout(new BorderLayout());

        loginFrame.setSize(400, 800);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton loginEnter = new JButton("Enter");
        loginEnter.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        loginEnter.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backLogin = new JButton("Back");
        backLogin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        backLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLogin = new JLabel("Log In");
        titleLogin.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        titleLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userLabel = new JLabel("Enter your Username:");
        userLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel passLabel = new JLabel("Enter your Password:");
        passLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField userLogin = new JTextField(10);
        userLogin.setPreferredSize(new Dimension(100, 30));
        userLogin.setMaximumSize(userLogin.getPreferredSize());

        JTextField passLogin = new JTextField(10);
        passLogin.setPreferredSize(new Dimension(100, 30));
        passLogin.setMaximumSize(userLogin.getPreferredSize());

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
        loginPanel.add(Box.createVerticalStrut(100));
        loginPanel.add(titleLogin, BorderLayout.CENTER);
        loginPanel.add(Box.createVerticalStrut(100));
        loginPanel.add(userLabel, BorderLayout.CENTER);
        loginPanel.add(userLogin, BorderLayout.CENTER);
        loginPanel.add(Box.createVerticalStrut(100));
        loginPanel.add(passLabel, BorderLayout.CENTER);
        loginPanel.add(passLogin, BorderLayout.CENTER);
        loginPanel.add(Box.createVerticalStrut(100));
        loginPanel.add(loginEnter, BorderLayout.CENTER);
        loginPanel.add(Box.createVerticalStrut(20));
        loginPanel.add(backLogin, BorderLayout.CENTER);
        loginPanel.add(Box.createVerticalStrut(100));

        loginFrame.add(loginPanel);

        JFrame signUpFrame = new JFrame();
        signUpFrame.setTitle("Faceless Book");

        Container signUpContent = signUpFrame.getContentPane();
        signUpContent.setLayout(new BorderLayout());

        signUpFrame.setSize(400, 800);
        signUpFrame.setLocationRelativeTo(null);
        signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton signUpEnter = new JButton("Enter");
        signUpEnter.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        signUpEnter.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backSign = new JButton("Back");
        backSign.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        backSign.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel signTitle = new JLabel("Sign Up");
        signTitle.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        signTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameSign = new JLabel("Enter your Name:");
        nameSign.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        nameSign.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailSign = new JLabel("Enter your Email:");
        emailSign.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        emailSign.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userSign = new JLabel("Enter your username:");
        userSign.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        userSign.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel passSign = new JLabel("Enter your password:");
        passSign.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        passSign.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        JPanel signPanel = new JPanel();
        signPanel.setLayout(new BoxLayout(signPanel, BoxLayout.PAGE_AXIS));
        signPanel.add(Box.createVerticalStrut(100));
        signPanel.add(signTitle, BorderLayout.CENTER);
        signPanel.add(Box.createVerticalStrut(100));
        signPanel.add(nameSign, BorderLayout.CENTER);
        signPanel.add(nameText, BorderLayout.CENTER);
        signPanel.add(Box.createVerticalStrut(20));
        signPanel.add(emailSign, BorderLayout.CENTER);
        signPanel.add(emailText, BorderLayout.CENTER);
        signPanel.add(Box.createVerticalStrut(20));
        signPanel.add(userSign, BorderLayout.CENTER);
        signPanel.add(userText, BorderLayout.CENTER);
        signPanel.add(Box.createVerticalStrut(20));
        signPanel.add(passSign, BorderLayout.CENTER);
        signPanel.add(passText, BorderLayout.CENTER);
        signPanel.add(Box.createVerticalStrut(50));
        signPanel.add(signUpEnter, BorderLayout.CENTER);
        signPanel.add(Box.createVerticalStrut(20));
        signPanel.add(backSign, BorderLayout.CENTER);

        signUpFrame.add(signPanel);

        mainMenuFrame = new JFrame();
        mainMenuFrame.setSize(400, 800);
        mainMenuFrame.setMaximumSize(new Dimension(400, 800));
        Container mainContent = mainMenuFrame.getContentPane();

        JPanel mainMenuPanel = new JPanel(new BorderLayout());

        // Input Panel
        JPanel inputPanel1 = new JPanel();
        inputPanel1.setLayout(new BoxLayout(inputPanel1, BoxLayout.X_AXIS));
        JTextField messageField = new JTextField("Enter message here");
        JTextField recipientField = new JTextField("Enter recipient(s) like: a;b;c");
        JButton sendButton = new JButton("Send");
        JButton editProfileButton = new JButton("Edit Profile");
        JButton logoutButton = new JButton("Log Out");

        sendButton.setMaximumSize(new Dimension(400, 20));
        sendButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        inputPanel1.add(messageField);
        inputPanel1.add(recipientField);
        inputPanel1.setMaximumSize(new Dimension(400, 30));
        inputPanel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Message Area
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        scrollPane = new JScrollPane(messageArea);

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(editProfileButton);
        topPanel.add(Box.createHorizontalStrut(175));
        topPanel.add(logoutButton);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.setMaximumSize(new Dimension(400, 30));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel1, BorderLayout.CENTER);
        mainPanel.add(sendButton, BorderLayout.CENTER);

        // Add components to the main panel
        mainMenuPanel.add(mainPanel, BorderLayout.CENTER);

        // Set up fonts and styles
        Font defaultFont = new Font("Times New Roman", Font.PLAIN, 18);
        sendButton.setFont(defaultFont);
        editProfileButton.setFont(defaultFont);
        logoutButton.setFont(defaultFont);

        mainContent.add(mainMenuPanel);

        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == messageField) {
                    messageField.setText("");
                } else if (e.getSource() == recipientField) {
                    recipientField.setText("");
                } else {
                    messageField.setText("Enter message here");
                    recipientField.setText("Enter recipient(s) like: a;b;c");
                }
            }


        };

        messageField.addMouseListener(mouseListener);
        recipientField.addMouseListener(mouseListener);
        editProfileButton.addMouseListener(mouseListener);
        mainMenuFrame.addMouseListener(mouseListener);
        messageArea.addMouseListener(mouseListener);
        logoutButton.addMouseListener(mouseListener);

        JFrame editUserFrame = new JFrame();
        editUserFrame.setTitle("Faceless Book");
        editUserFrame.setVisible(false);

        Container editContent = editUserFrame.getContentPane();
        editContent.setLayout(null);

        editUserFrame.setSize(400, 800);
        editUserFrame.setLocationRelativeTo(null);
        editUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton backEditUser = new JButton("Back");
        backEditUser.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        backEditUser.setSize(100, 20);
        backEditUser.setLocation(10, 730);

        JTextArea infoEditUser = new JTextArea();
        infoEditUser.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        infoEditUser.setSize(365, 110);
        infoEditUser.setLocation(10, 10);
        infoEditUser.setLineWrap(true);
        infoEditUser.setEditable(false);
        //infoEditUser.setText("Current Info\nUsername: username\nName: name\nPassword: password\nEmail: email");


        JLabel nameEditUser = new JLabel("Enter New Name:");
        nameEditUser.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        nameEditUser.setSize(200, 20);
        nameEditUser.setLocation(55, 160);

        JTextField tnameEditUser = new JTextField();
        tnameEditUser.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        tnameEditUser.setSize(120, 20);
        tnameEditUser.setLocation(210, 160);

        JLabel passwordEditUser = new JLabel("Enter New Password:");
        passwordEditUser.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        passwordEditUser.setSize(200, 20);
        passwordEditUser.setLocation(40, 220);

        JTextField tpasswordEditUser = new JTextField();
        tpasswordEditUser.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        tpasswordEditUser.setSize(120, 20);
        tpasswordEditUser.setLocation(210, 220);

        JLabel emailEditUser = new JLabel("Enter New Email:");
        emailEditUser.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        emailEditUser.setSize(200, 20);
        emailEditUser.setLocation(55, 280);

        JTextField temailEditUser = new JTextField();
        temailEditUser.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        temailEditUser.setSize(120, 20);
        temailEditUser.setLocation(210, 280);

        JButton saveEditUser = new JButton("Save");
        saveEditUser.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        saveEditUser.setSize(100, 20);
        saveEditUser.setLocation(145, 340);

        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        searchField.setSize(200, 20);
        searchField.setLocation(20, 400);

        JButton search = new JButton("Search User");
        search.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        search.setSize(100, 20);
        search.setLocation(240, 400);

        editContent.add(backEditUser);
        editContent.add(infoEditUser);
        editContent.add(nameEditUser);
        editContent.add(tnameEditUser);
        editContent.add(passwordEditUser);
        editContent.add(tpasswordEditUser);
        editContent.add(emailEditUser);
        editContent.add(temailEditUser);
        editContent.add(saveEditUser);
        editContent.add(searchField);
        editContent.add(search);


        JFrame viewUserFrame = new JFrame();
        viewUserFrame.setTitle("Faceless Book");
        viewUserFrame.setVisible(false);

        Container viewContent = viewUserFrame.getContentPane();
        viewContent.setLayout(null);

        viewUserFrame.setSize(400, 800);
        viewUserFrame.setLocationRelativeTo(null);
        viewUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton backViewUser = new JButton("Back");
        backViewUser.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        backViewUser.setSize(100, 20);
        backViewUser.setLocation(10, 730);

        JTextArea infoViewUser = new JTextArea();
        infoViewUser.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        infoViewUser.setSize(365, 110);
        infoViewUser.setLocation(10, 10);
        infoViewUser.setLineWrap(true);
        infoViewUser.setEditable(false);
        infoViewUser.setText("");

        JButton block = new JButton("Block");
        block.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        block.setSize(100, 20);
        block.setLocation(200, 200);
        block.setVisible(false);

        JButton unblock = new JButton("Unblock");
        unblock.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        unblock.setSize(100, 20);
        unblock.setLocation(200, 200);
        unblock.setVisible(false);

        JButton add = new JButton("Add");
        add.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        add.setSize(100, 20);
        add.setLocation(90, 200);
        add.setVisible(false);

        JButton unAdd = new JButton("Unfriend");
        unAdd.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        unAdd.setSize(100, 20);
        unAdd.setLocation(90, 200);
        unAdd.setVisible(false);

        viewContent.add(add);
        viewContent.add(unAdd);
        viewContent.add(block);
        viewContent.add(unblock);
        viewContent.add(backViewUser);
        viewContent.add(infoViewUser);


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
                        mainMenuFrame.setVisible(true);
                        user = username;
                        chats = client.viewChats(user);

                        System.out.println("user = " + user);

                        mainPanel.remove(chatsScrollPane);
                        System.out.println("chatsScrollPane is removed");

                        chatsScrollPane = refresh();
                        mainPanel.add(chatsScrollPane, BorderLayout.CENTER);
                        mainPanel.repaint();
                        mainPanel.revalidate();

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
                            mainMenuFrame.setVisible(true);
                            user = username;
                            chats = client.viewChats(user);
                            mainPanel.remove(chatsScrollPane);
                            System.out.println("chatsScrollPane is removed");

                            chatsScrollPane = refresh();
                            mainPanel.add(chatsScrollPane, BorderLayout.CENTER);
                            mainPanel.repaint();
                            mainPanel.revalidate();

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
                } else if (e.getSource() == editProfileButton) {
                    mainMenuFrame.setVisible(false);
                    editUserFrame.setVisible(true);
                    String[] strings = client.viewUser(user, true).split(";");
                    infoEditUser.selectAll();
                    infoEditUser.replaceSelection("");

                    infoEditUser.append("Current Info\nUsername: " + strings[2] + "\nName: " + strings[0] +
                            "\nPassword: " + strings[3] + "\nEmail: " + strings[1]);
                    infoEditUser.revalidate();
                    infoEditUser.repaint();

                } else if (e.getSource() == logoutButton) {
                    startFrame.setVisible(true);
                    mainMenuFrame.setVisible(false);
                    messageArea.selectAll();
                    messageArea.replaceSelection("");
                    infoEditUser.selectAll();
                    infoEditUser.replaceSelection("");
                    infoViewUser.selectAll();
                    infoViewUser.replaceSelection("");

                } else if (e.getSource() == sendButton) {
                    String message = messageField.getText();
                    String recipients = recipientField.getText();
                    if (!message.equals("")) {
                        if (client.message(user, recipients, message)) {
                            messageArea.append("Successfully sent: " + message + " to " + recipients + "\n");

                            String chat = user + ";" + recipients;
                            String[] split = chat.split(";");

                            chat = "";

                            for (int i = 0; i < split.length; i++) {
                                chat = chat + split[i] + "-";
                            }

                            chat = chat.substring(0, chat.length() - 1);

                            mainMenuFrame.setVisible(false);
                            displayChatWindow(chat, true);

                            chats = client.viewChats(user);

                            messageField.setText("Enter message here");
                            recipientField.setText("Enter recipient(s) like: a;b;c");
                        } else {
                            JOptionPane.showMessageDialog(null, "One of the recipients does not exist" +
                                            " or has you blocked. Make sure you enter your Recipients like: a;b;c.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            messageArea.append("Could not send message!\n");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a message", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else if (e.getSource() == backEditUser) {
                    editUserFrame.setVisible(false);
                    mainMenuFrame.setVisible(true);
                } else if (e.getSource() == saveEditUser) {
                    String name = tnameEditUser.getText();
                    String password = tpasswordEditUser.getText();
                    String email = temailEditUser.getText();
                    if (password.length() <= 7) {
                        JOptionPane.showMessageDialog(null, "Password must be at least 8 characters",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {

                        String[] strings = client.viewUser(user, true).split(";");
                        if (name.equals("")) {
                            name = strings[0];
                        }
                        if (email.equals("")) {
                            email = strings[1];
                        }

                        client.editProfile(user, name, password, email);
                        JOptionPane.showMessageDialog(null, "New Information has been Saved!",
                                "Saved", JOptionPane.INFORMATION_MESSAGE);
                        strings = client.viewUser(user, true).split(";");
                        infoEditUser.selectAll();
                        infoEditUser.replaceSelection("");

                        infoEditUser.append("Current Info\nUsername: " + strings[2] + "\nName: " + strings[0] +
                                "\nPassword: " + strings[3] + "\nEmail: " + strings[1]);
                        infoEditUser.revalidate();
                        infoEditUser.repaint();
                    }

                } else if (e.getSource() == search) {
                    String username = searchField.getText();
                    if (!client.viewUser(username, false).equals("Failure")) {
                        editUserFrame.setVisible(false);
                        viewUserFrame.setVisible(true);
                        infoViewUser.selectAll();
                        infoViewUser.replaceSelection("");
                        String[] strings = client.viewUser(username, false).split(";");
                        infoViewUser.append("Current Info\nUsername: " + strings[2] + "\nName: " + strings[0]
                                + "\nEmail: " + strings[1]);
                        System.out.println(client.getFriends(user));

                        if (client.getFriends(user).contains(username)) {
                            unAdd.setVisible(true);
                            System.out.println("unadd");
                        } else {
                            add.setVisible(true);
                            System.out.println("add");
                        }

                        if (client.getBlocked(user).contains(username)) {
                            unblock.setVisible(true);
                            add.setVisible(false);
                            System.out.println("unblock");
                        } else {
                            block.setVisible(true);
                            System.out.println("block");
                        }

                        viewing = username;

                    } else {
                        JOptionPane.showMessageDialog(null, "User does not exist",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if ((e.getSource() == add) || (e.getSource() == unAdd)) {
                    boolean friending = false;

                    if (e.getSource() == add) {
                        friending = true;
                    }
                    client.editFriends(friending, viewing, user);

                    if (friending) {
                        unAdd.setVisible(true);
                        add.setVisible(false);
                    } else {
                        unAdd.setVisible(false);
                        add.setVisible(true);
                    }
                } else if ((e.getSource() == block) || (e.getSource() == unblock)) {
                    boolean blocking = false;

                    if (e.getSource() == block) {
                        blocking = true;
                    }
                    client.editBlocked(blocking, viewing, user);

                    if (blocking) {
                        unblock.setVisible(true);
                        block.setVisible(false);
                        add.setVisible(false);
                        unAdd.setVisible(false);
                    } else {
                        unblock.setVisible(false);
                        block.setVisible(true);
                        add.setVisible(true);
                    }
                } else if (e.getSource() == backViewUser) {
                    editUserFrame.setVisible(true);
                    viewUserFrame.setVisible(false);
                }
            }
        };

        loginButton.addActionListener(actionListener);
        signUpButton.addActionListener(actionListener);
        loginEnter.addActionListener(actionListener);
        signUpEnter.addActionListener(actionListener);
        backLogin.addActionListener(actionListener);
        backSign.addActionListener(actionListener);
        logoutButton.addActionListener(actionListener);
        editProfileButton.addActionListener(actionListener);
        sendButton.addActionListener(actionListener);
        backEditUser.addActionListener(actionListener);
        saveEditUser.addActionListener(actionListener);
        search.addActionListener(actionListener);
        add.addActionListener(actionListener);
        unAdd.addActionListener(actionListener);
        block.addActionListener(actionListener);
        unblock.addActionListener(actionListener);
        backViewUser.addActionListener(actionListener);
    }

    private void displayChatWindow(String chat, boolean inChat) {
        System.out.println("chat in displaychatWindow " + chat);
        chatLog = client.viewChatLog(chat);
        JFrame chatFrame = new JFrame(chat);
        chatFrame.setSize(400, 800);
        chatFrame.setMinimumSize(new Dimension(400, 800));
        chatFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chatFrame.setLayout(new BorderLayout());

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(messagePanel);
        chatFrame.add(scrollPane, BorderLayout.CENTER);


        for (int i = 0; i < chatLog.size() - 1; i++) {
            String message = chatLog.get(i);
            JPanel messageContainer = new JPanel(new BorderLayout());
            JLabel messageLabel = new JLabel(message);

            messageLabel.setPreferredSize(new Dimension(300, 50));

            messageContainer.add(messageLabel, BorderLayout.WEST);
            messageLabel.setName(Integer.toString(i + 1));
            System.out.println("i = " + i);
            System.out.println("message = " + message);
            if (message.substring(0, user.length()).equals(user)) {
                JButton deleteButton = new JButton("Delete");
                deleteButton.setMaximumSize(new Dimension(50, 30));
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int buttonIndex = Integer.parseInt(messageLabel.getName());
                        System.out.println("Deleting in backend...");
                        client.deleteMessage(chat, buttonIndex);
                        // remove the message from the chat
                        System.out.println("Removing from the chat");
                        messagePanel.remove(messageContainer);
                        messagePanel.revalidate();
                        messagePanel.repaint();
                    }
                });
                messageContainer.add(deleteButton, BorderLayout.EAST);
            }
            messagePanel.add(messageContainer);
            messagePanel.add(Box.createVerticalStrut(5));

        }
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JTextField messageField = new JTextField();
        bottomPanel.add(messageField, BorderLayout.CENTER);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                message = message.replace("\n", "");

                if (!message.equals("")) {
                    if (inChat) {
                        // send messageInChat function from server
                        System.out.println("chat in GUI: \n" + chat);
                        if (!client.message1(chat, user, message)) { //msv
                            JOptionPane.showMessageDialog(null, "One of the recipients does not exist" +
                                    " or has you blocked.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        String info = chat.replace("-", ",");
                        String[] split = info.split(",");
                        client.message(split[0], split[1], message);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a message", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                chatLog = client.viewChatLog(chat);
                JPanel messageContainer = new JPanel(new BorderLayout());
                JLabel messageLabel = new JLabel(message);
                messageLabel.setPreferredSize(new Dimension(300, 50));

                messageLabel.setName(Integer.toString(chatLog.size()) + 1);
                messageContainer.add(messageLabel, BorderLayout.WEST);

                JButton deleteButton = new JButton("Delete");
                deleteButton.setMaximumSize(new Dimension(50, 30));
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int buttonIndex = Integer.parseInt(messageLabel.getName());
                        System.out.println("Deleting in backend...");
                        client.deleteMessage(chat, buttonIndex);
                        // remove the message from the chat
                        System.out.println("Removing from the chat");
                        messagePanel.remove(messageContainer);
                        messagePanel.revalidate();
                        messagePanel.repaint();
                    }
                });

                messageContainer.add(deleteButton, BorderLayout.EAST);
                messagePanel.repaint();
                messageField.setText("");
                messagePanel.repaint();
                displayChatWindow(chat, inChat);
                chatFrame.setVisible(false);
            }
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatFrame.setVisible(false);
                mainPanel.remove(chatsScrollPane);
                chatsScrollPane = refresh();
                mainPanel.add(chatsScrollPane, BorderLayout.CENTER);
                mainPanel.repaint();
                mainPanel.revalidate();
                mainMenuFrame.setVisible(true);
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(Box.createHorizontalStrut(380), BorderLayout.EAST);

        bottomPanel.add(sendButton, BorderLayout.EAST);
        chatFrame.add(topPanel, BorderLayout.NORTH);
        chatFrame.add(bottomPanel, BorderLayout.SOUTH);
        chatFrame.pack();
        chatFrame.setLocationRelativeTo(null);
        chatFrame.setVisible(true);


    }

    public JScrollPane refresh() {
        chatPanel = new JPanel();
        for (String chat : chats) {
            System.out.println("chat = " + chat);
            JPanel messagePanel = new JPanel();
            JButton chatButton = new JButton(chat);
            chatButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainMenuFrame.setVisible(false);
                    displayChatWindow(chat, true);
                    messagePanel.repaint();
                }
            });
            chatPanel.add(chatButton);
            chatPanel.add(Box.createVerticalStrut(10));
            chatPanel.setVisible(true);
        }
        chatsScrollPane = new JScrollPane(chatPanel);
        chatsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        chatsScrollPane.setMaximumSize(new Dimension(400, 30));
        System.out.println("chatsScrollPane is added");
        return chatsScrollPane;
    }
}