package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.LoggedUser;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {

    @FXML
    Label userNameTxt, userPasswordTxt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public static void addLabel(LoggedUser loggedUser, Label userNameTxt, Label userPasswordTxt) {
        Platform.runLater(() -> {
            userNameTxt.setText(userNameTxt.getText() + loggedUser.getUserName());
            userPasswordTxt.setText(loggedUser.getUserPassword() + loggedUser.getUserPassword());
        });
    }
}
