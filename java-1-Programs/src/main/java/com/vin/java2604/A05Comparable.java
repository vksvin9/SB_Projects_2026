package com.vin.java2604;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Persona implements Comparable<Persona> {
    String name;
    int age;

    Persona(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Natural order: name → age (null-safe)
    @Override
    public int compareTo(Persona other) {
        return Comparator
                .comparing((Persona p) -> p.name, Comparator.nullsLast(String::compareTo))
                .thenComparingInt(p -> p.age)
                .compare(this, other);
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

public class A05Comparable {

    public static void main(String[] args) {

        List<Persona> list = new ArrayList<>();
        list.add(new Persona("John", 25));
        list.add(new Persona("Alice", 30));
        list.add(new Persona("Bob", 20));
        list.add(new Persona(null, 40)); // test null handling

        // ---------------- Java 7 Comparable ----------------

        // Ascending (natural order)
        Collections.sort(list);
        System.out.println("Java 7 - Natural Order: " + list);
        // [Alice (30), Bob (20), John (25), null (40)]

        // Descending
        Collections.sort(list, Collections.reverseOrder());
        System.out.println("Java 7 - Reverse Order: " + list);
        // [null (40), John (25), Bob (20), Alice (30)]

        // ---------------- Java 8 Comparable ----------------

        // Natural order
        list.sort(Comparator.naturalOrder());
        System.out.println("Java 8 - Natural Order: " + list);
        // [Alice (30), Bob (20), John (25), null (40)]

        // Reverse order
        list.sort(Comparator.reverseOrder());
        System.out.println("Java 8 - Reverse Order: " + list);
        // [null (40), John (25), Bob (20), Alice (30)]

        // ---------------- Stream API ----------------

        List<Persona> streamSorted = list.stream()
                .sorted() // uses compareTo()
                .toList();
        System.out.println("Stream - Natural Order: " + streamSorted);
        // [Alice (30), Bob (20), John (25), null (40)]
    }
}
