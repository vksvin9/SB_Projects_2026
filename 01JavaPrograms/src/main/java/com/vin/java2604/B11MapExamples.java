package com.vin.java2604;

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

public class B11MapExamples {
	public static void main(String[] args) {
//		// ================= BASIC EXAMPLES =================
//
//		// 1. Map.of() - Create immutable Map
//		Map<Integer, String> immutableMap = Map.of(1, "A", 2, "B");
//		System.out.println("Immutable Map: " + immutableMap); // {1=A, 2=B}
//
//		// 2. Collections.emptyMap() - Create empty immutable Map
//		Map<String, String> emptyMap = Collections.emptyMap();
//		System.out.println("Empty Map: " + emptyMap); // {}
//
//		// 3. Collections.singletonMap() - Single entry immutable Map
//		Map<String, String> singletonMap = Collections.singletonMap("Key", "Value");
//		System.out.println("Singleton Map: " + singletonMap); // {Key=Value}
//
//		// 4. merge() - Merge two maps with custom logic
		Map<Integer, String> m1 = new HashMap<>(Map.of(1, "A", 2, "B"));
//		Map<Integer, String> m2 = Map.of(2, "C", 3, "D");
//		m2.forEach((k, v) -> m1.merge(k, v, (v1, v2) -> v1 + "/" + v2));
//		System.out.println("Merged Map: " + m1); // {1=A, 2=B/C, 3=D}
//
//		// 5. keySet() - Convert Map to List of keys
//		List<Integer> keyList = new ArrayList<>(m1.keySet());
//		System.out.println("Key List: " + keyList); // [1, 2, 3]
//
//		// 6. values() - Convert Map to List of values
//		List<String> valueList = new ArrayList<>(m1.values());
//		System.out.println("Value List: " + valueList); // [A, B/C, D]
//
//		// 7. TreeMap() - Sort Map by keys
//		Map<Integer, String> sortedByKey = new TreeMap<>(m1);
//		System.out.println("Sorted by Key: " + sortedByKey); // {1=A, 2=B/C, 3=D}
//
//		// 8. comparingByValue() - Sort Map by values
//		m1.entrySet().stream().sorted(Map.Entry.comparingByValue())
//				.forEach(e -> System.out.println("Sorted by Value: " + e)); // sorted output
//
//		// 9. Copy constructor - Copy Map
//		Map<Integer, String> copyMap = new HashMap<>(m1);
//		System.out.println("Copy Map: " + copyMap); // same as m1
//
//		// 10. Reverse mapping - Swap key and value
//		Map<String, Integer> reverseMap = new HashMap<>();
//		m1.forEach((k, v) -> reverseMap.put(v, k));
//		System.out.println("Reverse Map: " + reverseMap); // {A=1, B/C=2, D=3}
//
//		// ================= INTERMEDIATE EXAMPLES =================
//
//		// 1. TreeMap(CASE_INSENSITIVE_ORDER) - Case-insensitive keys
//		Map<String, String> caseInsensitive = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
//		caseInsensitive.put("a", "1");
//		caseInsensitive.put("A", "2");
//		System.out.println("Case Insensitive Map: " + caseInsensitive); // {a=2}
//
//		// 2. Multi-value Map - Map<K, List<V>>
//		Map<String, List<String>> multiMap = new HashMap<>();
//		multiMap.put("Fruit", Arrays.asList("Apple", "Banana"));
//		System.out.println("Multi Map: " + multiMap); // {Fruit=[Apple, Banana]}
//
//		// 3. Stream filter() - Filter Map by key
//		Map<Integer, String> filtered = m1.entrySet().stream().filter(e -> e.getKey() > 1)
//				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//		System.out.println("Filtered Map: " + filtered); // {2=B/C, 3=D}
//
//		// 4. Collections.max() - Find max key
//		Integer maxKey = Collections.max(m1.keySet());
//		System.out.println("Max Key: " + maxKey); // 3
//
//		// 5. Collections.max() - Find max value
//		String maxValue = Collections.max(m1.values());
//		System.out.println("Max Value: " + maxValue); // D
//
//		// 6. Properties.putAll() - Convert Map to Properties
//		Properties props = new Properties();
//		props.putAll(m1);
//		System.out.println("Properties: " + props); // {1=A, 2=B/C, 3=D}
//
//		// 7. Manual loop - Create Map from arrays
//		String[] keysArr = { "a", "b" };
//		String[] valsArr = { "1", "2" };
//		Map<String, String> fromArrays = new HashMap<>();
//		for (int i = 0; i < keysArr.length; i++)
//			fromArrays.put(keysArr[i], valsArr[i]);
//		System.out.println("Map from Arrays: " + fromArrays); // {a=1, b=2}
//
//		// 8. LinkedHashMap(accessOrder=true) - Access order map
//		Map<Integer, String> accessOrderMap = new LinkedHashMap<>(16, 0.75f, true);
//		accessOrderMap.put(1, "A");
//		accessOrderMap.put(2, "B");
//		accessOrderMap.get(1);
//		System.out.println("Access Order Map: " + accessOrderMap); // {2=B, 1=A}
//
//		// 9. getOrDefault() - Default value if key missing
//		String val = m1.getOrDefault(99, "Not Found");
//		System.out.println("Default Value: " + val); // Not Found
//
//		// 10. unmodifiableMap() - Read-only view
//		Map<Integer, String> unmodifiable = Collections.unmodifiableMap(m1);
//		System.out.println("Unmodifiable Map: " + unmodifiable); // same as m1

		// ================= ADVANCED EXAMPLES =================

		// 1. LinkedHashMap (LRU Cache) - Remove eldest entry
		Map<Integer, String> lruCache = new LinkedHashMap<Integer, String>(16, 0.75f, true) {
			protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
				return size() > 3;
			}
		};
		lruCache.put(1, "A");
		lruCache.put(2, "B");
		lruCache.put(3, "C");
		lruCache.put(4, "D");
		System.out.println("LRU Cache: " + lruCache); // {2=B,3=C,4=D}

//		// 2. ConcurrentHashMap - Thread-safe Map
//		Map<String, String> concurrentMap = new java.util.concurrent.ConcurrentHashMap<>();
//		concurrentMap.put("k", "v");
//		System.out.println("ConcurrentMap: " + concurrentMap); // {k=v}
//
//		// 3. computeIfAbsent() - Initialize if missing
//		Map<String, List<String>> mapList = new HashMap<>();
//		mapList.computeIfAbsent("fruits", k -> new ArrayList<>()).add("Apple");
//		System.out.println("computeIfAbsent: " + mapList); // {fruits=[Apple]}
//
//		// 4. computeIfPresent() - Update if exists
//		Map<Integer, String> numMap = new HashMap<>(Map.of(1, "One"));
//		numMap.computeIfPresent(1, (k, v) -> v.toUpperCase());
//		System.out.println("computeIfPresent: " + numMap); // {1=ONE}
//
//		// 5. merge() - Counting frequency
//		Map<String, Integer> wordCount = new HashMap<>();
//		String[] words = { "apple", "banana", "apple" };
//		for (String w : words)
//			wordCount.merge(w, 1, Integer::sum);
//		System.out.println("Word Count: " + wordCount); // {apple=2, banana=1}
//
//		// 6. groupingBy() - Group elements
//		List<String> fruits = Arrays.asList("Apple", "Apricot", "Banana");
//		Map<Character, List<String>> grouped = fruits.stream().collect(Collectors.groupingBy(s -> s.charAt(0)));
//		System.out.println("Grouped: " + grouped); // {A=[Apple, Apricot], B=[Banana]}
//
//		// 7. comparingByKey() - Sort by key
//		m1.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(e -> System.out.println("Sorted Key: " + e)); // sorted by key
//
//		// 8. comparingByValue() - Sort by value
//		m1.entrySet().stream().sorted(Map.Entry.comparingByValue())
//				.forEach(e -> System.out.println("Sorted Value: " + e)); // sorted by value
//
//		// 9. synchronizedMap() - Thread-safe wrapper
//		Map<Integer, String> syncMap = Collections.synchronizedMap(new HashMap<>());
//		syncMap.put(1, "A");
//		System.out.println("Synchronized Map: " + syncMap); // {1=A}
//
//		// 10. Map.ofEntries() - Immutable Map
//		Map<Integer, String> immMap = Map.ofEntries(Map.entry(1, "A"), Map.entry(2, "B"));
//		System.out.println("Map.ofEntries: " + immMap); // {1=A, 2=B}
//
//		// ================= EXTRA METHODS =================
//
//		// 11. compute() - General computation
//		m1.compute(1, (k, v) -> v + "_X");
//		System.out.println("compute(): " + m1); // {1=A_X, ...}
//
//		// 12. replaceAll() - Update all values
//		m1.replaceAll((k, v) -> v.toLowerCase());
//		System.out.println("replaceAll(): " + m1); // all lowercase
//
//		// 13. putIfAbsent() - Add if missing
//		m1.putIfAbsent(10, "Z");
//		System.out.println("putIfAbsent(): " + m1); // 10 added
//
//		// 14. containsKey() - Check key
//		System.out.println("containsKey(1): " + m1.containsKey(1)); // true
//
//		// 15. containsValue() - Check value
//		System.out.println("containsValue('a_x'): " + m1.containsValue("a_x")); // true/false
//
//		// 16. forEach() - Iterate
//		m1.forEach((k, v) -> System.out.println("forEach: " + k + "=" + v)); // prints all
//
//		// 17. entrySet() iteration
//		for (Map.Entry<Integer, String> e : m1.entrySet()) {
//			System.out.println("entrySet: " + e.getKey() + "=" + e.getValue()); // iteration
//		}
	}
}
