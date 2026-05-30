package com.vin.java2508;

interface Printable {
    void print(); // abstract method
}
interface Showable {
    void show(); // abstract method
}
// A class implementing two interfaces
public class A004_Interface implements Printable, Showable {
    // Provide implementation of print()
    public void print() {
        System.out.println("Printing");
    }
    // Provide implementation of show()
    public void show() {
        System.out.println("Showing");
    }
    // Main method to test
    public static void main(String[] args) {
        A004_Interface obj = new A004_Interface();  // create object of Demo
        obj.print(); // call print() method
        // Output: Printing
        obj.show();  // call show() method
        // Output: Showing
    }
}