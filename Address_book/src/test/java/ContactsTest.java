/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import mira.addressbook.logics.User;
import mira.addressbook.logics.Contact;
import mira.addressbook.dao.SqlUserDao;
import mira.addressbook.dao.SqlContactDao;
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
    SqlUserDao userDao;
    SqlContactDao contactDao;

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
        assertEquals(false, user.isParent());
    }

    @Test
    public void luoUusiAikuisKayttaja() {
        assertEquals("Mira", user.getName());
        assertEquals("User name: Mira, Permission: Parent, Contacts: ", user.toString());
        assertEquals(true, user.isParent());
        assertEquals(User.PARENT, user.getPermissionValue());
    }

    @Test
    public void luoUusiKayttajaGuidilla() {
        user = new User("Ville", User.PARENT, "GUIDITESTI");
        assertEquals("GUIDITESTI", user.getGuid());
    }

    @Test
    public void luoUusiLapsiGuidilla() {
        user = new User("Ville", User.CHILD, "GUIDITESTI");
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
        user.clearContacts();
        contacts = user.getContacts();
        assertEquals(0, contacts.size());
    }

    @Test
    public void lisaaUusiYstava() {
        User childUser = new User("Ville", false);
        contact.setUser(childUser);
        user.addContact(contact);
        ArrayList<Contact> contacts = user.getContacts();
        Contact addedContact = contacts.get(0);
        assertEquals(addedContact.getFriendName(), "Katja");
        assertEquals(addedContact.getFriendPhone(), "050505050");
        assertEquals(addedContact.getFriendAddress(), "Juhanintie 1");
        assertEquals(addedContact.getParentName(), "Anne");
        assertEquals(addedContact.getParentPhone(), "040404040");
        assertEquals(addedContact.getChildName(), "Ville");
        assertEquals(addedContact.getUser(), childUser);
    }

    @Test
    public void yhteystietoYstavaVanhempi() {
        assertEquals(contact.getFriendName(), "Katja");
        assertEquals(contact.getFriendPhone(), "050505050");
        assertEquals(contact.getFriendAddress(), "Juhanintie 1");
        assertEquals(contact.getParentName(), "Anne");
        assertEquals(contact.getParentPhone(), "040404040");
        assertEquals(contact.getChildName(), "");
    }

    @Test
    public void daoTestiKayttajat() throws Exception {
        userDao = new SqlUserDao(true);
        userDao.initTestDB();

        User user = userDao.findByUsername("eiooeituu");
        assertNull(user);
        user = userDao.findByUsername("Nella");
        assertEquals(user.getName(), "Nella");
        ArrayList<User> childUsers = userDao.getAllChildUsers();
        assertEquals(childUsers.size(), 3);
        User newUser = new User("Heikki", User.PARENT, "LISAYSTESTUGUID");
        userDao.addUser(newUser);
        user = userDao.findByUsername("Heikki");
        assertEquals(user.getGuid(), "LISAYSTESTUGUID");
    }
    
     @Test
    public void daoTestiKontaktit() throws Exception {
        userDao = new SqlUserDao(true);
        contactDao = new SqlContactDao(true);
        contactDao.initTestDB();

        User user = userDao.findByUsername("Alex");
        Contact guidContact = new Contact("Liisa", "0404040404", "Katu 1", "Jussi", "044044044", "TESTGUID");
        guidContact.setUser(user);
        
        contactDao.addContact(guidContact);
        ArrayList<Contact> contacts = contactDao.getAllContacts(user);
        Contact addedContact = contacts.get(0);
        assertEquals(addedContact.getGuid(), guidContact.getGuid());
        contactDao.deleteContact(addedContact);
        contacts = contactDao.getAllContacts(user);
        assertEquals(contacts.size(), 0);
    }
}
