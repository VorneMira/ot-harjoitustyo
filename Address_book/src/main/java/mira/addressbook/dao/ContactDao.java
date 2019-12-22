/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mira.addressbook.dao;

import java.util.ArrayList;
import mira.addressbook.logics.Contact;
import mira.addressbook.logics.User;

/**
 *
 * @author Mira Vorne
 */
public interface ContactDao {

    ArrayList<Contact> getAllContacts(User user) throws Exception;

    void deleteContact(Contact contact) throws Exception;

    void addContact(Contact contact) throws Exception;
}
