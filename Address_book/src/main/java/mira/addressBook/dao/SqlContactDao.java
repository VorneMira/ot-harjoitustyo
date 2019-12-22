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
import java.util.HashSet;
import java.util.Properties;
import mira.addressbook.logics.Contact;
import mira.addressbook.logics.User;

/**
 *
 * @author Mira Vorne
 */
public class SqlContactDao extends SqlBaseDao {

    public SqlContactDao() {

    }

    public SqlContactDao(boolean useTestDB) {
        super(useTestDB);
    }

    /**
     * Lisää kontakti tietokantaan
     * 
     * @param contact lisättävä kontakti
     * @throws Exception voi heittää virheen, jos ongelmia tietokantayhteydessä
     */
    public void addContact(Contact contact) throws Exception {
        Connection connection = connectToDB();

        String query = "INSERT INTO contacts (id, friendName, friendPhone, friendAddress, parentName, parentPhone, id_user) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Perform some SQL queries over the connection.
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, contact.getGuid());
            statement.setString(2, contact.getFriendName());
            statement.setString(3, contact.getFriendPhone());
            statement.setString(4, contact.getFriendAddress());
            statement.setString(5, contact.getParentName());
            statement.setString(6, contact.getParentPhone());
            statement.setString(7, contact.getUser().getGuid());
            int count = statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Encountered an error when executing given sql statement.", e);
        }
    }

    /**
     * Poistaa kontaktin tietokannasta
     * 
     * @param contact Poistettava kontakti
     * @throws Exception voi heittää virheen, jos ongelmia tietokantayhteydessä
     */
    public void deleteContact(Contact contact) throws Exception {
        Connection connection = connectToDB();
        if (connection != null) {
            System.out.println("Successfully created connection to database.");

            String query = "DELETE FROM contacts WHERE id = ?";

            // Perform some SQL queries over the connection.
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, contact.getGuid());
                int count = statement.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException("Encountered an error when executing given sql statement.", e);
            }
        } else {
            System.out.println("Failed to create connection to database.");
        }
        System.out.println("Execution finished.");
    }

    /** 
     * Luo PreparedStatement olio yhteystietojen hakemista varten
     * 
     * @param user Käyttäjä, jonka yhteystietoja haetaan
     * @param connection Tietokantayhteys
     * @return Palauttaa luodun PreparedStatement olion
     * @throws Exception voi heittää virheen, jos ongelmia tietokantayhteydessä
     */
    private PreparedStatement getStatementForAllContacts(User user, Connection connection) throws Exception {
        String query;
        String childQuery = "SELECT c.id, u.name, c.friendName, c.friendPhone, c.friendAddress, c.parentName, c.parentPhone, u.id AS user_id, u.role from contacts c, users u WHERE u.id = c.id_user and c.id_user = ?";
        String parentQuery = "SELECT c.id, u.name, c.friendName, c.friendPhone, c.friendAddress, c.parentName, c.parentPhone, u.id AS user_id, u.role from contacts c, users u WHERE u.id = c.id_user and c.id_user IN (SELECT users.id FROM users WHERE users.role = ?)";

        if (user.isParent()) {
            query = parentQuery;
        } else {
            query = childQuery;
        }

        PreparedStatement statement = connection.prepareStatement(query);
        if (!user.isParent()) {
            statement.setString(1, user.getGuid());
        } else {
            statement.setInt(1, User.CHILD);
        }
        return statement;
    }

    /**
     * Hakee käyttäjän yhteystiedot tietokannasta. Jos käyttäjä on lapsi, hakee
     * käyttäjän omat yhteystiedot. Jos käyttäjä on aikuinen, hakee kaikkien
     * lapsikäyttäjien yhteystiedot.
     *
     * @param user käyttäjä
     * @return yhteystiedot ArrayListana
     * @throws Exception voi heittää virheen, jos ongelmia tietokantayhteydessä
     */
    public ArrayList<Contact> getAllContacts(User user) throws Exception {
        ArrayList<Contact> contacts = new ArrayList();
        Connection connection = connectToDB();


        try {
            PreparedStatement statement = getStatementForAllContacts(user, connection);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                User contactUser = new User(results.getString(2), results.getInt(9), results.getString(8));
                Contact contact = new Contact(results.getString(3), results.getString(4), results.getString(5), results.getString(6), results.getString(7), results.getString(1));
                contact.setUser(contactUser);

                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new SQLException("Encountered an error when executing given sql statement.", e);
        }
        return contacts;
    }

}
