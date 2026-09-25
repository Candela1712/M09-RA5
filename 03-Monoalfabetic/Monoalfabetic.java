import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {

    private static String abc = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private static final char[] majuscules = abc.toUpperCase().toCharArray();

    public static char[] permutaAlfabet(char[] alfabet){
        char[] alfabetPermutat = new char[alfabet.length];

        List<Character> alfabetList = new ArrayList<>();
        for (char c : alfabet) {
            alfabetList.add(c);
        }
        Collections.shuffle(alfabetList);
        for(int i = 0; i < alfabetList.size(); i++){
            alfabetPermutat[i] = alfabetList.get(i);
        }
        return alfabetPermutat;

    }

    public static String xifraMonoAlfa(String cadena){
        String xifrat;

        return xifrat;
    }

    public static String desxifraMonoAlfa(String cadena){
        String desxifrat;

        return desxifrat;
    }
    public static void main(String[] args) {
        char[] a = permutaAlfabet(majuscules);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
            
        }
    }
}
