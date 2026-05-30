package com.vin.java2508;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class D007_HMCollectionMethod {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        // MAP METHODS
        // 1. put(K,V) - Add key-value
        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(3, "Cherry");
        System.out.println("put(): " + map); // Output: {1=Apple, 2=Banana, 3=Cherry}
        // 2. putIfAbsent(K,V) - Add only if absent
        map.putIfAbsent(2, "Orange");
        map.putIfAbsent(4, "Dates");
        System.out.println("putIfAbsent(): " + map); // Output: {1=Apple, 2=Banana, 3=Cherry, 4=Dates}
        // 3. get(Object key) - Get value
        System.out.println("get(2): " + map.get(2)); // Output: Banana
        // 4. getOrDefault(Object key, defaultVal)
        System.out.println("getOrDefault(5, 'Default'): " + map.getOrDefault(5, "Default")); // Output: Default
        // 5. containsKey(Object key)
        System.out.println("containsKey(3): " + map.containsKey(3)); // Output: true
        // 6. containsValue(Object value)
        System.out.println("containsValue('Banana'): " + map.containsValue("Banana")); // Output: true
        // 7. remove(Object key)
        map.remove(1);
        System.out.println("remove(1): " + map); // Output: {2=Banana, 3=Cherry, 4=Dates}
        // 8. remove(Object key, Object value) - Only if matches
        map.remove(2, "Orange");
        System.out.println("remove(2, 'Orange'): " + map); // Output: {2=Banana, 3=Cherry, 4=Dates}
        // 9. size()
        System.out.println("size(): " + map.size()); // Output: 3
        // 10. isEmpty()
        System.out.println("isEmpty(): " + map.isEmpty()); // Output: false
        // 11. replace(K key, V value)
        map.replace(3, "Citrus");
        System.out.println("replace(3, 'Citrus'): " + map); // Output: {2=Banana, 3=Citrus, 4=Dates}
        // 12. replace(K key, V oldValue, V newValue)
        map.replace(3, "Citrus", "Blueberry");
        System.out.println("replace(3, 'Citrus', 'Blueberry'): " + map); // Output: {2=Banana, 3=Blueberry, 4=Dates}
        // 13. keySet()
        System.out.println("keySet(): " + map.keySet()); // Output: [2, 3, 4]
        // 14. values()
        System.out.println("values(): " + map.values()); // Output: [Banana, Blueberry, Dates]
        // 15. entrySet()
        System.out.println("entrySet(): " + map.entrySet()); // Output: [2=Banana, 3=Blueberry, 4=Dates]
        // 16. forEach(BiConsumer)
        map.forEach((k, v) -> System.out.println("forEach: " + k + " => " + v));
        /*
        Output:
        forEach: 2 => Banana
        forEach: 3 => Blueberry
        forEach: 4 => Dates
        */
        // 17. clear()
        map.clear();
        System.out.println("clear(): " + map); // Output: {}
        // 18. putAll(Map<? extends K,? extends V>)
        Map<Integer, String> map2 = new HashMap<>();
        map2.put(10, "One");
        map2.put(20, "Two");
        map.putAll(map2);
        System.out.println("putAll(): " + map); // Output: {10=One, 20=Two}
        // 19. equals(Object o)
        Map<Integer, String> map3 = new HashMap<>(map2);
        System.out.println("equals(map2): " + map.equals(map3)); // Output: true
        // 20. hashCode()
        System.out.println("hashCode(): " + map.hashCode()); // Output: hash code of the map
        // COLLECTION METHODS (via keySet() and values())
        // keySet() - returns Set of keys
        Set<Integer> keys = map.keySet();
        System.out.println("keySet(): " + keys); // Output: [10, 20]
        // values() - returns Collection of values
        Collection<String> values = map.values();
        System.out.println("values(): " + values); // Output: [One, Two]
        // entrySet() - returns Set of key-value pairs
        System.out.println("entrySet(): " + map.entrySet()); // Output: [10=One, 20=Two]
        // Collection method: contains()
        System.out.println("values.contains('One'): " + values.contains("One")); // Output: true
        // Collection method: removeIf()
        values.removeIf(v -> v.startsWith("T")); // Remove values starting with 'T'
        System.out.println("values after removeIf(v -> v.startsWith('T')): " + values); // Output: [One]
        // Iterating collection
        values.forEach(v -> System.out.println("forEach value: " + v));
        /*
        Output:
        forEach value: One
        */
    }
}