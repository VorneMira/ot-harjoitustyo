package mira.addressBook.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        
        
        VBox vbox = new VBox(contactTable);
        
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
        User user = new User("Pekka Puupää", true);
        user.addContact(new Contact("Pätkä", "555-1234567", "Pätkätie 8", "Justiina", "5559876543"));
        showContacts(user);
        Scene scene = new Scene(vbox);
        
        primaryStage.setTitle("Address book");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void showContacts(User user) {
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
