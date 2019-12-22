package mira.addressbook.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import mira.addressbook.dao.SqlUserDao;
import mira.addressbook.dao.SqlContactDao;
import mira.addressbook.logics.Contact;
import mira.addressbook.logics.User;

/**
 *
 * @author Mira Vorne
 */
public class AddressBookGUI extends Application {

    TableView contactTable = new TableView();
    TableColumn<String, Contact> childName = new TableColumn<>("Child");
    TableColumn<String, Contact> friendName = new TableColumn<>("Name");
    TableColumn<String, Contact> friendPhone = new TableColumn<>("Phone number");
    TableColumn<String, Contact> friendAddress = new TableColumn<>("Address");
    TableColumn<String, Contact> parentName = new TableColumn<>("Parent");
    TableColumn<String, Contact> parentPhone = new TableColumn<>("Parent's phone");
    Label loggedInLabel = new Label("");
    MenuItem menuItem1;
    MenuItem menuItem2;
    MenuItem menuItem3;
    MenuItem menuItem4;
    User user;

    /**
     * Sovelluksen (JavaFX) käynnistäminen
     *
     * @param primaryStage primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        childName.setCellValueFactory(new PropertyValueFactory<>("childName"));
        friendName.setCellValueFactory(new PropertyValueFactory<>("friendName"));
        friendPhone.setCellValueFactory(new PropertyValueFactory<>("friendPhone"));
        friendAddress.setCellValueFactory(new PropertyValueFactory<>("friendAddress"));
        parentName.setCellValueFactory(new PropertyValueFactory<>("parentName"));
        parentPhone.setCellValueFactory(new PropertyValueFactory<>("parentPhone"));
        contactTable.getColumns().add(childName);
        contactTable.getColumns().add(friendName);
        contactTable.getColumns().add(friendPhone);
        contactTable.getColumns().add(friendAddress);
        contactTable.getColumns().add(parentName);
        contactTable.getColumns().add(parentPhone);

        MenuBar menuBar = new MenuBar();
        Menu menu1 = new Menu("File");

        menuBar.getMenus().add(menu1);
        menuItem1 = new MenuItem("Log in");
        menuItem2 = new MenuItem("Add contact");
        menuItem2.setDisable(true);
        menuItem4 = new MenuItem("Add new user");
        menuItem3 = new MenuItem("Exit");
        menuItem1.setOnAction(e -> {
            try {
                showLoginWindow();
            } catch (Exception ex) {
                System.out.println("exception: " + ex);
            }
            //System.out.println("Log in not implemented yet.");
        });
        menuItem2.setOnAction(e -> {
            try {
                showAddContactWindow(user);
            } catch (Exception ex) {
                System.out.println("exception: " + ex);
            }
        });
        menuItem3.setOnAction(e -> {
            Platform.exit();
        });
        menuItem4.setOnAction(e -> {
            try {
                showAddNewUserWindow();
            } catch (Exception ex) {
                System.out.println("exception: " + ex);
            }
        });

        menu1.getItems().add(menuItem1);
        menu1.getItems().add(menuItem2);
        menu1.getItems().add(menuItem4);
        menu1.getItems().add(menuItem3);

        loggedInLabel.setText("Log in to use application.");

        Button btnDelete = new Button();
        btnDelete.setText("Delete selected contact");
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<Contact> row, allRows;
                allRows = contactTable.getItems();
                row = contactTable.getSelectionModel().getSelectedItems();
                for (Contact contact : row) {
                    try {
                        deleteContact(contact);
                        allRows.remove(contact);
                    } catch (Exception ex) {
                        System.out.println("Exception: " + ex);
                    }
                }
            }
        });

        VBox vbox = new VBox(menuBar, loggedInLabel, contactTable, btnDelete);

        Scene scene = new Scene(vbox);
        scene.getStylesheets().add("AddressBookStyles.css");

        primaryStage.setTitle("Address book");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /** 
     * Palauttaa kaikki lapsikäyttäjät, jotka käyttäjä saa nähdä.
     * Jos kyseessä on lapsikäyttäjä, palauttaa ArrayListan, jossa on vain käyttäjä itse
     * Jos kyseessä on vanehmpi, palauttaa ArrayListan, jossa on kaikki lapsikäyttäjät
     * @param user Käyttäjä, jonka perusteella palautetaan lapsikäyttäjät
     * @return ArrayList<User> lista lapsikäyttäjistä.
     * @throws Exception 
     */
    private ArrayList<User> getAllChildUsers(User user) throws Exception {
        if (user.isParent()) {
            SqlUserDao dao = new SqlUserDao();
            return dao.getAllChildUsers();
        } else {
            ArrayList<User> users = new ArrayList<>();
            users.add(user);
            return users;
        }
    }

