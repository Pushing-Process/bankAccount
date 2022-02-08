package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    public void switchSceneLogin(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("login_view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));

    }

    public void switchSceneMenu(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("menu_view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
    }


}