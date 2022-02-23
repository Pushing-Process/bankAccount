package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Cuenta;
import model.Extracto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ServidorController implements Initializable {

    @FXML
    private TextArea datosTextArea;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField userTextField;

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
                userTextField.setText(aux.getUsuario());
                passwordTextField.setText(aux.getEncriptado());
                datosTextArea.appendText("\'" + aux.getUsuario() + "\' ha iniciado sesion con contraseña  \'" + aux.getEncriptado() + "\'");
                for (Extracto extracto : aux.getExtractos()) {
                    checkTipo(reciboDatos, extracto);
                }
            } else if (aux.getUsuario().equalsIgnoreCase(reciboDatos.getUsuario())) {
                ArrayList<Extracto> extractos = reciboDatos.getExtractos();
                if (extractos.size() > 0) {
                    Extracto extracto = extractos.get(extractos.size() - 1);
                    checkTipo(reciboDatos, extracto);
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
            datosTextArea.appendText(" - " + sdf.format(new Date()) + "\n");
            miConexion.close();
        }
    }

    private void checkTipo(Cuenta reciboDatos, Extracto extracto) {
        datosTextArea.appendText("\'" + reciboDatos.getUsuario() + "\'");
        switch (extracto.getTipo()) {
            case INGRESO:
                datosTextArea.appendText(" ha hecho un ingreso de " + extracto.getSaldo());
                break;
            case RETIRO:
                datosTextArea.appendText(" ha hecho un retiro de " + extracto.getSaldo());
                break;
            case TRANSFERENCIA:
                if (extracto.getSaldo() > 0) {
                    datosTextArea.appendText(" ha recibido una " + "transferencia " + "de " + extracto.getSaldo() +
                            " de " +
                            "parte de " + extracto.getPersonaTransferencia().getUsuario());
                } else {
                    datosTextArea.appendText(" ha hecho una transferencia " + "de " + extracto.getSaldo() + " a " + extracto.getPersonaTransferencia().getUsuario());
                }
                break;
        }
    }
}
