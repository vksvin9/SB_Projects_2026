package com.vin.java2508;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class D008_HMBsToAdv {
    public static void main(String[] args) {
        // BASIC EXAMPLES
    	 // 1. Create immutable Map (Java 8)
        Map<Integer, String> tempMap = new HashMap<>();
        tempMap.put(1, "A");
        tempMap.put(2, "B");
        Map<Integer, String> immutableMap = Collections.unmodifiableMap(tempMap);
        System.out.println("Immutable Map: " + immutableMap); // Output: {1=A, 2=B}
        // 2. Create empty Map
        Map<String, String> emptyMap = Collections.emptyMap();
        System.out.println("Empty Map: " + emptyMap); // Output: {}
        // 3. Create singleton Map
        Map<String, String> singletonMap = Collections.singletonMap("Key", "Value");
        System.out.println("Singleton Map: " + singletonMap); // Output: {Key=Value}
        // 4. Merge two maps (Java 8 merge())
        Map<Integer, String> m1 = new HashMap<>();
        m1.put(1, "A");
        m1.put(2, "B");
        Map<Integer, String> m2 = new HashMap<>();
        m2.put(2, "C");
        m2.put(3, "D");
        m2.forEach((k, v) -> m1.merge(k, v, (v1, v2) -> v1 + "/" + v2));
        System.out.println("Merged Map: " + m1); // Output: {1=A, 2=B/C, 3=D}
        // 5. Convert Map to List of keys
        List<Integer> keyList = new ArrayList<>(m1.keySet());
        System.out.println("Key List: " + keyList); // Output: [1, 2, 3]
        // 6. Convert Map to List of values
        List<String> valueList = new ArrayList<>(m1.values());
        System.out.println("Value List: " + valueList); // Output: [A, B/C, D]
        // 7. Sorting Map by keys
        Map<Integer, String> sortedByKey = new TreeMap<>(m1);
        System.out.println("Sorted by Key: " + sortedByKey); // Output: {1=A, 2=B/C, 3=D}
        // 8. Sorting Map by values
        m1.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(e -> System.out.println("Sorted by Value: " + e));
        /*
        Output:
        Sorted by Value: 1=A
        Sorted by Value: 2=B/C
        Sorted by Value: 3=D
        */
        // 9. Copy Map
        Map<Integer, String> copyMap = new HashMap<>(m1);
        System.out.println("Copied Map: " + copyMap);
        // 10. Reverse Map (keys <-> values)
        Map<String, Integer> reverseMap = new HashMap<>();
        m1.forEach((k, v) -> reverseMap.put(v, k));
        System.out.println("Reversed Map: " + reverseMap);
        // INTERMEDIATE EXAMPLES
        // 1. Case-insensitive Map using TreeMap
        Map<String, String> caseInsensitive = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        caseInsensitive.put("a", "1");
        caseInsensitive.put("A", "2"); // replaces previous
        System.out.println("Case-insensitive Map: " + caseInsensitive); // Output: {a=2}
        // 2. Multi-value Map using Map<Key, List<Value>>
        Map<String, List<String>> multiMap = new HashMap<>();
        multiMap.put("Fruit", Arrays.asList("Apple", "Banana"));
        System.out.println("Multi-value Map: " + multiMap);
        // 3. Filter Map by key
        Map<Integer, String> filtered = m1.entrySet().stream()
                .filter(e -> e.getKey() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("Filtered Map: " + filtered); // Output: {2=B/C, 3=D}
        // 4. Find max key
        Integer maxKey = Collections.max(m1.keySet());
        System.out.println("Max Key: " + maxKey); // Output: 3
        // 5. Find max value
        String maxValue = Collections.max(m1.values());
        System.out.println("Max Value: " + maxValue); // Output: D
        // 6. Convert Map to Properties
        Properties props = new Properties();
        props.putAll(m1);
        System.out.println("Properties: " + props);
        // 7. Create Map from 2 arrays
        String[] keysArr = {"a", "b"};
        String[] valsArr = {"1", "2"};
        Map<String, String> fromArrays = new HashMap<>();
        for (int i = 0; i < keysArr.length; i++) fromArrays.put(keysArr[i], valsArr[i]);
        System.out.println("Map from arrays: " + fromArrays);
        // 8. LinkedHashMap for access order
        Map<Integer, String> accessOrderMap = new LinkedHashMap<>(16, 0.75f, true);
        accessOrderMap.put(1, "A");
        accessOrderMap.put(2, "B");
        accessOrderMap.get(1); // access to reorder
        System.out.println("LinkedHashMap (access order): " + accessOrderMap);
        // 9. Map with default value using getOrDefault
        String val = m1.getOrDefault(99, "Not Found");
        System.out.println("getOrDefault: " + val); // Output: Not Found
        // 10. Unmodifiable map
        Map<Integer, String> unmodifiable = Collections.unmodifiableMap(m1);
        System.out.println("Unmodifiable Map: " + unmodifiable);
        // ADVANCED EXAMPLES
        // 1. LRU Cache using LinkedHashMap
        Map<Integer, String> lruCache = new LinkedHashMap<Integer, String>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > 100;
            }
        };
        lruCache.put(1, "A");
        lruCache.put(2, "B");
        lruCache.put(3, "C");
        lruCache.put(4, "D"); // oldest entry removed
        System.out.println("LRU Cache: " + lruCache);
        // 2. ConcurrentHashMap usage
        Map<String, String> concurrentMap = new java.util.concurrent.ConcurrentHashMap<>();
        concurrentMap.put("x", "1");
        System.out.println("Concurrent Map: " + concurrentMap);
        // 3. Map.computeIfAbsent()
        Map<String, List<String>> mapList = new HashMap<>();
        mapList.computeIfAbsent("fruits", k -> new ArrayList<>()).add("Apple");
        System.out.println("computeIfAbsent: " + mapList);
//        // 4. Map.computeIfPresent()
//        Map<Integer, String> numMap = new HashMap<>(Map.of(1, "One"));
//        numMap.computeIfPresent(1, (k, v) -> v.toUpperCase());
//        System.out.println("computeIfPresent: " + numMap);
        // 5. Map.merge() for counting
        Map<String, Integer> wordCount = new HashMap<>();
        String[] words = {"apple", "banana", "apple"};
        for (String w : words) wordCount.merge(w, 1, Integer::sum);
        System.out.println("Word Count Map: " + wordCount);
        // 6. Grouping by using Collectors.groupingBy()
        List<String> fruits = Arrays.asList("Apple", "Apricot", "Banana");
        Map<Character, List<String>> grouped = fruits.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0)));
        System.out.println("Grouped Map: " + grouped);
        // 7. Map.Entry.comparingByKey()
        m1.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.println("Sorted by Key: " + e));
        // 8. Map.Entry.comparingByValue()
        m1.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach(e -> System.out.println("Sorted by Value: " + e));
        // 9. Synchronized Map
        Map<Integer, String> syncMap = Collections.synchronizedMap(new HashMap<>());
        syncMap.put(1, "A");
        System.out.println("Synchronized Map: " + syncMap);
//        // 10. Immutable Map with Map.ofEntries()
//        Map<Integer, String> immMap = Map.ofEntries(
//                Map.entry(1, "A"),
//                Map.entry(2, "B")
//        );
//        System.out.println("Immutable Map (ofEntries): " + immMap);
    }
}