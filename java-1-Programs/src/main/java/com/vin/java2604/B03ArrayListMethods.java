package com.vin.java2604;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class B03ArrayListMethods {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		// 1. add(E e) - add element to end
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		System.out.println("After add: " + list); // [Apple, Banana, Cherry]
		// 2. add(int index, E element) - insert at index
		list.add(1, "Blueberry");
		System.out.println("After add at index: " + list); // [Apple, Blueberry, Banana, Cherry]
		// 3. addAll(Collection<? extends E> c) - add collection
		List<String> moreFruits = Arrays.asList("Mango", "Pineapple");
		list.addAll(moreFruits);
		System.out.println("After addAll: " + list); // [Apple, Blueberry, Banana, Cherry, Mango, Pineapple]
		// 4. addAll(int index, Collection<? extends E> c) - insert collection
		list.addAll(2, Arrays.asList("Orange", "Grapes"));
		System.out.println("After addAll at index: " + list); // [Apple, Blueberry, Orange, Grapes, Banana, Cherry, Mango, Pineapple]
		// 5. get(int index) - get element
		System.out.println("Element at index 2: " + list.get(2)); // Orange
		// 6. set(int index, E element) - update element
		list.set(3, "Blackberry");
		System.out.println("After set: " + list); // [Apple, Blueberry, Orange, Blackberry, Banana, Cherry, Mango, Pineapple]
		// 7. remove(Object o) - remove element
		list.remove("Apple");
		System.out.println("After remove object: " + list); // [Blueberry, Orange, Blackberry, Banana, Cherry, Mango, Pineapple]
		// 8. remove(int index) - remove by index
		list.remove(0);
		System.out.println("After remove index: " + list); // [Orange, Blackberry, Banana, Cherry, Mango, Pineapple]
		// 9. removeAll(Collection<?> c) - remove matching elements
		list.removeAll(Arrays.asList("Mango", "Cherry"));
		System.out.println("After removeAll: " + list); // [Orange, Blackberry, Banana, Pineapple]
		// 10. retainAll(Collection<?> c) - keep only given elements
		list.retainAll(Arrays.asList("Orange", "Blackberry"));
		System.out.println("After retainAll: " + list); // [Orange, Blackberry]
		// 11. clear() - remove all elements
		list.clear();
		System.out.println("List after clear(): " + list); // []
		// Reset list
		list.addAll(Arrays.asList("Dog", "Cat", "Parrot", "Cat", "Rabbit"));
		System.out.println("Reset list: " + list); // [Dog, Cat, Parrot, Cat, Rabbit]
		// 12. contains(Object o) - check presence
		System.out.println("Contains Cat? " + list.contains("Cat")); // true
		// 13. containsAll(Collection<?> c) - check all elements exist
		System.out.println("Contains Dog and Rabbit? " + list.containsAll(Arrays.asList("Dog", "Rabbit"))); // true
		// 14. isEmpty() - check empty
		System.out.println("Is list empty? " + list.isEmpty()); // false
		// 15. size() - number of elements
		System.out.println("Size: " + list.size()); // 5
		// 16. indexOf(Object o) - first occurrence
		System.out.println("First index of Cat: " + list.indexOf("Cat")); // 1
		// 17. lastIndexOf(Object o) - last occurrence
		System.out.println("Last index of Cat: " + list.lastIndexOf("Cat")); // 3
		// 18. subList(int fromIndex, int toIndex) - sub view
		System.out.println("Sublist (1,3): " + list.subList(1, 3)); // [Cat, Parrot]
		// 19. toArray() - convert to Object[]
		Object[] arr = list.toArray();
		System.out.println("Array: " + Arrays.toString(arr)); // [Dog, Cat, Parrot, Cat, Rabbit]
		// 20. toArray(T[] a) - convert to typed array
		String[] strArr = list.toArray(new String[0]);
		System.out.println("String Array: " + Arrays.toString(strArr)); // [Dog, Cat, Parrot, Cat, Rabbit]
		// 21. equals(Object o) - compare lists
		System.out.println("Equals another list? " + list.equals(Arrays.asList("Dog", "Cat", "Parrot", "Cat", "Rabbit"))); // true
		// 22. hashCode() - hash value
		System.out.println("HashCode: " + list.hashCode()); // (integer value)
		// 23. sort(Comparator<? super E> c) - sort list
		list.sort(Comparator.naturalOrder());
		System.out.println("Sorted List: " + list); // [Cat, Cat, Dog, Parrot, Rabbit]
		// 24. replaceAll(UnaryOperator<E> operator) - modify all elements
		list.replaceAll(e -> e.toUpperCase());
		System.out.println("Uppercase: " + list); // [CAT, CAT, DOG, PARROT, RABBIT]
		// 25. removeIf(Predicate<? super E> filter) - conditional removal
		list.removeIf(e -> e.startsWith("C"));
		System.out.println("After removeIf: " + list); // [DOG, PARROT, RABBIT]
		// 26. clone() (ArrayList specific) - shallow copy
		ArrayList<String> cloned = (ArrayList<String>) ((ArrayList<String>) list).clone();
		System.out.println("Cloned List: " + cloned); // [DOG, PARROT, RABBIT]
		// 27. ensureCapacity(int) - increase capacity (not size)
		ArrayList<String> arrList = (ArrayList<String>) list;
		arrList.ensureCapacity(50);
		System.out.println("After ensureCapacity(50), size: " + arrList.size()); // 3 ⚠ size unchanged
		// 28. trimToSize() - shrink capacity
		arrList.trimToSize();
		System.out.println("After trimToSize(), size: " + arrList.size()); // 3
	}

}
