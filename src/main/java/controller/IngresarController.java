package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Cuenta;
import model.Extracto;

import java.io.IOException;
import java.net.URL;
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
        balanceT.setText(LoginController.cuentaUser.getBalance().toString());

        ingresarBtn.setOnMouseClicked(mouseEvent -> {
            try {
                double ingreso = Double.parseDouble(text_ingreso.getText());
                LoginController.cuentaUser.setBalance(cuenta.getBalance() + ingreso);
                balanceT.setText(cuenta.getBalance().toString());
                Extracto extracto = new Extracto(cuenta.getBalance(), ingreso, Extracto.Tipo.INGRESO, null);
                LoginController.cuentaUser.getExtractos().add(extracto);
            }catch(NumberFormatException nfe){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("NUMBER FORMAT EXCEPTION");
                alert.setContentText("Introduce numbers");
                alert.showAndWait();
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