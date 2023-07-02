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
public class login_window extends Application{
    public loginScene loginScene;
    public registerScene registerScene;
    public static void main(String[]args){launch(args);}

    public void start(Stage primaryStage){
        primaryStage.setTitle("loginWindow");
        primaryStage.setResizable(false);
        //primaryStage.setHeight(600);
        //primaryStage.setWidth(900);
        //primaryStage.setFullScreen(true);

        GridPane grid = new GridPane();
        //set picture
        Label background = new Label();
        Image bg = new Image("loginWindow.jpg");
        ImageView bg1 = new ImageView(bg);
        background.setGraphic(bg1);
        grid.add(background,0,0);
        //set text
        GridPane grid1 = new GridPane();
        grid1.setPadding(new Insets(10,10,10,10));
        //Button
        Button loginButton = new Button("login");
        Button registerButton = new Button("register");
        grid1.add(loginButton,0,0);
        grid1.add(registerButton,1,0);
        grid1.setHgap(5);
        //Grid for checkbox
        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(10,50,10,50));
        Label userName = new Label("userName");
        Label Password = new Label("password");

        TextField userNameTextField = new TextField();
        TextField passwordTextField = new TextField();
        grid2.add(userName,0,0);
        grid2.add(Password,0,3);
        grid2.add(userNameTextField,0,1);
        grid2.add(passwordTextField,0,4);
        grid2.add(grid1,0,5);
        grid.add(grid2,1,0);
        grid2.setAlignment(Pos.CENTER);
        loginScene = new loginScene();
        //add event
        loginButton.setOnAction(event -> {
            String username = userNameTextField.getText();
            String password = passwordTextField.getText();

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
        registerScene = new registerScene();
        registerButton.setOnAction(e->{
            registerScene.start(primaryStage);
        });
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    static final String USER = "root";
    static final String PASS = "Mabohv123";
    static final String DB_URL = "jdbc:mysql://localhost:3306/user?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
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
}
