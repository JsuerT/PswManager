package service; 

import java.util.HashMap; 

public class Caeser {
    private HashMap<Character, Character> encryptionMap; 
    private HashMap<Character, Character> decryptionMap; 

    public Caeser(int moveChar) {
        encryptionMap = new HashMap<>(); 
        decryptionMap = new HashMap<>(); 
        initCaesar(moveChar); 
    }

    private void initCaesar(int moveChar) {
        for (int i = 32; i < 127; i++) {
            char original = (char) i;
            char shifted = (char) (32 + (i - 32 + moveChar) % 95);
            encryptionMap.put(original, shifted);
            decryptionMap.put(shifted, original);
        }
    }

    public String encrypt(String text) {
        return translate(text, encryptionMap);
    }

    public String decrypt(String text) {
        return translate(text, decryptionMap);
    }

    private String translate(String text, HashMap<Character, Character> alphabet) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            result.append(alphabet.getOrDefault(c, c));
        }
        return result.toString();
    }
}
