package com.vin.java2508;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class D010_ComparableJava78 {
    public static void main(String[] args) {
        // 1. Primitives (numbers)
        Integer[] numbers = {5, 2, 9, 1};
        // Java 7: Comparable is natural order for Integer
        Arrays.sort(numbers); // Integer implements Comparable
        System.out.println("Java7 sorted numbers (using Arrays.sort): " + Arrays.toString(numbers));
        // Output comment
        // Expected: 1 2 5 9
        // Java 8: same, using streams
        System.out.print("Java8 sorted numbers (using Streams): ");
        Arrays.stream(numbers).sorted().forEach(n -> System.out.print(n + " "));
        System.out.println();
        // Output comment
        // Expected: 1 2 5 9
        // 2. Strings
        String[] words = {"apple", "orange", "banana"};
        // Java 7: natural ordering using Comparable
        Arrays.sort(words);
        System.out.println("Java7 sorted strings (using Arrays.sort): " + Arrays.toString(words));
        // Output comment
        // Expected: [apple, banana, orange]
        // Java 8: streams
        System.out.print("Java8 sorted strings (using Streams): ");
        Arrays.stream(words).sorted().forEach(s -> System.out.print(s + " "));
        System.out.println();
        // Output comment
        // Expected: apple banana orange
        // 3. Custom objects
        List<People> people = new ArrayList<People>();
        people.add(new People("Alice", 30));
        people.add(new People("Bob", 25));
        people.add(new People("Charlie", 35));
        // Java 7: sort using Comparable implemented in Men
        Collections.sort(people);
        System.out.println("Java7 sorted by age (Comparable, Collections.sort):");
        for (People p : people) {
            System.out.println(p);
        }
        // Output comment
        // Expected: Bob - 25, Alice - 30, Charlie - 35
        // Java 8: same, using List.sort (still uses Comparable)
        people.sort(null); // null means use natural order (Comparable)
        System.out.println("Java8 sorted by age (Comparable, List.sort):");
        people.forEach(System.out::println);
        // Output comment
        // Expected: Bob - 25, Alice - 30, Charlie - 35
    }
}
// Custom class implementing Comparable
class People implements Comparable<People> {
    private String name;
    private int age;
    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() { return name; }
    public int getAge() { return age; }
    // Compare by age
    @Override
    public int compareTo(People other) {
        return this.age - other.age;
    }
    @Override
    public String toString() {
        return name + " - " + age;
    }
}
