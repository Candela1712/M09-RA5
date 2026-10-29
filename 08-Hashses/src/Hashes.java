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
    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception{
        PBEKeySpec spec = new PBEKeySpec(
            pw.toCharArray(),salt.getBytes(StandardCharsets.UTF_8),
            10000,128
        );

        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] bytes = skf.generateSecret(spec).getEncoded();

        spec.clearPassword();
        HexFormat hex = HexFormat.of();

        return hex.formatHex(bytes);
    }

    private int npass = 0;
    public String forcaBruta(String alg, String hash, String salt) throws Exception{
        npass = 0;

        String charset = "abcdefABCDEF1234567890!.";

        for (int longitud = 6; longitud >= 1; longitud--) {
            String resultat = provaCombinacions(alg, hash, salt, new char[longitud], 0, charset);
            if (resultat != null) {
                return resultat;
            }
        }

        return null;
    }

    private String provaCombinacions(String alg, String hash, String salt, char[] password, int pos, String charset)
        throws Exception {

        if (pos == password.length) {
            String candidata = new String(password);
            npass++;

            String hashCandidat;

            if (alg.equalsIgnoreCase("SHA-512")) {
                hashCandidat = getSHA512AmbSalt(candidata, salt);
            } else if (alg.equalsIgnoreCase("PBKDF2")) {
                hashCandidat = getPBKDF2AmbSalt(candidata, salt);
            } else {
                throw new IllegalArgumentException("Algorisme no suportat: " + alg);
            }

            if (hashCandidat.equalsIgnoreCase(hash)) {
                return candidata;
            }

            return null;
        }

        for (int i = 0; i < charset.length(); i++) {
            password[pos] = charset.charAt(i);

            String resultat = provaCombinacions(alg, hash, salt, password, pos + 1, charset);

            if (resultat != null) {
                return resultat;
            }
        }

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


    public static void main(String[] args) throws Exception {
        String salt = "qopweiruañsñlkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = {h.getSHA512AmbSalt(pw, salt),h.getPBKDF2AmbSalt(pw, salt)};

        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++) {

            System.out.printf("==============================%n");
            System.out.printf("Algorisme: %s%n", algorismes[i]);
            System.out.printf("Hash: %s%n", aHashes[i]);
            System.out.printf("------------------------------%n");
            System.out.printf("-- Inici de força bruta --%n");

            long t1 = System.currentTimeMillis();

            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);

            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s%n", pwTrobat);
            System.out.printf("Provats: %d%n", h.npass);
            System.out.printf("Temps : %s%n", h.getInterval(t1, t2));
            System.out.printf("------------------------------%n%n");
        }
    }
}