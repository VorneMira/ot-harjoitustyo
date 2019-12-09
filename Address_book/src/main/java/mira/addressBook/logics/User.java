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

    public User(String name, boolean isAdult) {
        this.name = name;
        if (isAdult) {
            this.permission = ADULT;
        } else {
            this.permission = CHILD;
        }
    }
    
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

    public String getGuid() {
        return this.guid;
    }
    
    public String getName() {
        return this.name;
    }

    public String getPermissionName() {
        if (this.permission == ADULT) {
            return "Adult";
        } else {
            return "Child";
        }
    }
    
    public boolean isAdult() {
        if (this.permission == ADULT) {
            return true;
        }
        else {
            return false;
        }
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);

    }

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
