// Import required packages for GUI and database
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Login window for student authentication
public class LoginGUI extends JFrame {
    // Text fields for user input
    private JTextField usernameField;
    private JPasswordField passwordField;

    // Constructor - sets up the login window
    public LoginGUI() {
        // Window settings
        setTitle("Student Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2)); // 3 rows, 2 columns layout

        // Username label and input field
        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        // Password label and input field (hidden input)
        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        // Login button
        JButton loginButton = new JButton("Login");
        add(loginButton);

        // Add click action to login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginStudent(); // Call login method when button clicked
            }
        });

        // Make window visible
        setVisible(true);
    }

    // Method to check login credentials against database
    private void loginStudent() {
        // Get text from input fields
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Try to connect to database and check credentials
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM students WHERE username = ? AND password = ?")) {

            // Set username and password in SQL query
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute query and get results
            ResultSet resultSet = statement.executeQuery();

            // Check if user exists in database
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                // TODO: Add code here to open main application window
                dispose(); // Close login window after successful login
            } else {
                JOptionPane.showMessageDialog(this, "Login failed: Invalid username or password.");
            }
        } catch (SQLException ex) {
            // Show error if database connection fails
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Login failed: " + ex.getMessage());
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
        // Create and show login window
        new LoginGUI();
    }
}
