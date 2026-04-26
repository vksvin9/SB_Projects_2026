package com.vin.java2508;

// A class to demonstrate different ways enums can be used in Java
public class A003_Enum {

    // 1. Simple Enum
    // Define an enum called Direction: NORTH, SOUTH, EAST, WEST
    enum Direction {
        NORTH, SOUTH, EAST, WEST
    }

    // 2. Enum with Fields and Methods
    // Enum Constants: Constants must be defined first, at the top of the enum.
    // Enums can have fields and constructors.
    // Enum constructors are always private or package-private (never public or protected).
    enum Day {
        // Two constants: MONDAY and SUNDAY
        // Each constant passes a string ("Weekday" / "Weekend") to the constructor
        MONDAY("Weekday"),
        SUNDAY("Weekend");

        // Instance variable (each enum object stores its own type)
        private String type;

        // Constructor for Day (always private in enums)
        Day(String type) {
            this.type = type;
        }

        // Method to return the type ("Weekday" or "Weekend")
        String getType() {
            return type;
        }
    }

    // 3. Enums can declare abstract methods and can define instance methods and static methods.
    // Enum constants can override methods individually using constant-specific class bodies:
    // Rule: Constants can have no body (just names) or a class body { ... } if they override methods.
    // Rule: If an enum has an abstract method, every constant must override it.
    // Rule: Abstract methods must be declared after the constants list.
    // Define an enum called Operation
    enum Operation {
        // First constant: ADD
        // Overrides the apply() method with addition logic
        ADD {
            @Override
            public int apply(int a, int b) {
                return a + b; // performs addition
            }
        },

        // Second constant: MULTIPLY
        // Overrides the apply() method with multiplication logic
        MULTIPLY {
            @Override
            public int apply(int a, int b) {
                return a * b; // performs multiplication
            }
        };

        // Abstract method (forces each constant to implement it)
        public abstract int apply(int a, int b); // must come after constants
    }

    // 4. Enum Implementing Interface
    // Enums can implement interfaces.
    // Each constant then provides its own implementation, if required.
    // Define an interface called Printable
    interface Printable {
        void print(); // abstract method
    }

    // Define an enum called Color that implements Printable
    enum Color implements Printable {
        RED, GREEN, BLUE;

        // Override the print() method from the interface
        @Override
        public void print() {
            System.out.println("Color: " + this.name());
            // this.name() returns enum constant name (RED, GREEN, BLUE)
        }
    }

    // 5. Enum inside a Class
    // Define a static inner class called TrafficLight
    static class TrafficLight {
        // Inside it, define an enum called Signal
        // with constants RED, YELLOW, GREEN
        enum Signal {
            RED, YELLOW, GREEN
        }
    }

    // 6. Enum with Main Method
    // enum cannot extend a class (already extends Enum implicitly).
    // enum can have a main() method (useful for testing).
    // enum.values() gives all constants as an array.
    // enum.name() gives the constant name (string).
    // enum.ordinal() gives position (0-based).
    // Main method to test all enums
    public static void main(String[] args) {
        // 1. Simple Enum Usage
        Direction dir = Direction.NORTH; // Assign NORTH to dir
        System.out.println("Direction: " + dir);
        // Output: Direction: NORTH
        
        // 2. Enum with Fields and Methods
        Day today = Day.MONDAY; // Assign MONDAY to today
        System.out.println("Today is: " + today);
        // Output: Today is: MONDAY
        System.out.println("Type: " + today.getType());
        // Output: Type: Weekday

        // 3. Enum with Overridden Methods
        int sum = Operation.ADD.apply(5, 3);       // 5 + 3
        int product = Operation.MULTIPLY.apply(5, 3); // 5 * 3
        System.out.println("ADD Result: " + sum);
        // Output: ADD Result: 8
        System.out.println("MULTIPLY Result: " + product);
        // Output: MULTIPLY Result: 15

        // 4. Enum Implementing Interface
        Color.RED.print();
        // Output: Color: RED

        // 5. Enum inside a Class
        TrafficLight.Signal signal = TrafficLight.Signal.GREEN;
        System.out.println("Traffic Signal: " + signal);
        // Output: Traffic Signal: GREEN

        // 6. Iterating over Enum values
        System.out.println("All Directions:");
        // Output: All Directions:
        for (Direction d : Direction.values()) {
            System.out.println(d);
            // Output: NORTH, SOUTH, EAST, WEST (one per line)
        }

        // 7. Demonstrating name(), ordinal()
        Direction d1 = Direction.EAST;
        System.out.println("name(): " + d1.name());
        // Output: name(): EAST
        System.out.println("ordinal(): " + d1.ordinal());
        // Output: ordinal(): 2
    }
}
