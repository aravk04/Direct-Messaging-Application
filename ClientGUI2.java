import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI2 extends JFrame implements Runnable {
    private Client client;
    private JTextField messageField, recipientField, userSearchField;
    private JTextArea messageArea;
    private JButton sendButton, editProfileButton, viewGroupChatButton, searchUserButton, logoutButton;
    private JComboBox<String> groupChatDropdown;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new ClientGUI2());
    }

    public ClientGUI2() {
        setTitle("Chat Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainMenuPanel = createMainMenuPanel();
        getContentPane().add(mainMenuPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true); // Make the frame visible
    }

    private JPanel createMainMenuPanel() {
        JPanel mainMenuPanel = new JPanel(new BorderLayout());
        mainMenuPanel.setPreferredSize(new Dimension(400, 800));

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 3));
        messageField = new JTextField("Enter message here");
        recipientField = new JTextField("Enter recipient here");
        userSearchField = new JTextField("Search user");
        sendButton = new JButton("Send");
        editProfileButton = new JButton("Edit Profile");
        groupChatDropdown = new JComboBox<>();
        viewGroupChatButton = new JButton("View Group Chat");
        searchUserButton = new JButton("Search User");
        logoutButton = new JButton("Log Out");

        inputPanel.add(messageField);
        inputPanel.add(recipientField);
        inputPanel.add(sendButton);
        inputPanel.add(editProfileButton);
        inputPanel.add(groupChatDropdown);
        inputPanel.add(viewGroupChatButton);
        inputPanel.add(userSearchField);
        inputPanel.add(searchUserButton);
        inputPanel.add(logoutButton);

        // Message Area
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.add(editProfileButton);
        bottomPanel.add(logoutButton);

        // Add components to the main panel
        mainMenuPanel.add(inputPanel, BorderLayout.NORTH);
        mainMenuPanel.add(scrollPane, BorderLayout.CENTER);
        mainMenuPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Set up fonts and styles
        Font defaultFont = new Font("Times New Roman", Font.PLAIN, 18);
        sendButton.setFont(defaultFont);
        editProfileButton.setFont(defaultFont);
        viewGroupChatButton.setFont(defaultFont);
        searchUserButton.setFont(defaultFont);
        logoutButton.setFont(defaultFont);

        // Set up event handlers
        sendButton.addActionListener(new SendMessageListener());
        editProfileButton.addActionListener(new EditProfileListener());
        viewGroupChatButton.addActionListener(new ViewGroupChatListener());
        searchUserButton.addActionListener(new SearchUserListener());
        logoutButton.addActionListener(new LogoutListener());

        return mainMenuPanel;
    }

    private class SendMessageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = messageField.getText();
            String recipient = recipientField.getText();

            if (message.isEmpty() || recipient.isEmpty()) {
                JOptionPane.showMessageDialog(ClientGUI2.this, "Please enter a message and recipient.");
            } else {
                // Send message logic here
                messageArea.append("Message sent to " + recipient + ": " + message + "\n");
                messageField.setText("");
                recipientField.setText("");
            }
        }
    }

    private class EditProfileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Open the Edit Profile screen
            JOptionPane.showMessageDialog(ClientGUI2.this, "Edit Profile screen not implemented yet.");
        }
    }

    private class ViewGroupChatListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedGroupChat = (String) groupChatDropdown.getSelectedItem();
            if (!selectedGroupChat.isEmpty()) {
                // View group chat logic here
                JOptionPane.showMessageDialog(ClientGUI2.this, "Viewing group chat: " + selectedGroupChat);
            } else {
                JOptionPane.showMessageDialog(ClientGUI2.this, "Please select a group chat.");
            }
        }
    }

    private class SearchUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchQuery = userSearchField.getText();
            if (!searchQuery.isEmpty()) {
                // Search user logic here
                JOptionPane.showMessageDialog(ClientGUI2.this, "Searching for user: " + searchQuery);
            } else {
                JOptionPane.showMessageDialog(ClientGUI2.this, "Please enter a search query.");
            }
        }
    }

    private class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(ClientGUI2.this, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                messageArea.setText("");
                messageField.setText("");
                recipientField.setText("");
                userSearchField.setText("");
                groupChatDropdown.removeAllItems();
                JOptionPane.showMessageDialog(ClientGUI2.this, "Logged out successfully.");
                startFrame.setVisible(true);
                dispose();
            }
        }
    }

    @Override
    public void run() {
        client = new Client();
        // Additional client logic if needed
    }
}
