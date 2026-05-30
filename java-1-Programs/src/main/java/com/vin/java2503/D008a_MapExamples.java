package com.vin.java2503;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class D008a_MapExamples {
	public static void main(String[] args) {
		System.out.println("\n🔹 Basic HashMap Operations:");
		basicHashMapOperations();

		System.out.println("\n🔹 Iterating a HashMap:");
		iterateHashMap();

		System.out.println("\n🔹 LinkedHashMap Example:");
		linkedHashMapExample();

		System.out.println("\n🔹 TreeMap Example:");
		treeMapExample();

		System.out.println("\n🔹 Finding Most Frequent Word:");
		mostFrequentWord();

		System.out.println("\n🔹 Sorting Map by Values:");
		sortMapByValues();

		System.out.println("\n🔹 Merging Two Maps:");
		mergeTwoMaps();
	}

	// 1️⃣ Basic HashMap Operations
	static void basicHashMapOperations() {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "Apple");
		map.put(2, "Banana");
		map.put(3, "Cherry");
		map.put(4, "Date");

		System.out.println("HashMap: " + map);

		// Accessing elements
		System.out.println("Get key 2: " + map.get(2));
		System.out.println("Contains key 3? " + map.containsKey(3));
		System.out.println("Contains value 'Apple'? " + map.containsValue("Apple"));

		// Removing an element
		map.remove(4);
		System.out.println("After removing key 4: " + map);

		// Checking size
		System.out.println("Size of map: " + map.size());

		// Clearing the map
		map.clear();
		System.out.println("After clearing: " + map);
	}

	// 2️⃣ Iterating a HashMap
	static void iterateHashMap() {
		Map<String, Integer> map = new HashMap<>();
		map.put("A", 10);
		map.put("B", 20);
		map.put("C", 30);
		map.put("D", 40);

		System.out.println("\n🔹 Iteration Methods:");

		// 1. Using keySet()
		for (String key : map.keySet()) {
			System.out.println("Key: " + key + ", Value: " + map.get(key));
		}

		// 2. Using entrySet()
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("Entry: " + entry.getKey() + " = " + entry.getValue());
		}

		// 3. Using forEach & Lambda
		map.forEach((key, value) -> System.out.println("Lambda: " + key + " -> " + value));

		// 4. Using Streams
		map.entrySet().stream().filter(entry -> entry.getValue() > 20)
				.forEach(entry -> System.out.println("Stream Filter: " + entry));
	}

	// 3️⃣ LinkedHashMap (Maintains Insertion Order)
	static void linkedHashMapExample() {
		Map<Integer, String> linkedMap = new LinkedHashMap<>();
		linkedMap.put(3, "C");
		linkedMap.put(1, "A");
		linkedMap.put(2, "B");

		System.out.println("\n🔹 LinkedHashMap (Insertion Order Maintained): " + linkedMap);
	}

	// 4️⃣ TreeMap (Sorted Order)
	static void treeMapExample() {
		TreeMap<Integer, String> treeMap = new TreeMap<>();
		treeMap.put(4, "Four");
		treeMap.put(2, "Two");
		treeMap.put(1, "One");
		treeMap.put(3, "Three");

		System.out.println("\n🔹 TreeMap (Sorted by Key): " + treeMap);
	}

	// 5️⃣ Finding Most Frequent Word
	static void mostFrequentWord() {
		List<String> words = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "banana");
		Map<String, Integer> freqMap = new HashMap<>();

		for (String word : words) {
			freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
		}

		String mostFrequent = Collections.max(freqMap.entrySet(), Map.Entry.comparingByValue()).getKey();
		System.out.println("\n🔹 Most Frequent Word: " + mostFrequent);
	}

	// 6️⃣ Sorting a Map by Values
	static void sortMapByValues() {
		Map<String, Integer> map = new HashMap<>();
		map.put("A", 5);
		map.put("B", 3);
		map.put("C", 8);
		map.put("D", 1);

		LinkedHashMap<String, Integer> sortedMap = map.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		System.out.println("\n🔹 Sorted Map by Values: " + sortedMap);
	}

	// 7️⃣ Merge Two Maps
	static void mergeTwoMaps() {
		Map<Integer, String> map1 = new HashMap<>();
		map1.put(1, "One");
		map1.put(2, "Two");

		Map<Integer, String> map2 = new HashMap<>();
		map2.put(2, "Twenty");
		map2.put(3, "Three");

		map1.forEach((key, value) -> map2.merge(key, value, (v1, v2) -> v1 + ", " + v2));
		System.out.println("\n🔹 Merged Map: " + map2);
	}

}
