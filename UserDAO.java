package discussussignup;

import java.sql.*;

public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/discussus";
    private static final String USER = "root"; // Update with your MySQL username
    private static final String PASSWORD = "root"; // Update with your MySQL password

   /* public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /*public boolean insertUser(String username, String email, String password) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            return stmt.executeUpdate() > 0; // Return true if the insertion was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}*/

public boolean insertUser(String username, String email, String password) {
    String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
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
