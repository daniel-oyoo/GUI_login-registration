# **README.md - Student Registration System**

## **Description**
A Java Swing desktop application for student registration and authentication. The system provides a secure login interface and a comprehensive registration form with database integration. Students can register with their personal details, and the application automatically handles minor students by requiring guardian information. The system uses MySQL for data persistence and follows a simple client-server architecture.

## **Features**
- **User Registration:** Complete student registration with validation
- **Secure Login:** Username/password authentication system
- **Minor Handling:** Automatic guardian information collection for underage students
- **Database Integration:** MySQL backend with JDBC connectivity
- **Responsive GUI:** Java Swing interface with dynamic form elements
- **Data Validation:** Input validation for age and required fields
- **Session Management:** Automatic window transitions between registration and login

## **Prerequisites**
Before running this application, ensure you have:

1. **Java Development Kit (JDK) 8 or higher**
   - Download from: https://adoptium.net/
   - Verify installation: `java -version`

2. **MySQL Database Server**
   - Download from: https://dev.mysql.com/downloads/
   - Or use XAMPP/WAMP for easy setup

3. **MySQL Connector/J**
   - Included in the project (`mysql-connector-j-9.2.0.jar`)

4. **Text Editor or IDE**
   - IntelliJ IDEA, Eclipse, or VS Code recommended

## **Project Structure**
```
student-registration-system/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── demo/
│                   ├── DatabaseConnection.java
│                   ├── LoginGUI.java
│                   ├── RegistrationGUI.java
│                   └── Main.java
├── lib/
│   └── mysql-connector-j-9.2.0.jar
├── database/
│   └── student_registration.sql
├── README.md
└── .gitignore
```

## **How to Run**

### **Step 1: Set Up the Database**
1. Open MySQL command line or phpMyAdmin
2. Create the database and table:
   ```bash
   mysql -u root -p < database/student_registration.sql
   ```
3. Or run the SQL commands manually:
   ```sql
   CREATE DATABASE student_registration;
   USE student_registration;
   
   CREATE TABLE students (
       student_id INT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(255) UNIQUE NOT NULL,
       password VARCHAR(255) NOT NULL,
       name VARCHAR(255) NOT NULL,
       age INT NOT NULL,
       is_minor BOOLEAN NOT NULL,
       guardian_name VARCHAR(255),
       guardian_contact VARCHAR(255)
   );
   ```

### **Step 2: Configure Database Connection**
1. Open `DatabaseConnection.java`
2. Update the database credentials:
   ```java
   private static final String USERNAME = "your_mysql_username";
   private static final String PASSWORD = "your_mysql_password";
   ```
3. If using a different database name or host, update:
   ```java
   private static final String JDBC_URL = "jdbc:mysql://localhost:3306/student_registration";
   ```

### **Step 3: Compile and Run**

#### **Option A: Using Command Line**
1. **Navigate to project root:**
   ```bash
   cd student-registration-system
   ```

2. **Compile all Java files:**
   ```bash
   javac -cp "lib/mysql-connector-j-9.2.0.jar" src/main/java/com/demo/*.java -d out/
   ```

3. **Run the application:**
   ```bash
   java -cp "out:lib/mysql-connector-j-9.2.0.jar" com.demo.RegistrationGUI
   ```
   Or to start with login:
   ```bash
   java -cp "out:lib/mysql-connector-j-9.2.0.jar" com.demo.LoginGUI
   ```

#### **Option B: Using IDE (IntelliJ/Eclipse)**
1. **Create new Java project** in your IDE
2. **Set up project structure** as shown above
3. **Add MySQL connector to libraries:**
   - Right-click project → Build Path → Add External Archives
   - Select `mysql-connector-j-9.2.0.jar`
4. **Run** `RegistrationGUI.java` or `LoginGUI.java`

#### **Option C: Create a Main.java (Recommended)**
Create a new file `Main.java` in `src/main/java/com/demo/`:
```java
package com.demo;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Ask user if they want to register or login
        String[] options = {"Register", "Login", "Exit"};
        int choice = JOptionPane.showOptionDialog(null,
                "Welcome to Student System\nChoose an option:",
                "Student System",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0: // Register
                SwingUtilities.invokeLater(() -> new RegistrationGUI());
                break;
            case 1: // Login
                SwingUtilities.invokeLater(() -> new LoginGUI());
                break;
            case 2: // Exit
                System.exit(0);
                break;
            default:
                System.exit(0);
        }
    }
}
```

## **Creating the Project Structure**

### **For Windows:**
```cmd
mkdir student-registration-system
cd student-registration-system
mkdir src\main\java\com\demo
mkdir lib
mkdir database
```

### **For macOS/Linux:**
```bash
mkdir -p student-registration-system/src/main/java/com/demo
mkdir -p student-registration-system/lib
mkdir -p student-registration-system/database
cd student-registration-system
```

### **Place Files in Correct Folders:**
1. Move these files to `src/main/java/com/demo/`:
   - `DatabaseConnection.java`
   - `LoginGUI.java`
   - `RegistrationGUI.java`
   - `Main.java` (if created)

2. Place `mysql-connector-j-9.2.0.jar` in `lib/` folder

3. Place `student_registration.sql` in `database/` folder

## **Troubleshooting**

### **Common Errors & Solutions:**

1. **"Class not found" error:**
   ```bash
   # Make sure classpath includes the jar file
   java -cp ".:lib/mysql-connector-j-9.2.0.jar" com.demo.LoginGUI
   ```

2. **MySQL connection failed:**
   - Check if MySQL service is running
   - Verify username/password in DatabaseConnection.java
   - Ensure database exists: `SHOW DATABASES;`

3. **"Access denied" error:**
   ```sql
   -- Grant privileges in MySQL:
   GRANT ALL PRIVILEGES ON student_registration.* TO 'username'@'localhost';
   FLUSH PRIVILEGES;
   ```

4. **GUI not appearing:**
   - Run with `SwingUtilities.invokeLater()` to ensure thread safety
   - Check for error messages in console

## **Security Note**
 **Important:** This application stores passwords as plain text for simplicity. For production use:

1. **Hash passwords** using BCrypt or SHA-256
2. **Use prepared statements** to prevent SQL injection
3. **Store credentials** in environment variables, not hardcoded
4. **Add SSL encryption** for database connections

## **Testing the Application**

1. **Test Registration:**
   - Fill all fields
   - Check "Minor" to see guardian fields appear
   - Submit and verify success message

2. **Test Login:**
   - Use registered credentials
   - Try wrong credentials to see error message
   - Verify successful login opens new window

## **Future Enhancements**
- Add password hashing for security
- Implement email verification
- Add admin panel for user management
- Include data export to CSV/PDF
- Add profile picture upload
- Implement password recovery system

## **License**
This project is for educational purposes. Feel free to modify and distribute.

## **Support**
For issues or questions:
1. Check the troubleshooting section
2. Ensure all prerequisites are installed
3. Verify database connection settings
4. Check console for error messages

---
