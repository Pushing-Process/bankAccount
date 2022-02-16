package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.LoggedUser;
import server.SendMessage;
import server.ServerInstance;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements SendMessage, Initializable  {

    @FXML
    Label userNameTxt, userPasswordTxt;

    private ServerInstance serverInstance;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            try {
                serverInstance = new ServerInstance(new ServerSocket(1234), ServerController.this);
                serverInstance.receiveMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void sendMessage(LoggedUser user) {
        Platform.runLater(() -> {
            userNameTxt.setText(userNameTxt.getText() + " " + user.getUserName());
            userPasswordTxt.setText(userPasswordTxt.getText() + " " + user.getUserPassword());
        });
    }
}
