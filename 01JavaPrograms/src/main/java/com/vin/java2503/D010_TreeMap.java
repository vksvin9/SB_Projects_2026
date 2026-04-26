package com.vin.java2503;

import java.util.*;
import java.util.stream.Collectors;

public class D010_TreeMap {
    public static void main(String[] args) {

        // ******************************************
        // 🚀 1. Create and Initialize TreeMap
        // ******************************************
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        // 🔹 put() - Insert key-value pairs (Sorted by Key)
        treeMap.put(5, "Elderberry");
        treeMap.put(1, "Apple");
        treeMap.put(3, "Cherry");
        treeMap.put(2, "Banana");
        treeMap.put(4, "Date");
        System.out.println("TreeMap after put(): " + treeMap);

        // 🔹 put() with duplicate key (Updates value)
        treeMap.put(3, "Coconut");
        System.out.println("After updating key 3: " + treeMap);

        // 🔹 get(key) - Retrieve value by key
        System.out.println("Value of key 2: " + treeMap.get(2));

        // 🔹 containsKey() - Check if key exists
        System.out.println("Contains key 4? " + treeMap.containsKey(4));

        // 🔹 containsValue() - Check if value exists
        System.out.println("Contains value 'Cherry'? " + treeMap.containsValue("Cherry"));

        // 🔹 remove(key) - Remove entry by key
        treeMap.remove(4);
        System.out.println("After remove(4): " + treeMap);

        // 🔹 remove(key, value) - Remove only if key-value pair matches
        treeMap.remove(5, "Elderberry");
        System.out.println("After remove(5, 'Elderberry'): " + treeMap);

        // 🔹 replace(key, newValue) - Update value if key exists
        treeMap.replace(2, "Blueberry");
        System.out.println("After replace(2, 'Blueberry'): " + treeMap);

        // 🔹 replace(key, oldValue, newValue) - Conditional replacement
        treeMap.replace(3, "Coconut", "Cantaloupe");
        System.out.println("After replace(3, 'Coconut', 'Cantaloupe'): " + treeMap);

        // 🔹 putIfAbsent(key, value) - Add only if key is absent
        treeMap.putIfAbsent(6, "Fig");
        System.out.println("After putIfAbsent(6, 'Fig'): " + treeMap);

        // 🔹 firstKey() - Get first (smallest) key
        System.out.println("First Key: " + treeMap.firstKey());

        // 🔹 lastKey() - Get last (largest) key
        System.out.println("Last Key: " + treeMap.lastKey());

        // 🔹 firstEntry() - Get first entry (smallest key-value pair)
        System.out.println("First Entry: " + treeMap.firstEntry());

        // 🔹 lastEntry() - Get last entry (largest key-value pair)
        System.out.println("Last Entry: " + treeMap.lastEntry());

        // 🔹 higherKey() - Key just greater than given key
        System.out.println("Key higher than 2: " + treeMap.higherKey(2));

        // 🔹 lowerKey() - Key just smaller than given key
        System.out.println("Key lower than 3: " + treeMap.lowerKey(3));

        // 🔹 ceilingKey() - Smallest key >= given key
        System.out.println("Ceiling Key for 3: " + treeMap.ceilingKey(3));

        // 🔹 floorKey() - Largest key <= given key
        System.out.println("Floor Key for 3: " + treeMap.floorKey(3));

        // 🔹 descendingMap() - Reverse order of TreeMap
        System.out.println("Descending Map: " + treeMap.descendingMap());

        // 🔹 keySet() - Get all keys
        System.out.println("Key Set: " + treeMap.keySet());

        // 🔹 values() - Get all values
        System.out.println("Values: " + treeMap.values());

        // 🔹 entrySet() - Get all key-value pairs
        System.out.println("Entry Set: " + treeMap.entrySet());

        // 🔹 size() - Get size of TreeMap
        System.out.println("Size of TreeMap: " + treeMap.size());

        // 🔹 isEmpty() - Check if TreeMap is empty
        System.out.println("Is TreeMap empty? " + treeMap.isEmpty());

        // 🔹 clear() - Remove all elements
        TreeMap<Integer, String> tempMap = new TreeMap<>(treeMap); // Backup before clearing
        treeMap.clear();
        System.out.println("After clear(): " + treeMap);

        // 🔹 putAll(Map) - Copy elements from another map
        treeMap.putAll(tempMap);
        System.out.println("After putAll(): " + treeMap);

        // ******************************************
        // 🚀 2. Iteration Techniques
        // ******************************************
        System.out.println("\n🔹 Iteration Techniques:");

        // 1️⃣ For-Each Loop (Keys)
        System.out.println("\n1️⃣ For-Each Loop (Keys):");
        for (Integer key : treeMap.keySet()) {
            System.out.println("Key: " + key + ", Value: " + treeMap.get(key));
        }

        // 2️⃣ For-Each Loop (Entry Set)
        System.out.println("\n2️⃣ For-Each Loop (Entry Set):");
        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // 3️⃣ Using Iterator (Entry Set)
        System.out.println("\n3️⃣ Using Iterator:");
        Iterator<Map.Entry<Integer, String>> iterator = treeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // 4️⃣ Using forEach() Method (Lambda Expression)
        System.out.println("\n4️⃣ Using forEach() Method:");
        treeMap.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        // 5️⃣ Using Streams API (Method Reference)
        System.out.println("\n5️⃣ Using Streams API:");
        treeMap.entrySet().stream().forEach(System.out::println);

        // 6️⃣ Using Streams API with Filtering
        System.out.println("\n6️⃣ Using Streams with Filter (Only keys > 2):");
        treeMap.entrySet().stream()
                .filter(entry -> entry.getKey() > 2)
                .forEach(entry -> System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue()));

        // 7️⃣ Using Streams API with Mapping (Convert values to uppercase)
        System.out.println("\n7️⃣ Using Streams with Mapping (Values to Uppercase):");
        List<String> upperCaseValues = treeMap.values().stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(upperCaseValues);
    }
}