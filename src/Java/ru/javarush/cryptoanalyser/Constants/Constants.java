package ru.javarush.cryptoanalyser.Constants;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и','к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '"', ':', '!', '?', ' '};

    public static final List<String> SHORT_WORD_DICTIONARY = Arrays.asList("а", "я", "и", "у", "о", "в",
            "к", "с", "б");
    public static final List<String> ILLEGAL_START_FOR_WORD = Arrays.asList("ь", "Ъ", "ы");
}
