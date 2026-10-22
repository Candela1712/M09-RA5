import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica{
    public keyPair generaParellClausRSA() throws Exception{
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
        generador.initialize(2048);
        return generador.generateKeyPair();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception{
        Cipher xifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        xifrador.init(Cipher.ENCRYPT_MODE, clauPublica);
        byte[] missatge = msg.getBytes(StandardCharsets.UTF_8);

        return xifrador.doFinal(missatge);
    }

    public String desxifrRSA(byte[] msgXifrat, PrivateKey ClauPrivada){

    }
} 