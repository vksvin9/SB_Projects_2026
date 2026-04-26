package com.vin.java2604;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;

public class B09MapIteration {
	public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(3, "Cherry");
        // 1. entrySet() for-each - Best practice (key + value together)
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("entrySet for-each: " + entry.getKey() + " => " + entry.getValue()); // prints all entries
        }
        // 2. keySet() for-each - Iterate keys and fetch values (less efficient)
        for (Integer key : map.keySet()) {
            System.out.println("keySet for-each: " + key + " => " + map.get(key)); // extra lookup
        }
        // 3. values() for-each - Only values
        for (String value : map.values()) {
            System.out.println("values for-each: " + value); // prints values only
        }
        // 4. Iterator on entrySet() - Allows safe removal
        Iterator<Map.Entry<Integer, String>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<Integer, String> entry = entryIterator.next();
            System.out.println("Iterator entrySet: " + entry.getKey() + " => " + entry.getValue()); // iteration
        }
        // 5. Iterator on keySet()
        Iterator<Integer> keyIterator = map.keySet().iterator();
        while (keyIterator.hasNext()) {
            Integer key = keyIterator.next();
            System.out.println("Iterator keySet: " + key + " => " + map.get(key)); // iteration with lookup
        }
        // 6. Iterator on values()
        Iterator<String> valueIterator = map.values().iterator();
        while (valueIterator.hasNext()) {
            System.out.println("Iterator values: " + valueIterator.next()); // values only
        }
        // 7. forEach(BiConsumer) - Java 8 lambda
        map.forEach((key, value) -> System.out.println("Java 8 forEach: " + key + " => " + value)); // clean iteration
        // 8. Stream API - Functional iteration
        map.entrySet().stream().forEach(e -> System.out.println("Stream API: " + e.getKey() + " => " + e.getValue())); // stream processing
        // 9. Parallel Stream - Multi-threaded iteration (unordered)
        map.entrySet().parallelStream().forEach(e -> System.out.println("Parallel Stream: " + e)); // order not guaranteed
        // 10. Enumeration (via Hashtable) - Legacy iteration
        Hashtable<Integer, String> table = new Hashtable<>(map);
        Enumeration<Integer> keys = table.keys();
        while (keys.hasMoreElements()) {
            Integer key = keys.nextElement();
            System.out.println("Enumeration: " + key + " => " + table.get(key)); // legacy style
        }
        // 11. Iterator with remove() - Safe removal during iteration (IMPORTANT)
        Iterator<Map.Entry<Integer, String>> removeIterator = map.entrySet().iterator();
        while (removeIterator.hasNext()) {
            Map.Entry<Integer, String> e = removeIterator.next();
            if (e.getKey() == 2) {
                removeIterator.remove(); // safe remove
            }
        }
        System.out.println("After iterator.remove(): " + map); // key 2 removed
        // 12. forEach with method reference - Shortest form
       map.entrySet().stream().forEach(System.out::println);;
       map.keySet().stream().forEach(System.out::println);;
       map.entrySet().stream().forEach(System.out::println);;
        // 13. Stream with filter - Real-world usage
        map.entrySet()
                .stream()
                .filter(e -> e.getKey() > 1)
                .forEach(e -> System.out.println("Filtered: " + e.getKey() + "=" + e.getValue())); // filtered output
        // 14. entrySet().forEach() - Direct iteration
        map.entrySet().forEach(e ->
                System.out.println("entrySet forEach: " + e.getKey() + " => " + e.getValue())
        ); // similar to lambda
        // 15. Spliterator - Advanced iteration
        Spliterator<Map.Entry<Integer, String>> spliterator = map.entrySet().spliterator();
        spliterator.forEachRemaining(e ->
                System.out.println("Spliterator: " + e.getKey() + " => " + e.getValue())
        ); // advanced API
	}
}
