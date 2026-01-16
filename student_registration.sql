-- Create the database for student registration system
CREATE DATABASE student_registration;

-- Switch to using the new database
USE student_registration;

-- Create the main students table to store student information
CREATE TABLE students (
    -- Unique ID for each student, automatically increases by 1 for each new student
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    
    -- Username for login (must be unique and cannot be empty)
    username VARCHAR(255) UNIQUE NOT NULL,
    
    -- Password for login (cannot be empty)
    password VARCHAR(255) NOT NULL,
    
    -- Full name of student (cannot be empty)
    name VARCHAR(255) NOT NULL,
    
    -- Age of student in years (cannot be empty)
    age INT NOT NULL,
    
    -- Whether student is a minor (true/false) (cannot be empty)
    is_minor BOOLEAN NOT NULL,
    
    -- Name of guardian (only needed if student is minor)
    guardian_name VARCHAR(255),
    
    -- Contact information for guardian (only needed if student is minor)
    guardian_contact VARCHAR(255)
);
