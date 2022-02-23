package controller;

import com.example.login.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Cuenta;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public static Cuenta cuentaUser;
    static SceneController s = new SceneController();
    @FXML
    public TextField userTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnMouseClicked(mouseEvent -> {
            try {
                extracted(mouseEvent);
            } catch (IOException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
                e.printStackTrace();
            }
        });
    }

    private void extracted(MouseEvent mouseEvent) throws IOException, NoSuchPaddingException,
            IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        for (Cuenta cuenta : Main.cuentas) {
            if (cuenta.getUsuario().equalsIgnoreCase(userTextField.getText()) && cuenta.getPassword() == Integer.parseInt(passwordTextField.getText())) {
                cuentaUser = cuenta;
                new EnvioController(cuenta);
                s.switchSceneMenu(mouseEvent);
                return;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Usuario o contraseña incorrectos.");
        alert.showAndWait();
    }
}