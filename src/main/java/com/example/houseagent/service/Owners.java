package com.example.houseagent.service;

import com.example.houseagent.dao.OwnerDao;
import com.example.houseagent.entity.Owner;
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
public class Owners extends Application {

    // Declare some global variables
    private TextField idField, firstNameField, lastNameField, phoneField, emailField, addressField;
    private Button addButton, editPropertiesButton, removeButton, refreshButton;
    private TableView<Owner> table;

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

        // Create buttons for adding, editing properties, removing, and refreshing owner information
        addButton = new Button("Add");
        editPropertiesButton = new Button("Edit Properties");
        removeButton = new Button("Remove");
        refreshButton = new Button("Refresh");

        // Create a horizontal box for the buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(addButton, editPropertiesButton, removeButton, refreshButton);

        // Create a grid pane for the fields and labels
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
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

        //Create a table view for displaying owner information
        table = new TableView<>();
        table.setPrefWidth(600);

        //Create table columns for each owner attribute
        TableColumn<Owner, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        TableColumn<Owner, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        TableColumn<Owner, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        TableColumn<Owner, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        TableColumn<Owner, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());

        //Add the columns to the table view
        table.getColumns().addAll(firstNameColumn, lastNameColumn, phoneColumn, emailColumn, addressColumn);

        //Create a border pane for the window layout
        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setRight(gridPane);
        root.setBottom(buttonBox);

        //Create a scene with the border pane as the root node
        Scene scene = new Scene(root, 800, 600);

        //Set the title and scene of the primary stage
        primaryStage.setTitle("Owners");
        primaryStage.setScene(scene);

        //Show the primary stage
        primaryStage.show();
    }
    public static void main(String []args){launch(args);}
}