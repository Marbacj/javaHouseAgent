package com.example.houseagent;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.sql.*;

import javafx.scene.layout.GridPane;

public class loginScene extends Application {
    private registerScene reg;
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
        grid.setVgap(40);
        grid.setHgap(40);
        grid.setAlignment(Pos.BASELINE_RIGHT);
        grid.setMinHeight(500);
        grid.setMinWidth(800);
        grid.setGridLinesVisible(true);
        // 添加用户名和密码文本框
        Label usernameLabel = new Label("Username:");
        TextField usernameTextField = new TextField();
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameTextField, 1, 0);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);

        Button loginButton = new Button("Login");
        //grid.add(loginButton, 1, 2);
        Button registerButton = new Button("register");
        //grid.add(loginButton, 3,4);
        //grid.add(registerButton,2,2);
        GridPane GridB = new GridPane();
        grid.add(GridB,1,2);
        GridB.setPadding(new Insets(5, 5, 5, 5));
        GridB.setHgap(12);
        GridB.add(registerButton,5,0);
        GridB.add(loginButton,0,0);

        Label background = new Label();
        background.setPadding(new Insets(0,0,0,50));
        // 创建图像对象
        Image image = new Image("loginWindow.jpg"); // 替换为您的图像路径

        // 创建图像视图对象
        ImageView imageView = new ImageView(image);

        // 将图像视图设置为标签的图形内容
        background.setGraphic(imageView);
        reg = new registerScene();
        registerButton.setOnAction(e->{
            reg.start(primaryStage);
        });
        // 添加事件处理
        loginButton.setOnAction(event -> {
            String username = usernameTextField.getText();
            String password = passwordField.getText();

            // 调用数据库验证登录信息
            boolean loginSuccessful = verifyLogin(username, password);

            if (loginSuccessful) {
                // 登录成功，打开主界面或执行其他操作
                loginButton.setOnAction(e->{
                    functionScene functionScene = new functionScene();
                    functionScene.start(primaryStage);
                        }
                );
                System.out.println("Login successful!");
            } else {
                // 登录失败，显示错误提示
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password.");
                alert.showAndWait();
            }
        });



        Scene scene = new Scene(grid,300, 200);
        //scene.setRoot(background);
        primaryStage.setScene(scene);
        //primaryStage.setScene(scene1);
        primaryStage.show();
    }
    static final String DB_URL = "jdbc:mysql://localhost:3306/user?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "Mabohv123";
    private boolean verifyLogin(String username, String password) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            boolean loginSuccessful = resultSet.next();
            conn.close();
            return loginSuccessful;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args) {
        launch(args);
    }
}

