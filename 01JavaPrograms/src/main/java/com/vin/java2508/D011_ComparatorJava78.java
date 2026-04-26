package com.vin.java2508;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class D011_ComparatorJava78 {
    public static void main(String[] args) {
        // 1. Primitives (numbers)
        Integer[] numbers = {5, 2, 9, 1};
        // Java 7: using Comparator (reverse order example)
        Arrays.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                return n2 - n1; // descending
            }
        });
        System.out.println("Java7 sorted numbers (Comparator, descending): " + Arrays.toString(numbers));
        // Output comment
        // Expected: 9 5 2 1
        // Java 8: using lambda Comparator
        Arrays.sort(numbers, (n1, n2) -> n1 - n2); // ascending
        System.out.println("Java8 sorted numbers (Comparator, ascending): " + Arrays.toString(numbers));
        // Output comment
        // Expected: 1 2 5 9
        // 2. Strings
        String[] words = {"apple", "orange", "banana"};
        // Java 7: Comparator to sort reverse alphabetically
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.compareTo(s1); // descending
            }
        });
        System.out.println("Java7 sorted strings (Comparator, reverse): " + Arrays.toString(words));
        // Output comment
        // Expected: orange, banana, apple
        // Java 8: lambda Comparator for natural order
        Arrays.sort(words, (s1, s2) -> s1.compareTo(s2));
        System.out.println("Java8 sorted strings (Comparator, natural order): " + Arrays.toString(words));
        // Output comment
        // Expected: apple, banana, orange
        // 3. Custom objects
        List<Men> people = new ArrayList<>();
        people.add(new Men("Alice", 30));
        people.add(new Men("Bob", 25));
        people.add(new Men("Charlie", 35));
        // Java 7: Comparator by age descending
        Collections.sort(people, new Comparator<Men>() {
            @Override
            public int compare(Men m1, Men m2) {
                return m2.getAge() - m1.getAge(); // descending
            }
        });
        System.out.println("Java7 sorted people by age (Comparator, descending):");
        for (Men p : people) {
            System.out.println(p);
        }
        // Output comment
        // Expected: Charlie - 35, Alice - 30, Bob - 25
        // Java 8: Comparator using lambda (ascending)
        people.sort((m1, m2) -> m1.getAge() - m2.getAge());
        System.out.println("Java8 sorted people by age (Comparator, ascending):");
        people.forEach(System.out::println);
        // Output comment
        // Expected: Bob - 25, Alice - 30, Charlie - 35
        // Java 8: Comparator.comparing by name
        people.sort(Comparator.comparing(Men::getName));
        System.out.println("Java8 sorted people by name (Comparator.comparing):");
        people.forEach(System.out::println);
        // Output comment
        // Expected: Alice - 30, Bob - 25, Charlie - 35
    }
}
// Custom class for objects
class Men {
    private String name;
    private int age;
    public Men(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() { return name; }
    public int getAge() { return age; }
    @Override
    public String toString() {
        return name + " - " + age;
    }
}