    /** 
     * Poistaa yhteystiedon tietokannasta
     * 
     * @param contact poistettava yhteystieto
     * @throws Exception 
     */
    private void deleteContact(Contact contact) throws Exception {
        SqlContactDao dao = new SqlContactDao();
        dao.deleteContact(contact);
    }

    /** 
     * Näyttää uuden yhteystiedon lisäysikkunan. 
     * Jos käyttäjä on vanhempi, valittavaksi tulee kenelle lapselle yhteystieto lisätään.
     * Jos käyttäjä on lapsi, yhteystieto lisätään käyttäjälle itselleen.
     * 
     * @param user Käyttäjä, joka käyttää sovellusta
     * @throws Exception 
     */
    private void showAddContactWindow(User user) throws Exception {
        Stage addContactWindow = new Stage();

        Label label1 = new Label("Name");
        Label label2 = new Label("Phone number");
        Label label3 = new Label("Address");
        Label label4 = new Label("Parent");
        Label label5 = new Label("Parent's phone");
        Label label6 = new Label("Child");
        Label infoLabel = new Label();
        TextField txtName = new TextField();
        TextField txtPhone = new TextField();
        TextField txtAddress = new TextField();
        TextField txtParent = new TextField();
        TextField txtParentPhone = new TextField();
        Button button1 = new Button("Cancel");
        Button button2 = new Button("Save");
        ObservableList<User> childUsers = FXCollections.observableArrayList();
        ArrayList<User> users = getAllChildUsers(user);
        childUsers.addAll(users);
        ComboBox<User> usersCombo = new ComboBox<>();
        usersCombo.setItems(childUsers);
        usersCombo.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User object) {
                return object.getName();
            }

