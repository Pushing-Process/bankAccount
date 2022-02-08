package com.example.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Cuenta;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {

    public static ArrayList<Cuenta> cuentas = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        cuentas.add(new Cuenta("dani", "111", (double) 100));
        cuentas.add(new Cuenta("flavio", "111", (double) 110));
        cuentas.add(new Cuenta("oleh", "111", (double) 120));
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setTitle("login");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}