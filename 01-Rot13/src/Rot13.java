public class Rot13 {

    static char[] majuscules ={'A','Á','À','B','C','Ç','D','E','É','È','F','G','H','I',
    'Í','Ì','Ï','J','K','L','M','N','Ñ','O','Ó','Ò','P','Q','R','S','T','U',
    'Ú','Ù','Ü','V','W','X','Y','Z'};

    static char[] minuscules ={'a','á','à','b','c','ç','d','e','é','è','f','g','h','i',
    'í','ì','ï','j','k','l','m','n','ñ','o','ó','ò','p','q','r','s','t','u',
    'ú','ù','ü','v','w','x','y','z'};
    
    public static int buscaPos(char c , char [] abc){
        for (int i = 0; i < abc.length; i++ ){
            if(abc[i] == c ){
                return i;
            }     
        }

        return -1;
    }

    public static char transformaChar(char c, int desplacament){
        char[] abc;

        if (Character.isUpperCase(c)){
            abc = majuscules;
        }else if(Character.isLowerCase(c)){
            abc = minuscules;
        }else{
            return c;
        }
        int pos = buscaPos(c, abc);
        int pos2 = (pos + desplacament + abc.length) % abc.length;
        return abc[pos2];
    }
    
    public static String xifraRot13(String cadena){
        String textXifrat = "";
        
        for (int i = 0; i < cadena.length(); i++ ){
            textXifrat += transformaChar(cadena.charAt(i), 13);
        }
        return textXifrat;
    }

    public static String desxifraRot13(String cadena){
        String textDesxifrat = "";
        for (int i = 0; i < cadena.length(); i++ ){
            textDesxifrat += transformaChar(cadena.charAt(i), -13);
        }

        return textDesxifrat;
    }
    public static void main(String[] args) {
        System.out.println(xifraRot13("ABZ"));
        System.out.println(xifraRot13("XYZ"));
        System.out.println(xifraRot13("Hola, Mr. calçot"));

        System.out.println(desxifraRot13("IÏJ"));
        System.out.println(desxifraRot13("FGH"));
        System.out.println(desxifraRot13("Òwúi, Ùá. jiúkwb"));
    }
}