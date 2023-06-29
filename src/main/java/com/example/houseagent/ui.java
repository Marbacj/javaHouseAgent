package com.example.houseagent;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;
public class ui extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    private addInfo addInfo;
    @Override
    public void start(Stage primaryStage) {

        // 创建表单布局
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        // 创建表单控件
        TextField nameField = new TextField();
        TextField areaField = new TextField();
        TextField buildingNumberField = new TextField();
        TextField roomNumberField = new TextField();
        TextField houseAreaField = new TextField();
        TextField unitPriceField = new TextField();
        TextField totalPriceField = new TextField();

        Button addButton = new Button("Add Property");

        // 将控件添加到表单布局中
        gridPane.add(new Label("Name:"), 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(new Label("Area:"), 0, 1);
        gridPane.add(areaField, 1, 1);
        gridPane.add(new Label("Building Number:"), 0, 2);
        gridPane.add(buildingNumberField, 1, 2);
        gridPane.add(new Label("Room Number:"), 0, 3);
        gridPane.add(roomNumberField, 1, 3);
        gridPane.add(new Label("House Area:"), 0, 4);
        gridPane.add(houseAreaField, 1, 4);
        gridPane.add(new Label("Unit Price:"), 0, 5);
        gridPane.add(unitPriceField, 1, 5);
        gridPane.add(new Label("Total Price:"), 0, 6);
        gridPane.add(totalPriceField, 1, 6);
        gridPane.add(addButton, 1, 7);

        // 创建场景
        Scene scene = new Scene(gridPane, 400, 300);

        // 设置舞台的标题和场景
        primaryStage.setTitle("Add Property");
        primaryStage.setScene(scene);
        primaryStage.setWidth(900);
        primaryStage.setHeight(600);
        primaryStage.show();

        addInfo = new addInfo();
        // 注册按钮的事件处理
        addButton.setOnAction(event -> {
            String name = nameField.getText();
            double area = Double.parseDouble(areaField.getText());
            int buildingNumber = Integer.parseInt(buildingNumberField.getText());
            int roomNumber = Integer.parseInt(roomNumberField.getText());
            double houseArea = Double.parseDouble(houseAreaField.getText());
            double unitPrice = Double.parseDouble(unitPriceField.getText());
            double totalPrice = Double.parseDouble(totalPriceField.getText());

            // 调用addProperty方法进行处理

            boolean success = addInfo.addProperty(name, area, buildingNumber, roomNumber, houseArea, unitPrice, totalPrice);

            if (success) {
                // 处理成功
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Property added successfully!");
                alert.showAndWait();
            } else {
                // 处理失败
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to add property!");
                alert.showAndWait();
            }

            // 清空表单字段
            nameField.clear();
            areaField.clear();
            buildingNumberField.clear();
            roomNumberField.clear();
            houseAreaField.clear();
            unitPriceField.clear();
            totalPriceField.clear();
        });
    }
}