package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import model.Cuenta;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ServidorController implements Initializable {

    @FXML
    private TextArea datosTextArea;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(56);
                //modificar datos para recepcion de paquete
                Cuenta reciboDatos;
                Cuenta aux;

                while (true) {
                    Socket miConexion = server.accept();
                    ObjectInputStream reciboDatosPak = new ObjectInputStream(miConexion.getInputStream());
                    reciboDatos = (Cuenta) reciboDatosPak.readObject();
                    datosTextArea.appendText("algo");
                    miConexion.close();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
