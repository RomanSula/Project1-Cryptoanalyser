package ru.javarush.cryptoanalyser.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class TxtFileWriter {
    public  void fileWriterMethod(List<String> fileLines, String fileName){
        try {
            Files.write(Path.of(fileName), fileLines, StandardOpenOption.CREATE);
            System.out.println(Path.of(fileName) + " has written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
