package controller;


import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import model.Extracto;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EstadisticasController implements Initializable {

    @FXML
    public PieChart grafico;
    @FXML
    public Button volverBtn;
    @FXML
    public LineChart<Integer, Number> linechart;
    @FXML
    public JFXButton linealBtn;
    public XYChart.Series series;
    public NumberAxis xAxis = new NumberAxis(0, LoginController.cuentaUser.getExtractos().size(), 1);
    public NumberAxis yAxis;


    SceneController s = new SceneController();
    double total;
    ArrayList<Extracto> extractoArrayList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        extractoArrayList = LoginController.cuentaUser.getExtractos();
        series = new XYChart.Series<Integer, Number>();
        series.setName("Extractos");
        getData();
        total = 0;
        grafico.getData().forEach(data -> {
            total += data.getPieValue();
        });
        grafico.getData().forEach(data -> {
            String text = String.format("%.1f%%", 100 * data.getPieValue() / total);
            Tooltip tooltip = new Tooltip(text);
            Tooltip.install(data.getNode(), tooltip);
        });
        grafico.setLegendSide(Side.LEFT);
        grafico.setStartAngle(90);
        grafico.setTitle("Movimientos de la cuenta");
        grafico.setClockwise(false);
        volverBtn.setOnMouseClicked(mouseEvent -> {
            try {
                s.switchSceneMenu(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        linealBtn.setOnMouseClicked(mouseEvent -> {
            if (grafico.isVisible()) {
                linechart.setVisible(true);
                grafico.setVisible(false);
                linealBtn.setText("Circular");
            } else if (linechart.isVisible()) {
                linechart.setVisible(false);
                grafico.setVisible(true);
                linealBtn.setText("Lineal");
            }
        });
    }


    private void getData() {
        double ingr = 0;
        double tran = 0;
        double retiro = 0;
        //cantidad de movimientos de cada tipo.
        for (int i = 0; i < extractoArrayList.size(); i++) {
            switch (extractoArrayList.get(i).getTipo()) {
                case INGRESO:
                    ingr++;
                    break;
                case RETIRO:
                    retiro++;
                    break;
                case TRANSFERENCIA:
                    tran++;
                    break;
            }
            series.getData().add(new XYChart.Data<Integer, Number>(i, extractoArrayList.get(i).getTotal()));
        }
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList(new PieChart.Data("Transferencias",
                tran), new PieChart.Data("Ingresos", ingr), new PieChart.Data("Retiros", retiro));

        grafico.setData(list);
        linechart.getData().add(series);
    }

}
