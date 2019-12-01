package mira.addressBook.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mira.addressBook.logics.Contact;
import mira.addressBook.logics.User;

/**
 *
 * @author Mira Vorne
 */
public class AddressBookGUI extends Application {

    TableView contactTable = new TableView();
    TableColumn<String, Contact> friendName = new TableColumn<>("Name");
    TableColumn<String, Contact> friendPhone = new TableColumn<>("Phone number");
    TableColumn<String, Contact> friendAddress = new TableColumn<>("Address");
    TableColumn<String, Contact> parentName = new TableColumn<>("Parent");
    TableColumn<String, Contact> parentPhone = new TableColumn<>("Parent's phone");
    User user;

    @Override
    public void start(Stage primaryStage) {
        friendName.setCellValueFactory(new PropertyValueFactory<>("friendName"));
        friendPhone.setCellValueFactory(new PropertyValueFactory<>("friendPhone"));
        friendAddress.setCellValueFactory(new PropertyValueFactory<>("friendAddress"));
        parentName.setCellValueFactory(new PropertyValueFactory<>("parentName"));
        parentPhone.setCellValueFactory(new PropertyValueFactory<>("parentPhone"));
        contactTable.getColumns().add(friendName);
        contactTable.getColumns().add(friendPhone);
        contactTable.getColumns().add(friendAddress);
        contactTable.getColumns().add(parentName);
        contactTable.getColumns().add(parentPhone);

        MenuBar menuBar = new MenuBar();
        Menu menu1 = new Menu("File");

        menuBar.getMenus().add(menu1);
        MenuItem menuItem1 = new MenuItem("Log in");
        MenuItem menuItem2 = new MenuItem("Add contact");
        MenuItem menuItem3 = new MenuItem("Exit");
        menuItem1.setOnAction(e -> {
            System.out.println("Log in not implemented yet.");
        });
        menuItem2.setOnAction(e -> {
            showAddContactWindow(user);
        });
        menuItem3.setOnAction(e -> {
            Platform.exit();
        });

        menu1.getItems().add(menuItem1);
        menu1.getItems().add(menuItem2);
        menu1.getItems().add(menuItem3);

        VBox vbox = new VBox(menuBar, contactTable);

        Button btn = new Button();
        btn.setText("Show contacts");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        user = new User("Pekka Puupää", true);
        user.addContact(new Contact("Pätkä", "555-1234567", "Pätkätie 8", "Justiina", "5559876543"));
        showContacts(user);
        Scene scene = new Scene(vbox);
        scene.getStylesheets().add("AddressBookStyles.css");

        primaryStage.setTitle("Address book");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAddContactWindow(User user) {
        Stage addContactWindow = new Stage();

        Label label1 = new Label("Name");
        Label label2 = new Label("Phone number");
        Label label3 = new Label("Address");
        Label label4 = new Label("Parent");
        Label label5 = new Label("Parent's phone");
        TextField txtName = new TextField();
        TextField txtPhone = new TextField();
        TextField txtAddress = new TextField();
        TextField txtParent = new TextField();
        TextField txtParentPhone = new TextField();
        Button button1 = new Button("Cancel");
        Button button2 = new Button("Save");

        button1.setOnAction(e -> {
            addContactWindow.close();    
        });
        // String friendName, String friendPhone, String friendAddress, String parentName, String parentPhone
        button2.setOnAction(e -> {
            Contact newContact = new Contact(txtName.getText(), txtPhone.getText(), txtAddress.getText(), txtParent.getText(), txtParentPhone.getText());
            user.addContact(newContact);
            showContacts(user);
            System.out.println("new contact added");
//            addContactWindow.close();    
        });

        GridPane gridPane = new GridPane();

        gridPane.add(label1, 0, 0, 1, 1);
        gridPane.add(txtName, 1, 0, 1, 1);
        gridPane.add(label2, 0, 1, 1, 1);
        gridPane.add(txtPhone, 1, 1, 1, 1);
        gridPane.add(label3, 0, 2, 1, 1);
        gridPane.add(txtAddress, 1, 2, 1, 1);
        gridPane.add(label4, 0, 3, 1, 1);
        gridPane.add(txtParent, 1, 3, 1, 1);
        gridPane.add(label5, 0, 4, 1, 1);
        gridPane.add(txtParentPhone, 1, 4, 1, 1);
        gridPane.add(button1, 0, 5, 1, 1);
        gridPane.add(button2, 1, 5, 1, 1);

        Scene scene = new Scene(gridPane);

        addContactWindow.setTitle("Add new contact");
        addContactWindow.setScene(scene);
        addContactWindow.showAndWait();
    }

    private void showContacts(User user) {
        contactTable.getItems().clear();
        ArrayList<Contact> contacts = user.getContacts();
        for (int i = 0; i < contacts.size(); i++) {
            contactTable.getItems().add(contacts.get(i));
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
