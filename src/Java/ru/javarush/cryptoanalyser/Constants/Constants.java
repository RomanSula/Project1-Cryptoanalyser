package ru.javarush.cryptoanalyser.Constants;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '"', ':', '!', '?', ' '};

    public static final List<String> SHORT_WORD_DICTIONARY = Arrays.asList("а", "я", "и", "у", "о", "в",
            "к", "с", "б");
    public static final List<String> ILLEGAL_START_FOR_WORD = Arrays.asList("ь", "Ъ", "ы");
    public static final String ENCRYPTED_FILE_NAME = "encrypted.txt";
    public static final String DECRYPTED_FILE_NAME = "decrypted.txt";
    public static final int MAX_PERCENT_VALUE = 100;
    public static final int SHOR_WORD_LENGTH = 1;

    public static final String START_CONSOLE_MESSAGE = """
            Format of commands is: [command] [file_name] [key_value] or [accuracy]
            [command]: [C] Crypt, [D] Decrypt, [B] Bruteforce, [A] StatisticalAnalysis, [exit] escape
            For [C]: c NormalFile.txt key(int)
            For [D]: d NormalFile.txt key(int)
            For [B]: b EncryptedFile.txt accuracy(0.95)
            For [A]: a EncryptedFile.txt ExampleFileName.txt
            """;
}
