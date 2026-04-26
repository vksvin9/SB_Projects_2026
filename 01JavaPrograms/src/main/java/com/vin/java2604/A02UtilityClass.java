package com.vin.java2604;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class A02UtilityClass {
	public static void main(String[] args) {
		// ===== Arrays Methods =====
		int[] arr = { 5, 2, 8, 1, 3 };
		// 1. toString() - convert array to readable string
		System.out.println("Original array: " + Arrays.toString(arr));// Original array: [5, 2, 8, 1, 3]
		// 2. sort() - sort array in ascending order
		Arrays.sort(arr);
		System.out.println("Sorted array: " + Arrays.toString(arr));// Sorted array: [1, 2, 3, 5, 8]
		// 3. binarySearch() - find index of element (must be sorted)
		int index = Arrays.binarySearch(arr, 3);
		System.out.println("Index of 3: " + index);// Index of 3: 2
		// 4. copyOf() - create copy of array
		int[] copy = Arrays.copyOf(arr, arr.length);
		System.out.println("Copied array: " + Arrays.toString(copy));// Copied array: [1, 2, 3, 5, 8]
		// 5. fill() - fill array with given value
		Arrays.fill(copy, 7);
		System.out.println("Filled array: " + Arrays.toString(copy));// Filled array: [7, 7, 7, 7, 7]
		// 6. equals() - compare two arrays
		System.out.println("Arrays equal: " + Arrays.equals(arr, copy));// Arrays equal: false
		// 7. compare() - lexicographically compare arrays
		int[] arr2 = { 5, 2, 8, 1, 3 };
		System.out.println("Array compare: " + Arrays.compare(arr, arr2));
		// ⚠ Output depends on comparison, NOT always -1
		// 8. mismatch() - find first different index
		System.out.println("First mismatch index: " + Arrays.mismatch(arr, arr2));// First mismatch index: 0
		// 9. copyOfRange() - copy subrange of array
		int[] sub = Arrays.copyOfRange(arr, 1, 4);
		System.out.println("copyOfRange: " + Arrays.toString(sub));// copyOfRange: [2, 3, 5]
		// 10. parallelSort() - parallel sorting (faster for large arrays)
		int[] arr3 = { 9, 4, 7, 1, 6 };
		Arrays.parallelSort(arr3);
		System.out.println("parallelSort: " + Arrays.toString(arr3));// parallelSort: [1, 4, 6, 7, 9]
		// 11. parallelPrefix() - cumulative operation (prefix sum)
		int[] prefixArr = { 1, 2, 3, 4 };
		Arrays.parallelPrefix(prefixArr, (x, y) -> x + y);
		System.out.println("parallelPrefix: " + Arrays.toString(prefixArr));// parallelPrefix: [1, 3, 6, 10]
		// 12. setAll() - initialize array using function
		int[] setArr = new int[5];
		Arrays.setAll(setArr, i -> i + 1);
		System.out.println("setAll: " + Arrays.toString(setArr));// setAll: [1, 2, 3, 4, 5]
		// 13. parallelSetAll() - parallel initialization
		int[] pSetArr = new int[5];
		Arrays.parallelSetAll(pSetArr, i -> i * 3);
		System.out.println("parallelSetAll: " + Arrays.toString(pSetArr));// parallelSetAll: [0, 3, 6, 9, 12]
		// 14. stream() - convert array to stream and process
		int sum = Arrays.stream(arr).sum();
		System.out.println("Stream sum: " + sum);// Stream sum: 19
		// 15. deepToString() - print multi-dimensional arrays
		int[][] matrix = { { 1, 2 }, { 3, 4, 5 } };
		System.out.println("deepToString: " + Arrays.deepToString(matrix));// deepToString: [[1, 2], [3, 4, 5]]
		// 16. deepEquals() - compare multi-dimensional arrays
		int[][] m1 = { { 1, 2 }, { 3, 4 } };
		int[][] m2 = { { 1, 2 }, { 3, 4 } };
		System.out.println("deepEquals: " + Arrays.deepEquals(m1, m2));// deepEquals: true
		// 17. deepHashCode() - hash for multi-dimensional arrays
		System.out.println("deepHashCode: " + Arrays.deepHashCode(m1));// deepHashCode: (some integer value)
		// List - convert array to list
		List<String> al = new ArrayList<>(Arrays.asList("Apple", "Banana", "Orange", "Apple"));
		System.out.println("Original List: " + al);// Original List: [Apple, Banana, Orange, Apple]

		// ===== Collections Methods =====
		// 1. Initialization - create and print different list types
		List<String> list = new ArrayList<>();
		list.add("b");
		list.add("c");
		list.add("a");
		list.add("d");
		list.add("e");
		System.out.println(list); // [b, c, a, d, e]
		List<String> list1 = Arrays.asList("c", "d", "a", "b", "e", "a");
		System.out.println(list1); // [c, d, a, b, e, a]
		List<String> list2 = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
		System.out.println(list2); // [1, 2, 3, 4]
		List<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 1, 1));
		System.out.println(list3); // [1, 2, 3, 4, 1, 1]
		// 2. Sorting - sort, reverse sort, reverse, shuffle
		Collections.sort(list);
		System.out.println("Sorted: " + list); // [a, b, c, d, e]
		Collections.sort(list, Comparator.reverseOrder());
		System.out.println("Sorted (reverse comparator): " + list); // [e, d, c, b, a]
		Collections.reverse(list);
		System.out.println("Reversed: " + list); // [a, b, c, d, e]
		Collections.shuffle(list);
		System.out.println("Shuffled: " + list); // random order
		// 3. Min / Max - find smallest and largest element
		System.out.println("Min: " + Collections.min(list)); // a
		System.out.println("Max: " + Collections.max(list)); // e
		System.out.println("Min (comp): " + Collections.min(list, Comparator.naturalOrder())); // a
		System.out.println("Max (comp): " + Collections.max(list, Comparator.naturalOrder())); // e
		// 4. Frequency - count occurrences of element
		System.out.println("Frequency of 'Apple': " + Collections.frequency(list, "Apple")); // 0
		// 5. ReplaceAll - replace all matching elements
		Collections.replaceAll(list, "Apple", "Mango");
		System.out.println("After replace: " + list); // no change
		// 6. Rotate - rotate elements by distance
		Collections.rotate(list, 1);
		System.out.println("After rotate: " + list); // rotated right by 1
		// 7. Binary Search - search element in sorted list
		Collections.sort(list);
		int index1 = Collections.binarySearch(list, "Mango");
		System.out.println("Binary Search (Mango): " + index1); // negative (not found)
		// 8. Copy - overwrite elements from source into an existing destination list
		List<String> source = Arrays.asList("A", "B", "C");
		List<String> dest = new ArrayList<>(Arrays.asList("X", "X", "X"));
		Collections.copy(dest, source);
		System.out.println("Copy Example: " + dest); // [A, B, C]
		// best practice copy
		List<String> destSafe = new ArrayList<>(source);
		System.out.println("Safe Copy: " + destSafe); // [A, B, C]
		// 9. nCopies - create immutable list with repeated elements
		List<String> copies = Collections.nCopies(3, "X");
		System.out.println("nCopies: " + copies); // [X, X, X]
		// 10. Fill - replace all elements with given value
		Collections.fill(dest, "X");
		System.out.println("Filled List: " + dest); // [X, X, X]
		// 11. Swap - swap two elements by index
		Collections.swap(list, 0, 1);
		System.out.println("After swap: " + list); // swapped first two
		// 12. addAll - add multiple elements to list
		Collections.addAll(list, "A", "B", "C");
		System.out.println("After addAll: " + list); // appended A, B, C
		// 13. disjoint - check if two collections have no common elements
		boolean noCommon = Collections.disjoint(list, Arrays.asList("Z"));
		System.out.println("Disjoint with [Z]: " + noCommon); // true
		// 14. SubList search - find sublist positions
		int pos = Collections.indexOfSubList(list, Arrays.asList("A", "B"));
		int lastPos = Collections.lastIndexOfSubList(list, Arrays.asList("A", "B"));
		System.out.println("indexOfSubList: " + pos); // index or -1
		System.out.println("lastIndexOfSubList: " + lastPos); // index or -1
		// 15. Enumeration - convert collection to enumeration and back
		Enumeration<String> e = Collections.enumeration(list);
		ArrayList<String> newList = Collections.list(e);
		System.out.println("Enumeration to List: " + newList); // same as list
		// 16. Checked Collection - runtime type safety
		List<String> checkedList = Collections.checkedList(new ArrayList<>(), String.class);
		checkedList.add("Safe");
		System.out.println("Checked List: " + checkedList); // [Safe]
		// 17. newSetFromMap - create set backed by map
		Set<String> customSet = Collections.newSetFromMap(new HashMap<>());
		customSet.add("A");
		System.out.println("newSetFromMap: " + customSet); // [A]
		// 18. asLifoQueue - treat deque as stack (LIFO)
		Queue<String> lifoQueue = Collections.asLifoQueue(new ArrayDeque<>());
		lifoQueue.add("One");
		lifoQueue.add("Two");
		System.out.println("LIFO Queue: " + lifoQueue); // [Two, One]
		// 19. Unmodifiable - read-only view of list
		List<String> unmodifiable = Collections.unmodifiableList(list);
		System.out.println("Unmodifiable List: " + unmodifiable); // same as list
		// 20. Synchronized - thread-safe wrapper
		List<String> syncList = Collections.synchronizedList(new ArrayList<>());
		syncList.add("ThreadSafe");
		System.out.println("Synchronized List: " + syncList); // [ThreadSafe]
		// 21. Singleton - immutable list with one element
		List<String> singleList = Collections.singletonList("OnlyOne");
		System.out.println("Singleton List: " + singleList); // [OnlyOne]
		// 22. Empty - immutable empty list
		List<String> emptyList = Collections.emptyList();
		System.out.println("Empty List: " + emptyList); // []
		// 23. Create from Set - convert Set to ArrayList
		Set<String> set = new HashSet<>(Arrays.asList("A", "B", "C"));
		ArrayList<String> listFromSet = new ArrayList<>(set);
		System.out.println("From Set: " + listFromSet); // ⚠ order not guaranteed

	}
}
