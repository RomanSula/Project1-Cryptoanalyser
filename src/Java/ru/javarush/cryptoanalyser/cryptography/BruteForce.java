package ru.javarush.cryptoanalyser.cryptography;

import ru.javarush.cryptoanalyser.Constants.Constants;
import ru.javarush.cryptoanalyser.utility.TxtFileWriter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BruteForce {
    public int decodeByBruteForce(Path fileName, double accuracy) {
        List<String> resultList;
        List<List<String>> successList = new ArrayList<>();

        for (int i = 1; i < Constants.ALPHABET.length; i++) {
            resultList = new Decode().decodeTxtFile(fileName, i);
            if (isBruteForceSucceeded(resultList, accuracy)) {
                successList.add(resultList);
                System.out.println("Key = " + i + " looks good");
            }
        }

        int nameCounter = 0;
        for (List<String> stringList : successList) {
            String outFileName = nameCounter == 0 ? "decryptedFile" : "decryptedFile" + nameCounter;
            new TxtFileWriter().fileWriterMethod(stringList, outFileName + ".txt");
            nameCounter++;
        }
        return successList.size();
    }

    public boolean isBruteForceSucceeded(List<String> fileLinesList, double accuracy) {
        int shortWordsCounter = 0;
        int correctShortWordsCounter = 0;

        for (String s : fileLinesList) {
            if (isGapIncluded(s) && isWordStartsNormal(s)) {
                String[] words = s.split(" ");
                for (String word : words) {
                    if (word.length() == 1) {
                        shortWordsCounter++;
                        if (Constants.SHORT_WORD_DICTIONARY.contains(word.toLowerCase())) {
                            correctShortWordsCounter++;
                        }
                    }
                }
            }
            else return false;
        }

        return (correctShortWordsCounter * 1.0 / shortWordsCounter > accuracy);
    }
    public boolean isGapIncluded(String s){
        return s.length() <= 40 || s.contains(" ");
    }
    public boolean isWordStartsNormal(String s){
        if (s.length() > 0)return (!Constants.ILLEGAL_START_FOR_WORD.contains(s.substring(0,1)));
        else return true;
    }
}
