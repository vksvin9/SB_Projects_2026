package com.vin.java2503;

import java.util.*;

public class D000b_CollectionsClassMethod {
    public static void main(String[] args) {

        // ******************************************
        // 🚀 1. Create and Initialize a List
        // ******************************************
        List<Integer> numbers = new ArrayList<>(Arrays.asList(10, 5, 8, 20, 15));

        System.out.println("Original List: " + numbers);

        // 🔹 sort(List) - Sort elements in natural order
        Collections.sort(numbers);
        System.out.println("After Collections.sort(): " + numbers);

        // 🔹 reverse(List) - Reverse the list
        Collections.reverse(numbers);
        System.out.println("After Collections.reverse(): " + numbers);

        // 🔹 shuffle(List) - Shuffle randomly
        Collections.shuffle(numbers);
        System.out.println("After Collections.shuffle(): " + numbers);

        // 🔹 binarySearch(List, key) - Find an element (List must be sorted)
        Collections.sort(numbers);
        int index = Collections.binarySearch(numbers, 10);
        System.out.println("Index of 10 after binarySearch(): " + index);

        // 🔹 min(Collection) - Get minimum element
        System.out.println("Minimum value: " + Collections.min(numbers));

        // 🔹 max(Collection) - Get maximum element
        System.out.println("Maximum value: " + Collections.max(numbers));

        // 🔹 fill(List, value) - Replace all elements with a value
        Collections.fill(numbers, 100);
        System.out.println("After Collections.fill(100): " + numbers);

        // ******************************************
        // 🚀 2. Working with Unmodifiable & Synchronized Collections
        // ******************************************

        // 🔹 unmodifiableList() - Make the list unmodifiable
        List<String> unmodifiableList = Collections.unmodifiableList(Arrays.asList("A", "B", "C"));
        System.out.println("Unmodifiable List: " + unmodifiableList);
        // unmodifiableList.add("D");  // Throws UnsupportedOperationException

        // 🔹 synchronizedList() - Get a thread-safe version of list
        List<Integer> syncList = Collections.synchronizedList(new ArrayList<>(Arrays.asList(1, 2, 3)));
        synchronized (syncList) {
            for (int num : syncList) {
                System.out.println("Synchronized List Element: " + num);
            }
        }

        // ******************************************
        // 🚀 3. Working with Set & Map
        // ******************************************
        Set<Integer> numberSet = new HashSet<>(Arrays.asList(3, 1, 4, 1, 5, 9));
        
        // 🔹 min() and max() on Set
        System.out.println("Min in Set: " + Collections.min(numberSet));
        System.out.println("Max in Set: " + Collections.max(numberSet));

        // 🔹 synchronizedSet() - Thread-safe Set
        Set<String> syncSet = Collections.synchronizedSet(new HashSet<>(Arrays.asList("X", "Y", "Z")));

        // 🔹 synchronizedMap() - Thread-safe Map
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
        syncMap.put("One", 1);
        syncMap.put("Two", 2);
        syncMap.put("Three", 3);
        synchronized (syncMap) {
            for (Map.Entry<String, Integer> entry : syncMap.entrySet()) {
                System.out.println("Synchronized Map Entry: " + entry.getKey() + " -> " + entry.getValue());
            }
        }
    }
}
