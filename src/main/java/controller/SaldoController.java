package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import model.Cuenta;
import model.Extracto;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SaldoController implements Initializable {

    SceneController s = new SceneController();
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

            switch (extracto.getTipo()) {
                case INGRESO:
                    saldoTextArea.appendText("Ingreso\t" + extracto.getSaldo() + "\n");
                    break;
                case RETIRO:
                    saldoTextArea.appendText("Retiro\t" + extracto.getSaldo() + "\n");
                    break;
                case TRANSFERENCIA:
                    saldoTextArea.appendText("Transferencia\t" + extracto.getSaldo() + "\n");
                    break;
            }
        }

        volverBtn.setOnMouseClicked(mouseEvent -> {
            try {
                s.switchSceneMenu(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        imprimirBtn.setOnAction(a -> {
            try {
                FileWriter myWriter = new FileWriter("factura.txt");
                myWriter.write(saldoTextArea.getText());
                myWriter.close();
                System.out.println("Impreso");
            } catch (IOException e) {
                System.out.println("error");
                e.printStackTrace();
            }
        });

    }
}
