/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mira.addressbook.dao;

import java.util.ArrayList;
import mira.addressbook.logics.User;

/**
 *
 * @author Mira Vorne
 */
public interface UserDao {

    boolean addUser(User user) throws Exception;

    User findByUsername(String username) throws Exception;

    ArrayList<User> getAllChildUsers() throws Exception;

}
