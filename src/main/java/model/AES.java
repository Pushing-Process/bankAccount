package model;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AES {
    private SecretKeySpec crear(String clave) throws NoSuchAlgorithmException {
        byte[] claveEncript = clave.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        claveEncript = sha.digest(claveEncript);
        claveEncript = Arrays.copyOf(claveEncript, 16);
        return new SecretKeySpec(claveEncript, "AES");
    }

    public String encriptar(String datosEncriptados, String claveSecreta) throws UnsupportedEncodingException,
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {

        SecretKeySpec secretKey = crear(claveSecreta);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] datosEcriptados = datosEncriptados.getBytes("UTF-8");
        byte[] bytesEncriptados = cipher.doFinal(datosEcriptados);

        return Base64.getEncoder().encodeToString(bytesEncriptados);

    }

    public String desencriptar(String datosEncriptados, String claveSecreta) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec secretKey = crear(claveSecreta);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] bytesEcriptados = Base64.getDecoder().decode(datosEncriptados);
        byte[] datosDesencriptados = cipher.doFinal(bytesEcriptados);

        return new String(datosDesencriptados);

    }
}