package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class HelloController {
    @FXML
    public TextField userTextField;

    @FXML
    public PasswordField passwordTextField;

    @FXML
    public Button loginButton;

    @FXML
    public void OnLoginButtonClick() {
        System.out.printf("111111");
    }
}