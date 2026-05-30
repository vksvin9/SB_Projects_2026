package com.vin.java2503;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//*****************Maven Project*********************
//java program to CRUD word file
//java program to CRUD excel file (DB to Excel & vice versa)
//java program to CRUD CSV file (DB to CSV & vice versa)
//java program to convert pdf to word
//**************************************

public class C004a_FileHandling {
    public static void main(String[] args) {
        String filename = "example.txt";
//        String filename = "example.docx";
        // 1. Create a file and write into it
        fileCreateAndWrite(filename);
        // 2. Read the file
//        readFromFile(filename);
//        // 3. Append a custom line dynamically
//        appendLine(filename, "This is a dynamically appended line.");
//        // 4. Remove a specific line (example: remove line 2)
//        removeLine(filename, 2);
//        // 5. Delete the file
//        deleteFile(filename);
    }

    private static void fileCreateAndWrite(String filename) {
        File file = new File(filename);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("This is the first line.");
                writer.newLine();
                writer.write("This is the second line.");
                writer.newLine();
                writer.write("This is the third line.");
            }
            System.out.println("File created and written.");
            System.out.println("Absolute File Path: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error creating/writing file: " + e.getMessage());
        }
    }

    private static void readFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("Reading the file:");
            reader.lines().forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // ✅ Dynamically append a line from an input parameter
    private static void appendLine(String filename, String line) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.newLine();
            writer.write(line);
            System.out.println("Line appended: " + line);
        } catch (IOException e) {
            System.err.println("Error appending to file: " + e.getMessage());
        }
    }

    private static void removeLine(String filename, int lineNumber) {
        Path path = Paths.get(filename);
        if (!Files.exists(path)) {
            System.out.println("File not found: " + filename);
            return;
        }
        try {
            List<String> lines = Files.readAllLines(path);
            
            if (lineNumber < 1 || lineNumber > lines.size()) {
                System.out.println("Invalid line number: " + lineNumber);
                return;
            }
            
            // Remove the specified line (1-based index)
            lines.remove(lineNumber - 1);
            
            // Write back the updated content
            Files.write(path, lines);
            System.out.println("Line " + lineNumber + " removed successfully.");
        } catch (IOException e) {
            System.err.println("Error modifying file: " + e.getMessage());
        }
    }

    private static void deleteFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.err.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }
}
