package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author adriansb3105
 */
public class Encriptacion {

    public static String MD2 = "MD2";
    public static String MD5 = "MD5";
    public static String SHA1 = "SHA-1";//Secure Hash Algorithm
    public static String SHA256 = "SHA-256";
    public static String SHA384 = "SHA-384";
    public static String SHA512 = "SHA-512";

    private static String convertirAHexadecimal(byte[] digest) {
        String hash = "";

        for (byte aux : digest) {

            int b = aux & 0xff;

            if (Integer.toHexString(b).length() == 1) {
                hash += "0";
            }
            hash += Integer.toHexString(b);
        }

        return hash;
    }

    public static String getStringMessageDigest(String message, String algorithm) {

        byte[] digest = null;//resumen
        byte[] buffer = message.getBytes();
        
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();

        } catch (NoSuchAlgorithmException ex) {
            System.err.println("Error creando Digest");
        }

        return convertirAHexadecimal(digest);
    }
}
