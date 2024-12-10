package discussussignup;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ForumApp {
    private JFrame mainFrame; // Main application frame
    private List<Forum> forums; // List to store multiple forums
    private Forum activeForum; // The currently selected forum
    private User loggedInUser; // The user currently logged in
    private List<User> users; // List to store all user accounts

    // Panels for user interface
    private JPanel loginPanel, signUpPanel; // Login and sign-up panels
    private JTextField emailField, nameField, studentIdField, forumTitleField; // Input fields for user data
    private JPasswordField passwordField, confirmPasswordField; // Password fields
    private JLabel messageLabel; // Label for displaying status messages
    private JButton loginButton, signUpButton, switchToSignUpButton, switchToLoginButton; // Buttons for navigation and actions

    public ForumApp() {
        // Initialize data structures and UI components
        forums = new ArrayList<>();
        users = new ArrayList<>();
        initialize();
    }

    // Initialize the main frame and setup the login and sign-up views
    private void initialize() {
        mainFrame = new JFrame("Forum Application");
        mainFrame.setSize(600, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        // Create login and sign-up panels
        initLoginPanel();
        initSignUpPanel();

        // Add a default user for demonstration
        User defaultUser = new User("U001", "Alice", "alice@example.com", "password123");
        users.add(defaultUser);

        // Show the login panel by default
        mainFrame.add(loginPanel);
        mainFrame.setLocationRelativeTo(null); // Center window
        mainFrame.setVisible(true);
    }

    // Initialize the login panel
    private void initLoginPanel() {
        loginPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // Grid layout for form-like appearance

        // Input fields and buttons
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        switchToSignUpButton = new JButton("Sign Up");

        // Add action listeners for buttons
        loginButton.addActionListener(this::handleLogin);
        switchToSignUpButton.addActionListener(e -> switchToPanel(signUpPanel));

        // Add components to the panel
        loginPanel.add(new JLabel("Email:"));
        loginPanel.add(emailField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // Placeholder for alignment
        loginPanel.add(loginButton);
        loginPanel.add(new JLabel("Don't have an account?"));
        loginPanel.add(switchToSignUpButton);

        // Message label for feedback
        messageLabel = new JLabel("", SwingConstants.CENTER);
        loginPanel.add(messageLabel);
    }

    // Initialize the sign-up panel
    private void initSignUpPanel() {
        signUpPanel = new JPanel(new GridLayout(7, 2, 10, 10)); // Grid layout for form-like appearance

        // Input fields and buttons
        studentIdField = new JTextField(20);
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);
        signUpButton = new JButton("Sign Up");
        switchToLoginButton = new JButton("Back to Login");

        // Add action listeners for buttons
        signUpButton.addActionListener(this::handleSignUp);
        switchToLoginButton.addActionListener(e -> switchToPanel(loginPanel));

        // Add components to the panel
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

        // Message label for feedback
        messageLabel = new JLabel("", SwingConstants.CENTER);
        signUpPanel.add(messageLabel);
    }

    // Handle login attempts
    private void handleLogin(ActionEvent e) {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Validate input
        if (email.isEmpty() || password.isEmpty()) {
            showMessage("Please fill in all fields.", Color.RED);
            return;
        }

        // Search for user by email
        User foundUser = users.stream()
