package controller;

import com.example.login.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.AES;
import model.Cuenta;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public static Cuenta cuentaUser;

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
                for (Cuenta cuenta : Main.cuentas) {
                    if (cuenta.getUsuario().equalsIgnoreCase(userTextField.getText()) && cuenta.getPassword() == Integer.parseInt(passwordTextField.getText())) {
                        cuentaUser = cuenta;
                        Socket miConexion = new Socket("localhost", 56);
                        AES aes = new AES();
                        cuentaUser.setEncriptado(aes.encriptar(String.valueOf(cuenta.getPassword()), "ExamenPSP2Eval"));
                        ObjectOutputStream oos = new ObjectOutputStream(miConexion.getOutputStream());
                        oos.writeObject(cuentaUser);
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
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        });
    }
}