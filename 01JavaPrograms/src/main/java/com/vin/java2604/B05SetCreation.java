package com.vin.java2604;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class B05SetCreation {

    enum Type { A, B }

    public static void main(String[] args) {

        // ================= HASHSET =================

        // 1. Default constructor - Unordered, no duplicates
        Set<String> hashSet1 = new HashSet<>();
        hashSet1.add("Apple");
        System.out.println("HashSet Default: " + hashSet1); // [Apple]

        // 2. With initial capacity
        Set<String> hashSet2 = new HashSet<>(10);
        hashSet2.add("Banana");
        System.out.println("HashSet Capacity: " + hashSet2); // [Banana]

        // 3. From collection
        Set<String> hashSet3 = new HashSet<>(Arrays.asList("A", "B", "C"));
        System.out.println("HashSet from collection: " + hashSet3); // [A, B, C]

        // 4. Using Set.of() (Java 9+ immutable)
        Set<String> hashSet4 = Set.of("X", "Y", "Z");
        System.out.println("Set.of (Immutable): " + hashSet4);

        // 5. Using Stream API
        Set<String> hashSet5 = Stream.of("P", "Q", "R")
                .collect(Collectors.toSet());
        System.out.println("HashSet Stream: " + hashSet5);

        // 6. Using copy constructor
        Set<String> hashSet6 = new HashSet<>(hashSet1);
        System.out.println("HashSet Copy: " + hashSet6);

        // 7. Using Collections.addAll()
        Set<String> hashSet7 = new HashSet<>();
        Collections.addAll(hashSet7, "One", "Two");
        System.out.println("HashSet addAll: " + hashSet7);

        // ================= LINKEDHASHSET =================

        // 8. Default constructor - Maintains insertion order
        Set<String> linkedHashSet1 = new LinkedHashSet<>();
        linkedHashSet1.add("Apple");
        linkedHashSet1.add("Banana");
        System.out.println("LinkedHashSet Default: " + linkedHashSet1); // ordered

        // 9. From collection
        Set<String> linkedHashSet2 = new LinkedHashSet<>(Arrays.asList("A", "B", "C"));
        System.out.println("LinkedHashSet from collection: " + linkedHashSet2);

        // 10. Copy constructor
        Set<String> linkedHashSet3 = new LinkedHashSet<>(hashSet3);
        System.out.println("LinkedHashSet Copy: " + linkedHashSet3);

        // 11. Using Collections.addAll()
        Set<String> linkedHashSet4 = new LinkedHashSet<>();
        Collections.addAll(linkedHashSet4, "X", "Y");
        System.out.println("LinkedHashSet addAll: " + linkedHashSet4);

        // ================= TREESET =================

        // 12. Default constructor - Sorted set
        Set<String> treeSet1 = new TreeSet<>();
        treeSet1.add("Banana");
        treeSet1.add("Apple");
        System.out.println("TreeSet Default: " + treeSet1); // sorted

        // 13. From collection
        Set<String> treeSet2 = new TreeSet<>(Arrays.asList("C", "A", "B"));
        System.out.println("TreeSet from collection: " + treeSet2); // sorted

        // 14. Using custom comparator
        Set<String> treeSet3 = new TreeSet<>(Comparator.reverseOrder());
        treeSet3.add("A");
        treeSet3.add("C");
        treeSet3.add("B");
        System.out.println("TreeSet Comparator: " + treeSet3); // reverse sorted

        // 15. Using Stream API
        Set<String> treeSet4 = Stream.of("X", "Z", "Y")
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("TreeSet Stream: " + treeSet4); // sorted

        // 16. Copy constructor
        Set<String> treeSet5 = new TreeSet<>(treeSet1);
        System.out.println("TreeSet Copy: " + treeSet5);

        // 17. Using NavigableSet (TreeSet feature)
        NavigableSet<String> treeSet6 = new TreeSet<>();
        treeSet6.add("A");
        treeSet6.add("B");
        System.out.println("NavigableSet (TreeSet): " + treeSet6);
    }
}