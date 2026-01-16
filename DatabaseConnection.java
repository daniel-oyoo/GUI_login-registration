// Import required SQL packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Database connection class - manages connection to MySQL database
public class DatabaseConnection {
    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/student_registration";
    private static final String USERNAME = "root"; // Your MySQL username here
    private static final String PASSWORD = "your_password"; // Your MySQL password here

    // Method to get database connection
    public static Connection getConnection() throws SQLException {
        // Create and return connection to database
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
