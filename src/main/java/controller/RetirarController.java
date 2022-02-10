package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RetirarController implements Initializable {

    @FXML
    JFXButton submitBtn, cancelBtn;

    @FXML
    Label saldoTextField;

    @FXML
    TextField retirarTextArea;

    Double saldo = 10.0;

    static SceneController s = new SceneController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        saldoTextField.setText("Tu saldo es: " + saldo);

        submitBtn.setOnMouseClicked(mouseEvent -> {
            if (saldo<Double.parseDouble(retirarTextArea.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("You don't have enough data");

                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("You have withdraw: " + Double.parseDouble(retirarTextArea.getText()) + "$");

                alert.showAndWait();
                try {
                    s.switchSceneMenu(mouseEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        cancelBtn.setOnMouseClicked(mouseEvent -> {
            try {
                s.switchSceneMenu(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
