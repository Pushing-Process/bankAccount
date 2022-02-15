package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Application {

    private ServerInstance serverInstance;
    Label userNameTxt, userPasswordTxt;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("server_view.fxml"));
        stage.setTitle("login");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

        try {
            serverInstance = new ServerInstance(new ServerSocket(1234));
        } catch (IOException e) {
            e.printStackTrace();
        }

        serverInstance.receiveMessage(userNameTxt, userPasswordTxt);
    }
}
