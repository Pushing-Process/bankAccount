package controller;

import com.example.login.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Cuenta;
import model.LoggedUser;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public static Cuenta cuentaUser;

    @FXML
    public TextField userTextField;

    @FXML
    public PasswordField passwordTextField;

    @FXML
    public Button loginButton;

    static SceneController s = new SceneController();

    @FXML
    public void OnLoginButtonClick(ActionEvent event) {
        SceneController s = new SceneController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnMouseClicked(mouseEvent -> {
            try {
                for (Cuenta cuenta : Main.cuentas) {
                    if (cuenta.getUsuario().equalsIgnoreCase(userTextField.getText()) && cuenta.getPassword() == Integer.parseInt(passwordTextField.getText())) {
                        cuentaUser = cuenta;
                        try {
                            Socket localhost = new Socket("localhost", 1234);
                            final String encryptKey = "secreta";

                            String password = encryptMethod(passwordTextField.getText(), encryptKey);
                            String userName = userTextField.getText();
                            LoggedUser data = new LoggedUser();
                            data.setUserPassword(password);
                            data.setUserPassword(userName);

                            ObjectOutputStream outputStream = new ObjectOutputStream(localhost.getOutputStream());
                            outputStream.writeObject(data);

                        } catch (IOException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e1) {
                            e1.printStackTrace();
                        }
                        s.switchSceneMenu(mouseEvent);
                        return;
                    }
                }

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Look, an Error Dialog");
                alert.setContentText("I have a great message for you!");
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private SecretKeySpec createKey(String clave) throws NoSuchAlgorithmException {
        byte[] encryptKey = clave.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        encryptKey = sha.digest(encryptKey);
        encryptKey = Arrays.copyOf(encryptKey, 16);
        return new SecretKeySpec(encryptKey, "AES");
    }

    public String encryptMethod(String datos, String secretKeySpec) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec secretKey = createKey(secretKeySpec);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptData = datos.getBytes(StandardCharsets.UTF_8);
        byte[] encryptBytes = cipher.doFinal(encryptData);

        return Base64.getEncoder().encodeToString(encryptBytes);
    }
}