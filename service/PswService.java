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
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class PswService {
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
            e.printStackTrace();
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(entry.toFileString());
            writer.newLine();
            System.out.println("Entry successfully saved to " + file.getName());
        } catch (IOException e) {
            System.out.println("Error while writing to file");
            e.printStackTrace();
        }
    }

    public static void viewPswEntry(Scanner scanner) {
        String fileName = "pswFile.txt";
        System.out.println("Enter Search String:");
        String searchedEntry = scanner.nextLine();

        try {
            List<String> allLines = Files.readAllLines(Paths.get(fileName));
            List<String> matches = allLines.stream()
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
                int indexToEdit = Integer.parseInt(scanner.nextLine()) - 1;

                if (indexToEdit >= 0 && indexToEdit < matches.size()) {
                    String lineToReplace = matches.get(indexToEdit);
                    
                    System.out.println("Enter new details:");
                    Entry updatedEntry = addInput(scanner);

                    for (int i = 0; i < allLines.size(); i++) {
                        if (allLines.get(i).equals(lineToReplace)) {
                            allLines.set(i, updatedEntry.toFileString());
                            break;
                        }
                    }

                    Files.write(Paths.get(fileName), allLines);
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
