package discussussignup;

import java.sql.*;

public class UserDAO {
    public static final String URL = "jdbc:mysql://localhost:3306/discussus";
    public static final String USER = "root"; // Update with your MySQL username
    public static final String PASSWORD = "root"; // Update with your MySQL password

    // A static variable to represent the logged-in user for the session (you can set this based on your session management)
    private static int loggedInUserId = -1; // Set this as needed, for example, after user login

    // Method to connect to the database
    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to retrieve the current user from the database based on logged-in session ID
    public static User getCurrentUser() {
    System.out.println("Logged in user ID: " + loggedInUserId);  // Debugging line
    if (loggedInUserId == -1) {
        return null;  // No user is logged in
    }

    User user = null;
    String sql = "SELECT * FROM users WHERE user_id = ?";
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, loggedInUserId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String username = rs.getString("username");
            String email = rs.getString("email");
            user = new User(String.valueOf(loggedInUserId), username, email);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return user;
}
    
     public static String getLoggedInUsername() {
        if (loggedInUserId == -1) {
            return null; // No user is logged in
        }

        String username = null;
        String sql = "SELECT username FROM users WHERE user_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, loggedInUserId);  // Set the logged-in user ID
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                username = rs.getString("username");  // Fetch the username
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }

    // Method to log in and set the logged-in user ID
    public static boolean loginUser(String username, String password) {
        String sql = "SELECT user_id FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                loggedInUserId = rs.getInt("user_id"); // Set the logged-in user ID
                return true; // Login successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Login failed
    }

    // Method to insert a new user into the database
    public boolean insertUser(String username, String email, String password) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Returns true if a row was successfully inserted
        } catch (SQLIntegrityConstraintViolationException e) {
            // Triggered by duplicate username or email
            System.out.println("User already exists: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}