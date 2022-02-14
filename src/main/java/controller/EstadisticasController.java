package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;

import java.net.URL;
import java.util.ResourceBundle;

public class EstadisticasController implements Initializable {

    @FXML
    public LineChart grafico;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grafico.setTitle("Balance");
    }
}
