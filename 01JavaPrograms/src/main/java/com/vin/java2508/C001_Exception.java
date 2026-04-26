package com.vin.java2508;

import java.io.*; // Required for IOException, FileReader, BufferedReader
public class C001_Exception {
    // throws: declares that this method may throw IOException
    public static void readFile(String filename) throws IOException {
        // try-with-resources: auto closes FileReader and BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            System.out.println("File Content:"); // Output: File Content: (if file exists)
            String line;
            // Read file line by line until null (end of file)
            while ((line = br.readLine()) != null) {
                System.out.println(line); // Output: <file line>
            }
        } // resources closed automatically here
    }
    // throw: manually throwing a custom exception
    public static void checkAge(int age) {
        if (age < 18) {
            // Explicitly throw an exception if condition fails
            throw new IllegalArgumentException("Age must be 18+ to vote");
        }
        System.out.println("You're eligible to vote"); // Output (if age >= 18): You're eligible to vote
    }
    public static void main(String[] args) {
        // try-catch-finally for runtime exceptions
        try {
            System.out.println("=== TRY-CATCH-FINALLY Example ==="); 
            // Output: === TRY-CATCH-FINALLY Example ===
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]); // ❌ Throws ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            // Catching runtime exception
            System.out.println("Caught Exception: " + e.getMessage()); 
            // Output: Caught Exception: Index 5 out of bounds for length 3
        } finally {
            // Finally always executes regardless of exception
            System.out.println("This block always executes (finally)"); 
            // Output: This block always executes (finally)
        }
        // Using `throw`
        try {	
            System.out.println("\n=== THROW Example ==="); 
            // Output: === THROW Example ===
            checkAge(16); // ❌ Will throw IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage()); 
            // Output: Caught: Age must be 18+ to vote
        }
        // Using `throws` + try-with-resources
        try {
            System.out.println("\n=== THROWS + TRY-WITH-RESOURCES Example ==="); 
            // Output: === THROWS + TRY-WITH-RESOURCES Example ===
            readFile("input.txt"); // throws IOException if file not found
        } catch (IOException e) {
            // Handling checked exception (IOException)
            System.out.println("File error: " + e.getMessage()); 
            // Output (if file missing): File error: input.txt (No such file or directory)
        }
        System.out.println("\nProgram continues normally..."); 
        // Output: Program continues normally...
    }
}