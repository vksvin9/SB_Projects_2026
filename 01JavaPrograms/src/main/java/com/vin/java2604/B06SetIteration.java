package com.vin.java2604;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.Vector;

public class B06SetIteration {

    public static void main(String[] args) {

        Set<String> set = new HashSet<>(Arrays.asList("Apple", "Banana", "Cherry"));

        // ================= BASIC ITERATION =================

        // 1. for-each loop - Most common
        for (String s : set) {
            System.out.println("for-each: " + s); // prints all elements
        }

        // 2. Iterator - Allows safe removal
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            System.out.println("Iterator: " + it.next()); // iteration
        }

        // ================= JAVA 8 =================

        // 3. forEach() with lambda
        set.forEach(s -> System.out.println("forEach lambda: " + s)); // clean iteration

        // 4. forEach() with method reference
        set.forEach(System.out::println); // shortest form

        // ================= STREAM =================

        // 5. Stream API
        set.stream().forEach(s -> System.out.println("Stream: " + s)); // functional

        // 6. Stream with filter (real use)
        set.stream()
                .filter(s -> s.startsWith("A"))
                .forEach(s -> System.out.println("Filtered: " + s)); // filtered output

        // 7. Parallel Stream (unordered)
        set.parallelStream().forEach(s -> System.out.println("Parallel: " + s)); // order not guaranteed

        // ================= ADVANCED =================

        // 8. Spliterator
        Spliterator<String> spliterator = set.spliterator();
        spliterator.forEachRemaining(s -> System.out.println("Spliterator: " + s)); // advanced iteration

        // ================= LEGACY =================

        // 9. Enumeration (via Vector conversion)
        Vector<String> vector = new Vector<>(set);
        Enumeration<String> enumeration = vector.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println("Enumeration: " + enumeration.nextElement()); // legacy
        }
    }
}