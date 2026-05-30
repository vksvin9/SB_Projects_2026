package com.vin.java2508;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class D006_HMCreateIterate {
    public static void main(String[] args) {
        // 1. Map Creation Ways
        // 1.a HashMap using default constructor
        Map<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "Apple");
        hashMap.put(2, "Banana");
        hashMap.put(3, "Cherry");
        // 1.b HashMap using copy constructor
        Map<Integer, String> hashMapCopy = new HashMap<>(hashMap);
        // 1.c LinkedHashMap (maintains insertion order)
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(4, "Date");
        linkedHashMap.put(5, "Elderberry");
        linkedHashMap.put(6, "Fig");
        // 1.d TreeMap (sorted by key)
        Map<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(7, "Grapes");
        treeMap.put(8, "Honeydew");
        treeMap.put(9, "Indian Fig");
        // 1.e Hashtable (legacy, synchronized)
        Map<Integer, String> hashtable = new Hashtable<>();
        hashtable.put(10, "Jackfruit");
        hashtable.put(11, "Kiwi");
        hashtable.put(12, "Lemon");
        // 1.h Map using Java 8 Streams + Collectors.toMap
        Map<Integer, String> streamMap = java.util.stream.Stream.of(
            new Object[][]{
                {19, "Strawberry"},
                {20, "Tomato"},
                {21, "Ugli Fruit"}
            }
        ).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));
        // For iteration demonstration, we can use hashMap
        Map<Integer, String> map = hashMap;

        // 2. Iteration Techniques
        // 2.a For-each loop on entrySet()
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("entrySet for-each: " + entry.getKey() + " => " + entry.getValue());
        }
        // Output:
        // entrySet for-each: 1 => Apple
        // entrySet for-each: 2 => Banana
        // entrySet for-each: 3 => Cherry

        // 2.b For-each loop on keySet()
        for (Integer key : map.keySet()) {
            System.out.println("keySet for-each: " + key + " => " + map.get(key));
        }
        // Output:
        // keySet for-each: 1 => Apple
        // keySet for-each: 2 => Banana
        // keySet for-each: 3 => Cherry

        // 2.c For-each loop on values()
        for (String value : map.values()) {
            System.out.println("values for-each: " + value);
        }
        // Output:
        // values for-each: Apple
        // values for-each: Banana
        // values for-each: Cherry

        // 2.d Iterator on entrySet()
        Iterator<Map.Entry<Integer, String>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<Integer, String> entry = entryIterator.next();
            System.out.println("Iterator entrySet: " + entry.getKey() + " => " + entry.getValue());
        }
        // Output same as entrySet for-each

        // 2.e Iterator on keySet()
        Iterator<Integer> keyIterator = map.keySet().iterator();
        while (keyIterator.hasNext()) {
            Integer key = keyIterator.next();
            System.out.println("Iterator keySet: " + key + " => " + map.get(key));
        }
        // Output same as keySet for-each

        // 2.f Iterator on values()
        Iterator<String> valueIterator = map.values().iterator();
        while (valueIterator.hasNext()) {
            System.out.println("Iterator values: " + valueIterator.next());
        }
        // Output same as values for-each

        // 2.g Java 8 forEach with lambda
        map.forEach((key, value) -> System.out.println("Java 8 forEach: " + key + " => " + value));
        // Output:
        // Java 8 forEach: 1 => Apple
        // Java 8 forEach: 2 => Banana
        // Java 8 forEach: 3 => Cherry

        // 2.h Streams API
        map.entrySet().stream().forEach(e -> System.out.println("Stream API: " + e.getKey() + " => " + e.getValue()));
        // Output same as above

        // 2.i Parallel Stream
        map.entrySet().parallelStream().forEach(e -> System.out.println("Parallel Stream: " + e));
        // Output (order may vary because parallel execution)

        // 2.j Enumeration (via Hashtable)
        Hashtable<Integer, String> table = new Hashtable<>(map);
        Enumeration<Integer> keys = table.keys();
        while (keys.hasMoreElements()) {
            Integer key = keys.nextElement();
            System.out.println("Enumeration: " + key + " => " + table.get(key));
        }
        // Output same as keySet for-each

        // 3. Demonstrate iteration on other map types (optional)
        System.out.println("\n--- Iterating LinkedHashMap ---");
        linkedHashMap.forEach((k, v) -> System.out.println(k + " => " + v));
        // Output:
        // 4 => Date
        // 5 => Elderberry
        // 6 => Fig

        System.out.println("\n--- Iterating TreeMap ---");
        treeMap.forEach((k, v) -> System.out.println(k + " => " + v));
        // Output (sorted by key):
        // 7 => Grapes
        // 8 => Honeydew
        // 9 => Indian Fig

        System.out.println("\n--- Iterating Hashtable ---");
        hashtable.forEach((k, v) -> System.out.println(k + " => " + v));
        // Output (no guaranteed order):
        // 10 => Jackfruit
        // 11 => Kiwi
        // 12 => Lemon

        System.out.println("\n--- Iterating Stream-created Map ---");
        streamMap.forEach((k, v) -> System.out.println(k + " => " + v));
        // Output (no guaranteed order):
        // 19 => Strawberry
        // 20 => Tomato
        // 21 => Ugli Fruit
    }
}

