package controller;

import com.example.login.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Cuenta;
import service.CuentaService;
import service.CuentaServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public static Cuenta cuentaUser;
    CuentaService cuentaService= new CuentaServiceImpl();

    List<Cuenta> cuentas = cuentaService.getAll();

    @FXML
    public TextField userTextField;

    @FXML
    public PasswordField passwordTextField;

    @FXML
    public Button loginButton;

    static SceneController s = new SceneController();

    @FXML
    public void OnLoginButtonClick(ActionEvent event) {
        SceneController s = new SceneController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnMouseClicked(mouseEvent -> {
            try {
                for (Cuenta cuenta : cuentas) {
                    if (cuenta.getUsuario().equalsIgnoreCase(userTextField.getText()) && cuenta.getPassword().equals(passwordTextField.getText())) {
                        cuentaUser = cuenta;
                        s.switchSceneMenu(mouseEvent);
                        return;
                    }
                }

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Look, an Error Dialog");
                alert.setContentText("I have a great message for you!");
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}