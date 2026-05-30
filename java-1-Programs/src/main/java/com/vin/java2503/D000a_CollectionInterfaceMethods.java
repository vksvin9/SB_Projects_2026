package com.vin.java2503;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class D000a_CollectionInterfaceMethods {
	public static void main(String[] args) {
		// Create a Collection object (ArrayList in this case)
		Collection<String> collection = new ArrayList<>();

		// 1. add(E e) - Add elements to the collection
		collection.add("Apple");
		collection.add("Banana");
		collection.add("Orange");
		System.out.println("Collection after adding elements: " + collection);

		// 2. addAll(Collection<? extends E> c) - Add all elements from another
		// collection
		Collection<String> anotherCollection = new ArrayList<>();
		anotherCollection.add("Grapes");
		anotherCollection.add("Mango");
		collection.addAll(anotherCollection);
		System.out.println("Collection after adding another collection: " + collection);

		// 3. clear() - Clear all elements from the collection
		collection.clear();
		System.out.println("Collection after clear(): " + collection);

		// 4. contains(Object o) - Check if a specific element exists
		collection.add("Apple");
		collection.add("Banana");
		System.out.println("Does collection contain 'Apple'? " + collection.contains("Apple")); // true

		// 5. containsAll(Collection<?> c) - Check if collection contains all elements
		// of another collection
		anotherCollection.add("Apple");
		anotherCollection.add("Banana");
		System.out.println("Does collection contain all elements of anotherCollection? "
				+ collection.containsAll(anotherCollection)); // true

		// 6. isEmpty() - Check if the collection is empty
		System.out.println("Is collection empty? " + collection.isEmpty()); // false

		// 7. iterator() - Iterate through the collection using an iterator
		Iterator<String> iterator = collection.iterator();
		System.out.print("Iterating through collection: ");
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		System.out.println();

		// 8. remove(Object o) - Remove a specific element
		collection.remove("Banana");
		System.out.println("Collection after remove('Banana'): " + collection);

		// 9. removeAll(Collection<?> c) - Remove all elements in collection that are in
		// another collection
		collection.removeAll(anotherCollection);
		System.out.println("Collection after removeAll(anotherCollection): " + collection);

		// 10. retainAll(Collection<?> c) - Retain only elements that are in both
		// collections
		collection.add("Apple");
		collection.add("Orange");
		collection.retainAll(anotherCollection);
		System.out.println("Collection after retainAll(anotherCollection): " + collection);

		// 11. size() - Get the number of elements in the collection
		System.out.println("Size of collection: " + collection.size());

		// 12. toArray() - Convert collection to an array
		Object[] array = collection.toArray();
		System.out.println("Array after toArray(): " + java.util.Arrays.toString(array));

		// 13. forEach(Consumer<? super E> action) - Perform an action for each element
		collection.forEach(System.out::println);
	}
}
