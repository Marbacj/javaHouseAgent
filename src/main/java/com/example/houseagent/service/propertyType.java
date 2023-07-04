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

import java.sql.*;

// Extend the Application class
public class propertyType extends Application {

    // Declare some global variables
    private TextField idField, nameField, descriptionField;
    private Button addButton, editButton, removeButton, refreshButton, closeButton;
    private ComboBox<String> typeBox;
    static final String USER = "root";
    static final String PASS = "Mabohv123";
    static final String DB_URL = "jdbc:mysql://localhost:3306/realestatemanage?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    public mainForm mainForm;
    // Override the start method
    @Override
    public void start(Stage primaryStage) {
        // Create a label for the ID field
        Label idLabel = new Label("ID:");

        // Create a text field for the ID field
        idField = new TextField();
        //idField.setEditable(false);

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
        addButton.setOnAction(e -> {
            try {
                addEvent();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        editButton.setOnAction(e->{
            try {
                editEvent();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        refreshButton.setOnAction(e->{
            try {
                removeEvent();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        removeButton.setOnAction(e->{
            try {
                removeEvent();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        mainForm = new mainForm();
        closeButton.setOnAction(e->{
            mainForm.start(primaryStage);
        });
        // Create a horizontal box for the buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(addButton, editButton, removeButton, refreshButton, closeButton);

        // Create a combo box for selecting property type
        typeBox = new ComboBox<>();
        typeBox.setItems(FXCollections.observableArrayList("Commercial", "Residential", "Industrial", "Land"));
        typeBox.setOnAction(e -> {
            String selectedType = typeBox.getValue();
            if (selectedType != null) {
                nameField.setText(selectedType);
            }
        });

        // Create a grid pane for the fields and labels
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Add the fields and labels to the grid pane
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(nameField, 1, 1);
        gridPane.add(descriptionLabel, 0, 2);
        gridPane.add(descriptionField, 1, 2);

        // Create a border pane for the window layout
        BorderPane root = new BorderPane();
        root.setLeft(gridPane);
        root.setRight(typeBox);
        root.setBottom(buttonBox);

        // Create a scene with the border pane as the root node
        Scene scene = new Scene(root, 600, 400);

        // Set the title and scene of the primary stage
        primaryStage.setTitle("Property Type");
        primaryStage.setScene(scene);

        // Show the primary stage
        primaryStage.show();
    }

    public void addEvent() throws SQLException {
        // Create a connection to the database
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        // Create a statement to execute SQL queries
        Statement stmt = conn.createStatement();

        // Get the values from the text fields
        String id = idField.getText();
        String name = nameField.getText();
        String description = descriptionField.getText();

        // Create a SQL query to insert the values into the property table
        String sql = "INSERT INTO property (id, type, description) VALUES (" + id + ", '" + name + "', '" + description + "')";

        // Execute the query and close the statement and connection
        stmt.executeUpdate(sql);
        stmt.close();
        conn.close();

    }
    // For the edit button, you need to update the existing record in the property table
    public void editEvent() throws SQLException {
        // Create a connection to the database
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // Create a statement to execute SQL queries
        Statement stmt = conn.createStatement();

        // Get the values from the text fields
        String id = idField.getText();
        String name = nameField.getText();
        String description = descriptionField.getText();

        // Create a SQL query to update the record in the property table
        String sql = "UPDATE property SET type = '" + name + "', description = '" + description + "' WHERE id = " + id;

        // Execute the query and close the statement and connection
        stmt.executeUpdate(sql);
        stmt.close();
        conn.close();
    }
    public void removeEvent() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // Create a statement to execute SQL queries
        Statement stmt = conn.createStatement();

        // Get the value from the id field
        String id = idField.getText();

        // Create a SQL query to delete the record from the property table
        String sql = "DELETE FROM property WHERE id = " + id;

        // Execute the query and close the statement and connection
        stmt.executeUpdate(sql);
        stmt.close();
        conn.close();
    }
// For the remove button, you need to delete the existing record from the property table
        public void refreshButton() throws SQLException {
                // Clear the text fields
                idField.clear();
                nameField.clear();
                descriptionField.clear();

                // Create a connection to the database
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

                // Create a statement to execute SQL queries
                Statement stmt = conn.createStatement();

                // Create a SQL query to select all records from the property table
                String sql = "SELECT * FROM property";

                // Execute the query and get the result set
                ResultSet rs = stmt.executeQuery(sql);

                // Loop through the result set and display the data in the text fields
                while (rs.next()) {
                    idField.setText(rs.getString("id"));
                    nameField.setText(rs.getString("name"));
                    descriptionField.setText(rs.getString("description"));
                    break; // Only display one record at a time
                }

                // Close the result set, statement and connection
                rs.close();
                stmt.close();
                conn.close();
        }

}


