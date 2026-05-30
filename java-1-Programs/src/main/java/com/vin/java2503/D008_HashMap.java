package com.vin.java2503;

import java.util.*;
import java.util.stream.Collectors;

public class D008_HashMap {
    public static void main(String[] args) {

        // ******************************************
        // 🚀 1. Create and Initialize HashMap
        // ******************************************
        HashMap<Integer, String> map = new HashMap<>();

        // 🔹 put() - Insert key-value pairs
        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(3, "Cherry");
        map.put(4, "Date");
        map.put(5, "Elderberry");
        System.out.println("HashMap after put(): " + map);

        // 🔹 put() with duplicate key (Updates value)
        map.put(3, "Coconut");
        System.out.println("After updating key 3: " + map);

        // 🔹 get(key) - Retrieve value by key
        System.out.println("Value of key 2: " + map.get(2));

        // 🔹 containsKey() - Check if key exists
        System.out.println("Contains key 4? " + map.containsKey(4));

        // 🔹 containsValue() - Check if value exists
        System.out.println("Contains value 'Cherry'? " + map.containsValue("Cherry"));

        // 🔹 remove(key) - Remove entry by key
        map.remove(4);
        System.out.println("After remove(4): " + map);

        // 🔹 remove(key, value) - Remove only if key-value pair matches
        map.remove(5, "Elderberry");
        System.out.println("After remove(5, 'Elderberry'): " + map);

        // 🔹 replace(key, newValue) - Update value if key exists
        map.replace(2, "Blueberry");
        System.out.println("After replace(2, 'Blueberry'): " + map);

        // 🔹 replace(key, oldValue, newValue) - Conditional replacement
        map.replace(3, "Coconut", "Cantaloupe");
        System.out.println("After replace(3, 'Coconut', 'Cantaloupe'): " + map);

        // 🔹 putIfAbsent(key, value) - Add only if key is absent
        map.putIfAbsent(6, "Fig");
        System.out.println("After putIfAbsent(6, 'Fig'): " + map);

        // 🔹 keySet() - Get all keys
        System.out.println("Key Set: " + map.keySet());

        // 🔹 values() - Get all values
        System.out.println("Values: " + map.values());

        // 🔹 entrySet() - Get all key-value pairs
        System.out.println("Entry Set: " + map.entrySet());

        // 🔹 size() - Get size of HashMap
        System.out.println("Size of HashMap: " + map.size());

        // 🔹 isEmpty() - Check if HashMap is empty
        System.out.println("Is HashMap empty? " + map.isEmpty());

        // 🔹 clear() - Remove all elements
        HashMap<Integer, String> tempMap = new HashMap<>(map); // Backup before clearing
        map.clear();
        System.out.println("After clear(): " + map);

        // 🔹 putAll(Map) - Copy elements from another map
        map.putAll(tempMap);
        System.out.println("After putAll(): " + map);

        // ******************************************
        // 🚀 2. Iteration Techniques
        // ******************************************
        System.out.println("\n🔹 Iteration Techniques:");

        // 1️⃣ For-Each Loop (Keys)
        System.out.println("\n1️⃣ For-Each Loop (Keys):");
        for (Integer key : map.keySet()) {
            System.out.println("Key: " + key + ", Value: " + map.get(key));
        }

        // 2️⃣ For-Each Loop (Entry Set)
        System.out.println("\n2️⃣ For-Each Loop (Entry Set):");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // 3️⃣ Using Iterator (Entry Set)
        System.out.println("\n3️⃣ Using Iterator:");
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // 4️⃣ Using forEach() Method (Lambda Expression)
        System.out.println("\n4️⃣ Using forEach() Method:");
        map.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        // 5️⃣ Using Streams API (Method Reference)
        System.out.println("\n5️⃣ Using Streams API:");
        map.entrySet().stream().forEach(System.out::println);

        // 6️⃣ Using Streams API with Filtering
        System.out.println("\n6️⃣ Using Streams with Filter (Only keys > 2):");
        map.entrySet().stream()
                .filter(entry -> entry.getKey() > 2)
                .forEach(entry -> System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue()));

        // 7️⃣ Using Streams API with Mapping (Convert values to uppercase)
        System.out.println("\n7️⃣ Using Streams with Mapping (Values to Uppercase):");
        List<String> upperCaseValues = map.values().stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(upperCaseValues);
    }
}

