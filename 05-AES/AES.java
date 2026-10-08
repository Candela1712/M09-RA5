import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV]
    private static final String CLAU = "ClauSecretaCandela";

    public static byte[] xifraAES(String msg, String password)throws Exception{
        byte[] bytesString = msg.getBytes();

        generaIV();
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        SecretKeySpec secretKey = generaHash(password);

    }
    public static String desxifraAES(byte[] bMsgXifrat, String password){

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

    public static void main(String[] args) {
        String msgs[]{"Lorem ipsum dicet", 
                "Hola Andrés como está tu cuñado",
                "Agora Ïlla Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";

            try {
                bXifrats = xifraAES(msg,CLAU);
                desxifrat = desxifraAES(bXifrats,CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }

            System.out.println("---------------------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: "+ new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
        
    }
    
}
