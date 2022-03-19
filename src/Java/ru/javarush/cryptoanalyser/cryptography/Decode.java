package ru.javarush.cryptoanalyser.cryptography;

import java.nio.file.Path;
import java.util.List;

public class Decode {

    public List<String> decodeTxtFile(Path encryptedFile, int key) {
        int encKey = -key;
        return new Encode().encodeTxtFile(encryptedFile, encKey);

    }
}
