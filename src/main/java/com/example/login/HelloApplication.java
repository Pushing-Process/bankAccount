package com.example.login;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    BorderPane bp;
    ImageView logo;
    GridPane gridPane;
    TextField user;
    PasswordField password;
    Label userText;
    Label passwordText;
    Button login;

    @Override
    public void start(Stage stage) {
        bp = new BorderPane();
        logo = new ImageView(getClass().getResource("/img/play.png").toExternalForm());
        logo.setFitHeight(50);
        logo.setFitWidth(50);
        bp.setLeft(logo);
        BorderPane.setAlignment(logo, Pos.CENTER);
        BorderPane.setMargin(logo, new Insets(0, 30, 0, 60));


        userText = new Label("User");
        passwordText = new Label("password");
        user = new TextField();
        password = new PasswordField();
        login = new Button("Login");

        gridPane = new GridPane();
        gridPane.setMinSize(200, 200);

        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(userText, 0, 0);
        gridPane.add(user, 1, 0);
        gridPane.add(passwordText, 0, 1);
        gridPane.add(password, 1, 1);
        gridPane.add(login, 0, 2);

        BorderPane.setAlignment(gridPane, Pos.CENTER);
        bp.setCenter(gridPane);

        login.setOnAction(actionEvent -> {
            Alert alert;
            if (user.getText().trim().isEmpty() || password.getText().trim().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Login fallido");
                alert.setContentText("La contraseña o usuario son incorrectos");
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Correcto");
                alert.setHeaderText("Login hecho correctamente");
            }
            alert.showAndWait();
        });

        Scene scene = new Scene(bp, 400, 240);
        stage.setTitle("LOGIN");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}