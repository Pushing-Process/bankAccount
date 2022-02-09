package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class IngresarController {
    @FXML
    private TextField text_ingreso;


    public void OnIngresarClick(MouseEvent mouseEvent) throws IOException {
        double ingreso = Double.parseDouble(text_ingreso.getText());
        SceneController s = new SceneController();
        s.switchSceneMenu(mouseEvent);

    }
}