package ru.javarush.cryptoanalyser.consoleUI;

import ru.javarush.cryptoanalyser.Constants.Constants;
import ru.javarush.cryptoanalyser.cryptography.BruteForce;
import ru.javarush.cryptoanalyser.cryptography.Decode;
import ru.javarush.cryptoanalyser.cryptography.Encode;
import ru.javarush.cryptoanalyser.cryptography.StatisticalAnalysis;
import ru.javarush.cryptoanalyser.utility.TxtFileWorker;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Dialogue {
    public void start() {

        System.out.println(Constants.START_CONSOLE_MESSAGE);
        Scanner console = new Scanner(System.in);
        String inLine = console.nextLine();


        while (!inLine.equals("exit")) {
            String[] userCommands = inLine.split(" ");

            if (isValidCommand(userCommands) && TxtFileWorker.getInstance().isValidFile(userCommands[1])) {
                if (userCommands[0].equalsIgnoreCase("c")) {
                    Path encryptedFileName = Path.of(Constants.ENCRYPTED_FILE_NAME);
                    TxtFileWorker.getInstance().cleanPreviousFiles(encryptedFileName);
                    List<String> cResultList = new Encode().encodeTxtFile(Path.of(userCommands[1]), Integer.parseInt(userCommands[2]));
                    TxtFileWorker.getInstance().fileWriterMethod(cResultList, Constants.ENCRYPTED_FILE_NAME);
                }
                if (userCommands[0].equalsIgnoreCase("d")) {
                    Path decryptedFileName = Path.of(Constants.DECRYPTED_FILE_NAME);
                    TxtFileWorker.getInstance().cleanPreviousFiles(decryptedFileName);
                    List<String> dResultList = new Decode().decodeTxtFile(Path.of(userCommands[1]), Integer.parseInt(userCommands[2]));
                    TxtFileWorker.getInstance().fileWriterMethod(dResultList, Constants.DECRYPTED_FILE_NAME);
                }
                if (userCommands[0].equalsIgnoreCase("b")) {
                    double accuracy = Double.parseDouble(userCommands[2]);
                    int decodedByBruteForceFile = new BruteForce().decodeByBruteForce(Path.of(userCommands[1]), accuracy);
                    if (decodedByBruteForceFile == 0) {
                        System.out.println("File wasn't decrypted... try to use lower accuracy.");
                    } else {
                        System.out.println(decodedByBruteForceFile + " file(s) decrypted successfully... ");
                    }
                }
                if (userCommands[0].equalsIgnoreCase("a")) {
                    String exampleFileName = userCommands[2];
                    String encryptedFileName = userCommands[1];
                    TxtFileWorker.getInstance().fileWriterMethod(new StatisticalAnalysis().
                            decodeByStatisticalAnalysis(encryptedFileName, exampleFileName), Constants.DECRYPTED_FILE_NAME);
                }
            } else {
                System.out.println("Invalid command");
            }
            inLine = console.nextLine();
        }
    }

    public static boolean isValidCommand(String[] commands) {
        return (commands.length == 3 && commands[0].equals("c") || commands[0].equals("d")
                || commands[0].equals("b") || commands[0].equals("a"));
    }
}
