package com.vin.java2604;

import java.util.HashMap;
import java.util.Map;

public class B10MapMethods {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        // 1. put(K,V) - Add or update key-value pair
        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(3, "Cherry");
        System.out.println("put(): " + map); // {1=Apple, 2=Banana, 3=Cherry}
        // 2. putIfAbsent(K,V) - Add only if key not present
        map.putIfAbsent(2, "Orange");
        map.putIfAbsent(4, "Dates");
        System.out.println("putIfAbsent(): " + map); // 2 not replaced, 4 added
        // 3. get(Object key) - Get value by key
        System.out.println("get(2): " + map.get(2)); // Banana
        // 4. getOrDefault(Object key, defaultVal) - Return default if key missing
        System.out.println("getOrDefault(5, 'Default'): " + map.getOrDefault(5, "Default")); // Default
        // 5. containsKey(Object key) - Check key existence
        System.out.println("containsKey(3): " + map.containsKey(3)); // true
        // 6. containsValue(Object value) - Check value existence
        System.out.println("containsValue('Banana'): " + map.containsValue("Banana")); // true
        // 7. remove(Object key) - Remove by key
        map.remove(1);
        System.out.println("remove(1): " + map); // 1 removed
        // 8. remove(Object key, Object value) - Remove only if matches
        map.remove(2, "Orange");
        System.out.println("remove(2, 'Orange'): " + map); // no removal (value mismatch)
        // 9. size() - Number of entries
        System.out.println("size(): " + map.size()); // size count
        // 10. isEmpty() - Check if map is empty
        System.out.println("isEmpty(): " + map.isEmpty()); // false
        // 11. replace(K key, V value) - Replace value if key exists
        map.replace(3, "Citrus");
        System.out.println("replace(3, 'Citrus'): " + map); // Cherry → Citrus
        // 12. replace(K key, V oldValue, V newValue) - Replace only if match
        map.replace(3, "Citrus", "Blueberry");
        System.out.println("replace(3, 'Citrus', 'Blueberry'): " + map); // Citrus → Blueberry
        // 13. keySet() - Get all keys
        System.out.println("keySet(): " + map.keySet()); // [keys]
        // 14. values() - Get all values
        System.out.println("values(): " + map.values()); // [values]
        // 15. entrySet() - Get all key-value pairs
        System.out.println("entrySet(): " + map.entrySet()); // [k=v pairs]
        // 16. forEach(BiConsumer) - Iterate entries
        map.forEach((k, v) -> System.out.println("forEach: " + k + " => " + v)); // prints all entries
        // 17. clear() - Remove all entries
        map.clear();
        System.out.println("clear(): " + map); // {}
        // 18. putAll(Map) - Copy all entries from another map
        Map<Integer, String> map2 = new HashMap<>();
        map2.put(10, "One");
        map2.put(20, "Two");
        map.putAll(map2);
        System.out.println("putAll(): " + map); // copied map2
        // 19. equals(Object o) - Compare maps
        Map<Integer, String> map3 = new HashMap<>(map2);
        System.out.println("equals(map2): " + map.equals(map3)); // true
        // 20. hashCode() - Hash of map
        System.out.println("hashCode(): " + map.hashCode()); // hash value
        // 21. compute(K, BiFunction) - Compute new value
        map.compute(10, (k, v) -> v + "_Updated");
        System.out.println("compute(): " + map); // value updated
        // 22. computeIfAbsent(K, Function) - Compute if key missing
        map.computeIfAbsent(30, k -> "Three");
        System.out.println("computeIfAbsent(): " + map); // 30 added
        // 23. computeIfPresent(K, BiFunction) - Compute if key exists
        map.computeIfPresent(20, (k, v) -> v + "_Modified");
        System.out.println("computeIfPresent(): " + map); // 20 modified
        // 24. merge(K, V, BiFunction) - Merge values
        map.merge(20, "New", (oldVal, newVal) -> oldVal + "+" + newVal);
        System.out.println("merge(): " + map); // merged value
        // 25. replaceAll(BiFunction) - Update all values
        map.replaceAll((k, v) -> v.toUpperCase());
        System.out.println("replaceAll(): " + map); // all uppercase
    }
}