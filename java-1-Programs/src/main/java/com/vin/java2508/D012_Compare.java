package com.vin.java2508;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//Custom class Person
class People1 implements Comparable<People1> {
 private String name;
 private int age;
 public People1(String name, int age) {
     this.name = name;
     this.age = age;
 }
 public String getName() { return name; }
 public int getAge() { return age; }
 @Override
 public String toString() {
     return name + " (" + age + ")";
 }
 // Comparable -> natural order = by name
 @Override
 public int compareTo(People1 other) {
     return this.name.compareTo(other.name);
 }
}
//Main class: merged examples
public class D012_Compare {
 public static void main(String[] args) {
     // 1. Primitives and Strings
     Integer[] numbers = {5, 2, 9, 1};
     String[] words = {"Charlie", "Alice", "Bob", "David"};
     // Java 7: sorting numbers ascending
     Arrays.sort(numbers);
     System.out.println("Java7 numbers ascending: " + Arrays.toString(numbers));
     // Expected: [1, 2, 5, 9]
     // Java 7: sorting strings ascending
     Arrays.sort(words);
     System.out.println("Java7 strings ascending: " + Arrays.toString(words));
     // Expected: [Alice, Bob, Charlie, David]
     // Java 7: sorting strings descending using Comparator
     Arrays.sort(words, Collections.reverseOrder());
     System.out.println("Java7 strings descending: " + Arrays.toString(words));
     // Expected: [David, Charlie, Bob, Alice]
     // Java 8: numbers using Streams
     System.out.print("Java8 numbers ascending (Stream): ");
     Arrays.stream(numbers).sorted().forEach(n -> System.out.print(n + " "));
     System.out.println();
     // Expected: 1 2 5 9
     // Java 8: strings using Comparator.comparing
     List<String> namesList = new ArrayList<>(Arrays.asList("Charlie", "Alice", "Bob", "David"));
     namesList.sort(Comparator.comparing(String::length));
     System.out.println("Java8 strings by length: " + namesList);
     // Expected: [Bob, Alice, David, Charlie]
     // 2. Custom Objects (Person)
     List<People1> people = Arrays.asList(
             new People1("Charlie", 30),
             new People1("Alice", 25),
             new People1("Bob", 28)
     );
     // Java 7: Comparable -> sort by name
     Collections.sort(people);
     System.out.println("Java7 sorted by name (Comparable): " + people);
     // Expected: [Alice (25), Bob (28), Charlie (30)]
     // Java 7: Comparator -> sort by age
     Collections.sort(people, new Comparator<People1>() {
         @Override
         public int compare(People1 p1, People1 p2) {
             return Integer.compare(p1.getAge(), p2.getAge());
         }
     });
     System.out.println("Java7 sorted by age (Comparator): " + people);
     // Expected: [Alice (25), Bob (28), Charlie (30)]
     // Java 8: Comparable -> sort by name using naturalOrder()
     people.sort(Comparator.naturalOrder());
     System.out.println("Java8 sorted by name (Comparable): " + people);
     // Expected: [Alice (25), Bob (28), Charlie (30)]
     // Java 8: Comparator -> sort by age using lambda
     people.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
     System.out.println("Java8 sorted by age (Lambda): " + people);
     // Expected: [Alice (25), Bob (28), Charlie (30)]
     // Java 8: Comparator -> sort by age using method reference
     people.sort(Comparator.comparing(People1::getAge));
     System.out.println("Java8 sorted by age (Method Reference): " + people);
     // Expected: [Alice (25), Bob (28), Charlie (30)]
     // Java 8: Stream API -> sort by name
     List<People1> sortedByNameStream = people.stream()
             .sorted(Comparator.comparing(People1::getName))
             .collect(Collectors.toList());
     System.out.println("Java8 Stream sorted by name: " + sortedByNameStream);
     // Expected: [Alice (25), Bob (28), Charlie (30)]
     // Java 8: Stream API -> sort by age
     List<People1> sortedByAgeStream = people.stream()
             .sorted(Comparator.comparingInt(People1::getAge))
             .collect(Collectors.toList());
     System.out.println("Java8 Stream sorted by age: " + sortedByAgeStream);
     // Expected: [Alice (25), Bob (28), Charlie (30)]
 }
}
