package com.example.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Servidor extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login_view.fxml"));
        stage.setTitle("SERVIDOR");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
