package controller;

import com.example.login.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cuenta;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    public TextField userTextField;

    @FXML
    public PasswordField passwordTextField;

    @FXML
    public Button loginButton;

    @FXML
    public void OnLoginButtonClick() {
        for (Cuenta cuenta : HelloApplication.cuentas) {
            if (userTextField.getText().equalsIgnoreCase(cuenta.getUsuario()) && passwordTextField.getText().equals(cuenta.getPassword())) {

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnMouseClicked(mouseEvent -> {
            Parent parent;
            try {
                parent = FXMLLoader.load(getClass().getResource("menu_view.fxml"));
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(parent));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}