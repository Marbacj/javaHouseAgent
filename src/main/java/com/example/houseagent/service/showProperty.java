package com.example.houseagent.service;// Import the necessary JavaFX classes
import com.example.houseagent.entity.Property;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

// Extend the Application class
public class showProperty extends Application {

    // Declare some global variables
    private Button closeButton, showPropertiesButton;
    private TableView<Property> table;
    private TextField addressField, descriptionField;

    // Override the start method
    @Override
    public void start(Stage primaryStage) {
        // Create a button for closing the window
        closeButton = new Button("Close");

        // Create a button for showing properties
        showPropertiesButton = new Button("Show Properties");

        // Create a horizontal box for the buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(closeButton, showPropertiesButton);

        // Create a table view for displaying property information
        table = new TableView<>();
        table.setPrefWidth(800);

        // Create table columns for each property attribute
        TableColumn<Property, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        TableColumn<Property, Double> squareFtColumn = new TableColumn<>("Square ft");
        squareFtColumn.setCellValueFactory(cellData -> cellData.getValue().squareFtProperty().asObject());



        //Create more table columns for the remaining property attributes
        TableColumn<Property, String> ownerColumn = new TableColumn<>("Owner");
        ownerColumn.setCellValueFactory(cellData -> cellData.getValue().ownerProperty());
        TableColumn<Property, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        TableColumn<Property, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        TableColumn<Property, Integer> bedroomsColumn = new TableColumn<>("Bedrooms");
        bedroomsColumn.setCellValueFactory(cellData -> cellData.getValue().bedroomsProperty().asObject());
        TableColumn<Property, Integer> bathroomsColumn = new TableColumn<>("Bathrooms");
        bathroomsColumn.setCellValueFactory(cellData -> cellData.getValue().bathroomsProperty().asObject());
        TableColumn<Property, Boolean> balconyColumn = new TableColumn<>("Balcony");
        balconyColumn.setCellValueFactory(cellData -> cellData.getValue().balconyProperty());
        TableColumn<Property, Boolean> poolColumn = new TableColumn<>("Pool");
        poolColumn.setCellValueFactory(cellData -> cellData.getValue().poolProperty());
        TableColumn<Property, Boolean> garageColumn = new TableColumn<>("Garage");
        garageColumn.setCellValueFactory(cellData -> cellData.getValue().garageProperty());
        TableColumn<Property, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        //Add the columns to the table view
        table.getColumns().addAll(typeColumn, squareFtColumn, ownerColumn, priceColumn, addressColumn, bedroomsColumn, bathroomsColumn, balconyColumn, poolColumn, garageColumn, descriptionColumn);

        //Create a label for the address field
        Label addressLabel = new Label("Address:");

        //Create a text field for the address field
                addressField = new TextField();

        //Create a label for the description/comment field
        Label descriptionLabel = new Label("Description/Comment:");

        //Create a text field for the description/comment field
        descriptionField = new TextField();

        //Create a border pane for the window layout
        BorderPane root = new BorderPane();
        root.setTop(table);
        root.setBottom(buttonBox);
        root.setLeft(addressLabel);
        root.setCenter(addressField);
        root.setRight(descriptionLabel);
        root.setBottom(descriptionField);

        //Create a scene with the border pane as the root node
        Scene scene = new Scene(root, 800, 600);

        //Set the title and scene of the primary stage
        primaryStage.setTitle("Show Properties");
        primaryStage.setScene(scene);

        //Show the primary stage
        primaryStage.show();
    }
    public static void main(String[]args){launch(args);}
}

