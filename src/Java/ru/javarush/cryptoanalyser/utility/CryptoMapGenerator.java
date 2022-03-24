package ru.javarush.cryptoanalyser.utility;

import ru.javarush.cryptoanalyser.Constants.Constants;

import java.util.HashMap;

public class CryptoMapGenerator {

    public HashMap<Character, Character> generateCryptoMap(int key) {
        HashMap<Character, Character> cryptoMap = new HashMap<>();
        if (key > 0) {
            for (int i = 0; i < Constants.ALPHABET.length; i++) {
                char encodedChar;
                if ((i + key) < Constants.ALPHABET.length) {
                    encodedChar = Constants.ALPHABET[i + key];
                } else {
                    encodedChar = Constants.ALPHABET[i - Constants.ALPHABET.length + key];
                }
                cryptoMap.put(Constants.ALPHABET[i], encodedChar);

            }
        } else {
            for (int i = 0; i < Constants.ALPHABET.length; i++) {
                char decodedChar;
                if ((i + key) < 0) {
                    decodedChar = Constants.ALPHABET[Constants.ALPHABET.length + key + i];
                } else {
                    decodedChar = Constants.ALPHABET[i + key];
                }
                cryptoMap.put(Constants.ALPHABET[i], decodedChar);
            }
        }
        return cryptoMap;
    }
}
