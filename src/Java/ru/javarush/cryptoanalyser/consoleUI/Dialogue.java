package ru.javarush.cryptoanalyser.consoleUI;

import ru.javarush.cryptoanalyser.Constants.Constants;
import ru.javarush.cryptoanalyser.cryptography.Encode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Dialogue {
    public void Start() {

        System.out.println("C-crypt, D-decrypt, B-Bruteforce, exit-escape");
        System.out.println("c FilePath key");
        Scanner console = new Scanner(System.in);
        String inLine = console.nextLine();


        while (!inLine.equals("exit")) {
            String[] userCommands = inLine.split(" ");

            if (isValidCommand(userCommands) && isValidKey(Integer.parseInt(userCommands[2])) && isValidFile(userCommands[1])) {
                if (userCommands[0].equals("c")) {
                    new Encode().encodeTxtFile(Path.of(userCommands[1]), Integer.parseInt(userCommands[2]));
                }
            } else {
                if (!isValidCommand(userCommands)) System.out.println("Invalid command");
                if (!isValidFile(userCommands[1])) System.out.println("Invalid file name");
                if (!isValidKey(Integer.parseInt(userCommands[2]))) System.out.println("Invalid key value");
            }


            inLine = console.nextLine();
        }


    }

    public static boolean isValidFile(String file) {
        return (file.endsWith(".txt") && Files.isRegularFile(Path.of(file)));
    }

    public static boolean isValidKey(int key) {
        return (key > 0 && key < Constants.ALPHABET.length);
    }

    public static boolean isValidCommand(String[] commands) {
        return (commands.length == 3 && commands[0].equals("c") || commands[0].equals("d") || commands[0].equals("b"));
    }
}
