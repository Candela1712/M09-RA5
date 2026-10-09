public class XifradorRotx implements Xifrador{
    static String abc = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    static char[] minuscules = abc.toCharArray();
    static char[] majuscules = abc.toUpperCase().toCharArray();


    public static String xifraRotX(String cadena, int desplacament){
        String textXifrat = "";

        for(int i = 0; i<cadena.length(); i++){
            textXifrat += transformaChar(cadena.charAt(i), desplacament);
        }
        return textXifrat;
    }
    public static String desxifraRotX( String cadena, int desplacament){
        String textDesxifrat = "";
        
        for(int i = 0; i < cadena.length(); i++){
            textDesxifrat += transformaChar(cadena.charAt(i), desplacament * -1);
        }
        return textDesxifrat;
    }

    public static void forcaBrutaRotX(String cadenaXifrada){
        String cadenaDesxifrada;
        System.out.printf("%nMissatge xifrat: %s%n",  cadenaXifrada);
        for(int i = 0; i < abc.length(); i++){
            cadenaDesxifrada =  desxifraRotX(cadenaXifrada, i);
            System.out.printf("(%d) -> %s%n",i,cadenaDesxifrada);
        }

    }
    public static int buscaPosAbecedari(char c, char[]abecedari){
        for(int i = 0 ; i < abecedari.length; i++ ){
            if(abecedari[i] == c){
                return i;
            }
        }
        return -1;
    }

    public static char transformaChar(char c, int desplacament){
        char[] abecedari;

        if(Character.isUpperCase(c)){
            abecedari = majuscules;
        }else if (Character.isLowerCase(c)){
            abecedari = minuscules;
        }else{
            return c;
        }
        int pos = (buscaPosAbecedari(c, abecedari) + desplacament +abecedari.length) %abecedari.length;
        return abecedari[pos];
    }
}
