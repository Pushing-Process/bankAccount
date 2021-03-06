package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Cuenta;
import model.Extracto;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class RetirarController implements Initializable {

    static SceneController s = new SceneController();
    @FXML
    JFXButton submitBtn, cancelBtn;
    @FXML
    Label saldoTextField;
    @FXML
    TextField retirarTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Cuenta cuenta = LoginController.cuentaUser;
        double saldoActual = cuenta.getBalance();
        saldoTextField.setText("Tu saldo es: " + saldoActual);

        submitBtn.setOnMouseClicked(mouseEvent -> {
            try {
                double saldoRetirado = Double.parseDouble(retirarTextArea.getText());
                if (saldoRetirado < 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("No puedes usar numeros negativos.");
                    alert.showAndWait();
                    return;
                }
                if (saldoActual < saldoRetirado) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("No tienes suficiente saldo.");
                    alert.showAndWait();
                    return;
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Has retirado: " + saldoRetirado + "???");
                cuenta.setBalance(cuenta.getBalance() - saldoRetirado);
                cuenta.getExtractos().add(new Extracto(cuenta.getBalance(), -saldoRetirado,
                        Extracto.Tipo.RETIRO));
                new EnvioController(cuenta);
                System.out.println(cuenta.getExtractos());
                alert.showAndWait();
                try {
                    s.switchSceneMenu(mouseEvent);
                } catch (IOException e) {
                    e.printStackTrace();

                }

            } catch (NumberFormatException nfe) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("NUMBER FORMAT EXCEPTION");
                alert.setContentText("Introduce numeros");
                alert.showAndWait();
            } catch (NoSuchPaddingException | IllegalBlockSizeException | IOException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
                e.printStackTrace();
            }
        });


        cancelBtn.setOnMouseClicked(mouseEvent -> {
            try {
                s.switchSceneMenu(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
