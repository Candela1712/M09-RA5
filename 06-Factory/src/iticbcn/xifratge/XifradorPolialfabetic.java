package src.iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.nio.charset.StandardCharsets;

public class XifradorPolialfabetic implements Xifrador {
   private static String alfabet = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
   private static final char[] majuscules = alfabet.toUpperCase().toCharArray();
   private char[] alfabetPermutat = new char[majuscules.length];
   private Random random;
   public void initRandom(long clau) {random = new Random(clau);}

   private void permutaAlfabet() {
      List<Character> alfabetList = new ArrayList<>();

      for (char c : majuscules) {
           alfabetList.add(c);
       }
      Collections.shuffle(alfabetList, random);

      for (int i = 0; i < alfabetList.size(); i++) {
            alfabetPermutat[i] = alfabetList.get(i);
        }
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
    public TextXifrat xifra(String msg, String clau)throws ClauNoSuportada {
        long clauNumerica;

        try {
            clauNumerica = Long.parseLong(clau);
        } catch (Exception e) {
            throw new ClauNoSuportada(
                "La clau per xifrar Polialfabètic ha de ser un String convertible a long"
            );
        }

        initRandom(clauNumerica);

        String xifrat = "";

        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            permutaAlfabet();

            int pos = buscaPosAbecedari(
                Character.toUpperCase(c), majuscules
            );

            if (pos == -1) {
                xifrat += c;
            } else {
                char lletraXifrada = alfabetPermutat[pos];

                if (Character.isLowerCase(c)) {
                    lletraXifrada =
                        Character.toLowerCase(lletraXifrada);
                }

                xifrat += lletraXifrada;
            }
        }

        return new TextXifrat(xifrat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
   public String desxifra(TextXifrat xifrat, String clau)throws ClauNoSuportada {
      long clauNumerica;

       try {
         clauNumerica = Long.parseLong(clau);
      } catch (Exception e) {
         throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
      }

      initRandom(clauNumerica);

      String msgXifrat = new String(xifrat.getBytes(), StandardCharsets.UTF_8);
      String desxifrat = "";

      for (int i = 0; i < msgXifrat.length(); i++) {
         char c = msgXifrat.charAt(i);
         permutaAlfabet();
         int pos = buscaPosAbecedari(Character.toUpperCase(c), alfabetPermutat);
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