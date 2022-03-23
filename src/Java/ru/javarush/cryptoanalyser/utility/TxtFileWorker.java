package ru.javarush.cryptoanalyser.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class TxtFileWorker {
    public static final TxtFileWorker TXT_FILE_WORKER = new TxtFileWorker();

    public TxtFileWorker() {
    }

    public static TxtFileWorker getInstance() {
        return TXT_FILE_WORKER;
    }

    public List<String> txtToList(Path fileName) {
        List<String> fileLinesList = new ArrayList<>();
        try {
            fileLinesList = Files.readAllLines(fileName);
        }
        catch (IOException ex){
            System.out.println("File " + fileName + " not found or corrupted.");
            System.err.println(ex.getMessage());
        }
        return fileLinesList;
    }

    public void fileWriterMethod(List<String> fileLines, String fileName) {
        try {
            Files.write(Path.of(fileName), fileLines, StandardOpenOption.CREATE);
            System.out.println(Path.of(fileName) + " has written successfully.");
        } catch (IOException ex) {
            System.out.println("File " + fileName + " not found or corrupted.");
            System.err.println(ex.getMessage());
        }
    }

    public void cleanPreviousFiles(Path fileName) {
        if (Files.exists(fileName)) {
            try {
                Files.delete(fileName);
            } catch (IOException ex) {
                System.out.println("File " + fileName + " not found or corrupted.");
                System.err.println(ex.getMessage());
            }
        }
    }

    public boolean isValidFile(String file) {
        return (file.endsWith(".txt") && Files.isRegularFile(Path.of(file)));
    }
}
