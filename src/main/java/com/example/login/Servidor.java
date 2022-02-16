package com.example.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Servidor extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("servidor_view.fxml"));
        stage.setTitle("Servidor");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
