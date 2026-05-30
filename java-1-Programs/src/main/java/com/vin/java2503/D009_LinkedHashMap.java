package com.vin.java2503;

import java.util.*;
import java.util.stream.Collectors;

public class D009_LinkedHashMap {
    public static void main(String[] args) {

        // ******************************************
        // 🚀 1. Create and Initialize LinkedHashMap
        // ******************************************
        LinkedHashMap<Integer, String> linkedMap = new LinkedHashMap<>();

        // 🔹 put() - Insert key-value pairs (Maintains Insertion Order)
        linkedMap.put(1, "Apple");
        linkedMap.put(2, "Banana");
        linkedMap.put(3, "Cherry");
        linkedMap.put(4, "Date");
        linkedMap.put(5, "Elderberry");
        System.out.println("LinkedHashMap after put(): " + linkedMap);

        // 🔹 put() with duplicate key (Updates value)
        linkedMap.put(3, "Coconut");
        System.out.println("After updating key 3: " + linkedMap);

        // 🔹 get(key) - Retrieve value by key
        System.out.println("Value of key 2: " + linkedMap.get(2));

        // 🔹 containsKey() - Check if key exists
        System.out.println("Contains key 4? " + linkedMap.containsKey(4));

        // 🔹 containsValue() - Check if value exists
        System.out.println("Contains value 'Cherry'? " + linkedMap.containsValue("Cherry"));

        // 🔹 remove(key) - Remove entry by key
        linkedMap.remove(4);
        System.out.println("After remove(4): " + linkedMap);

        // 🔹 remove(key, value) - Remove only if key-value pair matches
        linkedMap.remove(5, "Elderberry");
        System.out.println("After remove(5, 'Elderberry'): " + linkedMap);

        // 🔹 replace(key, newValue) - Update value if key exists
        linkedMap.replace(2, "Blueberry");
        System.out.println("After replace(2, 'Blueberry'): " + linkedMap);

        // 🔹 replace(key, oldValue, newValue) - Conditional replacement
        linkedMap.replace(3, "Coconut", "Cantaloupe");
        System.out.println("After replace(3, 'Coconut', 'Cantaloupe'): " + linkedMap);

        // 🔹 putIfAbsent(key, value) - Add only if key is absent
        linkedMap.putIfAbsent(6, "Fig");
        System.out.println("After putIfAbsent(6, 'Fig'): " + linkedMap);

        // 🔹 keySet() - Get all keys
        System.out.println("Key Set: " + linkedMap.keySet());

        // 🔹 values() - Get all values
        System.out.println("Values: " + linkedMap.values());

        // 🔹 entrySet() - Get all key-value pairs
        System.out.println("Entry Set: " + linkedMap.entrySet());

        // 🔹 size() - Get size of LinkedHashMap
        System.out.println("Size of LinkedHashMap: " + linkedMap.size());

        // 🔹 isEmpty() - Check if LinkedHashMap is empty
        System.out.println("Is LinkedHashMap empty? " + linkedMap.isEmpty());

        // 🔹 clear() - Remove all elements
        LinkedHashMap<Integer, String> tempMap = new LinkedHashMap<>(linkedMap); // Backup before clearing
        linkedMap.clear();
        System.out.println("After clear(): " + linkedMap);

        // 🔹 putAll(Map) - Copy elements from another map
        linkedMap.putAll(tempMap);
        System.out.println("After putAll(): " + linkedMap);

        // ******************************************
        // 🚀 2. Iteration Techniques
        // ******************************************
        System.out.println("\n🔹 Iteration Techniques:");

        // 1️⃣ For-Each Loop (Keys)
        System.out.println("\n1️⃣ For-Each Loop (Keys):");
        for (Integer key : linkedMap.keySet()) {
            System.out.println("Key: " + key + ", Value: " + linkedMap.get(key));
        }

        // 2️⃣ For-Each Loop (Entry Set)
        System.out.println("\n2️⃣ For-Each Loop (Entry Set):");
        for (Map.Entry<Integer, String> entry : linkedMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // 3️⃣ Using Iterator (Entry Set)
        System.out.println("\n3️⃣ Using Iterator:");
        Iterator<Map.Entry<Integer, String>> iterator = linkedMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // 4️⃣ Using forEach() Method (Lambda Expression)
        System.out.println("\n4️⃣ Using forEach() Method:");
        linkedMap.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        // 5️⃣ Using Streams API (Method Reference)
        System.out.println("\n5️⃣ Using Streams API:");
        linkedMap.entrySet().stream().forEach(System.out::println);

        // 6️⃣ Using Streams API with Filtering
        System.out.println("\n6️⃣ Using Streams with Filter (Only keys > 2):");
        linkedMap.entrySet().stream()
                .filter(entry -> entry.getKey() > 2)
                .forEach(entry -> System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue()));

        // 7️⃣ Using Streams API with Mapping (Convert values to uppercase)
        System.out.println("\n7️⃣ Using Streams with Mapping (Values to Uppercase):");
        List<String> upperCaseValues = linkedMap.values().stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(upperCaseValues);
    }
}
