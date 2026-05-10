package service; 

import java.util.HashMap; 

public class Caeser{
    HashMap<Character, Character> encryption; 
    HashMap<Character, Character> decryption; 

    public Caesar(int moveChar){
        super(); 
        encryption = new HashMap<Character, Character>(); 
        decryption = new HashMap<Character, Character>(); 

        initCaesar(moveChar); 
    }

    private void initChar(int moveChar){
        for(char loop='A'; loop <= 'Z'; loop++){
            if((loop+moveChar) <= 'Z'){
            encryption.put(loop,(char) (loop+moveChar));
            decryption.put((char) (loop+moveChar), loop);
            }else{
            encryption.put(loop,(char) (loop+moveChar-26));
            decryption.put((char) (loop+moveChar-26), loop);

            }            
            
        }
    }

    public String decryption(String text){
        return translage(text, decryption);
    }

    private String translate(String text, HashMap<Character, Character>alphabet){
        String textOptimized = text.toUpperCase().replace(" ", "");
        String final = "";
        for (int = 0; i < textOptimized.length(); i++){
            final += alphabet.get(textOptimized.charAt(i));
        }
        return text; 

    }
}
