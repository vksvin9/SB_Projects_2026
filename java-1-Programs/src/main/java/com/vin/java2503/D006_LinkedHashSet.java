package com.vin.java2503;

import java.util.*;
import java.util.stream.Collectors;

public class D006_LinkedHashSet {
    public static void main(String[] args) {

        // ******************************************
        // 🚀 1. Create and Initialize LinkedHashSet
        // ******************************************
        LinkedHashSet<Integer> set = new LinkedHashSet<>();

        // 🔹 add() - Insert elements (Maintains Insertion Order)
        set.add(10);
        set.add(30);
        set.add(20);
        set.add(50);
        set.add(40);
        System.out.println("LinkedHashSet after add(): " + set);

        // 🔹 add() with duplicate value (No effect)
        set.add(20);
        System.out.println("After adding duplicate 20: " + set);

        // 🔹 contains() - Check if an element exists
        System.out.println("Contains 30? " + set.contains(30));

        // 🔹 remove() - Remove an element
        set.remove(40);
        System.out.println("After remove(40): " + set);

        // 🔹 size() - Get size of LinkedHashSet
        System.out.println("Size of LinkedHashSet: " + set.size());

        // 🔹 isEmpty() - Check if LinkedHashSet is empty
        System.out.println("Is LinkedHashSet empty? " + set.isEmpty());

        // 🔹 toArray() - Convert LinkedHashSet to array
        Object[] array = set.toArray();
        System.out.println("Converted to Array: " + Arrays.toString(array));

        // 🔹 clear() - Remove all elements
        LinkedHashSet<Integer> tempSet = new LinkedHashSet<>(set); // Backup before clearing
        set.clear();
        System.out.println("After clear(): " + set);

        // 🔹 addAll(Collection) - Add elements back from backup
        set.addAll(tempSet);
        System.out.println("After addAll(): " + set);

        // ******************************************
        // 🚀 2. Iteration Techniques
        // ******************************************
        System.out.println("\n🔹 Iteration Techniques:");

        // 1️⃣ For-Each Loop
        System.out.println("\n1️⃣ For-Each Loop:");
        for (Integer item : set) {
            System.out.println(item);
        }

        // 2️⃣ Using Iterator
        System.out.println("\n2️⃣ Using Iterator:");
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 3️⃣ Using forEach() Method (Lambda Expression)
        System.out.println("\n3️⃣ Using forEach() Method:");
        set.forEach(item -> System.out.println(item));

        // 4️⃣ Using Streams API (Method Reference)
        System.out.println("\n4️⃣ Using Streams API:");
        set.stream().forEach(System.out::println);

        // 5️⃣ Using Streams API with Filtering
        System.out.println("\n5️⃣ Using Streams with Filter (Only numbers > 20):");
        set.stream()
                .filter(item -> item > 20)
                .forEach(System.out::println);

        // 6️⃣ Using Streams API with Mapping (Multiply each by 2)
        System.out.println("\n6️⃣ Using Streams with Mapping (Multiply by 2):");
        List<Integer> doubledSet = set.stream()
                .map(num -> num * 2)
                .collect(Collectors.toList());
        System.out.println(doubledSet);
    }
}
