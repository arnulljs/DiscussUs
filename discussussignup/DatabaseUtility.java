package discussussignup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtility {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/discussus";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root"; // Replace with your actual password

    // Get a connection to the database
    public static Connection getConnection() {
        try {
            // Load the JDBC driver (if required)
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure this matches your driver
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver not found: " + e.getMessage());
            throw new RuntimeException("Database driver error", e); // Propagate as runtime exception
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
            throw new RuntimeException("Database connection error", e); // Propagate as runtime exception
        }
    }
}
