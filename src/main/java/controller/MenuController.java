package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private ImageView ingresos;

    @FXML
    private VBox vBoxIngreso, vBoxRetiro;

    static SceneController s = new SceneController();

    @FXML
    public void ingresosClick(){
        System.out.printf("adasdsad");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vBoxRetiro.setOnMouseClicked(mouseEvent -> {
            try {
                s.switchSceneRetirar(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
