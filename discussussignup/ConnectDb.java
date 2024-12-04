package discussussignup;

import java.sql.*;

public class ConnectDb {
	// to establish connection
    private static Connection con = null;
    private static final String URL = "jdbc:mysql://localhost:3306/discussus"; //forum_app is database name on mysql
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    // Private constructor to prevent instantiation
    private ConnectDb() {}

    // Singleton method to get database connection
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (con == null || con.isClosed()) {
            try {
            	// register driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Connected to the database!");
            } catch (SQLException e) {
                System.err.println("Connection failed: " + e.getMessage());
                throw e;
            }
        }
        return con;
    }

    // Method to close connection
    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Connection closed!");
            } catch (SQLException e) {
                System.err.println("Failed to close connection: " + e.getMessage());
            }
        }
    }

    // Execute SELECT queries
    public static ResultSet executeQuery(String query, Object... params) throws SQLException, ClassNotFoundException {
        if (getConnection() == null) {
            throw new SQLException("Database connection is not established.");
        }

        PreparedStatement stmt = con.prepareStatement(query);
        setParameters(stmt, params);
        return stmt.executeQuery();
    }

    // Execute INSERT, UPDATE, DELETE queries
    public static int executeUpdate(String query, Object... params) throws SQLException, ClassNotFoundException {
        if (getConnection() == null) {
            throw new SQLException("Database connection is not established.");
        }

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            setParameters(stmt, params);
            return stmt.executeUpdate();
        }
    }

    // Set parameters for PreparedStatement
    private static void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }

    // Fetch and display query results
    public static void displayQueryData(String query, Object... params) throws SQLException, ClassNotFoundException {
        try (ResultSet resultSet = executeQuery(query, params)) {
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            int columnCount = rsMetaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + " (" + rsMetaData.getColumnName(i) + ")");
                    if (i < columnCount) System.out.print(", ");
                }
                System.out.println();
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        try {
            // Example: Insert a new user
            String insertQuery = "INSERT INTO user (user_id, name, email, password) VALUES (?, ?, ?, ?)";
            int rowsInserted = executeUpdate(insertQuery, "user123", "John Doe", "john.doe@example.com", "password123");
            System.out.println(rowsInserted + " row(s) inserted.");

            // Example: Fetch and display users
            String selectQuery = "SELECT * FROM user";
            displayQueryData(selectQuery);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}