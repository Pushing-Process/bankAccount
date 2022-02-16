package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

public class IngresarController implements Initializable {
    @FXML
    private Button ingresarBtn;
    @FXML
    private Button volverBtn;
    @FXML
    public TextField balanceT;
    @FXML
    private TextField text_ingreso;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Cuenta cuenta = LoginController.cuentaUser;
        balanceT.setText(cuenta.getBalance().toString());

        ingresarBtn.setOnMouseClicked(mouseEvent -> {
            try {
                double ingreso = Double.parseDouble(text_ingreso.getText());
                cuenta.setBalance(cuenta.getBalance() + ingreso);
                balanceT.setText(cuenta.getBalance().toString());
                Extracto extracto = new Extracto(cuenta.getBalance(), ingreso, Extracto.Tipo.INGRESO);
                LoginController.cuentaUser.getExtractos().add(extracto);
                new EnvioController(cuenta);
            } catch (NumberFormatException nfe) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("NUMBER FORMAT EXCEPTION");
                alert.setContentText("Introduce numbers");
                alert.showAndWait();
            } catch (NoSuchPaddingException | IllegalBlockSizeException | IOException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
                e.printStackTrace();
            }
        });
        volverBtn.setOnMouseClicked(mouseEvent -> {
            SceneController s = new SceneController();
            try {
                s.switchSceneMenu(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}