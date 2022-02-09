package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Cuenta;

import java.io.IOException;
import java.util.ArrayList;

public class IngresarController {
    public Button ingresarBtn;
    public TextField balanceT;
    @FXML
    private TextField text_ingreso;
    @FXML
    private Cuenta cuenta;

    public void OnIngresarClick(MouseEvent mouseEvent) throws IOException {
        double ingreso = Double.parseDouble(text_ingreso.getText());
        SceneController s = new SceneController();
        s.switchSceneMenu(mouseEvent);

    }

    public IngresarController(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void RecibirInf(ActionEvent actionEvent) {
        balanceT = new TextField();
        balanceT.setText(this.cuenta.getBalance().toString());
    }
}