package com.vin.java2503;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class D002_LinkedList {
    public static void main(String[] args) {
        
        // ******************************************
        // 🚀 1. Create and Initialize LinkedList
        // ******************************************
        LinkedList<String> animals = new LinkedList<>();
        
        // 🔹 add() - Add elements
        animals.add("Dog");
        animals.add("Cat");
        animals.add("Horse");
        animals.add("Elephant");
        animals.add("Tiger");
        System.out.println("LinkedList after add(): " + animals);

        // 🔹 addFirst() - Add element at the beginning
        animals.addFirst("Lion");
        System.out.println("After addFirst(): " + animals);

        // 🔹 addLast() - Add element at the end
        animals.addLast("Zebra");
        System.out.println("After addLast(): " + animals);

        // 🔹 getFirst() - Get first element
        System.out.println("First Element: " + animals.getFirst());

        // 🔹 getLast() - Get last element
        System.out.println("Last Element: " + animals.getLast());

        // 🔹 get(index) - Retrieve an element
        System.out.println("Element at index 2: " + animals.get(2));

        // 🔹 set(index, element) - Update an element
        animals.set(1, "Leopard");
        System.out.println("After set(index, element): " + animals);

        // 🔹 removeFirst() - Remove first element
        animals.removeFirst();
        System.out.println("After removeFirst(): " + animals);

        // 🔹 removeLast() - Remove last element
        animals.removeLast();
        System.out.println("After removeLast(): " + animals);

        // 🔹 remove(index) - Remove element at index
        animals.remove(2);
        System.out.println("After remove(index): " + animals);

        // 🔹 remove(Object) - Remove a specific element
        animals.remove("Tiger");
        System.out.println("After remove(Object): " + animals);

        // 🔹 contains() - Check if an element exists
        System.out.println("Contains 'Elephant'? " + animals.contains("Elephant"));

        // 🔹 indexOf() - Get index of an element
        System.out.println("Index of 'Cat': " + animals.indexOf("Cat"));

        // 🔹 size() - Get the size of the LinkedList
        System.out.println("Size of LinkedList: " + animals.size());

        // 🔹 isEmpty() - Check if LinkedList is empty
        System.out.println("Is LinkedList empty? " + animals.isEmpty());

        // 🔹 toArray() - Convert to Array
        String[] animalArray = animals.toArray(new String[0]);
        System.out.println("Converted to Array: " + Arrays.toString(animalArray));

        // 🔹 clear() - Remove all elements
        LinkedList<String> tempAnimals = new LinkedList<>(animals); // Backup before clearing
        animals.clear();
        System.out.println("After clear(): " + animals);

        // 🔹 addAll(Collection) - Add all elements from another collection
        animals.addAll(tempAnimals);
        System.out.println("After addAll(): " + animals);

        // 🔹 clone() - Clone a LinkedList
        LinkedList<String> clonedAnimals = (LinkedList<String>) animals.clone();
        System.out.println("Cloned LinkedList: " + clonedAnimals);

        // ******************************************
        // 🚀 2. Iteration Techniques
        // ******************************************
        System.out.println("\n🔹 Iteration Techniques:");

        // 1️⃣ For-Each Loop
        System.out.println("\n1️⃣ For-Each Loop:");
        for (String animal : animals) {
            System.out.println(animal);
        }

        // 2️⃣ Using Iterator
        System.out.println("\n2️⃣ Using Iterator:");
        Iterator<String> iterator = animals.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 3️⃣ Using ListIterator (Forward)
        System.out.println("\n3️⃣ Using ListIterator (Forward):");
        ListIterator<String> listIterator = animals.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }

        // 4️⃣ Using ListIterator (Backward)
        System.out.println("\n4️⃣ Using ListIterator (Backward):");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }

        // 5️⃣ Using forEach() Method (Lambda Expression)
        System.out.println("\n5️⃣ Using forEach() Method:");
        animals.forEach(animal -> System.out.println(animal));

        // 6️⃣ Using Streams API (Method Reference)
        System.out.println("\n6️⃣ Using Streams API:");
        animals.stream().forEach(System.out::println);

        // 7️⃣ Using Streams API with Filtering
        System.out.println("\n7️⃣ Using Streams with Filter (Only names with length > 4):");
        animals.stream()
                .filter(animal -> animal.length() > 4)
                .forEach(System.out::println);

        // 8️⃣ Using Streams API with Mapping (Convert to Uppercase)
        System.out.println("\n8️⃣ Using Streams with Mapping (Uppercase):");
        List<String> upperCaseAnimals = animals.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(upperCaseAnimals);
    }
}
