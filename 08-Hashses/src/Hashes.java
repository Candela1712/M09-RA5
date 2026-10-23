import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    public String getSHA512AmbSalt(String pw, String salt)throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");

        byte[] bytes = md.digest((pw + salt).getBytes(StandardCharsets.UTF_8));
        HexFormat hex = HexFormat.of();

        return hex.formatHex(bytes);
    }
    public String getPBKDFAmbSalt(String pw, String salt) throws Exception{
        PBEKeySpec spec = new PBEKeySpec(
            pw.toCharArray(),salt.getBytes(StandardCharsets.UTF_8),
            10000,256
        );

        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] bytes = skf.generateSecret(spec).getEncoded();

        spec.clearPassword();
        HexFormat hex = HexFormat.of();

        return hex.formatHex(bytes);
    }

    public  String forcaBruta(String alg, String hash, String salt){
        return null;
    }
    
    public String getInterval(long t1, long t2){

        long dif = t2 - t1;
        long dias = dif / 86400000;
        dif %= 86400000;
        long horas = dif / 3600000;
        dif %= 3600000;
        long min = dif / 60000;
        dif %= 60000;
        long segundos = dif / 1000;
        long miliseg = dif % 1000;

        return dias + " dies / " + horas + " hores / "
            + min + " minuts / " + segundos
            + " segons / " + miliseg + " millis";
    }

    public static  void main(String[] args){

    }
}