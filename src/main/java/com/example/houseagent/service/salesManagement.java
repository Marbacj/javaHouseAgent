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
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

// Extend the Application class
public class salesManagement extends Application {

    // Declare some global variables
    private Button editButton, removeButton, refreshButton, addNewClientButton, addNewPropertyButton;
    private TableView<sale> salesTable;
    private TableView<Client> clientsTable;
    private TableView<Property> propertiesTable;
    public clients clients;
    public onwerProperty ownerProperty;
    static final String USER = "root";
    static final String PASS = "Mabohv123";
    static final String DB_URL = "jdbc:mysql://localhost:3306/realestatemanage?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    // Override the start method
    @Override
    public void start(Stage primaryStage) throws SQLException {
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
        clients = new clients();
        addNewClientButton.setOnAction(e->{
            clients.start(primaryStage);
        });
        ownerProperty = new onwerProperty();
        addNewPropertyButton.setOnAction(e->{
            ownerProperty.start(primaryStage);
        });
// For the edit button, you need to open a new window with text fields for editing the selected sale
        editButton.setOnAction(e -> {
            try {
                editEvent();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        refreshButton.setOnAction(e-> {
            try {
                refreshEvent();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        removeButton.setOnAction(e-> {
            try {
                removeEvent();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
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
        loadSalesData();
        loadClientsData();
        loadPropertiesData();
        //Create a scene with the border pane as the root node
        Scene scene = new Scene(root, 800, 600);

        //Set the title and scene of the primary stage
        primaryStage.setTitle("Sales Management");
        primaryStage.setScene(scene);

        //Show the primary stage
        primaryStage.show();
    }
    // A method to load sales data from the database and display it in the sales table view
    public void loadSalesData() throws SQLException {
        // Create a connection to the database
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // Create a statement to execute SQL queries
        Statement stmt = conn.createStatement();

        // Create a SQL query to select all records from the sale table
        String sql = "SELECT * FROM sale";

        // Execute the query and get the result set
        ResultSet rs = stmt.executeQuery(sql);

        // Create an observable list to store the data from the result set
        ObservableList<sale> data = FXCollections.observableArrayList();

        // Loop through the result set and add each record as a sale object to the observable list
        while (rs.next()) {
            data.add(new sale(rs.getInt("propertyId"), rs.getInt("clientId"), rs.getDouble("finalPrice"), rs.getString("date")));
        }

        // Close the result set, statement and connection
        rs.close();
        stmt.close();
        conn.close();

        // Set the items of the sales table view to the observable list
        salesTable.setItems(data);
    }

    // A method to load clients data from the database and display it in the clients table view
    public void loadClientsData() throws SQLException {
        // Create a connection to the database
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // Create a statement to execute SQL queries
        Statement stmt = conn.createStatement();

        // Create a SQL query to select all records from the client table
        String sql = "SELECT * FROM client";

        // Execute the query and get the result set
        ResultSet rs = stmt.executeQuery(sql);

        // Create an observable list to store the data from the result set
        ObservableList<Client> data = FXCollections.observableArrayList();

        // Loop through the result set and add each record as a Client object to the observable list
        while (rs.next()) {
            data.add(new Client(rs.getInt("id"), rs.getString("name"), rs.getString("status")));
        }

        // Close the result set, statement and connection
        rs.close();
        stmt.close();
        conn.close();

        // Set the items of the clients table view to the observable list
        clientsTable.setItems(data);
    }

    // A method to load properties data from the database and display it in the properties table view
    public void loadPropertiesData() throws SQLException {
        // Create a connection to the database
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // Create a statement to execute SQL queries
        Statement stmt = conn.createStatement();

        // Create a SQL query to select all records from the property table
        String sql = "SELECT * FROM property";

        // Execute the query and get the result set
        ResultSet rs = stmt.executeQuery(sql);

        // Create an observable list to store the data from the result set
        ObservableList<Property> data = FXCollections.observableArrayList();

        // Loop through the result set and add each record as a Property object to the observable list
        while (rs.next()) {
            data.add(new Property(rs.getInt("id"), rs.getString("type"), rs.getDouble("squareFt"), rs.getInt("owner"), rs.getDouble("price"), rs.getString("address"), rs.getInt("bedrooms"), rs.getInt("bathrooms"), rs.getInt("age"), rs.getBoolean("balcony"), rs.getBoolean("pool"), rs.getBoolean("backyard"), rs.getBoolean("garage"), rs.getString("description")));
        }

        // Close the result set, statement and connection
        rs.close();
        stmt.close();
        conn.close();

        // Set the items of the properties table view to the observable list
        propertiesTable.setItems(data);
    }
    public void removeEvent() throws SQLException {
        // Get the selected sale from the sales table view
        sale selectedSale = salesTable.getSelectionModel().getSelectedItem();

        // Check if a sale is selected
        if (selectedSale != null) {
            // Create a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Create a statement to execute SQL queries
            Statement stmt = conn.createStatement();

            // Get the values from the selected sale
            int propertyId = selectedSale.getPropertyId();
            int clientId = selectedSale.getClientId();

            // Create a SQL query to delete the record from the sale table based on the property id and client id
            String sql = "DELETE FROM sale WHERE propertyId = " + propertyId + " AND clientId = " + clientId;

            // Execute the query and close the statement and connection
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();

            // Reload the sales data from the database and display it in the sales table view
            loadSalesData();
        }
    }

    public void editEvent() throws SQLException {
// For the edit button, you need to open a new window with text fields for editing the selected sale
        editButton.setOnAction(e -> {
            // Get the selected sale from the sales table view
            sale selectedSale = salesTable.getSelectionModel().getSelectedItem();

            // Check if a sale is selected
            if (selectedSale != null) {
                // Create a new stage for the edit window
                Stage editStage = new Stage();

                // Create a grid pane for the edit window layout
                GridPane editPane = new GridPane();
                editPane.setHgap(10);
                editPane.setVgap(10);
                editPane.setPadding(new Insets(10));

                // Create labels and text fields for each sale attribute
                Label propertyIdLabel_ = new Label("Property ID:"); // Add an underscore to avoid conflict with the main window variable
                TextField propertyIdField_ = new TextField(String.valueOf(selectedSale.getPropertyId()));
                propertyIdField_.setEditable(false); // Property ID cannot be changed
                Label clientIdLabel_ = new Label("Client ID:"); // Add an underscore to avoid conflict with the main window variable
                TextField clientIdField_ = new TextField(String.valueOf(selectedSale.getClientId()));
                clientIdField_.setEditable(false); // Client ID cannot be changed
                Label finalPriceLabel_ = new Label("Final Price:"); // Add an underscore to avoid conflict with the main window variable
                TextField finalPriceField_ = new TextField(String.valueOf(selectedSale.getFinalPrice()));
                Label dateLabel_ = new Label("Date:"); // Add an underscore to avoid conflict with the main window variable
                TextField dateField_ = new TextField(selectedSale.getDate());

                // Add the labels and text fields to the grid pane
                editPane.add(propertyIdLabel_, 0, 0);
                editPane.add(propertyIdField_, 1, 0);
                editPane.add(clientIdLabel_, 0, 1);
                editPane.add(clientIdField_, 1, 1);
                editPane.add(finalPriceLabel_, 0, 2);
                editPane.add(finalPriceField_, 1, 2);
                editPane.add(dateLabel_, 0, 3);
                editPane.add(dateField_, 1, 3);

                // Create a save button for saving the changes
                Button saveButton = new Button("Save");

                // Add the save button to the grid pane
                editPane.add(saveButton, 1, 4);

                // Create a scene with the grid pane as the root node
                Scene editScene = new Scene(editPane);

                // Set the title and scene of the edit stage
                editStage.setTitle("Edit Sale");
                editStage.setScene(editScene);

                // Show the edit stage
                editStage.show();

                // For the save button, you need to update the record in the sale table based on the values in the text fields
                saveButton.setOnAction(f -> {
                    // Create a connection to the database
                    Connection conn = null;
                    try {
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement();

                        // Get the values from the text fields
                        int propertyId = Integer.parseInt(propertyIdField_.getText()); // Use the underscored variable name
                        int clientId = Integer.parseInt(clientIdField_.getText()); // Use the underscored variable name
                        double finalPrice = Double.parseDouble(finalPriceField_.getText()); // Use the underscored variable name
                        String date = dateField_.getText(); // Use the underscored variable name

                        // Create a SQL query to update the record in the sale table based on the property id and client id
                        String sql = "UPDATE sale SET finalPrice = " + finalPrice + ", date = '" + date + "' WHERE propertyId = " + propertyId + " AND clientId = " + clientId;

                        // Execute the query and close the statement and connection
                        stmt.executeUpdate(sql);
                        stmt.close();
                        conn.close();

                        // Reload the sales data from the database and display it in the sales table view
                        loadSalesData();

                        // Close the edit stage
                        editStage.close();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    // Create a statement to execute SQL queries

                });
            }
        });


        }

    private void SaveEvent() throws SQLException {
            // Create a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Create a statement to execute SQL queries
            Statement stmt = conn.createStatement();
            sale selectedSale = salesTable.getSelectionModel().getSelectedItem();
            Label propertyIdLabel = new Label("Property ID:");
            TextField propertyIdField = new TextField(String.valueOf(selectedSale.getPropertyId()));
            propertyIdField.setEditable(false); // Property ID cannot be changed
            Label clientIdLabel = new Label("Client ID:");
            TextField clientIdField = new TextField(String.valueOf(selectedSale.getClientId()));
            clientIdField.setEditable(false); // Client ID cannot be changed
            Label finalPriceLabel = new Label("Final Price:");
            TextField finalPriceField = new TextField(String.valueOf(selectedSale.getFinalPrice()));
            Label dateLabel = new Label("Date:");
            TextField dateField = new TextField(selectedSale.getDate());

            // Get the values from the text fields
            int propertyId = Integer.parseInt(propertyIdField.getText());
            int clientId = Integer.parseInt(clientIdField.getText());
            double finalPrice = Double.parseDouble(finalPriceField.getText());
            String date = dateField.getText();

            // Create a SQL query to update the record in the sale table based on the property id and client id
            String sql = "UPDATE sale SET finalPrice = " + finalPrice + ", date = '" + date + "' WHERE propertyId = " + propertyId + " AND clientId = " + clientId;

            // Execute the query and close the statement and connection
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();

            // Reload the sales data from the database and display it in the sales table view
            loadSalesData();
            // Close the edit stag
        }

    public void refreshEvent() throws SQLException {
        loadSalesData();
        loadClientsData();
        loadPropertiesData();
    }

}
