package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import model.Cuenta;
import model.Extracto;

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
                extracted();
            } catch (IOException | ClassNotFoundException | CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void extracted() throws IOException, ClassNotFoundException, CloneNotSupportedException {
        ServerSocket server = new ServerSocket(56);
        Cuenta reciboDatos = null;
        Cuenta aux = null;

        while (true) {
            Socket miConexion = server.accept();
            ObjectInputStream reciboDatosPak = new ObjectInputStream(miConexion.getInputStream());
            if (reciboDatos == null) {
                reciboDatos = (Cuenta) reciboDatosPak.readObject();
                datosTextArea.appendText("\"" + reciboDatos.getUsuario() + "\" ha iniciado sesion " + reciboDatos.getEncriptado());
            }
            aux = (Cuenta) reciboDatosPak.readObject();
            miConexion.close();
        }
    }
}
