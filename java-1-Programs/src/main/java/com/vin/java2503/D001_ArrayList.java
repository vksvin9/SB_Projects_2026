package com.vin.java2503;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class D001_ArrayList {
	public static void main(String[] args) {

		// ******************************************
		// 🚀 1. Create and Initialize ArrayList
		// ******************************************
		ArrayList<String> fruits = new ArrayList<>();

		// 🔹 add() - Add elements
		fruits.add("Apple");
		fruits.add("Banana");
		fruits.add("Cherry");
		fruits.add("Mango");
		fruits.add("Grapes");
		System.out.println("ArrayList after add(): " + fruits);

		// 🔹 add(index, element) - Insert at a specific index
		fruits.add(2, "Pineapple");
		System.out.println("After add(index, element): " + fruits);

		// 🔹 get(index) - Retrieve an element
		System.out.println("Element at index 2: " + fruits.get(2));

		// 🔹 set(index, element) - Update an element
		fruits.set(1, "Blueberry");
		System.out.println("After set(index, element): " + fruits);

		// 🔹 remove(index) - Remove element at index
		fruits.remove(3);
		System.out.println("After remove(index): " + fruits);

		// 🔹 remove(Object) - Remove a specific element
		fruits.remove("Grapes");
		System.out.println("After remove(Object): " + fruits);

		// 🔹 contains() - Check if an element exists
		System.out.println("Contains 'Mango'? " + fruits.contains("Mango"));

		// 🔹 indexOf() - Get index of an element
		System.out.println("Index of 'Cherry': " + fruits.indexOf("Cherry"));

		// 🔹 size() - Get the size of the ArrayList
		System.out.println("Size of ArrayList: " + fruits.size());

		// 🔹 isEmpty() - Check if ArrayList is empty
		System.out.println("Is ArrayList empty? " + fruits.isEmpty());

		// 🔹 subList(fromIndex, toIndex) - Get a sublist
		List<String> subList = fruits.subList(1, 3);
		System.out.println("SubList (index 1 to 3): " + subList);

		// 🔹 toArray() - Convert to Array
		String[] fruitArray = fruits.toArray(new String[0]);
		System.out.println("Converted to Array: " + Arrays.toString(fruitArray));

		// 🔹 clear() - Remove all elements
		ArrayList<String> tempFruits = new ArrayList<>(fruits); // Backup before clearing
		fruits.clear();
		System.out.println("After clear(): " + fruits);

		// 🔹 addAll(Collection) - Add all elements from another collection
		fruits.addAll(tempFruits);
		System.out.println("After addAll(): " + fruits);

		// 🔹 clone() - Clone an ArrayList
		ArrayList<String> clonedFruits = (ArrayList<String>) fruits.clone();
		System.out.println("Cloned ArrayList: " + clonedFruits);

		// ******************************************
		// 🚀 2. Iteration Techniques
		// ******************************************
		System.out.println("\n🔹 Iteration Techniques:");

		// 1For-Each Loop
		System.out.println("\nFor-Each Loop:");
		for (String fruit : fruits) {
			System.out.println(fruit);
		}

		// Using Iterator
		System.out.println("\nUsing Iterator:");
		Iterator<String> iterator = fruits.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		// Using ListIterator (Forward)
		System.out.println("Using ListIterator (Forward):");
		ListIterator<String> listIterator = fruits.listIterator();
		while (listIterator.hasNext()) {
			System.out.println(listIterator.next());
		}

		// Using ListIterator (Backward)
		System.out.println("\nUsing ListIterator (Backward):");
		while (listIterator.hasPrevious()) {
			System.out.println(listIterator.previous());
		}

		// Using forEach() Method (Lambda Expression)
		System.out.println("\nUsing forEach() Method:");
		fruits.forEach(fruit -> System.out.println(fruit));

		// Using Streams API (Method Reference)
		System.out.println("\nUsing Streams API:");
		fruits.stream().forEach(System.out::println);

		// Using Streams API with Filtering
		System.out.println("\nUsing Streams with Filter (Only fruits with length > 5):");
		fruits.stream().filter(fruit -> fruit.length() > 5).forEach(System.out::println);

		// Using Streams API with Mapping (Convert to Uppercase)
		System.out.println("\nUsing Streams with Mapping (Uppercase):");
		List<String> upperCaseFruits = fruits.stream().map(String::toUpperCase).collect(Collectors.toList());
		System.out.println(upperCaseFruits);
	}
}
