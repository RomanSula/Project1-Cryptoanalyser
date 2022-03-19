package ru.javarush.cryptoanalyser.cryptography;

import ru.javarush.cryptoanalyser.utility.CryptoMapGenerator;
import ru.javarush.cryptoanalyser.utility.TxtFileReader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Encode {

    public List<String> encodeTxtFile(Path normalFile, int key) {
        HashMap<Character, Character> cryptoMap = new CryptoMapGenerator().generateCryptoMap(key);
        List<String> resultList = new ArrayList<>();

        try {
            //Charset charset = StandardCharsets.UTF_8;
            List<String> allLinesFromNormalFile = new TxtFileReader().txtToList(normalFile);

            for (String s : allLinesFromNormalFile) {
                char[] stringChars = s.toCharArray();
                for (int i = 0; i < stringChars.length; i++) {
                    for (Map.Entry<Character, Character> entry : cryptoMap.entrySet()) {
                        boolean isUpperCase = Character.isUpperCase(stringChars[i]);
                        if (Character.toLowerCase(stringChars[i]) == entry.getKey()) {
                            stringChars[i] = isUpperCase ? Character.toUpperCase(entry.getValue()) : entry.getValue();
                            break;
                        }
                    }
                }
                resultList.add(new String(stringChars));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
