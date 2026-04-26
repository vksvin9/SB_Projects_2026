package com.vin.java2503;

import java.util.*;
import java.util.stream.Collectors;

public class D004_PriorityQueue {
    public static void main(String[] args) {

        // ******************************************
        // 🚀 1. Create and Initialize PriorityQueue
        // ******************************************
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 🔹 add() - Insert elements
        pq.add(30);
        pq.add(10);
        pq.add(50);
        pq.add(20);
        pq.add(40);
        System.out.println("PriorityQueue after add(): " + pq);

        // 🔹 offer() - Alternative to add()
        pq.offer(60);
        System.out.println("PriorityQueue after offer(60): " + pq);

        // 🔹 peek() - Retrieve the head element without removing it
        System.out.println("Top Element (peek): " + pq.peek());

        // 🔹 poll() - Retrieve and remove the head element
        System.out.println("Removed Element (poll): " + pq.poll());
        System.out.println("PriorityQueue after poll(): " + pq);

        // 🔹 remove() - Remove specific element
        pq.remove(40);
        System.out.println("After remove(40): " + pq);

        // 🔹 contains() - Check if an element exists
        System.out.println("Contains 20? " + pq.contains(20));

        // 🔹 isEmpty() - Check if queue is empty
        System.out.println("Is PriorityQueue empty? " + pq.isEmpty());

        // 🔹 size() - Get size of the queue
        System.out.println("Size of PriorityQueue: " + pq.size());

        // 🔹 toArray() - Convert to array
        Object[] pqArray = pq.toArray();
        System.out.println("Converted to Array: " + Arrays.toString(pqArray));

        // 🔹 clear() - Remove all elements
        PriorityQueue<Integer> tempPQ = new PriorityQueue<>(pq); // Backup before clearing
        pq.clear();
        System.out.println("After clear(): " + pq);

        // 🔹 addAll(Collection) - Add elements back from backup
        pq.addAll(tempPQ);
        System.out.println("After addAll(): " + pq);

        // ******************************************
        // 🚀 2. Iteration Techniques
        // ******************************************
        System.out.println("\n🔹 Iteration Techniques:");

        // 1️⃣ For-Each Loop
        System.out.println("\n1️⃣ For-Each Loop:");
        for (Integer item : pq) {
            System.out.println(item);
        }

        // 2️⃣ Using Iterator
        System.out.println("\n2️⃣ Using Iterator:");
        Iterator<Integer> iterator = pq.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 3️⃣ Using forEach() Method (Lambda Expression)
        System.out.println("\n3️⃣ Using forEach() Method:");
        pq.forEach(item -> System.out.println(item));

        // 4️⃣ Using Streams API (Method Reference)
        System.out.println("\n4️⃣ Using Streams API:");
        pq.stream().forEach(System.out::println);

        // 5️⃣ Using Streams API with Filtering
        System.out.println("\n5️⃣ Using Streams with Filter (Only numbers > 20):");
        pq.stream()
                .filter(item -> item > 20)
                .forEach(System.out::println);

        // 6️⃣ Using Streams API with Mapping (Multiply each by 2)
        System.out.println("\n6️⃣ Using Streams with Mapping (Multiply by 2):");
        List<Integer> doubledQueue = pq.stream()
                .map(num -> num * 2)
                .collect(Collectors.toList());
        System.out.println(doubledQueue);
    }
}
