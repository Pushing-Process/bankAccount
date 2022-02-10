package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    public void switchSceneLogin(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../com/example/login/login_view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));

    }

    public void switchSceneMenu(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("menu_view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
    }

    public void switchSceneIngresos(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ingresar_view.fxml"));
        Parent parent = loader.load();
        IngresarController ingresos = loader.getController();
        ingresos.setDatos(LoginController.cuentaUser.getBalance().toString());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
    }

    public void switchSceneRetirar(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("retirar_view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
    }

    public void switchSceneTransferencia(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("transferir_view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
    }

    public void switchSceneSaldo(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("saldo_view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
    }

    public void switchSceneEstadisticas(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("estadisticas_view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
    }
}
