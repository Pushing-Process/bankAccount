package controller;

import model.AES;
import model.Cuenta;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class EnvioController {
    final private String clave = "ExamenPSP2Eval";

    public EnvioController(Cuenta cuenta) throws IOException, NoSuchPaddingException, IllegalBlockSizeException,
            NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Socket miConexion = new Socket("localhost", 56);
        AES aes = new AES();
        cuenta.setEncriptado(aes.encriptar(String.valueOf(cuenta.getPassword()), clave));
        ObjectOutputStream oos = new ObjectOutputStream(miConexion.getOutputStream());
        oos.writeObject(cuenta);
    }
}
