/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mira.addressBook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import mira.addressBook.logics.Contact;
import mira.addressBook.logics.User;

/**
 *
 * @author Mira Vorne
 */
public class SqlContactDao {

    private Connection connectToDB() {
        // Initialize connection variables.	
        String host = "testitietokanta2019.mysql.database.azure.com";
        String database = "addressbook";
        String sqluser = "abdemouser@testitietokanta2019";
        String password = "Suomi102";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //throw new ClassNotFoundException("Mysql driver NOT detected in library path.", e);
            System.out.println("SqlUserDao.findByUsername: Mysql driver NOT detected in library path.: " + e);
        }
        System.out.println("mysql driver loaded succesfully");

        Connection connection = null;

        // Initialize connection object
        try {
            String url = String.format("jdbc:mysql://%s/%s", host, database);

            // Set connection properties.
            Properties properties = new Properties();
            properties.setProperty("user", sqluser);
            properties.setProperty("password", password);
            properties.setProperty("useSSL", "true");
            properties.setProperty("verifyServerCertificate", "true");
            properties.setProperty("requireSSL", "false");

            // get connection
            connection = DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            System.out.println("Failed to create connection to database.: " + e);
//            throw new SQLException("Failed to create connection to database.", e);
        }
        return connection;
     }

    public void addContact(Contact contact) {
        Connection connection = connectToDB();
        if (connection != null) {
            System.out.println("Successfully created connection to database.");

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
System.out.println("sql1: " + statement);
                int count = statement.executeUpdate();
/*                query = "INSERT INTO user_contacts (id_contact, id_user) VALUES (?, ?)";
                statement = connection.prepareStatement(query);
                statement.setString(1, contact.getGuid());
                statement.setString(2, contact.getUser().getGuid());
System.out.println("sql2: " + statement);                
                count = statement.executeUpdate();*/
            } catch (SQLException e) {
                //throw new SQLException("Encountered an error when executing given sql statement.", e);
                System.out.println("Encountered an error when executing given sql statement." + e);
            }
        } else {
            System.out.println("Failed to create connection to database.");
        }
        System.out.println("Execution finished.");
    }

    public void deleteContact(Contact contact) {
        Connection connection = connectToDB();
        if (connection != null) {
            System.out.println("Successfully created connection to database.");

            String query = "DELETE FROM contacts WHERE id = ?";

            // Perform some SQL queries over the connection.
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, contact.getGuid());
System.out.println("sql1: " + statement);
                int count = statement.executeUpdate();
            } catch (SQLException e) {
                //throw new SQLException("Encountered an error when executing given sql statement.", e);
                System.out.println("Encountered an error when executing given sql statement." + e);
            }
        } else {
            System.out.println("Failed to create connection to database.");
        }
        System.out.println("Execution finished.");
    }

    
    /**
     * Hakee käyttäjän yhteystiedot tietokannasta. Jos käyttäjä on lapsi, hakee
     * käyttäjän omat yhteystiedot. Jos käyttäjä on aikuinen, hakee kaikkien
     * lapsikäyttäjien yhteystiedot.
     *
     * @param user käyttäjä
     * @return yhteystiedot ArrayListana
     */
    public ArrayList<Contact> getAllContacts(User user) {
        ArrayList<Contact> contacts = new ArrayList();
        Connection connection = connectToDB();
        if (connection != null) {
            System.out.println("Successfully created connection to database.");

            String childQuery = "SELECT c.id, u.name, c.friendName, c.friendPhone, c.friendAddress, c.parentName, c.parentPhone, u.id AS user_id, u.role from contacts c, users u WHERE u.id = c.id_user and c.id_user = ?";
            String adultQuery = "SELECT c.id, u.name, c.friendName, c.friendPhone, c.friendAddress, c.parentName, c.parentPhone, u.id AS user_id, u.role from contacts c, users u WHERE u.id = c.id_user and c.id_user IN (SELECT users.id FROM users WHERE users.role = ?)";
            String query;

            if (user.isAdult()) {
                query = adultQuery;
            } else {
                query = childQuery;
            }

            // Perform some SQL queries over the connection.
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                if (!user.isAdult()) {
                    statement.setString(1, user.getGuid());
                }
                else {
                    statement.setInt(1, User.CHILD);
                }
                ResultSet results = statement.executeQuery();
                while (results.next()) {
                    User contactUser = new User(results.getString(2), results.getInt(9), results.getString(8));
                    Contact contact = new Contact(/*results.getString(2), */results.getString(3), results.getString(4), results.getString(5), results.getString(6), results.getString(7), results.getString(1));
                    contact.setUser(contactUser);
                    
                    contacts.add(contact);
                }
            } catch (SQLException e) {
                //throw new SQLException("Encountered an error when executing given sql statement.", e);
                System.out.println("Encountered an error when executing given sql statement." + e);
            }
        } else {
            System.out.println("Failed to create connection to database.");
        }
        System.out.println("Execution finished.");
        return contacts;
    }

}
