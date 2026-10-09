import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador{
    
   private static String alfabet = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
   private static final char[] majuscules = alfabet.toUpperCase().toCharArray();
   private static char[] alfabetPermutat = new char[majuscules.length];
   private static Random random;
   private static final int clauSecreta = 2468;

   public static void permutaAlfabet(){
      List<Character> alfabetList = new ArrayList<>();

      for(char c: majuscules){
         alfabetList.add(c);
      }

      Collections.shuffle(alfabetList, random);
      for (int i = 0; i < alfabetList.size(); i++) {
         alfabetPermutat[i] = alfabetList.get(i);
      }
   }

   public static String xifraPoliAlfa(String msg){
      String xifrat = "";

      for (int i = 0; i < msg.length(); i++) {
         char c = msg.charAt(i);
         permutaAlfabet();
         int pos = buscaPosAbecedari(Character.toUpperCase(c), majuscules);

         if (pos == -1) {
            xifrat += c;
         }else{
            char lletraXifrada = alfabetPermutat[pos];
            if (Character.isLowerCase(c)) {
               lletraXifrada = Character.toLowerCase(lletraXifrada);
            }
            xifrat += lletraXifrada;
         }
      }
      return xifrat;
   }

   public static String desxifraPoliAlfa(String msgXifrat){
      String desxifrat = "";

      for (int i = 0; i < msgXifrat.length(); i++) {
         char c = msgXifrat.charAt(i);
         permutaAlfabet();
         
         int pos = buscaPosAbecedari(Character.toUpperCase(c), alfabetPermutat);

         if (pos == -1) {
            desxifrat += c;
         }else{
            char lletraDesxifrada = majuscules[pos];
            if(Character.isLowerCase(c)){
               lletraDesxifrada = Character.toLowerCase(lletraDesxifrada);
            }
            desxifrat += lletraDesxifrada;
         }
      }
      return desxifrat;
   }

   public static void initRandom(int clau){
      random = new Random(clau);
   }

   public static int buscaPosAbecedari(char c, char[] abc){
      for (int i = 0; i < abc.length; i++ ){
         if(abc[i] == c ){
            return i;
         }     
      }
        return -1;
    }
}
