/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.address_book.User;
import com.mycompany.address_book.Contact;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mira Vorne
 */
public class ContactsTest {
    
    public ContactsTest() {
    }
    
    User user;

    @Before
    public void setUp() {
        user = new User("Heikki", false);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void luoUusiYstava() {
        assertEquals("User name: Heikki, Permission: Child, Contacts: ", user.toString());
    }
}
