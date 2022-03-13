package ru.javarush.cryptoanalyser.cryptography;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Encode {

    public void encodeTxtFile(Path normalFile, int key) {
        //String encodedString = "";
        try {
            Charset charset = StandardCharsets.UTF_8;
            List<String> allLinesFromNormalFile = Files.readAllLines(normalFile, charset);
            System.out.println(allLinesFromNormalFile.size() + " lines read");


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
