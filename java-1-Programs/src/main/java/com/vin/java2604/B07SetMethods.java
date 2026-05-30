package com.vin.java2604;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;

public class B07SetMethods {

    public static void main(String[] args) {

        Set<String> set = new HashSet<>();

        // 1. add(E e) - Add element
        set.add("Apple");
        set.add("Banana");
        System.out.println("add(): " + set); // [Apple, Banana]

        // 2. addAll(Collection c) - Add all elements
        set.addAll(Arrays.asList("Cherry", "Dates"));
        System.out.println("addAll(): " + set); // added multiple

        // 3. remove(Object o) - Remove element
        set.remove("Apple");
        System.out.println("remove(): " + set); // Apple removed

        // 4. removeAll(Collection c) - Remove matching elements
        set.removeAll(Arrays.asList("Banana"));
        System.out.println("removeAll(): " + set); // Banana removed

        // 5. retainAll(Collection c) - Keep only matching elements
        set.retainAll(Arrays.asList("Cherry"));
        System.out.println("retainAll(): " + set); // only Cherry remains

        // 6. contains(Object o) - Check if element exists
        System.out.println("contains('Cherry'): " + set.contains("Cherry")); // true

        // 7. containsAll(Collection c) - Check multiple elements
        System.out.println("containsAll(): " + set.containsAll(Arrays.asList("Cherry"))); // true

        // 8. size() - Number of elements
        System.out.println("size(): " + set.size()); // count

        // 9. isEmpty() - Check if empty
        System.out.println("isEmpty(): " + set.isEmpty()); // false

        // 10. clear() - Remove all elements
        set.clear();
        System.out.println("clear(): " + set); // {}

        // Refill set
        set.addAll(Arrays.asList("A", "B", "C"));

        // 11. iterator() - Get iterator
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            System.out.println("iterator(): " + it.next()); // iteration
        }

        // 12. toArray() - Convert to Object array
        Object[] arr1 = set.toArray();
        System.out.println("toArray(): " + Arrays.toString(arr1));

        // 13. toArray(T[] a) - Convert to typed array
        String[] arr2 = set.toArray(new String[0]);
        System.out.println("toArray(T[]): " + Arrays.toString(arr2));

        // 14. equals(Object o) - Compare sets
        Set<String> set2 = new HashSet<>(set);
        System.out.println("equals(): " + set.equals(set2)); // true

        // 15. hashCode() - Hash of set
        System.out.println("hashCode(): " + set.hashCode());

        // ================= JAVA 8+ DEFAULT METHODS =================

        // 16. forEach(Consumer) - Iterate
        set.forEach(s -> System.out.println("forEach: " + s));

        // 17. removeIf(Predicate) - Remove conditionally
        set.removeIf(s -> s.equals("A"));
        System.out.println("removeIf(): " + set); // A removed

        // 18. spliterator() - Advanced iteration
        Spliterator<String> spliterator = set.spliterator();
        spliterator.forEachRemaining(s -> System.out.println("spliterator: " + s));

        // 19. stream() - Sequential stream
        set.stream().forEach(s -> System.out.println("stream: " + s));

        // 20. parallelStream() - Parallel stream
        set.parallelStream().forEach(s -> System.out.println("parallelStream: " + s));
    }
}