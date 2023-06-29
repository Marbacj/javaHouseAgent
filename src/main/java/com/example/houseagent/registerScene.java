package com.example.houseagent;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class registerScene extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registration");

        // 创建GridPane布局
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setAlignment(Pos.CENTER);
        // 添加用户名和密码文本框
        Label usernameLabel = new Label("Username:");
        TextField usernameTextField = new TextField();
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameTextField, 1, 0);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);

        Button registerButton = new Button("Register");
        grid.add(registerButton, 1, 2);

        //button cancel
        Button cButton = new Button("cancel");
        grid.add(cButton,1,3);
        cButton.setOnAction(e->{
            loginScene loginScene = new loginScene();
            loginScene.start(primaryStage);
        });
        // 添加事件处理
        registerButton.setOnAction(event -> {
            String username = usernameTextField.getText();
            String password = passwordField.getText();

            // 调用方法将注册信息添加到数据库
            boolean registrationSuccessful = registerUser(username, password);

            if (registrationSuccessful) {
                // 注册成功，提示用户并关闭注册页面
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registration Success");
                alert.setHeaderText(null);
                alert.setContentText("Registration successful!");
                alert.showAndWait();

                primaryStage.close();
            } else {
                // 注册失败，显示错误提示
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registration Error");
                alert.setHeaderText(null);
                alert.setContentText("Registration failed. Please try again.");
                alert.showAndWait();
            }
        });

        // 创建Scene并显示窗口
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean registerUser(String username, String password) {
        // 连接数据库并将注册信息添加到数据库
        // 返回true表示注册成功，返回false表示注册失败

        // 这里只是一个简单的示例，你需要根据你的数据库和表结构进行适当的实现
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/houseAgent?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        // 假设你的数据库连接信息已经正确配置
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root", "Mabohv123")) {

            //建表操作
            // 创建表的SQL语句
            String createTableSql = "CREATE TABLE users (id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(50), password VARCHAR(50))";
            Statement statement = conn.createStatement();
            statement.executeUpdate("ALTER TABLE users ADD COLUMN new_column2 INT");

            // 执行创建表操作
            //Statement statement = conn.createStatement();
            //statement.executeUpdate(createTableSql);

            // 创建PreparedStatement对象
            PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            insertStatement.setString(1, username);
            insertStatement.setString(2, password);

            // 执行插入操作
            int rowsAffected = insertStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

