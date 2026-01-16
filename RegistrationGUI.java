// Import required packages for GUI and database
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Registration window for student sign-up
public class RegistrationGUI extends JFrame {
    // Input fields for student information
    private JTextField usernameField, nameField, ageField, guardianNameField, guardianContactField;
    private JPasswordField passwordField;
    private JCheckBox minorCheckBox;

    // Constructor - sets up the registration window
    public RegistrationGUI() {
        // Window settings
        setTitle("Student Registration");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2)); // 8 rows, 2 columns layout

        // Username input
        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        // Password input (hidden)
        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        // Full name input
        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        // Age input
        add(new JLabel("Age:"));
        ageField = new JTextField();
        add(ageField);

        // Minor checkbox
        minorCheckBox = new JCheckBox("Minor");
        add(minorCheckBox);
        add(new JLabel("")); // Empty cell for alignment

        // Guardian name (shown only if minor is checked)
        add(new JLabel("Guardian Name:"));
        guardianNameField = new JTextField();
        add(guardianNameField);
        guardianNameField.setVisible(false); // Hidden by default

        // Guardian contact (shown only if minor is checked)
        add(new JLabel("Guardian Contact:"));
        guardianContactField = new JTextField();
        add(guardianContactField);
        guardianContactField.setVisible(false); // Hidden by default

        // Register button
        JButton registerButton = new JButton("Register");
        add(registerButton);

        // Action for minor checkbox - show/hide guardian fields
        minorCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isMinor = minorCheckBox.isSelected();
                guardianNameField.setVisible(isMinor);
                guardianContactField.setVisible(isMinor);
            }
        });

        // Action for register button - save student data
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerStudent();
            }
        });

        // Make window visible
        setVisible(true);
    }

    // Method to save student registration to database
    private void registerStudent() {
        // Get text from all input fields
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        boolean isMinor = minorCheckBox.isSelected();
        String guardianName = guardianNameField.getText();
        String guardianContact = guardianContactField.getText();

        // Try to save data to database
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO students (username, password, name, age, is_minor, guardian_name, guardian_contact) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            // Set values in SQL query
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setInt(4, age);
            statement.setBoolean(5, isMinor);
            statement.setString(6, guardianName);
            statement.setString(7, guardianContact);

            // Execute the insert query
            statement.executeUpdate();
            
            // Show success message
            JOptionPane.showMessageDialog(this, "Registration successful!");
            
            // Open login window and close registration window
            new LoginGUI();
            dispose();
            
        } catch (SQLException ex) {
            // Show error if database operation fails
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Registration failed: " + ex.getMessage());
        }
    }

    // Main method to start the registration window
    public static void main(String[] args) {
        // Create and show registration window
        new RegistrationGUI();
    }
}
