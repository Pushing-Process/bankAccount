package controller;


import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import model.Extracto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EstadisticasController implements Initializable {

    @FXML
    public PieChart grafico;

    @FXML
    public Button volverBtn;
    public LineChart<Integer, Number> linechart;
    public JFXButton linealBtn;
    public NumberAxis xAxis = new NumberAxis(0,LoginController.cuentaUser.getExtractos().size(),1);
    public NumberAxis yAxis;


    SceneController s = new SceneController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Grafico cicular
        grafico.setData(getChartData());
        grafico.setLegendSide(Side.LEFT);
        grafico.setStartAngle(90);
        grafico.setTitle("Movimientos de la cuenta");
        grafico.setClockwise(false);
        //grafico lineal
        XYChart.Series series = new XYChart.Series<Integer, Number>();
        series.setName("Extractos");

        for (int i = 0; i < LoginController.cuentaUser.getExtractos().size(); i++) {

            series.getData().add(new XYChart.Data<Integer, Number>(i, LoginController.cuentaUser.getExtractos().get(i).getTotal()));

        }
        linechart.getData().add(series);


        volverBtn.setOnMouseClicked(mouseEvent -> {
            try {
                s.switchSceneMenu(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        linealBtn.setOnMouseClicked(mouseEvent -> {
            if (grafico.isVisible()) {
                mostrarGraficoLineal();
            } else if (linechart.isVisible()) {
                linechart.getData().clear();
                linechart.setVisible(false);
                grafico.setVisible(true);
                linealBtn.setText("Lineal");
            }
        });
    }

    private void mostrarGraficoLineal() {
        linealBtn.setText("Circular");
        grafico.setVisible(false);
        linechart.setVisible(true);

    }

    private ObservableList<PieChart.Data> getChartData() {
        double ingr = 0;
        double tran = 0;
        double retiro = 0;

        //sacar el total de ingresos,tranferencias, retiros
        for (int i = 0; i < LoginController.cuentaUser.getExtractos().size(); i++) {
            if (LoginController.cuentaUser.getExtractos().get(i).getTipo() == Extracto.Tipo.RETIRO) {
                retiro = LoginController.cuentaUser.getExtractos().get(i).getTotal() + retiro;
            } else if (LoginController.cuentaUser.getExtractos().get(i).getTipo() == Extracto.Tipo.TRANSFERENCIA) {
                tran = LoginController.cuentaUser.getExtractos().get(i).getTotal() + tran;
            } else if (LoginController.cuentaUser.getExtractos().get(i).getTipo() == Extracto.Tipo.INGRESO) {
                ingr = LoginController.cuentaUser.getExtractos().get(i).getTotal() + ingr;
            }
        }
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList(new PieChart.Data("Transferencias", tran), new PieChart.Data("Ingresos", ingr), new PieChart.Data("Retiros", retiro));


        System.out.println(ingr);
        System.out.println(tran);
        System.out.println(retiro);

        return list;
    }

}
