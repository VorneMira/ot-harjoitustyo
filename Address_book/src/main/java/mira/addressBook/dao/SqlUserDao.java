/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mira.addressbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import mira.addressbook.logics.Contact;
import mira.addressbook.logics.User;

/**
 *
 * @author Mira Vorne
 */
public class SqlUserDao extends SqlBaseDao implements UserDao {

    public SqlUserDao() {

    }

    public SqlUserDao(boolean useTestDB) {
        super(useTestDB);
    }

    /**
     * Lisää käyttäjän tietokantaan. Varmistaa, ettei käyttäjää löydy ennestään ennen lisäämistä.
     * 
     * @param user Lisättävä käyttäjä
     * @return true, jos lisäys onnistui, false, jos käyttäjä on jo olemassa
     * @throws Exception voi heittää virheen, jos ongelmia tietokantayhteydessä
     */
    public boolean addUser(User user) throws Exception {
        if (findByUsername(user.getName()) != null) {
            // user exists
            return false;
        }
        // else
        Connection connection = connectToDB();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, role, id) VALUES (?, ?, ?)");
            statement.setString(1, user.getName());
            statement.setInt(2, user.getPermissionValue());
            statement.setString(3, user.getGuid());
            int count = statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Encountered an error when executing given sql statement.", e);
        }
        return true;
    }

    /** 
     * Luo User olion tietokannasta haetun ResultSetin perusteella
     * 
     * @param results ResultSet, jossa tietokannasta haettu data
     * @return User olion
     * @throws Exception voi heittää virheen, jos ongelmia tietokantayhteydessä
     */
    private User createUserFromResultSet(ResultSet results) throws Exception {
        String uname = results.getString(1);
        int role = results.getInt(2);
        String guid = results.getString(3);
        User user = new User(uname, role, guid);
        user.clearContacts();
        SqlContactDao contactDao = new SqlContactDao();
        ArrayList<Contact> contacts = contactDao.getAllContacts(user);
        for (Contact contact : contacts) {
            user.addContact(contact);
        }

        return user;
    }

    /**
     * Käyttäjän hakeminen tietokannasta
     *
     * @param username käyttäjän nimi, jota haetaan
     * @return null, jos käyttäjää ei löydy. User luokan ilmentymän, jos
     * käyttäjä löytyy. Käyttäjän yhteystiedot haetaan myös SqlContactDao:n
     * avulla
     * @throws Exception voi heittää virheen, jos ongelmia tietokantayhteydessä
     */
    public User findByUsername(String username) throws Exception {
        Connection connection = connectToDB();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT name, role, id from users WHERE name = ?");
            statement.setString(1, username);
            ResultSet results = statement.executeQuery();
            if (results.first()) {
                User user = createUserFromResultSet(results);
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Encountered an error when executing given sql statement.", e);
        }
    }

    /** 
     * Palauttaa kaikki lapsikäyttäjät
     * 
     * @return tietokannasta haetut lapsikäyttäjät
     * @throws Exception voi heittää virheen, jos ongelmia tietokantayhteydessä
     */
    public ArrayList<User> getAllChildUsers() throws Exception {
        Connection connection = connectToDB();
        ArrayList<User> childUsers = new ArrayList<>();

        // Perform some SQL queries over the connection.
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT name, role, id from users WHERE role = ?");
            statement.setInt(1, User.CHILD);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                User user = new User(results.getString(1), results.getInt(2), results.getString(3));

                childUsers.add(user);
            }
            return childUsers;
        } catch (SQLException e) {
            throw new SQLException("Encountered an error when executing given sql statement.", e);
        }
    }
}
