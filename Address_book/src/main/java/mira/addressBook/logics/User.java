/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mira.addressbook.logics;

import mira.addressbook.logics.Contact;
import java.util.ArrayList;

/**
 *
 * @author Mira Vorne
 */
public class User {

    private String guid = java.util.UUID.randomUUID().toString();
    private String name;
    private int permission;
    public static final int CHILD = 10;
    public static final int PARENT = 20;
    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    /**
     * Konstruktori User luokalle.
     * @param name käyttäjän nimi
     * @param isParent onko käyttäjä aikuinen
     */
    public User(String name, boolean isParent) {
        this.name = name;
        if (isParent) {
            this.permission = PARENT;
        } else {
            this.permission = CHILD;
        }
    }
    
    /** 
     * Konstruktori User luokalle. Tällä saa tietokannasta haetun käyttäjän ilmentymän luotua.
     * @param name käyttäjän nimi
     * @param role käyttäjän rooli (10 child, 20 parent)
     * @param guid guid
     */
    public User(String name, int role, String guid) {
        this.name = name;
        if (role == PARENT) {
            this.permission = PARENT;
        } else {
            this.permission = CHILD;
        }
        this.guid = guid;
    }

    public String getGuid() {
        return this.guid;
    }
    
    public String getName() {
        return this.name;
    }

    public String getPermissionName() {
        if (this.permission == PARENT) {
            return "Parent";
        } else {
            return "Child";
        }
    }
    
    public int getPermissionValue() {
        return this.permission;
    }
    
    /**
     * @return true, jos käyttäjä on vanhempi, false jos lapsi
     */
    public boolean isParent() {
        return this.permission == PARENT;
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

    public ArrayList<Contact> getContacts() {
        return contacts;
    }
    
    /**
     * Poistaa kaikki yhteyshenkilöt
     */
    public void clearContacts() {
        contacts.clear();
    }
}
