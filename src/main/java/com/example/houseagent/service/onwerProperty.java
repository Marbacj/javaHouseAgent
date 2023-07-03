package com.example.houseagent.service;


// Import the necessary JavaFX classes
import com.example.houseagent.entity.Owner;
import com.example.houseagent.entity.Property;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

// Extend the Application class
public class onwerProperty extends Application {

    // Declare some global variables
    private Button closeButton, ownerPropertiesButton;
    private TableView<Property> table;

    // Override the start method
    @Override
    public void start(Stage primaryStage) {
        // Create a button for closing the window
        closeButton = new Button("Close");

        // Create a button for viewing the owner properties
        ownerPropertiesButton = new Button("Owner Properties");

        // Create a horizontal box for the buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(closeButton, ownerPropertiesButton);

        // Create a table view for displaying property information
        table = new TableView<>();
        table.setPrefWidth(800);

        // Create table columns for each property attribute
        TableColumn<Property, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        TableColumn<Property, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        TableColumn<Property, Double> squareFtColumn = new TableColumn<>("Square ft");
        squareFtColumn.setCellValueFactory(cellData -> cellData.getValue().squareFtProperty().asObject());
        TableColumn<Property, String> ownerColumn = new TableColumn<>("Owner");
        ownerColumn.setCellValueFactory(cellData -> cellData.getValue().ownerProperty());
        TableColumn<Property, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());


        //Create more table columns for the remaining property attributes
        TableColumn<Property, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        TableColumn<Property, Integer> bedroomsColumn = new TableColumn<>("Bedrooms");
        bedroomsColumn.setCellValueFactory(cellData -> cellData.getValue().bedroomsProperty().asObject());
        TableColumn<Property, Integer> bathroomsColumn = new TableColumn<>("Bathrooms");
        bathroomsColumn.setCellValueFactory(cellData -> cellData.getValue().bathroomsProperty().asObject());
        TableColumn<Property, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        TableColumn<Property, Boolean> balconyColumn = new TableColumn<>("Balcony");
        balconyColumn.setCellValueFactory(cellData -> cellData.getValue().balconyProperty());
        TableColumn<Property, Boolean> poolColumn = new TableColumn<>("Pool");
        poolColumn.setCellValueFactory(cellData -> cellData.getValue().poolProperty());
        TableColumn<Property, Boolean> backyardColumn = new TableColumn<>("Backyard");
        backyardColumn.setCellValueFactory(cellData -> cellData.getValue().backyardProperty());
        TableColumn<Property, Boolean> garageColumn = new TableColumn<>("Garage");
        garageColumn.setCellValueFactory(cellData -> cellData.getValue().garageProperty());
        TableColumn<Property, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        //Add the columns to the table view
        table.getColumns().addAll(idColumn, typeColumn, squareFtColumn, ownerColumn, priceColumn, addressColumn, bedroomsColumn, bathroomsColumn, ageColumn, balconyColumn, poolColumn, backyardColumn, garageColumn, descriptionColumn);

        //Create a border pane for the window layout
        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setBottom(buttonBox);

        //Create a scene with the border pane as the root node
        Scene scene = new Scene(root, 800, 600);

        //Set the title and scene of the primary stage
        primaryStage.setTitle("Owner Properties");
        primaryStage.setScene(scene);

        //Show the primary stage
        primaryStage.show();
    }
    public static void main(String []args){launch(args);}
}


