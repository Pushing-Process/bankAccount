package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.Cuenta;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    private Cuenta cuenta;
    @FXML
    private VBox vBoxIngreso, vBoxRetiro, vBoxTransferencia, vBoxSaldo, vBoxEstadisticas;

    @FXML
    private Button salirButton;

    static SceneController s = new SceneController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vBoxRetiro.setOnMouseClicked(mouseEvent -> {
            try {

                s.switchSceneRetirar(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        vBoxIngreso.setOnMouseClicked(mouseEvent -> {
            try {
                s.switchSceneIngresos(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        vBoxTransferencia.setOnMouseClicked(mouseEvent -> {
            try {
                s.switchSceneTransferencia(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        vBoxSaldo.setOnMouseClicked(mouseEvent -> {
            try {
                s.switchSceneSaldo(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        vBoxEstadisticas.setOnMouseClicked(mouseEvent -> {
            try {
                s.switchSceneEstadisticas(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        salirButton.setOnMouseClicked(mouseEvent -> {
            try {
                s.switchSceneLogin(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
