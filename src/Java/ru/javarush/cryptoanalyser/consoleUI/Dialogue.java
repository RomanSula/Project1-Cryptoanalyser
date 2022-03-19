package ru.javarush.cryptoanalyser.consoleUI;

import ru.javarush.cryptoanalyser.Constants.Constants;
import ru.javarush.cryptoanalyser.cryptography.BruteForce;
import ru.javarush.cryptoanalyser.cryptography.Decode;
import ru.javarush.cryptoanalyser.cryptography.Encode;
import ru.javarush.cryptoanalyser.cryptography.StatisticalAnalysis;
import ru.javarush.cryptoanalyser.utility.TxtFileWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Dialogue {
    public void Start() {

        System.out.println("Format of commands is: [command] [file_name] [key_value] or [accuracy]");
        System.out.println("[command]: [C] crypt, [D] decrypt, [B] Bruteforce, [exit] escape");
        Scanner console = new Scanner(System.in);
        String inLine = console.nextLine();


        while (!inLine.equals("exit")) {
            String[] userCommands = inLine.split(" ");

            if (isValidCommand(userCommands) && isValidFile(userCommands[1])) {
                if (userCommands[0].equalsIgnoreCase("c")) {
                    List<String> resultList = new Encode().encodeTxtFile(Path.of(userCommands[1]), Integer.parseInt(userCommands[2]));
                    new TxtFileWriter().fileWriterMethod(resultList, "encrypted.txt");
                }
                if (userCommands[0].equalsIgnoreCase("d")){
                    List<String> resultList = new Decode().decodeTxtFile(Path.of(userCommands[1]), Integer.parseInt(userCommands[2]));
                    new TxtFileWriter().fileWriterMethod(resultList, "decrypted.txt");
                }
                if (userCommands[0].equalsIgnoreCase("b")){
                    double accuracy = Double.parseDouble(userCommands[2]);
                    int decodedByBruteForceFile = new BruteForce().decodeByBruteForce(Path.of(userCommands[1]), accuracy);
                    if (decodedByBruteForceFile == 0) {
                        System.out.println("File wasn't decrypted...");
                    }
                    else {
                        System.out.println(decodedByBruteForceFile +  " file(s) decrypted successfully... " );
                    }
                }
                if (userCommands[0].equalsIgnoreCase("a")){
                    String exampleFileName = userCommands[2];
                    String encryptedFileName = userCommands[1];
                    new TxtFileWriter().fileWriterMethod(new StatisticalAnalysis().
                            decodeByStatisticalAnalysis(encryptedFileName, exampleFileName), "decrypted.txt");
                    //new StatisticalAnalysis().
                    //      decodeByStatisticalAnalysis(exampleFileName, encryptedFileName);
                }
            } else {
                System.out.println("Invalid command");
            }


            inLine = console.nextLine();
        }


    }

    public static boolean isValidFile(String file) {
        return (file.endsWith(".txt") && Files.isRegularFile(Path.of(file)));
    }

    public static boolean isValidKey(String key) {
        double keyValue = 0;
        try {
            keyValue = Double.parseDouble(key);
        }
        catch (NumberFormatException e){
            System.out.println("Illegal key value...");
        }
        return (keyValue > 0 && keyValue < Constants.ALPHABET.length);
    }

    public static boolean isValidCommand(String[] commands) {

        return (commands.length == 3 && commands[0].equals("c") || commands[0].equals("d")
                || commands[0].equals("b") || commands[0].equals("a"));
    }
}
