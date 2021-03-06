package controller;

import com.example.login.Main;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TransferirController implements Initializable {

    static SceneController s = new SceneController();
    @FXML
    JFXButton submitBtn, cancelBtn;
    @FXML
    TextField personNameTxt, moneyCountTxt;
    @FXML
    Label restTxt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Cuenta cuenta = LoginController.cuentaUser;
        double saldoActual = cuenta.getBalance();
        restTxt.setText("Tu saldo es: " + saldoActual);

        List<String> names = Main.cuentas.stream()
                .map(Cuenta::getUsuario)
                .collect(Collectors.toList());
        cancelBtn.setOnMouseClicked(mouseEvent -> {
            try {
                s.switchSceneMenu(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        submitBtn.setOnMouseClicked(mouseEvent -> {
            String nameTxt = personNameTxt.getText().trim();
            try {
                double moneyToPass = Double.parseDouble(moneyCountTxt.getText());
                if (moneyToPass < 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("No puedes usar numeros negativos.");
                    alert.showAndWait();
                    return;
                }
                if (!names.contains(nameTxt) || nameTxt.equalsIgnoreCase(cuenta.getUsuario())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Nombre de persona es invalido");
                    alert.showAndWait();
                    return;
                }

                Cuenta cuentaToPass =
                        Main.cuentas.stream().filter(cuentaFilter -> nameTxt.equalsIgnoreCase(cuentaFilter.getUsuario())).findAny().orElse(null);

                if (saldoActual < moneyToPass) {
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
                alert.setContentText("Has transferido: " + moneyToPass + "???");
                cuenta.setBalance(cuenta.getBalance() - moneyToPass);
                cuenta.getExtractos().add(new Extracto(cuenta.getBalance(), -moneyToPass,
                        Extracto.Tipo.TRANSFERENCIA, cuentaToPass));

                cuentaToPass.setBalance(cuentaToPass.getBalance() + moneyToPass);
                cuentaToPass.getExtractos().add(new Extracto(cuentaToPass.getBalance(), moneyToPass,
                        Extracto.Tipo.TRANSFERENCIA, cuenta));
                new EnvioController(cuenta);

                System.out.println(Main.cuentas.toString());

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
    }
}
