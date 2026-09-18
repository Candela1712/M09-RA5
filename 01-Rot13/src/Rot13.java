public class Rot13 {

    static char[] majuscules ={'A','Á','À','B','C','Ç','D','E','É','È','F','G','H','I',
    'Í','Ì','Ï','J','K','L','M','N','Ñ','O','Ó','Ò','P','Q','R','S','T','U',
    'Ú','Ù','Ü','V','W','X','Y','Z'};

    static char[] minuscules ={'a','á','à','b','c','ç','d','e','é','è','f','g','h','i',
    'í','ì','ï','j','k','l','m','n','ñ','o','ó','ò','p','q','r','s','t','u',
    'ú','ù','ü','v','w','x','y','z'};
    
   
    public static String xifraRot13(String cadena){
        String textXifrat = "";
        
        for (int i = 0; i < cadena.length(); i++ ){
            int pos = -1;
            int pos2 = -1;
            char c = cadena.charAt(i);
            char c2 = ' ';

            if (Character.isUpperCase(c)){
                for(int j = 0; j < majuscules.length; j++ ){
                    if(majuscules[j] == c ){
                        pos = j;
                        break;
                    } 
                }
                pos2 = (pos + 13) % majuscules.length;
                c2 = majuscules[pos2];
            }else if (Character.isLowerCase(c)){
                for(int j = 0; j < minuscules.length; j++ ){
                    if(minuscules[j] == c ){
                        pos = j;
                        break;
                    } 
                }
                pos2 = (pos + 13) % minuscules.length;
                c2 = minuscules[pos2];
            }else{
                c2=c;
            }
            textXifrat += c2;
        }
        return textXifrat;
    }
    public static String desxifraRot13(String cadena){
        String textDesxifrat = "";
        return textDesxifrat;
    }
    public static void main(String[] args) {
        System.out.println(xifraRot13("ABZ"));
        System.out.println(xifraRot13("XYZ"));
        System.out.println(xifraRot13("Hola, Mr. calçot"));
    }
}