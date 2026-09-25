public class Rotx {
    static String abc = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüwxyz";
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

    public static void forcaBrutaRotX( String cadenaXifrada){


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

    public static void main(String[] args) {
        System.out.println(xifraRotX("ABZ",0));
        System.out.println(xifraRotX("XYZ", 2));
        System.out.println(xifraRotX("Hola, Mr. calçot", 4));
        System.out.println(xifraRotX("Perdó, per tu què és ?", 6));
        
    }
}
