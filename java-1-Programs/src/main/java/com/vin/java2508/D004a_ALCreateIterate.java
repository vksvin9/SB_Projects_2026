package com.vin.java2508;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Spliterator;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class D004a_ALCreateIterate {
    public static void main(String[] args) {
        // Different Ways to Create ArrayList
        // 1. Default constructor (empty list, grows dynamically)
        List<String> list1 = new ArrayList<>();
        list1.add("Apple");
        list1.add("Banana");
        list1.add("Cherry");
        list1.add("Mango");
        System.out.println("list1: " + list1);
        // [Apple, Banana, Cherry, Mango]
        // 2. With initial capacity
        List<String> list2 = new ArrayList<>(20);
        list2.add("A");
        list2.add("B");
        System.out.println("list2: " + list2);
        // [A, B]
        // 3. Using Arrays.asList()
        List<String> list3 = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry", "Mango"));
        System.out.println("list3: " + list3);
        // [Apple, Banana, Cherry, Mango]
        // 4. Copying another collection
        List<String> list4 = new ArrayList<>(list3);
        System.out.println("list4: " + list4);
        // [Apple, Banana, Cherry, Mango]
        // 5. Using Collections.nCopies()
        List<String> list5 = new ArrayList<>(Collections.nCopies(3, "Hello"));
        System.out.println("list5: " + list5);
        // [Hello, Hello, Hello]
//        // 6. Using Stream.of().toList() (Java 16+)
//        List<String> list6 = new ArrayList<>(Stream.of("X", "Y", "Z").toList());
//        System.out.println("list6: " + list6);
//        // [X, Y, Z]
        // 7. Using Collectors.toCollection()
        List<String> list7 = Stream.of("One", "Two", "Three")
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("list7: " + list7);
        // [One, Two, Three]
        // 8. Double brace initialization (⚠️ not recommended)
        List<String> list8 = new ArrayList<String>() {{
            add("Dog");
            add("Cat");
            add("Rabbit");
        }};
        System.out.println("list8: " + list8);
        // [Dog, Cat, Rabbit]
//        // 9. Java 9+ List.of() (immutable, wrapped into ArrayList for mutability)
//        List<String> list9 = new ArrayList<>(List.of("Red", "Green", "Blue"));
//        System.out.println("list9: " + list9);
//        // [Red, Green, Blue]
        // 10. From Iterator
        java.util.Iterator<String> itSrc = java.util.Arrays.asList("Sun", "Moon", "Star").iterator();
        java.util.List<String> list10 = new java.util.ArrayList<>();
        itSrc.forEachRemaining(list10::add);
        System.out.println("list10: " + list10);
        // [Sun, Moon, Star]
        // 11. From Spliterator
        Spliterator<String> sp = Arrays.asList("Jan", "Feb", "Mar").spliterator();
        List<String> list11 = new ArrayList<>();
        sp.forEachRemaining(list11::add);
        System.out.println("list11: " + list11);
        // [Jan, Feb, Mar]
//        // 12. From Array
//        String[] arr = {"AA", "BB", "CC"};
//        List<String> list12 = new ArrayList<>(Arrays.stream(arr).toList());
//        System.out.println("list12: " + list12);
//        // [AA, BB, CC]
        // Iteration Techniques
        List<String> list = Arrays.asList("Apple", "Banana", "Cherry", "Mango");
        // 1. For loop (index based)
        for (int i = 0; i < list.size(); i++) {
            System.out.println("For loop: " + list.get(i));
            // For loop: Apple
            // For loop: Banana
            // For loop: Cherry
            // For loop: Mango
        }
        // 2. Enhanced for loop
        for (String fruit : list) {
            System.out.println("Enhanced for loop: " + fruit);
            // Enhanced for loop: Apple
            // Enhanced for loop: Banana
            // Enhanced for loop: Cherry
            // Enhanced for loop: Mango
        }
        // 3. Iterator
        java.util.Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println("Iterator: " + it.next());
            // Iterator: Apple
            // Iterator: Banana
            // Iterator: Cherry
            // Iterator: Mango
        }

        // 4. ListIterator (forward)
        java.util.ListIterator<String> listIt = list.listIterator();
        while (listIt.hasNext()) {
            System.out.println("ListIterator forward: " + listIt.next());
            // ListIterator forward: Apple
            // ListIterator forward: Banana
            // ListIterator forward: Cherry
            // ListIterator forward: Mango
        }

        // 5. ListIterator (backward)
        java.util.ListIterator<String> revListIt = list.listIterator(list.size());
        while (revListIt.hasPrevious()) {
            System.out.println("ListIterator backward: " + revListIt.previous());
            // ListIterator backward: Mango
            // ListIterator backward: Cherry
            // ListIterator backward: Banana
            // ListIterator backward: Apple
        }
        // 6. forEach (Java 8)
        list.forEach(e -> System.out.println("forEach lambda: " + e));
        // forEach lambda: Apple
        // forEach lambda: Banana
        // forEach lambda: Cherry
        // forEach lambda: Mango
        // 7. forEach with method reference
        list.forEach(System.out::println);
        // Apple
        // Banana
        // Cherry
        // Mango
        // 8. Streams forEach
        list.stream().forEach(e -> System.out.println("Stream forEach: " + e));
        // Stream forEach: Apple
        // Stream forEach: Banana
        // Stream forEach: Cherry
        // Stream forEach: Mango
        // 9. Streams parallel forEach (order may vary)
        list.parallelStream().forEach(e -> System.out.println("Parallel Stream: " + e));
        // Parallel Stream: Apple
        // Parallel Stream: Banana
        // Parallel Stream: Cherry
        // Parallel Stream: Mango
        // (⚠️ Order may change each run)
        // 10. Enumeration
        Vector<String> v = new Vector<>(list);
        Enumeration<String> en = v.elements();
        while (en.hasMoreElements()) {
            System.out.println("Enumeration: " + en.nextElement());
            // Enumeration: Apple
            // Enumeration: Banana
            // Enumeration: Cherry
            // Enumeration: Mango
        }
    }
}