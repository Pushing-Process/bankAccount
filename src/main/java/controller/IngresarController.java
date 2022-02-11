package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Cuenta;

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

        balanceT.setText(LoginController.cuentaUser.getBalance().toString());

        ingresarBtn.setOnMouseClicked(mouseEvent -> {
            double ingreso = Double.parseDouble(text_ingreso.getText());
            LoginController.cuentaUser.setBalance(LoginController.cuentaUser.getBalance()+ingreso);
            balanceT.setText(LoginController.cuentaUser.getBalance().toString());

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