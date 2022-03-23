package ru.javarush.cryptoanalyser.utility;

import ru.javarush.cryptoanalyser.Constants.Constants;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterFrequencyMapGenerator {
    public HashMap<Character, Double> generateFrequencyMap(String fileName) {
        HashMap<Character, Double> resultMap;
        List<String> fileLines = new ArrayList<>();
        double sumOfCharsNumbers = 0;
        try {
            fileLines = TxtFileWorker.getInstance().txtToList(Path.of(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultMap = calculateCharsNumbers(fileLines);

        for (Map.Entry<Character, Double> entry : resultMap.entrySet()) {
            sumOfCharsNumbers += entry.getValue();
        }

        for (Map.Entry<Character, Double> entry : resultMap.entrySet()) {
            entry.setValue(entry.getValue() / sumOfCharsNumbers * Constants.MAX_PERCENT_VALUE);
        }

        return resultMap;
    }

    public HashMap<Character, Double> calculateCharsNumbers(List<String> fileLines) {
        HashMap<Character, Double> workMap = new HashMap<>();

        for (int i = 0; i < Constants.ALPHABET.length; i++) {
            workMap.put(Constants.ALPHABET[i], 0.0);
        }
        for (String s : fileLines) {
            char[] charsFromLine = s.toCharArray();
            for (char currentChar : charsFromLine) {
                if (workMap.containsKey(currentChar)) {
                    workMap.put(currentChar, (workMap.get(currentChar) + 1));
                }
            }
        }
        return workMap;
    }


}
