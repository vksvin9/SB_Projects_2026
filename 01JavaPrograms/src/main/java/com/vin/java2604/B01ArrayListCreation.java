package com.vin.java2604;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class B01ArrayListCreation {

	public static void main(String[] args) {

		// 1. Default constructor - Empty list
		List<String> list1 = new ArrayList<>();
		list1.add("Apple");
		System.out.println("Default: " + list1); // [Apple]

		// 2. With initial capacity - Performance optimization
		List<String> list2 = new ArrayList<>(10);
		list2.add("Banana");
		System.out.println("With capacity: " + list2); // [Banana]

		// 3. From another collection (copy constructor)
		List<String> list3 = new ArrayList<>(list1);
		System.out.println("Copy constructor: " + list3); // copy of list1

		// 4. Using Arrays.asList() (fixed-size list → wrap into ArrayList)
		List<String> list4 = new ArrayList<>(Arrays.asList("A", "B", "C"));
		System.out.println("Arrays.asList: " + list4); // [A, B, C]

		// 5. Using List.of() (Java 9+ immutable → wrap if needed)
		List<String> list5 = new ArrayList<>(List.of("X", "Y", "Z"));
		System.out.println("List.of: " + list5); // [X, Y, Z]

		// 6. Using Collections.addAll()
		List<String> list6 = new ArrayList<>();
		Collections.addAll(list6, "One", "Two", "Three");
		System.out.println("Collections.addAll: " + list6); // [One, Two, Three]

		// 7. Using Stream API
		List<String> list7 = Stream.of("P", "Q", "R").collect(Collectors.toList());
		System.out.println("Stream: " + list7); // [P, Q, R]

		// 8. Using Stream + Collectors.toCollection (explicit ArrayList)
		List<String> list8 = Stream.of("M", "N").collect(Collectors.toCollection(ArrayList::new));
		System.out.println("Stream to ArrayList: " + list8); // [M, N]

		// 9. Double brace initialization (NOT recommended)
		List<String> list9 = new ArrayList<String>() {
			{
				add("A");
				add("B");
			}
		};
		System.out.println("Double brace: " + list9); // [A, B]

		// 10. Using Collections.singletonList() (immutable → wrap)
		List<String> list10 = new ArrayList<>(Collections.singletonList("Single"));
		System.out.println("SingletonList: " + list10); // [Single]
		
		// ================= LINKEDLIST =================

		// 11. Default constructor - Empty LinkedList
		List<String> linkedList1 = new LinkedList<>();
		linkedList1.add("Apple");
		System.out.println("LinkedList Default: " + linkedList1); // [Apple]

		// 12. From another collection
		List<String> linkedList2 = new LinkedList<>(list1);
		System.out.println("LinkedList Copy: " + linkedList2); // copy of list1

		// 13. Using Arrays.asList()
		List<String> linkedList3 = new LinkedList<>(Arrays.asList("L1", "L2"));
		System.out.println("LinkedList Arrays.asList: " + linkedList3); // [L1, L2]


		// ================= VECTOR =================

		// 14. Default constructor - Thread-safe list
		List<String> vector1 = new Vector<>();
		vector1.add("Apple");
		System.out.println("Vector Default: " + vector1); // [Apple]

		// 15. With initial capacity
		List<String> vector2 = new Vector<>(5);
		vector2.add("Banana");
		System.out.println("Vector Capacity: " + vector2); // [Banana]

		// 16. From another collection
		List<String> vector3 = new Vector<>(list1);
		System.out.println("Vector Copy: " + vector3); // copy of list1


		// ================= STACK =================

		// 17. Default constructor - LIFO stack
		Stack<String> stack1 = new Stack<>();
		stack1.push("Apple");
		System.out.println("Stack Default: " + stack1); // [Apple]

		// 18. Using push()
		stack1.push("Banana");
		System.out.println("Stack push: " + stack1); // [Apple, Banana]

		// 19. From collection
		Stack<String> stack2 = new Stack<>();
		stack2.addAll(list1);
		System.out.println("Stack from collection: " + stack2); // copied elements
	}
}