import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ClientGUI extends JFrame implements Runnable {
    private Client client;
    private String user;
    private ArrayList<String> chats;
    private JComboBox groupChatDropdown;
    private ArrayList<String> chatLog;
    private JScrollPane chatsScrollPane;

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

        JFrame mainMenuFrame = new JFrame();
        mainMenuFrame.setSize(400, 800);
        mainMenuFrame.setMaximumSize(new Dimension(400, 800));
        Container mainContent = mainMenuFrame.getContentPane();

        JPanel mainMenuPanel = new JPanel(new BorderLayout());

        // Input Panel
        JPanel inputPanel1 = new JPanel();
        inputPanel1.setLayout(new BoxLayout(inputPanel1, BoxLayout.X_AXIS));
        JPanel inputPanel2 = new JPanel(new GridLayout(1, 2));
        JTextField messageField = new JTextField("Enter message here");
        JTextField recipientField = new JTextField("Enter recipient(s) like: a,b,c");
        JButton sendButton = new JButton("Send");
        JButton editProfileButton = new JButton("Edit Profile");
        groupChatDropdown = new JComboBox<>();
        JButton viewGroupChatButton = new JButton("View Group Chat");
        JButton logoutButton = new JButton("Log Out");

        sendButton.setMaximumSize(new Dimension(400, 20));
        sendButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        inputPanel1.add(messageField);
        inputPanel1.add(recipientField);
        inputPanel1.setMaximumSize(new Dimension(400, 30));
        inputPanel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel2.add(groupChatDropdown);
        inputPanel2.add(viewGroupChatButton);
        inputPanel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel2.setMaximumSize(new Dimension(400, 30));

        // Message Area
        JTextArea messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(editProfileButton);
        topPanel.add(Box.createHorizontalStrut(175));
        topPanel.add(logoutButton);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.setMaximumSize(new Dimension(400, 30));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel1, BorderLayout.CENTER);
        mainPanel.add(sendButton, BorderLayout.CENTER);
        mainPanel.add(chatsScrollPane, BorderLayout.CENTER);

        // Add components to the main panel
        mainMenuPanel.add(mainPanel, BorderLayout.CENTER);

        // Set up fonts and styles
        Font defaultFont = new Font("Times New Roman", Font.PLAIN, 18);
        sendButton.setFont(defaultFont);
        editProfileButton.setFont(defaultFont);
        viewGroupChatButton.setFont(defaultFont);
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
                    recipientField.setText("Enter recipient(s) like: a,b,c");
                }
            }


        };

        messageField.addMouseListener(mouseListener);
        recipientField.addMouseListener(mouseListener);
        editProfileButton.addMouseListener(mouseListener);
        mainMenuFrame.addMouseListener(mouseListener);
        //groupChatDropdown.addMouseListener(mouseListener);
        messageArea.addMouseListener(mouseListener);
        logoutButton.addMouseListener(mouseListener);

        JPanel chatPanel = new JPanel();

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
                        //groupChatDropdown = new JComboBox<>(chats.toArray());
                        for (String chat : chats) {
                            JPanel messagePanel = new JPanel();
                            messagePanel.setPreferredSize(new Dimension(300, 200));
                            JButton chatButton = new JButton(chat);
                            chatButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
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
                            //groupChatDropdown = new JComboBox<>(chats.toArray());
                            for (String chat : chats) {
                                JPanel messagePanel = new JPanel();
                                JButton chatButton = new JButton(chat);
                                chatButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
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
                } else if (e.getSource() == logoutButton) {
                    startFrame.setVisible(true);
                    mainMenuFrame.setVisible(false);
                } else if (e.getSource() == sendButton) {
                    String message = messageField.getText();
                    String recipients = recipientField.getText();

                    if (client.message(user, recipients, message)) {
                        messageArea.append("Successfully sent: " + message + " to " + recipients + "\n");
                    } else {
                        JOptionPane.showMessageDialog(null, "One of the recipients does not exist" +
                                " or has you blocked. Make sure you enter your Recipients like: a,b,c.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else if (e.getSource() == viewGroupChatButton) {
                    String chat = (String) groupChatDropdown.getSelectedItem();
                    chatLog = client.viewChatLog(chat);
                    mainMenuFrame.setVisible(false);
                    messageField.setText("Enter message here");
                    recipientField.setText("Enter recipient(s) like: a,b,c");
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
        viewGroupChatButton.addActionListener(actionListener);
        editProfileButton.addActionListener(actionListener);
        sendButton.addActionListener(actionListener);
    }

    private void displayChatWindow(String chat, boolean inChat) {
        chatLog = client.viewChatLog(chat);
        JFrame chatFrame = new JFrame(chat);
        chatFrame.setSize(400, 800);
        chatFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chatFrame.setLayout(new BorderLayout());

        //JTextArea messageArea = new JTextArea();
        //messageArea.setEditable(false);
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(messagePanel);
        chatFrame.add(scrollPane, BorderLayout.CENTER);


        for (int i = 0; i < chatLog.size(); i++) {
            String message = chatLog.get(i);
            JPanel messageContainer = new JPanel(new BorderLayout());
            JLabel messageLabel = new JLabel(message);
            messageLabel.setPreferredSize(new Dimension(300, 50));
            messageContainer.add(messageLabel, BorderLayout.WEST);
            messageLabel.setName(Integer.toString(i + 1));

            JButton deleteButton = new JButton("Delete");
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
            messagePanel.add(messageContainer);
            messagePanel.add(Box.createVerticalStrut(5));

            // JPanel messageButtonPanel = new JPanel(new BorderLayout());
            // messageButtonPanel.add(messageButton, BorderLayout.CENTER);
            // messageButtonPanel.add(deleteButton, BorderLayout.EAST);
            // messagePanel.add(messageButtonPanel);
            // messagePanel.add(Box.createVerticalStrut(5));

            //messageArea.append(message + "\n");
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
                if (inChat) {
                    // send messageInChat function from server
                    client.message(chat, message);
                }
                else {
                    String info = chat.replace("-", ",");
                    String[] split = info.split(",");
                    client.message(split[0], split[1], message);
                }
                chatLog = client.viewChatLog(chat);
                message = chatLog.get(chatLog.size() - 1);
                //messageArea.setText("");
                JPanel messageContainer = new JPanel(new BorderLayout());
                JLabel messageLabel = new JLabel(message);
                messageLabel.setPreferredSize(new Dimension(300, 50));
                messageLabel.setName(Integer.toString(chatLog.size()) + 1);
                messageContainer.add(messageLabel, BorderLayout.WEST);

                JButton deleteButton = new JButton("Delete");
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
                messagePanel.add(messageContainer);
                messagePanel.add(Box.createVerticalGlue());
                messagePanel.revalidate();
                messagePanel.repaint();
                messageField.setText("");
                messagePanel.repaint();
            }
        });
        bottomPanel.add(sendButton, BorderLayout.EAST);
        chatFrame.add(bottomPanel, BorderLayout.SOUTH);
        chatFrame.pack();
        chatFrame.setLocationRelativeTo(null);
        chatFrame.setVisible(true);
    }
}
