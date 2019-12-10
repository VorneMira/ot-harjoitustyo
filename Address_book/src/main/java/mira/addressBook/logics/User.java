/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mira.addressBook.logics;

import mira.addressBook.logics.Contact;
import java.util.ArrayList;

/**
 *
 * @author Mira Vorne
 */
public class User {

    private String guid = java.util.UUID.randomUUID().toString();
    private String name;
    private int permission;
    private static final int CHILD = 10;
    private static final int ADULT = 20;
    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    /**
     * Konstruktori User luokalle.
     * @param name käyttäjän nimi
     * @param isAdult onko käyttäjä aikuinen
     */
    public User(String name, boolean isAdult) {
        this.name = name;
        if (isAdult) {
            this.permission = ADULT;
        } else {
            this.permission = CHILD;
        }
    }
    
    /** 
     * Konstruktori User luokalle. Tällä saa tietokannasta haetun käyttäjän ilmentymän luotua.
     * @param name käyttäjän nimi
     * @param role käyttäjän rooli (10 child, 20 adult)
     * @param guid guid
     */
    public User(String name, int role, String guid) {
        this.name = name;
        if (role == ADULT) {
            this.permission = ADULT;
        }
        else {
            this.permission = CHILD;
        }
        this.guid = guid;
    }

    /**
     * @return Guidin
     */
    public String getGuid() {
        return this.guid;
    }
    
    /**
     * @return nimen
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return käyttöoikeuden Stringinä (Adult/Child)
     */
    public String getPermissionName() {
        if (this.permission == ADULT) {
            return "Adult";
        } else {
            return "Child";
        }
    }
    
    /**
     * @return true, jos käyttäjä on aikuinen, false jos lapsi
     */
    public boolean isAdult() {
        if (this.permission == ADULT) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Lisää yhteystiedon käyttäjälle
     * @param contact lisättävä yhteystieto
     */
    public void addContact(Contact contact) {
        this.contacts.add(contact);

    }

    /**
     * @return String esitys käyttäjästä. 
     */
    public String toString() {
        String listOfContacts = "";

        for (int i = 0; i < contacts.size(); i++) {
            listOfContacts += contacts.get(i) + ", ";
        }
        return "User name: " + this.name + ", Permission: " + this.getPermissionName() + ", Contacts: " + listOfContacts;
    }

    /**
     * @return yhteyshenkilöt ArrayListana
     */
    public ArrayList<Contact> getContacts() {
        return contacts;
    }
    
    /**
     * Poistaa kaikki yhteyshenkilöt
     */
    public void clearContacts() {
        contacts.clear();
    }
    /*public static void main(String[] args){
        User user = new User("Pekka", true);
        Contact contact = new Contact("Lauri");
        user.addContact(contact);
        System.out.println(user);
    } */
}
