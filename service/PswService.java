package service;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class PswService {
    private static final Caeser caesar = new Caeser(5);

    private static class Entry {
        private String application, username, psw, email, additionalInfo;

        public Entry(String application, String username, String psw, String email, String additionalInfo) {
            this.application = application;
            this.username = username;
            this.psw = psw;
            this.email = email;
            this.additionalInfo = additionalInfo;
        }

        public String toFileString() {
            return application + "(" + username + "; " + psw + "; " + email + "; " + additionalInfo + ";)";
        }
    }

    public static void addPswEntry(Scanner scanner) {
        File pswFile = new File("pswFile.txt");
        try {
            if (pswFile.createNewFile()) {
                System.out.println("File created: " + pswFile.getName());
            }
        } catch (IOException e) {
            System.out.println("Error while creating pswFile");
        }

        Entry newEntry = addInput(scanner);
        saveToFile(pswFile, newEntry);
    }

    private static Entry addInput(Scanner scanner) {
        System.out.println("Enter application:");
        String application = scanner.nextLine();
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter psw:");
        String psw = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter additionalInfo:");
        String additionalInfo = scanner.nextLine();

        return new Entry(application, username, psw, email, additionalInfo);
    }

    private static void saveToFile(File file, Entry entry) {
        String encryptedEntry = caesar.encrypt(entry.toFileString());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(encryptedEntry);
            writer.newLine();
            System.out.println("Entry successfully saved to " + file.getName());
        } catch (IOException e) {
            System.out.println("Error while writing to file");
        }
    }

    public static void viewPswEntry(Scanner scanner) {
        String fileName = "pswFile.txt";
        System.out.println("Enter Search String:");
        String searchedEntry = scanner.nextLine();

        try {
            List<String> allLinesEncrypted = Files.readAllLines(Paths.get(fileName));
            List<String> allLinesDecrypted = allLinesEncrypted.stream()
                    .map(caesar::decrypt)
                    .collect(Collectors.toList());

            List<String> matches = allLinesDecrypted.stream()
                    .filter(line -> line.contains(searchedEntry))
                    .collect(Collectors.toList());

            if (matches.isEmpty()) {
                System.out.println("No entries found.");
                return;
            }

            for (int i = 0; i < matches.size(); i++) {
                System.out.println((i + 1) + ") " + matches.get(i));
            }

            System.out.println("Do you want to edit an entry? /y /n");
            String editConfirm = scanner.nextLine();

            if (editConfirm.equalsIgnoreCase("y")) {
                System.out.println("Enter the number of the entry to edit:");
                int selection = Integer.parseInt(scanner.nextLine()) - 1;

                if (selection >= 0 && selection < matches.size()) {
                    String decryptedLineToReplace = matches.get(selection);
                    
                    System.out.println("Enter new details:");
                    Entry updatedEntry = addInput(scanner);
                    String encryptedUpdate = caesar.encrypt(updatedEntry.toFileString());

                    for (int i = 0; i < allLinesEncrypted.size(); i++) {
                        if (caesar.decrypt(allLinesEncrypted.get(i)).equals(decryptedLineToReplace)) {
                            allLinesEncrypted.set(i, encryptedUpdate);
                            break;
                        }
                    }

                    Files.write(Paths.get(fileName), allLinesEncrypted);
                    System.out.println("Entry updated successfully.");
                } else {
                    System.out.println("Invalid selection.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error: File not found or readable.");
        }
    }
}
