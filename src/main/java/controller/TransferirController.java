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

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TransferirController implements Initializable {

    @FXML
    JFXButton submitBtn, cancelBtn;

    @FXML
    TextField personNameTxt, moneyCountTxt;

    @FXML
    Label restTxt;

    static SceneController s = new SceneController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Cuenta cuenta = LoginController.cuentaUser;
        double saldoActual = cuenta.getBalance();
        restTxt.setText("Tu saldo es: " + saldoActual);

        List<String> names = Main.cuentas.stream()
                .map(Cuenta::getUsuario)
                .collect(Collectors.toList());

        submitBtn.setOnMouseClicked(mouseEvent -> {
            try {
                double moneyToPass = Double.parseDouble(moneyCountTxt.getText());
                if (!names.contains(personNameTxt.getText().trim())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Nombre de persona es invalido");
                    alert.showAndWait();
                } else {

                    Cuenta cuentaToPass = Main.cuentas.stream()
                            .filter(cuentaFilter -> personNameTxt.getText().trim().equals(cuentaFilter.getUsuario()))
                            .findAny()
                            .orElse(null);

                    if (saldoActual < moneyToPass) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("No tienes suficiente saldo.");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Has transferido: " + moneyToPass + "€");
                        cuenta.setBalance(cuenta.getBalance() - moneyToPass);
                        cuenta.getExtractos().add(new Extracto(-moneyToPass));

                        cuentaToPass.setBalance(cuentaToPass.getBalance() + moneyToPass);
                        cuentaToPass.getExtractos().add(new Extracto(+moneyToPass));

                        System.out.println(Main.cuentas.toString());

                        System.out.println(cuenta.getExtractos());
                        alert.showAndWait();
                        try {
                            s.switchSceneMenu(mouseEvent);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (NumberFormatException nfe) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("NUMBER FORMAT EXCEPTION");
                alert.setContentText("Introduce numeros");
                alert.showAndWait();
            }
        });
    }
}
