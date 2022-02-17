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
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ServidorController implements Initializable {

    @FXML
    private TextArea datosTextArea;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            try {
                checkTipo();
            } catch (IOException | ClassNotFoundException | CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void checkTipo() throws IOException, ClassNotFoundException, CloneNotSupportedException {
        ServerSocket server = new ServerSocket(56);
        Cuenta reciboDatos = null;
        Cuenta aux = null;

        while (true) {
            Socket miConexion = server.accept();
            ObjectInputStream reciboDatosPak = new ObjectInputStream(miConexion.getInputStream());
            reciboDatos = (Cuenta) reciboDatosPak.readObject();
            if (aux == null || !reciboDatos.getUsuario().equalsIgnoreCase(aux.getUsuario())) {
                aux = (Cuenta) reciboDatos.clone();
                datosTextArea.appendText(aux.getUsuario() + " ha iniciado sesion");
                for (Extracto extracto : aux.getExtractos()) {
                    checkTipo(reciboDatos, extracto);
                }
            } else if (aux.getUsuario().equalsIgnoreCase(reciboDatos.getUsuario())) {
                ArrayList<Extracto> extractos = reciboDatos.getExtractos();
                Extracto extracto = extractos.get(extractos.size() - 1);
                checkTipo(reciboDatos, extracto);
            }
            datosTextArea.appendText(" " + new Date().toString());
            miConexion.close();
        }
    }

    private void checkTipo(Cuenta reciboDatos, Extracto extracto) {
        switch (extracto.getTipo()) {
            case INGRESO:
                datosTextArea.appendText("\n" + reciboDatos.getUsuario() + " ha hecho un ingreso de " + extracto.getSaldo());
                break;
            case RETIRO:
                datosTextArea.appendText("\n" + reciboDatos.getUsuario() + " ha hecho un retiro de " + extracto.getSaldo());
                break;
            case TRANSFERENCIA:
                if (extracto.getSaldo() > 0) {
                    datosTextArea.appendText("\n" + reciboDatos.getUsuario() + " ha recibido una " + "transferencia " + "de " + extracto.getSaldo() + " de parte de " + extracto.getPersonaTransferencia().getUsuario());
                } else {
                    datosTextArea.appendText("\n" + reciboDatos.getUsuario() + " ha hecho una transferencia " + "de " + extracto.getSaldo() + " a " + extracto.getPersonaTransferencia().getUsuario());
                }
                break;
        }
    }
}
