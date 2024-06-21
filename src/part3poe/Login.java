/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package part3poe;

import javax.swing.JOptionPane;

/**
 *
 * @author mabel
 */
public class Login {
    
    //Modified my code by adding and declaring string variables for the registration of the user
    private static String registeredUsername;
    private static String registeredPassword;
    
    //Username and student number
    private static String registeredName;
    private static String registeredStudentNumber;

    public static void main(String[] args) {
        login();
    }

    // Login feature with JOptionPane, derived from my part 1 (corrected) and incorporated with my POE for part 3.
    public static void promptUserForLoginDeets() {
        // Opening of the login feature starting with "Greetings" and asks user to proceed...
        String proceedInput = JOptionPane.showInputDialog(null, "Good day! Please type 'Yes' to proceed--");

        if (!"Yes".equalsIgnoreCase(proceedInput)) {
            JOptionPane.showMessageDialog(null, "Exiting the registration process.");
            return;
        }

        // For the user to enter their desired username according to the requirements (Registration)
        String registerUser = JOptionPane.showInputDialog(null, "Enter your desired username: ");
        if (!checkUsername(registerUser)) {
            JOptionPane.showMessageDialog(null, "Username must contain an underscore.\n");
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Username successfully captured.\n");
        }

        // For the user to enter their password following the requirements
        String password = JOptionPane.showInputDialog(null, "Enter your desired password (at least 8 characters with digits & special characters): ");
        if (!checkPasswordComplexity(password)) {
            JOptionPane.showMessageDialog(null, "Password does not meet the complexity\n");
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Password successfully captured.\n");
        }
      

        // For the user to enter the name and student number as final authentication
        String name = JOptionPane.showInputDialog(null, "Enter your name: ");
        String studentNumber = JOptionPane.showInputDialog(null, "Enter your student number (9 digits): ");

        // Store registration details
        registeredUsername = registerUser;
        registeredPassword = password;
        registeredName = name;
        registeredStudentNumber = studentNumber;

        JOptionPane.showMessageDialog(null, "Registration successful! Thank you!");
    }
    

    // Method to check if the username is valid or not
    public static boolean checkUsername(String username) {
        return username != null && username.contains("_");
    }

    // Validation Process
    public static void login() {
        JOptionPane.showMessageDialog(null, "Registration");
        promptUserForLoginDeets();

        // Prompt user for login details
        String username = JOptionPane.showInputDialog(null, "Enter your username to login: ");
        String password = JOptionPane.showInputDialog(null, "Enter your password to login: ");

        // Verify login details
        if (loginUser(username, password)) {
            returnLoginStatus(true);
        } else {
            returnLoginStatus(false);
        }
    }

    public static boolean loginUser(String username, String password) {
        return registeredUsername.equals(username) && registeredPassword.equals(password);
    }

    public static boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }
        return hasDigit && hasSpecialChar;
    }

    public static void returnLoginStatus(boolean isSuccess) {
        if (isSuccess) {
            JOptionPane.showMessageDialog(null, "Login successful.");
        } else {
            JOptionPane.showMessageDialog(null, "Login failed. Please check your username and password. \n");
        }
    }
}
