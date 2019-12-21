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
import java.util.Properties;
import mira.addressBook.logics.Contact;
import mira.addressBook.logics.User;

/**
 *
 * @author Mira Vorne
 */
public class SqlUserDao {

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

    public boolean addUser(User user) {
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
            /*if (results.first()) {
                    String uname = results.getString(1);
                    int role = results.getInt(2);
                    String guid = results.getString(3);
                    User user = new User(uname, role, guid);
                    SqlContactDao contactDao = new SqlContactDao();
                    ArrayList<Contact> contacts = contactDao.getAllContacts(user);
                    user.clearContacts();
                    for (Contact contact: contacts) {
                        user.addContact(contact);
                    }
                    return user;
                }
                else {
                    return null;
                }*/
        } catch (SQLException e) {
            //throw new SQLException("Encountered an error when executing given sql statement.", e);
            System.out.println("Encountered an error when executing given sql statement." + e);
            return false;
        }
        return true;
    }

    /**
     * Käyttäjän hakeminen tietokannasta
     *
     * @param username käyttäjän nimi, jota haetaan
     * @return null, jos käyttäjää ei löydy. User luokan ilmentymän, jos
     * käyttäjä löytyy. Käyttäjän yhteystiedot haetaan myös SqlContactDao:n
     * avulla
     */
    public User findByUsername(String username) {
        Connection connection = connectToDB();

        if (connection != null) {
            System.out.println("Successfully created connection to database.");

            // Perform some SQL queries over the connection.
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT name, role, id from users WHERE name = ?");
                statement.setString(1, username);
                ResultSet results = statement.executeQuery();
                if (results.first()) {
                    String uname = results.getString(1);
                    int role = results.getInt(2);
                    String guid = results.getString(3);
                    User user = new User(uname, role, guid);
                    SqlContactDao contactDao = new SqlContactDao();
                    ArrayList<Contact> contacts = contactDao.getAllContacts(user);
                    user.clearContacts();
                    for (Contact contact : contacts) {
                        user.addContact(contact);
                    }
                    return user;
                } else {
                    return null;
                }
            } catch (SQLException e) {
                //throw new SQLException("Encountered an error when executing given sql statement.", e);
                System.out.println("Encountered an error when executing given sql statement." + e);
            }
        } else {
            System.out.println("Failed to create connection to database.");
        }
        System.out.println("Execution finished.");
        return null;
    }

    public ArrayList<User> getAllChildUsers() {
        Connection connection = connectToDB();
        ArrayList<User> childUsers = new ArrayList<>();

        if (connection != null) {
            System.out.println("Successfully created connection to database.");

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
                //throw new SQLException("Encountered an error when executing given sql statement.", e);
                System.out.println("Encountered an error when executing given sql statement." + e);
            }
        } else {
            System.out.println("Failed to create connection to database.");
        }
        System.out.println("Execution finished.");
        return null;
    }
}
