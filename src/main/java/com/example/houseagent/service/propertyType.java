package com.example.houseagent.service;

// Import the necessary JavaFX classes
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

// Extend the Application class
public class propertyType extends Application {

    // Declare some global variables
    private TextField idField, nameField, descriptionField;
    private Button addButton, editButton, removeButton, refreshButton, closeButton;
    private ComboBox<String> typeBox;

    // Override the start method
    @Override
    public void start(Stage primaryStage) {
        // Create a label for the ID field
        Label idLabel = new Label("ID:");

        // Create a text field for the ID field
        idField = new TextField();
        idField.setEditable(false);

        // Create a label for the name field
        Label nameLabel = new Label("Name:");

        // Create a text field for the name field
        nameField = new TextField();

        // Create a label for the description field
        Label descriptionLabel = new Label("Description:");

        // Create a text field for the description field
        descriptionField = new TextField();

        // Create buttons for adding, editing, removing, refreshing, and closing property type information
        addButton = new Button("Add");
        editButton = new Button("Edit");
        removeButton = new Button("Remove");
        refreshButton = new Button("Refresh");
        closeButton = new Button("Close");

        // Create a horizontal box for the buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(addButton, editButton, removeButton, refreshButton, closeButton);


        //Create a combo box for selecting property type
        typeBox = new ComboBox<>();
        typeBox.setItems(FXCollections.observableArrayList("Commercial", "Residential", "Industrial", "Land"));

        //Create a grid pane for the fields and labels
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        //Add the fields and labels to the grid pane
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(nameField, 1, 1);
        gridPane.add(descriptionLabel, 0, 2);
        gridPane.add(descriptionField, 1, 2);

        //Create a border pane for the window layout
        BorderPane root = new BorderPane();
        root.setLeft(gridPane);
        root.setRight(typeBox);
        root.setBottom(buttonBox);

        //Create a scene with the border pane as the root node
        Scene scene = new Scene(root, 600, 400);

        //Set the title and scene of the primary stage
        primaryStage.setTitle("Property Type");
        primaryStage.setScene(scene);

        //Show the primary stage
        primaryStage.show();
    }
}
