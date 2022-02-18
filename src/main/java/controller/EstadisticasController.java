package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import model.Extracto;

import java.net.URL;
import java.util.ResourceBundle;

public class EstadisticasController implements Initializable {

    @FXML
    public PieChart grafico;
    public Button mostrar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grafico = new PieChart();
        ObservableList<PieChart.Data> a = getChartData();
        System.out.println(a.toString());
        grafico.setData(a);
        grafico.setLegendSide(Side.LEFT);
        grafico.setStartAngle(90);
        grafico.setTitle("Movimientos de la cuenta");
        grafico.setClockwise(false);
    }

    public void mostrarOnclick(MouseEvent mouseEvent) {


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
