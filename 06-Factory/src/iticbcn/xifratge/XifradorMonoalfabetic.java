package src.iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {
    private static String alfabet = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private static final char[] majuscules = alfabet.toUpperCase().toCharArray();

    private char[] alfabetPermutat;

    // La permutació s'inicialitza al constructor
    public XifradorMonoalfabetic() {
        alfabetPermutat = permutaAlfabet(majuscules);
    }

    private char[] permutaAlfabet(char[] abc) {
        char[] permutat = new char[abc.length];

        List<Character> alfabetList = new ArrayList<>();
        for (char c : abc) {
            alfabetList.add(c);
        }

        Collections.shuffle(alfabetList);

        for (int i = 0; i < alfabetList.size(); i++) {
            permutat[i] = alfabetList.get(i);
        }

        return permutat;
    }

    private int buscaPosAbecedari(char c, char[] abc) {
        for (int i = 0; i < abc.length; i++) {
            if (abc[i] == c) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada(
                "Xifratge monoalfabètic no suporta clau != null"
            );
        }

        String xifrat = xifraMonoAlfa(msg);
        return new TextXifrat(
            xifrat.getBytes(java.nio.charset.StandardCharsets.UTF_8)
        );
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada(
                "Xifratge monoalfabètic no suporta clau != null"
            );
        }
        String textXifrat = new String(
            xifrat.getBytes(),
            java.nio.charset.StandardCharsets.UTF_8
        );
        return desxifraMonoAlfa(textXifrat);
    }

    private String xifraMonoAlfa(String cadena) {
        String xifrat = "";
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            int pos = buscaPosAbecedari(
                Character.toUpperCase(c), majuscules
            );

            if (pos == -1) {
                xifrat += c;
            } else {
                char lletraXifrada = alfabetPermutat[pos];

                if (Character.isLowerCase(c)) {
                    lletraXifrada = Character.toLowerCase(lletraXifrada);
                }

                xifrat += lletraXifrada;
            }
        }

        return xifrat;
    }

    private String desxifraMonoAlfa(String cadena) {
        String desxifrat = "";
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

            int pos = buscaPosAbecedari(
                Character.toUpperCase(c), alfabetPermutat
            );

            if (pos == -1) {
                desxifrat += c;
            } else {
                char lletraDesxifrada = majuscules[pos];

                if (Character.isLowerCase(c)) {
                    lletraDesxifrada =
                        Character.toLowerCase(lletraDesxifrada);
                }

                desxifrat += lletraDesxifrada;
            }
        }

        return desxifrat;
    }
}