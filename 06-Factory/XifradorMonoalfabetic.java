import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador{

    private static String alfabet = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private static final char[] majuscules = alfabet.toUpperCase().toCharArray();
    private static final char[] alfabetPermutat = permutaAlfabet(majuscules);

    public static char[] permutaAlfabet(char[] abc){
        char[] alfabetPermutat = new char[abc.length];

        List<Character> alfabetList = new ArrayList<>();
        for (char c : abc) {
            alfabetList.add(c);
        }
        Collections.shuffle(alfabetList);
        for(int i = 0; i < alfabetList.size(); i++){
            alfabetPermutat[i] = alfabetList.get(i);
        }
        return alfabetPermutat;

    }
    public static int buscaPosAbecedari(char c, char[] abc){
            for (int i = 0; i < abc.length; i++ ){
            if(abc[i] == c ){
                return i;
            }     
        }

        return -1;
    }

    public static String xifraMonoAlfa(String cadena){
        String xifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            
            int pos = buscaPosAbecedari(Character.toUpperCase(c), majuscules);

            if (pos == -1) {
                xifrat += c;
            }else{
                char lletraXifrada = alfabetPermutat[pos];

                if(Character.isLowerCase(c)){
                    lletraXifrada = Character.toLowerCase(lletraXifrada);
                }
                xifrat += lletraXifrada;
            }
        }
        return xifrat;
    }

    public static String desxifraMonoAlfa(String cadena){
        String desxifrat = "";

            for (int i = 0; i < cadena.length(); i++) {
                char c = cadena.charAt(i);

                int pos = buscaPosAbecedari(Character.toUpperCase(c), alfabetPermutat);
                if (pos == -1) {
                    desxifrat += c;
                }else{
                    char lletraDesxifrada = majuscules[pos];

                    if (Character.isLowerCase(c)) {
                        lletraDesxifrada = Character.toLowerCase(lletraDesxifrada);
                    }
                    desxifrat += lletraDesxifrada;
                }
            }
        return desxifrat;
    }
}
