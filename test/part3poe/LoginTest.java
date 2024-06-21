/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package part3poe;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mabel
 */
public class LoginTest {
    
    public LoginTest() {
    }

    /**
     * Test of main method, of class Login.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Login.main(args);
    }

    /**
     * Test of promptUserForLoginDeets method, of class Login.
     */
    @Test
    public void testPromptUserForLoginDeets() {
        System.out.println("promptUserForLoginDeets");
        Login.promptUserForLoginDeets();
    }

    /**
     * Test of checkUsername method, of class Login.
     */
    @Test
    public void testCheckUsername() {
        System.out.println("checkUsername");
        String username = "kyl_1";
        boolean expResult = true;
        boolean result = Login.checkUsername(username);
        assertEquals(expResult, result);
        
        username = "kyle!!!!!!!";
        expResult = false;
        result = Login.checkUsername(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkPasswordComplexity method, of class Login.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        String password = "Ch&&sec@ke99";
        boolean expResult = true;
        boolean result = Login.checkPasswordComplexity(password);
        assertEquals(expResult, result);
        
        password = "password";
        expResult = false;
        result = Login.checkPasswordComplexity(password);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginUser method, of class Login.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String name = "kylebrown";
        String studentNumber = "123456789";
        boolean expResult = true;
        boolean result = Login.loginUser(name, studentNumber);
        assertEquals(expResult, result);
        
        name = "kyle";
        studentNumber = "12345678";
        expResult = false;
        result = Login.loginUser(name, studentNumber);
        assertEquals(expResult, result);
    }
}
