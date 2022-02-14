package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import model.Cuenta;
import model.Extracto;

import java.net.URL;
import java.util.ResourceBundle;

public class SaldoController implements Initializable {

    @FXML
    private TextArea saldoTextArea;

    @FXML
    private Button imprimirBtn;

    @FXML
    private Button volverBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Cuenta cuenta = LoginController.cuentaUser;
        saldoTextArea.setText("Saldo\t\t\t " + cuenta.getBalance() + "\n");
        for (Extracto extracto : cuenta.getExtractos()) {
            if (extracto.isIngreso()) {
                saldoTextArea.appendText("Ingreso\t\t\t");
            } else {
                saldoTextArea.appendText("Extracto\t\t\t");
            }
            saldoTextArea.appendText(extracto.getSaldo() + "\n");
        }

    }
}
