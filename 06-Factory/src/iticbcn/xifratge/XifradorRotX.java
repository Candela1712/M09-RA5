package src.iticbcn.xifratge;
public class XifradorRotX implements Xifrador {
    private static String abc = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private static char[] minuscules = abc.toCharArray();
    private static char[] majuscules = abc.toUpperCase().toCharArray();

    @Override
    public TextXifrat xifra(String msg, String clau)throws ClauNoSuportada {
        int desplacament;

        try {
            desplacament = Integer.parseInt(clau);
        } catch (Exception e) {
            throw new ClauNoSuportada(
                "La clau de RotX ha de ser un nombre entre 0 i 40"
            );
        }

        if (desplacament < 0 || desplacament > 40) {
            throw new ClauNoSuportada(
                "La clau de RotX ha de ser un nombre entre 0 i 40"
            );
        }

        String textXifrat = xifraRotX(msg, desplacament);
        return new TextXifrat(textXifrat.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau)throws ClauNoSuportada {
        int desplacament;

        try {
            desplacament = Integer.parseInt(clau);
        } catch (Exception e) {
            throw new ClauNoSuportada(
                "La clau de RotX ha de ser un nombre entre 0 i 40"
            );
        }

        if (desplacament < 0 || desplacament > 40) {
            throw new ClauNoSuportada(
                "La clau de RotX ha de ser un nombre entre 0 i 40"
            );
        }
        String textXifrat = new String(xifrat.getBytes(), java.nio.charset.StandardCharsets.UTF_8);

        return desxifraRotX(textXifrat, desplacament);
    }

    private static String xifraRotX(String cadena, int desplacament) {
        String textXifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            textXifrat += transformaChar(cadena.charAt(i), desplacament);
        }

        return textXifrat;
    }

    private static String desxifraRotX(String cadena, int desplacament) {
        String textDesxifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            textDesxifrat += transformaChar(cadena.charAt(i), -desplacament);
        }

        return textDesxifrat;
    }

    private static int buscaPosAbecedari(char c, char[] abecedari) {
        for (int i = 0; i < abecedari.length; i++) {
            if (abecedari[i] == c) {
                return i;
            }
        }

        return -1;
    }

    private static char transformaChar(char c, int desplacament) {
        char[] abecedari;

        if (Character.isUpperCase(c)) {
            abecedari = majuscules;
        } else if (Character.isLowerCase(c)) {
            abecedari = minuscules;
        } else {
            return c;
        }

        int pos = buscaPosAbecedari(c, abecedari);

        if (pos == -1) {
            return c;
        }

        pos = (pos + desplacament + abecedari.length) % abecedari.length;

        return abecedari[pos];
    }
}