package com.example.login;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Cuenta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main extends Application {

    public static List<Cuenta> cuentas = new ArrayList<>();

    static {
        cuentas.add(new Cuenta("dani", 111, (double) 100, new ArrayList<>()));
        cuentas.add(new Cuenta("flavio", 111, (double) 110, new ArrayList<>()));
        cuentas.add(new Cuenta("oleh", 111, (double) 120, new ArrayList<>()));
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/login/login_view.fxml"));
        stage.setTitle("login");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Style.css")).toExternalForm());

        Platform.runLater(() -> {
            try {
                new Servidor().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}