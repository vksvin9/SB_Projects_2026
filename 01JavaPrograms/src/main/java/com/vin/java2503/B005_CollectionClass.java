package com.vin.java2503;
import java.util.*;

public class B005_CollectionClass {
    public static void main(String[] args) {
        // Create sample collections
        Collection<String> collection1 = new ArrayList<>();
        Collection<String> collection2 = Arrays.asList("Apple", "Banana", "Cherry");

        // 1. add() - Add a single element
        collection1.add("Apple");
        collection1.add("Banana");
        System.out.println("After add: " + collection1);

        // 2. addAll() - Add all elements from another collection
        collection1.addAll(collection2);
        System.out.println("After addAll: " + collection1);

        // 3. remove() - Remove a single element
        collection1.remove("Banana");
        System.out.println("After remove: " + collection1);

        // 4. removeAll() - Remove all elements that match another collection
        collection1.removeAll(Arrays.asList("Apple", "Cherry"));
        System.out.println("After removeAll: " + collection1);

        // 5. retainAll() - Retain only elements that exist in another collection
        collection1.addAll(Arrays.asList("Grapes", "Orange", "Apple"));
        collection1.retainAll(Arrays.asList("Apple", "Orange"));
        System.out.println("After retainAll: " + collection1);

        // 6. clear() - Remove all elements
        collection1.clear();
        System.out.println("After clear: " + collection1);

        // 7. contains() - Check if collection contains an element
        System.out.println("Contains 'Apple': " + collection2.contains("Apple"));

        // 8. containsAll() - Check if collection contains all elements of another collection
        System.out.println("ContainsAll ['Apple', 'Banana']: " + collection2.containsAll(Arrays.asList("Apple", "Banana")));

        // 9. isEmpty() - Check if collection is empty
        System.out.println("Is collection1 empty: " + collection1.isEmpty());

        // 10. size() - Get number of elements
        System.out.println("Size of collection2: " + collection2.size());

        // 11. iterator() - Get iterator to loop through collection
        System.out.print("Iterating collection2: ");
        Iterator<String> iterator = collection2.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        // 12. toArray() - Convert collection to array
        Object[] array = collection2.toArray();
        System.out.println("To array: " + Arrays.toString(array));

        // 13. forEach() - Loop using lambda (Java 8+)
        System.out.print("Using forEach: ");
        collection2.forEach(item -> System.out.print(item + " "));
        System.out.println();

        // 14. stream() - Create a stream from collection (Java 8+)
        long count = collection2.stream().filter(s -> s.startsWith("B")).count();
        System.out.println("Elements starting with 'B': " + count);
    }
}
