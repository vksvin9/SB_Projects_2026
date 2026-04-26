package com.vin.java2503;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.stream.Collectors;

public class D003_Stack {
    public static void main(String[] args) {

        // ******************************************
        // 🚀 1. Create and Initialize Stack
        // ******************************************
        Stack<String> stack = new Stack<>();
        
        // 🔹 push() - Add elements to the stack
        stack.push("Apple");
        stack.push("Banana");
        stack.push("Cherry");
        stack.push("Date");
        stack.push("Elderberry");
        System.out.println("Stack after push(): " + stack);

        // 🔹 pop() - Remove and return the top element
        System.out.println("Popped Element: " + stack.pop());
        System.out.println("Stack after pop(): " + stack);

        // 🔹 peek() - Get the top element without removing it
        System.out.println("Top Element (peek): " + stack.peek());

        // 🔹 search() - Find an element (1-based index)
        System.out.println("Position of 'Banana': " + stack.search("Banana"));

        // 🔹 contains() - Check if an element exists
        System.out.println("Contains 'Cherry'? " + stack.contains("Cherry"));

        // 🔹 isEmpty() - Check if the stack is empty
        System.out.println("Is Stack empty? " + stack.isEmpty());

        // 🔹 size() - Get the size of the stack
        System.out.println("Size of Stack: " + stack.size());

        // 🔹 clone() - Clone the stack
        Stack<String> clonedStack = (Stack<String>) stack.clone();
        System.out.println("Cloned Stack: " + clonedStack);

        // 🔹 toArray() - Convert to Array
        String[] stackArray = stack.toArray(new String[0]);
        System.out.println("Converted to Array: " + Arrays.toString(stackArray));

        // 🔹 clear() - Remove all elements
        Stack<String> tempStack = (Stack<String>) stack.clone(); // Backup before clearing
        stack.clear();
        System.out.println("After clear(): " + stack);

        // 🔹 addAll(Collection) - Add elements back from backup
        stack.addAll(tempStack);
        System.out.println("After addAll(): " + stack);

        // ******************************************
        // 🚀 2. Iteration Techniques
        // ******************************************
        System.out.println("\n🔹 Iteration Techniques:");

        // 1️⃣ For-Each Loop
        System.out.println("\n1️⃣ For-Each Loop:");
        for (String item : stack) {
            System.out.println(item);
        }

        // 2️⃣ Using Iterator
        System.out.println("\n2️⃣ Using Iterator:");
        Iterator<String> iterator = stack.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 3️⃣ Using ListIterator (Forward)
        System.out.println("\n3️⃣ Using ListIterator (Forward):");
        ListIterator<String> listIterator = stack.listIterator();
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
        stack.forEach(item -> System.out.println(item));

        // 6️⃣ Using Streams API (Method Reference)
        System.out.println("\n6️⃣ Using Streams API:");
        stack.stream().forEach(System.out::println);

        // 7️⃣ Using Streams API with Filtering
        System.out.println("\n7️⃣ Using Streams with Filter (Only names with length > 5):");
        stack.stream()
                .filter(item -> item.length() > 5)
                .forEach(System.out::println);

        // 8️⃣ Using Streams API with Mapping (Convert to Uppercase)
        System.out.println("\n8️⃣ Using Streams with Mapping (Uppercase):");
        List<String> upperCaseStack = stack.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(upperCaseStack);
    }
}