            @Override
            public User fromString(String string) {
                return usersCombo.getItems().stream().filter(ap
                        -> ap.getName().equals(string)).findFirst().orElse(null);
            }
        });

        button1.setOnAction(e -> {
            addContactWindow.close();
        });
        button2.setOnAction(e -> {
            if (usersCombo.getValue() == null) {
                infoLabel.setText("Please select Child!");
            } else {
                try {
                    Contact newContact = new Contact(txtName.getText(), txtPhone.getText(), txtAddress.getText(), txtParent.getText(), txtParentPhone.getText());
                    newContact.setUser(usersCombo.getValue());

                    SqlContactDao contactDao = new SqlContactDao();
                    contactDao.addContact(newContact);
                    user.addContact(newContact);
                    showContacts(user);
                    System.out.println("new contact added");
                } catch (Exception ex) {
                    infoLabel.setText("Exception: " + ex);
                }
            }
        });

        GridPane gridPane = new GridPane();

        gridPane.add(label6, 0, 0, 1, 1);
        gridPane.add(usersCombo, 1, 0, 1, 1);
        gridPane.add(label1, 0, 1, 1, 1);
        gridPane.add(txtName, 1, 1, 1, 1);
        gridPane.add(label2, 0, 2, 1, 1);
        gridPane.add(txtPhone, 1, 2, 1, 1);
        gridPane.add(label3, 0, 3, 1, 1);
        gridPane.add(txtAddress, 1, 3, 1, 1);
        gridPane.add(label4, 0, 4, 1, 1);
        gridPane.add(txtParent, 1, 4, 1, 1);
        gridPane.add(label5, 0, 5, 1, 1);
        gridPane.add(txtParentPhone, 1, 5, 1, 1);
        gridPane.add(button1, 0, 6, 1, 1);
        gridPane.add(button2, 1, 6, 1, 1);
        gridPane.add(infoLabel, 0, 7, 2, 1);

        Scene scene = new Scene(gridPane);

        addContactWindow.setTitle("Add new contact");
        addContactWindow.setScene(scene);
        addContactWindow.showAndWait();
    }

    /** 
     * Lisää käyttäjän yhteyshenkilöt contactTable:en
     * Jos kyseessä on lapsikäyttäjä, näytetään vain omat yhteyshenkilöt
     * Jos kyseessä on vanhempi, näytetään kaikkien lasten yhteyshenkilöt
     * 
     * @param user Käyttäjä, jonka perusteella yhteyshenkilöt näytetään.
     */
    private void showContacts(User user) {
        contactTable.getItems().clear();
        ArrayList<Contact> contacts = user.getContacts();
        for (int i = 0; i < contacts.size(); i++) {
            contactTable.getItems().add(contacts.get(i));
        }
        autoResizeColumns(contactTable);
    }

    /**
     * Auto resize TableView
     * https://stackoverflow.com/questions/14650787/javafx-column-in-tableview-auto-fit-size
     *
     * @param table TableView to auto resize
     */
    private void autoResizeColumns(TableView<?> table) {
        //Set the right policy
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        table.getColumns().stream().forEach((column)
                -> {
            //Minimal width = columnheader
            Text t = new Text(column.getText());
            double max = t.getLayoutBounds().getWidth();
            for (int i = 0; i < table.getItems().size(); i++) {
                //cell must not be empty
                if (column.getCellData(i) != null) {
                    t = new Text(column.getCellData(i).toString());
                    double calcwidth = t.getLayoutBounds().getWidth();
                    //remember new max-width
                    if (calcwidth > max) {
                        max = calcwidth;
                    }
                }
            }
            //set the new max-widht with some extra space
            column.setPrefWidth(max + 10.0d);
            // https://stackoverflow.com/questions/33348757/javafx-8-tablecolumn-setprefwidth-doing-nothing-if-user-manually-resizes-the-co
            column.setMinWidth(max + 10.0d);
            column.setMaxWidth(max + 10.0d);
            column.setMinWidth(0);
            column.setMaxWidth(5000);
        });
    }

    /** 
     * Käyttäjän sisäänkirjautuminen
     * 
     * 
     * @param username käyttäjänimi, joka kirjataan sisään
     * @return true, jos käyttäjä löytyi. false, jos käyttäjää ei löydy
     * @throws Exception 
     */
    private boolean loginUser(String username) throws Exception {
        SqlUserDao userDao = new SqlUserDao();
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;
        } else {
            showContacts(user);
            this.user = user;
            return true;
        }
    }

    /** 
     * Sisäänkirjausikkunan näyttäminen
     */
    private void showLoginWindow() {
        Stage loginWindow = new Stage();

        Label label1 = new Label("Name");
        TextField txtName = new TextField();
        Button button1 = new Button("Cancel");
        Button button2 = new Button("Login");
        Label errorLabel = new Label("");
        errorLabel.setTextFill(Color.RED);

        button1.setOnAction(e -> {
            loginWindow.close();
        });
        button2.setOnAction(e -> {
            try {
                boolean loginOk = loginUser(txtName.getText());
                if (!loginOk) {
                    errorLabel.setText("Wrong user name");
                } else {
                    loggedInLabel.setText("Logged in user: " + this.user.getName());
                    menuItem2.setDisable(false);
                    loginWindow.close();
                }
            } catch (Exception ex) {
                errorLabel.setText("Connection problem: " + ex);
            }
        });

        GridPane gridPane = new GridPane();

        gridPane.add(label1, 0, 0, 1, 1);
        gridPane.add(txtName, 1, 0, 1, 1);
        gridPane.add(button1, 0, 1, 1, 1);
        gridPane.add(button2, 1, 1, 1, 1);
        gridPane.add(errorLabel, 0, 2, 2, 1);

        Scene scene = new Scene(gridPane);

        loginWindow.setTitle("Login");
        loginWindow.setScene(scene);
        loginWindow.showAndWait();
    }

    /** 
     * Käyttäjän lisääminen
     * 
     * @param username käyttäjänimi, joka lisätään
     * @param isParent onko lisättävä käyttäjä vanhempi
     * @return lisätyn käyttäjän User olio 
     * @throws Exception 
     */
    private User addUser(String username, boolean isParent) throws Exception {
        SqlUserDao userDao = new SqlUserDao();

        User user = new User(username, isParent);
        boolean ok = userDao.addUser(user);
        if (ok) {
            return user;
        } else {
            return null;
        }

    }

    /** 
     * Käyttäjän lisäämisikkunan näyttäminen
     * 
     */
    private void showAddNewUserWindow() {
        Stage addNewUserWindow = new Stage();

        Label label1 = new Label("Name");
        TextField txtName = new TextField();
        Button button1 = new Button("Cancel");
        Button button2 = new Button("Add User");
        Label infoLabel = new Label("");
        CheckBox checkBox1 = new CheckBox("Parent user");

        button1.setOnAction(e -> {
            addNewUserWindow.close();
        });
        button2.setOnAction(e -> {
            try {
                User user = addUser(txtName.getText(), checkBox1.isSelected());
                if (user == null) {
                    infoLabel.setText("User not added. User with same name.");
                } else {
                    infoLabel.setText("User added.");
                }
            } catch (Exception ex) {
                infoLabel.setText("Exception: " + ex);
            }
        });

        GridPane gridPane = new GridPane();

        gridPane.add(label1, 0, 0, 1, 1);
        gridPane.add(txtName, 1, 0, 1, 1);
        gridPane.add(checkBox1, 1, 1, 1, 1);
        gridPane.add(button1, 0, 2, 1, 1);
        gridPane.add(button2, 1, 2, 1, 1);
        gridPane.add(infoLabel, 0, 3, 2, 1);

        Scene scene = new Scene(gridPane);

        addNewUserWindow.setTitle("Add user");
        addNewUserWindow.setScene(scene);
        addNewUserWindow.showAndWait();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
