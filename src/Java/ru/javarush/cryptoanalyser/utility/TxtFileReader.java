package ru.javarush.cryptoanalyser.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TxtFileReader {
    public List<String> txtToList(Path fileName) throws IOException {
        return Files.readAllLines(fileName);
    }
}
