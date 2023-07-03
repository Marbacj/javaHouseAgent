package com.example.houseagent.service;

// Import the necessary JavaFX classes
import com.example.houseagent.entity.Client;
import com.example.houseagent.entity.Property;
import com.example.houseagent.entity.sale;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Extend the Application class
public class salesManagement extends Application {

    // Declare some global variables
    private Button editButton, removeButton, refreshButton, addNewClientButton, addNewPropertyButton;
    private TableView<sale> salesTable;
    private TableView<Client> clientsTable;
    private TableView<Property> propertiesTable;

    // Override the start method
    @Override
    public void start(Stage primaryStage) {
        // Create a label for the sales list
        Label salesLabel = new Label("Sales List");

        // Create a table view for displaying sales information
        salesTable = new TableView<>();
        salesTable.setPrefWidth(400);

        // Create table columns for each sale attribute
        TableColumn<sale, Integer> propertyIdColumn = new TableColumn<>("Property ID");
        propertyIdColumn.setCellValueFactory(cellData -> cellData.getValue().propertyIdProperty().asObject());
        TableColumn<sale, Integer> clientIdColumn = new TableColumn<>("Client ID");
        clientIdColumn.setCellValueFactory(cellData -> cellData.getValue().clientIdProperty().asObject());
        TableColumn<sale, Double> finalPriceColumn = new TableColumn<>("Final Price");
        finalPriceColumn.setCellValueFactory(cellData -> cellData.getValue().finalPriceProperty().asObject());
        TableColumn<sale, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        //Add the columns to the table view
        salesTable.getColumns().addAll(propertyIdColumn, clientIdColumn, finalPriceColumn, dateColumn);

        //Create a label for the clients list
        Label clientsLabel = new Label("Clients List");

        //Create a table view for displaying clients information
                clientsTable = new TableView<>();
        clientsTable.setPrefWidth(200);

        //Create table columns for each client attribute
        TableColumn<Client, Integer> clientIdColumn2 = new TableColumn<>("Client ID");
        clientIdColumn2.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        TableColumn<Client, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        TableColumn<Client, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        //Add the columns to the table view
        clientsTable.getColumns().addAll(clientIdColumn2, nameColumn, statusColumn);

        //Create a label for the properties list
        Label propertiesLabel = new Label("Properties List");

        //Create a table view for displaying properties information
                propertiesTable = new TableView<>();
        propertiesTable.setPrefWidth(200);

        //Create table columns for each property attribute
        TableColumn<Property, Integer> propertyIdColumn2 = new TableColumn<>("Property ID");
        propertyIdColumn2.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        TableColumn<Property, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        TableColumn<Property, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        //Add the columns to the table view
        propertiesTable.getColumns().addAll(propertyIdColumn2, addressColumn, priceColumn);

        //Create buttons for editing, removing, refreshing, adding new client, and adding new property
        editButton = new Button("Edit");
        removeButton = new Button("Remove");
        refreshButton = new Button("Refresh");
        addNewClientButton = new Button("Add New Client");
        addNewPropertyButton = new Button("Add New Property");

        //Create a horizontal box for the buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(editButton, removeButton, refreshButton, addNewClientButton, addNewPropertyButton);

        //Create a vertical box for the sales list and label
        VBox salesBox = new VBox(10);
        salesBox.setPadding(new Insets(10));
        salesBox.getChildren().addAll(salesLabel, salesTable);

        //Create a vertical box for the clients list and label
        VBox clientsBox = new VBox(10);
        clientsBox.setPadding(new Insets(10));
        clientsBox.getChildren().addAll(clientsLabel, clientsTable);

        //Create a vertical box for the properties list and label
        VBox propertiesBox = new VBox(10);
        propertiesBox.setPadding(new Insets(10));
        propertiesBox.getChildren().addAll(propertiesLabel, propertiesTable);

        //Create a horizontal box for the lists
        HBox listBox = new HBox(10);
        listBox.setPadding(new Insets(10));
        listBox.getChildren().addAll(salesBox, clientsBox, propertiesBox);

        //Create a border pane for the window layout
        BorderPane root = new BorderPane();
        root.setCenter(listBox);
        root.setBottom(buttonBox);

        //Create a scene with the border pane as the root node
        Scene scene = new Scene(root, 800, 600);

        //Set the title and scene of the primary stage
        primaryStage.setTitle("Sales Management");
        primaryStage.setScene(scene);

        //Show the primary stage
        primaryStage.show();
    }
}
