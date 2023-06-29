package com.example.houseagent;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class mainScene extends Application {
    private ObservableList<String> propertyNames = FXCollections.observableArrayList(
            "seekForFloor", "addForFloor", "delForFloor");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Real Estate Management System");
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.setWidth(900);
        primaryStage.setHeight(600);
        // 创建GridPane布局
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setAlignment(Pos.CENTER);
        // 添加楼盘名称下拉框
        Label propertyLabel = new Label("Property:");
        ComboBox<String> propertyComboBox = new ComboBox<>(propertyNames);
        grid.add(propertyLabel, 0, 0);
        grid.add(propertyComboBox, 1, 0);

        // 添加文本框和按钮
        Label floorLabel = new Label("Floor:");
        TextField floorTextField = new TextField();
        grid.add(floorLabel, 0, 1);
        grid.add(floorTextField, 1, 1);

        Label roomLabel = new Label("Room:");
        TextField roomTextField = new TextField();
        grid.add(roomLabel, 0, 2);
        grid.add(roomTextField, 1, 2);

        Button addButton = new Button("Add");
        grid.add(addButton, 1, 3);

        // 添加事件处理
        addButton.setOnAction(event -> {
            String property = propertyComboBox.getValue();
            String floor = floorTextField.getText();
            String room = roomTextField.getText();

            // 执行相应的业务逻辑操作，例如添加房屋信息到数据库

            // 清空文本框
            floorTextField.clear();
            roomTextField.clear();

            // 提示添加成功
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("House added successfully!");
            alert.showAndWait();
        });

        // 创建Scene并显示窗口
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);

        primaryStage.show();

        //seekForFloor

    }
    public static void main(String[] args) {
        launch(args);
    }
}
