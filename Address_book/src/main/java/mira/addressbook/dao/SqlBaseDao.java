/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mira.addressbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Mira Vorne
 */
public class SqlBaseDao {

    private boolean useTestDB = false;
    private String host;
    private String database;
    private String sqluser;
    private String password;

    public SqlBaseDao() {
    }

    public SqlBaseDao(boolean useTestDB) {
        this.useTestDB = useTestDB;
    }

    public void initTestDB() throws Exception {
        if (this.useTestDB) {
            Connection connection = connectToDB();
            String query = "delete from contacts";
            PreparedStatement statement = connection.prepareStatement(query);
            int count = statement.executeUpdate();
            query = "delete from users; ";
            statement = connection.prepareStatement(query);
            count = statement.executeUpdate();
            query = "insert into users (id, name, role) values ('nellaguid', 'Nella', 10);";
            statement = connection.prepareStatement(query);
            count = statement.executeUpdate();
            query = "insert into users (id, name, role) values ('iinaguid', 'Iina', 10);";
            statement = connection.prepareStatement(query);
            count = statement.executeUpdate();
            query = "insert into users (id, name, role) values ('alexguid', 'Alex', 10);";
            statement = connection.prepareStatement(query);
            count = statement.executeUpdate();
            query = "insert into users (id, name, role) values ('miraguid', 'Mira', 20);";
            statement = connection.prepareStatement(query);
            count = statement.executeUpdate();
            query = "insert into contacts (id, friendName, friendPhone, friendAddress, parentName, parentPhone, id_user) values ('46bf0e77-d96b-49fc-8d69-97df8e505883', 'Emma', '+358442222222', 'Kuja 2, 15100 Lahti', 'Liisa', '+358453333333', 'nellaguid');";
            statement = connection.prepareStatement(query);
            count = statement.executeUpdate();
            query = "insert into contacts (id, friendName, friendPhone, friendAddress, parentName, parentPhone, id_user) values ('4a392f1d-32a2-4af2-9303-f2aa3a821a59', 'Heikki', '+358501234567', 'Katu 1, 00100 Helsinki', 'Anna-Maija', '+358401234567', 'nellaguid');";
            statement = connection.prepareStatement(query);
            count = statement.executeUpdate();
            query = "insert into contacts (id, friendName, friendPhone, friendAddress, parentName, parentPhone, id_user) values ('760cad35-0969-4663-8f40-56841b0a943e', 'Alex', '+358401111111', 'Tie 5, 00940 Helsinki', 'Heikki', '+358501234567', 'iinaguid')";
            statement = connection.prepareStatement(query);
            count = statement.executeUpdate();
        }
    }

    private void initConnectionVariables() {
        // Initialize connection variables.	
        this.host = "testitietokanta2019.mysql.database.azure.com";
        this.database = "addressbook";
        if (this.useTestDB) {
            database = "addressbook_test";
        }
        this.sqluser = "abdemouser@testitietokanta2019";
        this.password = "Suomi102";
    }

    private Properties getConnectionProperties() {
        Properties properties = new Properties();
        properties.setProperty("user", this.sqluser);
        properties.setProperty("password", this.password);
        properties.setProperty("useSSL", "true");
        properties.setProperty("verifyServerCertificate", "true");
        properties.setProperty("requireSSL", "false");
        return properties;
    }

    protected Connection connectToDB() throws Exception {
        initConnectionVariables();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Mysql driver NOT detected in library path.", e);
        }

        Connection connection = null;

        // Initialize connection object
        try {
            String url = String.format("jdbc:mysql://%s/%s", this.host, this.database);
            // Set connection properties.
            Properties properties = getConnectionProperties();

            // get connection
            connection = DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            throw new SQLException("Failed to create connection to database.", e);
        }
        return connection;
    }

}
