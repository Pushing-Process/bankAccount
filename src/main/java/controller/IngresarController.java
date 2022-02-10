package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IngresarController implements Initializable {
    @FXML
    public Button ingresarBtn;
    @FXML
    public TextField balanceT;
    @FXML
    private TextField text_ingreso;

    public void setDatos(String balance){
        balanceT.setText(balance);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ingresarBtn.setOnMouseClicked(mouseEvent -> {
            double ingreso = Double.parseDouble(text_ingreso.getText());
            SceneController s = new SceneController();
            try {
                s.switchSceneMenu(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}