/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import mira.addressBook.logics.User;
import mira.addressBook.logics.Contact;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
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
        user = new User("Mira", true);       
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
    public void luoUusiLapsiKayttaja() {
        user = new User("Ville", false);
        assertEquals("User name: Ville, Permission: Child, Contacts: ", user.toString());
        assertEquals(false, user.isAdult());
    }
    
    @Test
    public void luoUusiAikuisKayttaja() {
        assertEquals("Mira", user.getName());
        assertEquals("User name: Mira, Permission: Adult, Contacts: ", user.toString());
        assertEquals(true, user.isAdult());
    }
    
    @Test
    public void luoUusiKayttajaGuidilla() {
        user = new User("Ville", 20, "GUIDITESTI");
        assertEquals("GUIDITESTI", user.getGuid());
    }
    
    @Test
    public void luoUusiYstavaGuidilla() {
        Contact guidContact = new Contact("Liisa", "0404040404", "Katu 1", "Jussi", "044044044", "TESTGUID");
        user.addContact(guidContact);
        ArrayList<Contact> contacts = user.getContacts();
        Contact addedContact = contacts.get(0);
        assertEquals(addedContact.getGuid(), "TESTGUID");
        assertEquals(addedContact.toString(), "Liisa");
    }
    
    @Test
    public void lisaaUusiYstava() {
        user.addContact(contact);
        ArrayList<Contact> contacts = user.getContacts();
        Contact addedContact = contacts.get(0);
        assertEquals(addedContact.getFriendName(), "Katja");
        assertEquals(addedContact.getFriendPhone(), "050505050");
        assertEquals(addedContact.getFriendAddress(), "Juhanintie 1");
        assertEquals(addedContact.getParentName(), "Anne");
        assertEquals(addedContact.getParentPhone(), "040404040");
    }
    
    @Test
    public void yhteystietoYstavaVanhempi(){
        assertEquals(contact.getFriendName(), "Katja");
        assertEquals(contact.getFriendPhone(), "050505050");
        assertEquals(contact.getFriendAddress(), "Juhanintie 1");
        assertEquals(contact.getParentName(), "Anne");
        assertEquals(contact.getParentPhone(), "040404040");     
    }
    @Test
    public void lisaaYhteystieto(){
        assertEquals(contact.getFriendName(), "Katja");
        assertEquals(contact.getFriendPhone(), "050505050");
        assertEquals(contact.getFriendAddress(), "Juhanintie 1");
        assertEquals(contact.getParentName(), "Anne");
        assertEquals(contact.getParentPhone(), "040404040");     
    }
}
