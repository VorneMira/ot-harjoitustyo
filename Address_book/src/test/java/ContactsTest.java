/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import mira.addressBook.logics.User;
import mira.addressBook.logics.Contact;
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
    Contact contact;
    
    @Before
    public void setUp() {
        user = new User("Heikki", false);       
        contact = new Contact("Katja", "050505050", "Juhanintie 1", "Anne", "040404040");
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
    @Test
    public void yhteystietoYstavaVanhempi(){
        assertEquals(contact.getFriendName(), "Katja");
        assertEquals(contact.getFriendPhone(), "050505050");
        assertEquals(contact.getFriendAddress(), "Juhanintie 1");
        assertEquals(contact.getParentName(), "Anne");
        assertEquals(contact.getParentPhone(), "040404040");     
    }
}
