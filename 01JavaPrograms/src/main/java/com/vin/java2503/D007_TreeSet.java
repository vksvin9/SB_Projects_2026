package com.vin.java2503;

import java.util.*;
import java.util.stream.Collectors;

public class D007_TreeSet {
    public static void main(String[] args) {

        // ******************************************
        // 🚀 1. Create and Initialize TreeSet
        // ******************************************
        TreeSet<Integer> treeSet = new TreeSet<>();

        // 🔹 add() - Insert elements (Sorted Order)
        treeSet.add(40);
        treeSet.add(10);
        treeSet.add(30);
        treeSet.add(50);
        treeSet.add(20);
        System.out.println("TreeSet after add(): " + treeSet);

        // 🔹 add() with duplicate value (No effect)
        treeSet.add(20);
        System.out.println("After adding duplicate 20: " + treeSet);

        // 🔹 contains() - Check if an element exists
        System.out.println("Contains 30? " + treeSet.contains(30));

        // 🔹 remove() - Remove an element
        treeSet.remove(40);
        System.out.println("After remove(40): " + treeSet);

        // 🔹 size() - Get size of TreeSet
        System.out.println("Size of TreeSet: " + treeSet.size());

        // 🔹 isEmpty() - Check if TreeSet is empty
        System.out.println("Is TreeSet empty? " + treeSet.isEmpty());

        // 🔹 first() - Get first (lowest) element
        System.out.println("First element: " + treeSet.first());

        // 🔹 last() - Get last (highest) element
        System.out.println("Last element: " + treeSet.last());

        // 🔹 higher(element) - Get next higher element
        System.out.println("Higher than 30: " + treeSet.higher(30));

        // 🔹 lower(element) - Get next lower element
        System.out.println("Lower than 30: " + treeSet.lower(30));

        // 🔹 ceiling(element) - Smallest element >= given value
        System.out.println("Ceiling of 25: " + treeSet.ceiling(25));

        // 🔹 floor(element) - Largest element <= given value
        System.out.println("Floor of 25: " + treeSet.floor(25));

        // 🔹 pollFirst() - Remove and return first element
        System.out.println("Poll First: " + treeSet.pollFirst());
        System.out.println("After pollFirst(): " + treeSet);

        // 🔹 pollLast() - Remove and return last element
        System.out.println("Poll Last: " + treeSet.pollLast());
        System.out.println("After pollLast(): " + treeSet);

        // 🔹 toArray() - Convert TreeSet to array
        Object[] array = treeSet.toArray();
        System.out.println("Converted to Array: " + Arrays.toString(array));

        // 🔹 clear() - Remove all elements
        TreeSet<Integer> tempSet = new TreeSet<>(treeSet); // Backup before clearing
        treeSet.clear();
        System.out.println("After clear(): " + treeSet);

        // 🔹 addAll(Collection) - Add elements back from backup
        treeSet.addAll(tempSet);
        System.out.println("After addAll(): " + treeSet);

        // ******************************************
        // 🚀 2. Iteration Techniques
        // ******************************************
        System.out.println("\n🔹 Iteration Techniques:");

        // 1️⃣ For-Each Loop
        System.out.println("\n1️⃣ For-Each Loop:");
        for (Integer item : treeSet) {
            System.out.println(item);
        }

        // 2️⃣ Using Iterator
        System.out.println("\n2️⃣ Using Iterator:");
        Iterator<Integer> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 3️⃣ Using forEach() Method (Lambda Expression)
        System.out.println("\n3️⃣ Using forEach() Method:");
        treeSet.forEach(item -> System.out.println(item));

        // 4️⃣ Using Streams API (Method Reference)
        System.out.println("\n4️⃣ Using Streams API:");
        treeSet.stream().forEach(System.out::println);

        // 5️⃣ Using Streams API with Filtering
        System.out.println("\n5️⃣ Using Streams with Filter (Only numbers > 20):");
        treeSet.stream()
                .filter(item -> item > 20)
                .forEach(System.out::println);

        // 6️⃣ Using Streams API with Mapping (Multiply each by 2)
        System.out.println("\n6️⃣ Using Streams with Mapping (Multiply by 2):");
        List<Integer> doubledSet = treeSet.stream()
                .map(num -> num * 2)
                .collect(Collectors.toList());
        System.out.println(doubledSet);

        // 7️⃣ Using Descending Iterator (Reverse Order)
        System.out.println("\n7️⃣ Using Descending Iterator:");
        Iterator<Integer> descendingIterator = treeSet.descendingIterator();
        while (descendingIterator.hasNext()) {
            System.out.println(descendingIterator.next());
        }
    }
}

