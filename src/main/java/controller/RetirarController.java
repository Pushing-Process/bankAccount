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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        saldoTextField.setText("Tu saldo es: " + saldo);

        submitBtn.setOnMouseClicked(mouseEvent -> {
            if (saldo<Double.parseDouble(retirarTextArea.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("You don't have enough data");
            } else {
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("menu_view.fxml"));
                    Stage stage = (Stage) submitBtn.getScene().getWindow();
                    stage.setScene(new Scene(parent));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        cancelBtn.setOnMouseClicked(mouseEvent -> {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("menu_view.fxml"));
                Stage stage = (Stage) cancelBtn.getScene().getWindow();
                stage.setScene(new Scene(parent));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
