package service;

import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

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
    ///////////////////////////////////////////////////////////////
    public static void viewPswEntry(Scanner scanner) {
        String fileName = "pswFile.txt";
        System.out.println("Enter string to search:");

        String searchedEntry = scanner.nextLine();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.filter(line -> line.contains(searchedEntry))
                .forEach(System.out::println);

        } catch (IOException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }
    }
}
