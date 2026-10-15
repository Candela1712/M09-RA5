package src.iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static final String CLAU = "ClauSecretaCandela";
    private byte[] iv = new byte[MIDA_IV];

    @Override
    public TextXifrat xifra(String msg, String password)throws ClauNoSuportada {

        try {
            byte[] bytesMsg = msg.getBytes(StandardCharsets.UTF_8);

            generaIV();

            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            SecretKeySpec secretKey = generaHash(password);

            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

            byte[] msgXifrat = cipher.doFinal(bytesMsg);

            byte[] resultat = new byte[iv.length + msgXifrat.length];

            System.arraycopy(iv, 0, resultat, 0, iv.length);
            System.arraycopy(
                msgXifrat, 0, resultat, iv.length, msgXifrat.length
            );

            return new TextXifrat(resultat);

        } catch (Exception e) {
            throw new ClauNoSuportada(
                "Error en el xifratge AES: " + e.getMessage()
            );
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String password) throws ClauNoSuportada {

        try {
            byte[] bMsgXifrat = xifrat.getBytes();

            byte[] ivExtret = extreureIv(bMsgXifrat);
            byte[] bytesXifrats = getBytesXifrats(bMsgXifrat);

            SecretKeySpec secretKey = generaHash(password);

            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            IvParameterSpec ivSpec = new IvParameterSpec(ivExtret);

            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            byte[] bytesDesxifrats = cipher.doFinal(bytesXifrats);

            return new String(bytesDesxifrats, StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new ClauNoSuportada("Error en el desxifratge AES: " + e.getMessage());
        }
    }

    private void generaIV() {
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
    }

    private SecretKeySpec generaHash(String password)throws Exception {
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);

        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }

    private byte[] extreureIv(byte[] bMsgXifrat) {
        if (bMsgXifrat == null || bMsgXifrat.length < MIDA_IV) {
            throw new IllegalArgumentException("El missatge xifrat no conté un IV vàlid");
        }
        return Arrays.copyOfRange(bMsgXifrat, 0, MIDA_IV);
    }

    private byte[] getBytesXifrats(byte[] bMsgXifrat) {
        return Arrays.copyOfRange(bMsgXifrat, MIDA_IV, bMsgXifrat.length);
    }
}