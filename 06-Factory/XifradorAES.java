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
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "ClauSecretaCandela";

    public static byte[] xifraAES(String msg, String password)throws Exception{
        byte[] bytesMsg = msg.getBytes();

        generaIV();
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        SecretKeySpec secretKey = generaHash(password);

        Cipher cipher =Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        byte[] msgXifrat = cipher.doFinal(bytesMsg);
        byte[]resultat = new byte[iv.length + msgXifrat.length];

        System.arraycopy(iv, 0, resultat, 0, iv.length);
        System.arraycopy(msgXifrat, 0, resultat, iv.length, msgXifrat.length);

        return resultat;
    }

    public static String desxifraAES(byte[] bMsgXifrat, String password)throws Exception{
        byte[] iv = extreureIv(bMsgXifrat);
        byte[] bytesXifrats = getBytesXifrats(bMsgXifrat);

        SecretKeySpec secretKey = generaHash(password);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        byte[] bytesDesxifrats = cipher.doFinal(bytesXifrats);

        return new String(bytesDesxifrats,"UTF-8");
    }

    public static void generaIV() {
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
    }
    
    public static SecretKeySpec generaHash(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = digest.digest(password.getBytes());
        return new SecretKeySpec(hash,ALGORISME_XIFRAT);
    }

    public static byte[] extreureIv(byte[] bMsgXifrat) {
        return Arrays.copyOfRange(bMsgXifrat, 0, MIDA_IV);
    }
    public static byte[] getBytesXifrats(byte[] bMsgXifrat) {
        return Arrays.copyOfRange(bMsgXifrat, MIDA_IV, bMsgXifrat.length);
    }
}
