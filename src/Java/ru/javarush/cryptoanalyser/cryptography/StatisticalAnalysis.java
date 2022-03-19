package ru.javarush.cryptoanalyser.cryptography;

import ru.javarush.cryptoanalyser.utility.CharacterFrequencyMapGenerator;
import ru.javarush.cryptoanalyser.utility.TxtFileReader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticalAnalysis {

    public List<String> decodeByStatisticalAnalysis(String encryptedFileName, String exampleFileName) {
        List<String> encryptedLines = null;
        List<String> resultList = new ArrayList<>();
        try {
            encryptedLines = new TxtFileReader().txtToList(Path.of(encryptedFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<Character, Character> decryptDictionary;
        HashMap<Character, Double> exampleMap =
                new CharacterFrequencyMapGenerator().generateFrequencyMap(exampleFileName);
        HashMap<Character, Double> encryptedMap =
                new CharacterFrequencyMapGenerator().generateFrequencyMap(encryptedFileName);

        decryptDictionary = confirmMaps(encryptedMap, exampleMap);

        if (encryptedLines != null) {
            for (String s : encryptedLines) {
                char[] stringChars = s.toCharArray();
                for (int i = 0; i < stringChars.length; i++) {
                    for (Map.Entry<Character, Character> entry : decryptDictionary.entrySet()) {
                        boolean isUpperCase = Character.isUpperCase(stringChars[i]);
                        if (Character.toLowerCase(stringChars[i]) == entry.getValue()) {
                            stringChars[i] = isUpperCase ? Character.toUpperCase(entry.getKey()) : entry.getKey();
                            break;
                        }
                    }
                }
                resultList.add(new String(stringChars));
            }
        }

        return resultList;
    }

    public HashMap<Character, Character> confirmMaps(HashMap<Character, Double> encryptedMap, HashMap<Character, Double> exampleMap){
        HashMap<Character, Character> confirmationResult = new HashMap<>();
        for (Map.Entry<Character, Double> entry : exampleMap.entrySet()) {
            double frequencyExample = entry.getValue();
            for (Map.Entry<Character, Double> encryptedEntry : encryptedMap.entrySet()) {
                double frequencyEncrypted = encryptedEntry.getValue();
                if (Math.abs(frequencyExample - frequencyEncrypted) < frequencyExample * 0.035){
                    confirmationResult.put(entry.getKey(), encryptedEntry.getKey());
                }
            }
        }
        return confirmationResult;
    }
}
