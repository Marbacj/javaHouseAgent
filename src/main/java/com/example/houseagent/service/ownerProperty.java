package com.example.houseagent.service;


// Import the necessary JavaFX classes
import com.example.houseagent.entity.Property;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

// Extend the Application class
public class ownerProperty extends Application {
    public mainForm mainForm;
    // Declare some global variables
    private Button closeButton, ownerPropertiesButton;
    private TableView<Property> table;

    // Override the start method
    @Override
    public void start(Stage primaryStage) {
        // Create a button for closing the window
        closeButton = new Button("Close");
        mainForm = new mainForm();
        closeButton.setOnAction(e->{
            mainForm.start(primaryStage);
        });
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
        TableColumn<Property, Integer> ownerColumn = new TableColumn<>("Owner");
        ownerColumn.setCellValueFactory(cellData -> cellData.getValue().ownerProperty().asObject());
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
        //load date from datebase
        loadDataFromDatabase();
        //Create a scene with the border pane as the root node
        Scene scene = new Scene(root, 800, 600);

        //Set the title and scene of the primary stage
        primaryStage.setTitle("Owner Properties");
        primaryStage.setScene(scene);

        //Show the primary stage
        primaryStage.show();
    }
    public static void main(String []args){launch(args);}
    static final String USER = "root";
    static final String PASS = "Mabohv123";
    static final String DB_URL = "jdbc:mysql://localhost:3306/realestatemanage?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private void loadDataFromDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // 执行查询获取属性数据
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM property");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
// 获取其他属性值...
                double squareFt = resultSet.getDouble("squareFt");
                int owner = resultSet.getInt("owner");
                double price = resultSet.getDouble("price");
                String address = resultSet.getString("address");
                int bedrooms = resultSet.getInt("bedrooms");
                int bathrooms = resultSet.getInt("bathrooms");
                int age = resultSet.getInt("age");
                boolean balcony = resultSet.getBoolean("balcony");
                boolean pool = resultSet.getBoolean("pool");
                boolean backyard = resultSet.getBoolean("backyard");
                boolean garage = resultSet.getBoolean("garage");
                String description = resultSet.getString("description");

// 创建 Property 对象并添加到表格中
                Property property = new Property(id, type, squareFt, owner, price, address, bedrooms, bathrooms, age,
                        balcony, pool, backyard, garage, description);
                table.getItems().add(property);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



