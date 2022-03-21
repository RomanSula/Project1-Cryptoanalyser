package ru.javarush.cryptoanalyser.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PreviousFileCleaner {

    public void cleanPreviousFiles(Path fileName) {
        if (Files.exists(fileName)) {
            try {
                Files.delete(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
