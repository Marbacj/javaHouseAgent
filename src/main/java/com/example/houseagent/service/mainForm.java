package com.example.houseagent.service;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Import the necessary JavaFX classes
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;

// Extend the Application class
public class mainForm extends Application {
    public onwerProperty onwerProperty;
    public Owners owners;
    public clients clients;
    public propertyType propertyType;
    public salesManagement salesManagement;
    // Override the start method
    @Override
    public void start(Stage primaryStage) {
        // Create an image view for the app logo
        Image logo = new Image("logo.png");
        ImageView logoView = new ImageView(logo);

        // Create a label for the app name
        Label appName = new Label("MyApp");
        appName.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

        // Create a horizontal box for the header
        HBox header = new HBox(10);
        header.setPadding(new Insets(10));
        header.setStyle("-fx-background-color: orange;");
        header.getChildren().addAll(logoView, appName);

        // Create buttons for the options
        Button propertyButton = new Button("Property");
        Button propertyTypeButton = new Button("Property-Type");
        Button ownerButton = new Button("Owner");
        Button clientButton = new Button("Client");
        Button saleButton = new Button("Sale");
        onwerProperty = new onwerProperty();
        propertyButton.setOnAction(e->{
            onwerProperty.start(primaryStage);
        });
        owners = new Owners();
        ownerButton.setOnAction(e->{
            owners.start(primaryStage);
        });
        clients = new clients();
        clientButton.setOnAction(e->{
            clients.start(primaryStage);
        });
        propertyType = new propertyType();
        propertyTypeButton.setOnAction(e->{
            propertyType.start(primaryStage);
        });
        salesManagement = new salesManagement();
        saleButton.setOnAction(e->{
            try {
                salesManagement.start(primaryStage);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        // Create a vertical box for the options
        VBox options = new VBox(10);
        options.setPadding(new Insets(10));
        options.getChildren().addAll(propertyButton, propertyTypeButton,ownerButton, clientButton, saleButton);

        // Create a border pane for the window layout
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setLeft(options);

        // Create a scene with the border pane as the root node
        Scene scene = new Scene(root, 600, 400);

        // Set the title and scene of the primary stage
        primaryStage.setTitle("MyApp");
        primaryStage.setScene(scene);

        // Show the primary stage
        primaryStage.show();
    }

    // Launch the application
    public static void main(String[] args) {
        launch(args);
    }
}


