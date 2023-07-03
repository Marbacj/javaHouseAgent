package com.example.houseagent.service;

import com.example.houseagent.dao.ClientDao;
import com.example.houseagent.entity.Client;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

// Extend the Application class
public class clients extends Application {

    // Declare some global variables
    private TextField idField, firstNameField, lastNameField, phoneField, emailField, addressField, searchField;
    private Button addButton, editButton, removeButton, refreshButton, searchButton;
    private TableView<Client> table;

    // Override the start method
    @Override
    public void start(Stage primaryStage) {
        // Create a label for the ID field
        Label idLabel = new Label("ID:");

        // Create a text field for the ID field
        idField = new TextField();
        idField.setEditable(false);

        // Create a label for the first name field
        Label firstNameLabel = new Label("First Name:");

        // Create a text field for the first name field
        firstNameField = new TextField();

        // Create a label for the last name field
        Label lastNameLabel = new Label("Last Name:");

        // Create a text field for the last name field
        lastNameField = new TextField();

        // Create a label for the phone field
        Label phoneLabel = new Label("Phone:");

        // Create a text field for the phone field
        phoneField = new TextField();

        // Create a label for the email field
        Label emailLabel = new Label("Email:");

        // Create a text field for the email field
        emailField = new TextField();

        // Create a label for the address field
        Label addressLabel = new Label("Address:");

        // Create a text field for the address field
        addressField = new TextField();

        // Create buttons for adding, editing, removing, and refreshing client information
        addButton = new Button("Add");
        editButton = new Button("Edit");
        removeButton = new Button("Remove");
        refreshButton = new Button("Refresh");

        // Create a horizontal box for the buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(addButton, editButton, removeButton, refreshButton);

        // Create a grid pane for the fields and labels
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Add the fields and labels to the grid pane
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(firstNameLabel, 0, 1);
        gridPane.add(firstNameField, 1, 1);
        gridPane.add(lastNameLabel, 0, 2);
        gridPane.add(lastNameField, 1, 2);
        gridPane.add(phoneLabel, 0, 3);
        gridPane.add(phoneField, 1, 3);
        gridPane.add(emailLabel, 0, 4);
        gridPane.add(emailField, 1, 4);
        gridPane.add(addressLabel, 0, 5);
        gridPane.add(addressField, 1, 5);

        searchField = new TextField();

        //Create a button for the search function
        searchButton = new Button("Search");

        //Create a horizontal box for the search bar and button
        HBox searchBar = new HBox(10);
        searchBar.setPadding(new Insets(10));
        searchBar.getChildren().addAll(searchField, searchButton);



        //Create a scene with the border pane as the root node

        //create a table view for displaying client information
        // Create table columns for each client attribute
        table = new TableView<>();
        table.setPrefWidth(600);
        TableColumn<Client, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        TableColumn<Client, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        TableColumn<Client, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        TableColumn<Client, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        TableColumn<Client, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        TableColumn<Client, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        table.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, phoneColumn, emailColumn, addressColumn);

        //Create a border pane for the window layout
        BorderPane root = new BorderPane();
        root.setTop(searchBar);
        root.setCenter(table);
        root.setRight(gridPane);
        root.setBottom(buttonBox);
        Scene scene = new Scene(root, 800, 600);
        //Set the title and scene of the primary stage
        primaryStage.setTitle("Client Manager");
        primaryStage.setScene(scene);

        //Show the primary stage
        primaryStage.show();

    }
    public static void main(String []args){launch(args);}
}
