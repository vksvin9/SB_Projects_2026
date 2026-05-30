package com.vin.java2604;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.Vector;

public class B02ArrayListIteration {
	public static void main(String[] args) {
		//Fixed-size list (cannot add/remove)
		List<String> list = Arrays.asList("Apple", "Banana", "Cherry", "Mango");
		// 1. For loop (index based) - iterate using index
		for (int i = 0; i < list.size(); i++) {
			System.out.println("For loop: " + list.get(i));
			// Output: Apple, Banana, Cherry, Mango (line by line)
		}
		// 2. Enhanced for loop - direct element iteration
		for (String fruit : list) {
			System.out.println("Enhanced for loop: " + fruit);
			// Output: Apple, Banana, Cherry, Mango
		}
		// 3. Iterator - forward traversal
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println("Iterator: " + it.next());
			// Output: Apple, Banana, Cherry, Mango
		}
		// 4. ListIterator (forward) - bidirectional iterator forward
		ListIterator<String> listIt = list.listIterator();
		while (listIt.hasNext()) {
			System.out.println("ListIterator forward: " + listIt.next());
			// Output: Apple, Banana, Cherry, Mango
		}
		// 5. ListIterator (backward) - reverse traversal
		ListIterator<String> revListIt = list.listIterator(list.size());
		while (revListIt.hasPrevious()) {
			System.out.println("ListIterator backward: " + revListIt.previous());
			// Output: Mango, Cherry, Banana, Apple
		}
		// 6. forEach (Java 8) - lambda iteration
		list.forEach(e -> System.out.println("forEach lambda: " + e));
		// Output: Apple, Banana, Cherry, Mango
		// 7. forEach with method reference - direct print
		list.forEach(System.out::println);
		// Output: Apple, Banana, Cherry, Mango (⚠ no prefix text)
		// 8. Streams forEach - stream iteration
		list.stream().forEach(e -> System.out.println("Stream forEach: " + e));
		// Output: Apple, Banana, Cherry, Mango
		// 9. Streams parallel forEach - parallel (order not guaranteed)
		list.parallelStream().forEach(e -> System.out.println("Parallel Stream: " + e));
		// ⚠ Output order NOT guaranteed
		// 10. Enumeration - legacy iteration
		Vector<String> v = new Vector<>(list);
		Enumeration<String> en = v.elements();
		while (en.hasMoreElements()) {
			System.out.println("Enumeration: " + en.nextElement());
			// Output: Apple, Banana, Cherry, Mango
		}
		// 11. Spliterator - advanced iterator (supports parallelism)
		Spliterator<String> sp = list.spliterator();
		sp.forEachRemaining(e -> System.out.println("Spliterator: " + e));
		// Output: Apple, Banana, Cherry, Mango
		// 12. parallelStream with order - maintains order
		list.parallelStream().forEachOrdered(e -> System.out.println("forEachOrdered: " + e));
		// Output: Apple, Banana, Cherry, Mango
		// 13. Stream map + forEach - transform then iterate
		list.stream().map(String::toUpperCase).forEach(e -> System.out.println("Stream map: " + e));
		// Output: APPLE, BANANA, CHERRY, MANGO
		// 14. Stream filter + forEach - conditional iteration
		list.stream().filter(e -> e.startsWith("B")).forEach(e -> System.out.println("Stream filter: " + e));
		// Output: Banana
	}
}
