package com.vin.java2503;

import java.util.*;
import java.util.stream.Collectors;

public class D001a_ArrayListExamples {
	public static void main(String[] args) {
		System.out.println("\n🔹 Removing Duplicates:");
		removeDuplicates();

		System.out.println("\n🔹 Finding Most Frequent Element:");
		mostFrequentElement();

		System.out.println("\n🔹 Reversing a List:");
		reverseList();

		System.out.println("\n🔹 Finding Second Largest Number:");
		secondLargestNumber();

		System.out.println("\n🔹 Converting Array ↔ ArrayList:");
		convertArrayListAndArray();

		System.out.println("\n🔹 Removing Even Numbers:");
		removeEvenNumbers();

		System.out.println("\n🔹 Finding Common Elements:");
		commonElementsBetweenLists();

		System.out.println("\n🔹 Sorting Custom Objects:");
		sortCustomObjects();

		System.out.println("\n🔹 Stream Example (Filter Odd Numbers):");
		streamExample();
	}

	// 1️⃣ Remove Duplicates
	static void removeDuplicates() {
		List<Integer> list = new ArrayList<>(Arrays.asList(10, 20, 30, 10, 40, 50, 20));
		List<Integer> uniqueList = new ArrayList<>(new LinkedHashSet<>(list));
		System.out.println("List without duplicates: " + uniqueList);
	}

	// 2️⃣ Find Most Frequent Element
	static void mostFrequentElement() {
		List<String> list = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
		Map<String, Integer> freqMap = new HashMap<>();
		for (String item : list) {
			freqMap.put(item, freqMap.getOrDefault(item, 0) + 1);
		}
		String mostFrequent = Collections.max(freqMap.entrySet(), Map.Entry.comparingByValue()).getKey();
		System.out.println("Most frequent element: " + mostFrequent);
	}

	// 3️⃣ Reverse an ArrayList
	static void reverseList() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		Collections.reverse(list);
		System.out.println("Reversed List: " + list);
	}

	// 4️⃣ Find Second Largest Number
	static void secondLargestNumber() {
		List<Integer> list = Arrays.asList(10, 20, 5, 30, 25);
		int largest = Integer.MIN_VALUE, secondLargest = Integer.MIN_VALUE;
		for (int num : list) {
			if (num > largest) {
				secondLargest = largest;
				largest = num;
			} else if (num > secondLargest && num != largest) {
				secondLargest = num;
			}
		}
		System.out.println("Second largest number: " + secondLargest);
	}

	// 5️⃣ Convert Array to ArrayList & Vice Versa
	static void convertArrayListAndArray() {
		String[] array = { "A", "B", "C" };
		List<String> list = new ArrayList<>(Arrays.asList(array));
		System.out.println("Converted to List: " + list);
		String[] newArray = list.toArray(new String[0]);
		System.out.println("Converted to Array: " + Arrays.toString(newArray));
	}

	// 6️⃣ Remove Even Numbers
	static void removeEvenNumbers() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		list.removeIf(num -> num % 2 == 0);
		System.out.println("List after removing evens: " + list);
	}

	// 7️⃣ Find Common Elements Between Two Lists
	static void commonElementsBetweenLists() {
		List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 7);
		List<Integer> common = new ArrayList<>(list1);
		common.retainAll(list2);
		System.out.println("Common elements: " + common);
	}

	// 8️⃣ Sort a List of Custom Objects (Sorting by Age)
	static void sortCustomObjects() {
		List<Person1> people = new ArrayList<>();
		people.add(new Person1("Alice", 30));
		people.add(new Person1("Bob", 25));
		people.add(new Person1("Charlie", 35));
		people.sort(Comparator.comparingInt(p -> p.age));
		System.out.println("Sorted by Age: " + people);
	}

	// 9️⃣ Stream Example: Filter Odd Numbers
	static void streamExample() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		List<Integer> oddNumbers = numbers.stream().filter(num -> num % 2 != 0).collect(Collectors.toList());
		System.out.println("Odd numbers: " + oddNumbers);
	}

}

class Person1 {
	String name;
	int age;

	Person1(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return name + " (" + age + ")";
	}
}