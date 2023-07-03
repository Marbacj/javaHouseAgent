package com.example.houseagent.service;

// Import the necessary JavaFX classes
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

// Extend the Application class
public class propertySearch extends Application {

    // Declare some global variables
    private TextField idField, squareFtField, ownerIdField, priceField, addressField, descriptionField, bedroomsField, ageField;
    private Button addButton, editButton, removeButton, showButton, searchButton;
    private ComboBox<String> typeBox;
    private CheckBox balconyBox, poolBox, garageBox;

    // Override the start method
    @Override
    public void start(Stage primaryStage) {
        // Create a label for the ID field
        Label idLabel = new Label("ID:");

        // Create a text field for the ID field
        idField = new TextField();

        // Create a button for the search function
        searchButton = new Button("Search");

        // Create a label for the type field
        Label typeLabel = new Label("Type:");

        // Create a combo box for selecting property type
        typeBox = new ComboBox<>();
        typeBox.setItems(FXCollections.observableArrayList("Commercial", "Residential", "Industrial", "Land"));

        // Create a label for the square feet field
        Label squareFtLabel = new Label("Square ft:");

        // Create a text field for the square feet field
        squareFtField = new TextField();
        //Some additional sentences are:

        //Create a label for the owner ID field
        Label ownerIdLabel = new Label("Owner ID:");

        //Create a text field for the owner ID field
        ownerIdField = new TextField();

        //Create a label for the price field
        Label priceLabel = new Label("Price:");

        //Create a text field for the price field
        priceField = new TextField();

        //Create a label for the address field
        Label addressLabel = new Label("Address:");

        //Create a text field for the address field
        addressField = new TextField();

        //Create a label for the description/comment field
        Label descriptionLabel = new Label("Description/Comment:");

        //Create a text field for the description/comment field
        descriptionField = new TextField();

        //Create buttons for adding, editing, removing, and showing properties
        addButton = new Button("Add");
        editButton = new Button("Edit");
        removeButton = new Button("Remove");
        showButton = new Button("Show");

        //Create a horizontal box for the buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(addButton, editButton, removeButton, showButton);

        //Create a label for the property features section
        Label featuresLabel = new Label("Property Features:");

        //Create fields and checkboxes for property features such as bedrooms, age of the house, balcony, pool, and garage
        bedroomsField = new TextField();
        ageField = new TextField();
        balconyBox = new CheckBox("Balcony");
        poolBox = new CheckBox("Pool");
        garageBox = new CheckBox("Garage");

        //Create a grid pane for the fields and labels
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        //Add the fields and labels to the grid pane
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(searchButton, 2, 0);
        gridPane.add(typeLabel, 0, 1);
        gridPane.add(typeBox, 1, 1);
        gridPane.add(squareFtLabel, 0, 2);
        gridPane.add(squareFtField, 1, 2);
        gridPane.add(ownerIdLabel, 0, 3);
        gridPane.add(ownerIdField, 1, 3);
        gridPane.add(priceLabel, 0, 4);
        gridPane.add(priceField, 1, 4);
        gridPane.add(addressLabel, 0, 5);
        gridPane.add(addressField, 1, 5);
        gridPane.add(descriptionLabel, 0, 6);
        gridPane.add(descriptionField, 1, 6);
        gridPane.add(featuresLabel, 0, 7);
        gridPane.add(bedroomsField, 1, 7);
        gridPane.add(ageField, 2, 7);
        gridPane.add(balconyBox, 1, 8);
        gridPane.add(poolBox, 2, 8);
        gridPane.add(garageBox, 3, 8);

        //Create a border pane for the window layout
        BorderPane root = new BorderPane();
        root.setCenter(gridPane);
        root.setBottom(buttonBox);

        //Create a scene with the border pane as the root node
        Scene scene = new Scene(root, 800, 600);

        //Set the title and scene of the primary stage
        primaryStage.setTitle("Property Search");
        primaryStage.setScene(scene);

        //Show the primary stage
        primaryStage.show();
    }
    public static void main(String []args){launch(args);}
}

