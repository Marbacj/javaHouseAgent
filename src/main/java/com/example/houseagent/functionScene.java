package com.example.houseagent;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class functionScene extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button button1 = new Button("addInfo");
        Button button2 = new Button("showInfo");
        Button button3 = new Button("delInfo");
        Button button4 = new Button("updateInfo");

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(button1, button2, button3, button4);

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(hbox);
        //addInfo
        button1.setOnAction(e->{
            ui ui = new ui();
            ui.start(primaryStage);
        });

        Scene scene = new Scene(vbox, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

