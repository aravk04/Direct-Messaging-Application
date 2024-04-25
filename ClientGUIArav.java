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
