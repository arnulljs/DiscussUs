package dicussed;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ForumApp {
    private JFrame mainFrame;
    private JTextArea displayArea;
    private List<Forum> forums; // List to store multiple forums
    private Forum activeForum; // Currently selected forum
    private User loggedInUser;
    private List<User> users; // List to store User objects

    // Panels for login and sign-up
    private JPanel loginPanel, signUpPanel;
    private JTextField emailField, nameField, studentIdField, forumTitleField;
    private JPasswordField passwordField, confirmPasswordField;
    private JLabel messageLabel;
    private JButton loginButton, signUpButton, switchToSignUpButton, switchToLoginButton;

    public ForumApp() {
        forums = new ArrayList<>(); // Initialize the forums list
        users = new ArrayList<>();  // Initialize the users list
        initialize();
    }

    private void initialize() {
        mainFrame = new JFrame("Forum Application");
        mainFrame.setSize(600, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        // Initialize the login and sign-up panels
        initLoginPanel();
        initSignUpPanel();

        // Default user (for demonstration purposes)
        User defaultUser = new User("U001", "Alice", "alice@example.com", "password123");
        users.add(defaultUser); // Add the default user to the list

        // Set up initial view to show login screen
        mainFrame.add(loginPanel);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    // Initialize the login panel
    private void initLoginPanel() {
        loginPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");
        switchToSignUpButton = new JButton("Sign Up");

        loginButton.addActionListener(this::handleLogin);
        switchToSignUpButton.addActionListener(e -> switchToPanel(signUpPanel));

        loginPanel.add(new JLabel("Email:"));
        loginPanel.add(emailField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // Empty cell
        loginPanel.add(loginButton);
        loginPanel.add(new JLabel("Don't have an account?"));
        loginPanel.add(switchToSignUpButton);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        loginPanel.add(messageLabel); // Message for login result
    }

    // Initialize the sign-up panel
    private void initSignUpPanel() {
        signUpPanel = new JPanel(new GridLayout(7, 2, 10, 10));

        studentIdField = new JTextField(20);
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);

        signUpButton = new JButton("Sign Up");
        switchToLoginButton = new JButton("Back to Login");

        signUpButton.addActionListener(this::handleSignUp);
        switchToLoginButton.addActionListener(e -> switchToPanel(loginPanel));

        signUpPanel.add(new JLabel("Student ID:"));
        signUpPanel.add(studentIdField);
        signUpPanel.add(new JLabel("Name:"));
        signUpPanel.add(nameField);
        signUpPanel.add(new JLabel("Email:"));
        signUpPanel.add(emailField);
        signUpPanel.add(new JLabel("Password:"));
        signUpPanel.add(passwordField);
        signUpPanel.add(new JLabel("Confirm Password:"));
        signUpPanel.add(confirmPasswordField);
        signUpPanel.add(switchToLoginButton);
        signUpPanel.add(signUpButton);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        signUpPanel.add(messageLabel); // Message for sign-up result
    }

    // Handle login functionality
    private void handleLogin(ActionEvent e) {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            showMessage("Please fill in all fields.", Color.RED);
            return;
        }

        User foundUser = null;
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                foundUser = u;
                break;
            }
        }

        if (foundUser == null) {
            showMessage("No such email signed up.", Color.RED);
            return;
        }

        if (foundUser.getPassword().equals(password)) {
            loggedInUser = foundUser; // Store logged-in user
            showMessage("Login successful! Welcome " + foundUser.getName() + ".", Color.GREEN);
            switchToForumSelectionPanel(); // Transition to the forum selection panel
        } else {
            showMessage("Incorrect password.", Color.RED);
        }
    }

    // Handle sign-up functionality
    private void handleSignUp(ActionEvent e) {
        String studentId = studentIdField.getText().trim();
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (studentId.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showMessage("Please fill in all fields.", Color.RED);
            return;
        }

        // Check if email already exists
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                showMessage("Email already registered. Please log in.", Color.RED);
                return;
            }
        }

        if (!password.equals(confirmPassword)) {
            showMessage("Passwords do not match.", Color.RED);
            return;
        }

        // Register the new user
        User newUser = new User(studentId, name, email, password);
        users.add(newUser);
        showMessage("Sign-up successful! Please log in.", Color.GREEN);
        switchToPanel(loginPanel); // After sign-up, go back to login screen
    }

    // Show messages on the screen
    private void showMessage(String message, Color color) {
        messageLabel.setText(message);
        messageLabel.setForeground(color);
    }

    // Switch to the forum selection panel after login
    private void switchToForumSelectionPanel() {
        JPanel forumSelectionPanel = new JPanel(new BorderLayout());

        // Forum List
        DefaultListModel<String> forumListModel = new DefaultListModel<>();
        for (Forum forum : forums) {
            forumListModel.addElement(forum.getTitle() + " (ID: " + forum.getForumId() + ")");
        }

        JList<String> forumList = new JList<>(forumListModel);
        forumList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane forumListScrollPane = new JScrollPane(forumList);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton openForumButton = new JButton("Open Forum");
        JButton createForumButton = new JButton("Create New Forum");
        JButton logoutButton = new JButton("Logout");

        openForumButton.addActionListener(e -> {
            int selectedIndex = forumList.getSelectedIndex();
            if (selectedIndex != -1) {
                activeForum = forums.get(selectedIndex);
                switchToForumPanel(); // Open the selected forum
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Please select a forum to open.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        createForumButton.addActionListener(e -> openCreateForumDialog());
        logoutButton.addActionListener(e -> logout());

        buttonPanel.add(openForumButton);
        buttonPanel.add(createForumButton);
        buttonPanel.add(logoutButton);

        forumSelectionPanel.add(new JLabel("Select a Forum:"), BorderLayout.NORTH);
        forumSelectionPanel.add(forumListScrollPane, BorderLayout.CENTER);
        forumSelectionPanel.add(buttonPanel, BorderLayout.SOUTH);

        switchToPanel(forumSelectionPanel);
    }

    // Open dialog to create a new forum
    private void openCreateForumDialog() {
        String forumTitle = JOptionPane.showInputDialog(mainFrame, "Enter Forum Title:", "Create New Forum", JOptionPane.PLAIN_MESSAGE);
        if (forumTitle != null && !forumTitle.trim().isEmpty()) {
            String forumId = "F" + (forums.size() + 1); // Generate a unique forum ID
            Forum newForum = new Forum(forumId, forumTitle, loggedInUser);
            forums.add(newForum);
            JOptionPane.showMessageDialog(mainFrame, "Forum created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            switchToForumSelectionPanel(); // Refresh the forum selection panel
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Forum title cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void switchToForumPanel() {
        JPanel forumPanel = new JPanel(new BorderLayout());

        // Forum title
        JLabel forumTitleLabel = new JLabel(activeForum.getTitle(), JLabel.CENTER);
        forumTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Posts display area
        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));

        for (Post post : activeForum.getPosts()) {
            // Display post details (Content and file info)
            JTextArea postDetailsArea = new JTextArea(post.displayPost());
            postDetailsArea.setEditable(false);
            postDetailsArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            postDetailsArea.setWrapStyleWord(true);
            postDetailsArea.setLineWrap(true);
            postDetailsArea.setCaretPosition(0); // Ensure the content starts at the top

            postsPanel.add(postDetailsArea);

            // Display image if available
            JLabel imageLabel = post.getImageLabel();  // Get the image label using the getImageLabel method
            if (imageLabel != null) {
                // Create a panel to hold the image and allow it to scale properly
                JPanel imagePanel = new JPanel();
                imagePanel.add(imageLabel);  // Add the actual image to the posts panel
                postsPanel.add(imagePanel);
            }
        }

        JScrollPane postsScrollPane = new JScrollPane(postsPanel);

        // Buttons for adding posts and going back
        JPanel buttonPanel = new JPanel();
        JButton addPostButton = new JButton("Add Post");
        JButton backToForumSelectionButton = new JButton("Back");

        addPostButton.addActionListener(e -> openAddPostDialog());
        backToForumSelectionButton.addActionListener(e -> switchToForumSelectionPanel());

        buttonPanel.add(addPostButton);
        buttonPanel.add(backToForumSelectionButton);

        forumPanel.add(forumTitleLabel, BorderLayout.NORTH);
        forumPanel.add(postsScrollPane, BorderLayout.CENTER);
        forumPanel.add(buttonPanel, BorderLayout.SOUTH);

        switchToPanel(forumPanel);
    }



    private void openAddPostDialog() {
        // Panel for creating a post
        JPanel addPostPanel = new JPanel();
        addPostPanel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 rows, 2 columns

        JTextArea postContentArea = new JTextArea(10, 20); // Increased height for content
        postContentArea.setLineWrap(true);
        postContentArea.setWrapStyleWord(true);
        postContentArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Buttons for file and image selection
        JButton selectFileButton = new JButton("Select File");
        JButton selectImageButton = new JButton("Select Image");

        // Previews for file and image
        JLabel filePreviewLabel = new JLabel("File Preview: None", SwingConstants.CENTER);
        JLabel imagePreviewLabel = new JLabel("Image Preview: None", SwingConstants.CENTER);

        // Add components to the panel
        addPostPanel.add(new JLabel("Post Content:"));
        addPostPanel.add(new JScrollPane(postContentArea)); // Text area for post content
        addPostPanel.add(selectFileButton);
        addPostPanel.add(filePreviewLabel);  // File preview on the right

        // Add a new row for the image selection and preview
        addPostPanel.add(selectImageButton);
        addPostPanel.add(imagePreviewLabel); // Image preview on the right

        // File chooser for selecting files
        selectFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(mainFrame);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                filePreviewLabel.setText("File: " + selectedFilePath); // Show file path
            }
        });

        // File chooser for selecting images
        selectImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files (JPG, PNG)", "jpg", "jpeg", "png"));
            int returnValue = fileChooser.showOpenDialog(mainFrame);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String selectedImagePath = fileChooser.getSelectedFile().getAbsolutePath();

                // Update image preview
                ImageIcon imageIcon = new ImageIcon(selectedImagePath);
                if (imageIcon.getIconWidth() > 0) {
                    // Scale image for preview
                    Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    imagePreviewLabel.setIcon(new ImageIcon(scaledImage));
                    imagePreviewLabel.setText(""); // Clear default text
                } else {
                    imagePreviewLabel.setIcon(null);
                    imagePreviewLabel.setText("Invalid Image");
                }
            }
        });

        // Buttons for submitting or canceling the post creation
        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Submit Post");
        JButton cancelButton = new JButton("Cancel");

        submitButton.addActionListener(e -> {
            String postContent = postContentArea.getText().trim();

            if (postContent.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "Post content cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // File and image handling logic
            String filePath = filePreviewLabel.getText().equals("File Preview: None") ? null : filePreviewLabel.getText().substring(6); // Extract file path
            String imagePath = (imagePreviewLabel.getIcon() == null) ? null : filePreviewLabel.getText(); // Extract image path if exists

            // Create and add the new post
            Post newPost = new Post(loggedInUser, postContent, filePath, imagePath);
            activeForum.addPost(newPost);

            JOptionPane.showMessageDialog(mainFrame, "Post added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            switchToForumPanel(); // Refresh the forum panel to display the new post
        });

        cancelButton.addActionListener(e -> switchToForumPanel()); // Return to forum panel on cancel

        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        addPostPanel.add(buttonPanel);

        // Replace current content with the add post panel
        mainFrame.getContentPane().removeAll();
        mainFrame.add(addPostPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }




    // Log out and return to login panel
    private void logout() {
        loggedInUser = null;
        activeForum = null;
        switchToPanel(loginPanel);
    }

    // Utility method to switch panels
    private void switchToPanel(JPanel panel) {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(panel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ForumApp::new);
    }
}
