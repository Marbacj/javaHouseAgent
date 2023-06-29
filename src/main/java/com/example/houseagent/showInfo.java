package com.example.houseagent;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class showInfo extends Application {
    final static String DB_URL = "jdbc:mysql://localhost:3306/houseinfo?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Mabohv123";

    private static final String TABLE_NAME = "properties";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TableView<Object[]> tableView = new TableView<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // 创建表格列
            for (int i = 1; i <= columnCount; i++) {
                TableColumn<Object[], Object> column = new TableColumn<>(metaData.getColumnName(i));
                final int columnIndex = i;
                column.setCellValueFactory(cellData ->
                        new SimpleObjectProperty<>(cellData.getValue()[columnIndex - 1]));
                tableView.getColumns().add(column);
            }

            // 添加数据行
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                tableView.getItems().add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        VBox root = new VBox(tableView);
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Table Data");
        primaryStage.show();
    }
}
