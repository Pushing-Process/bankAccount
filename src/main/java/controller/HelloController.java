package controller;

import com.example.login.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Cuenta;

public class HelloController {
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
}