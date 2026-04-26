package com.vin.java2508;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class D004b_ALCollectionMethods {
    public static void main(String[] args) {
        // 👉 ArrayList Methods Demo
        System.out.println("===== ArrayList Methods Demo =====");
        List<String> list = new ArrayList<>();
        // 1. add(E e) : adds element at the end
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        System.out.println("After add(): " + list); 
        // Output: [Apple, Banana, Cherry]
        // 2. add(int index, E element) : inserts at given index
        list.add(1, "Blueberry");
        System.out.println("After add(index, element): " + list); 
        // Output: [Apple, Blueberry, Banana, Cherry]
        // 3. addAll(Collection<? extends E> c) : adds all elements from another collection
        List<String> moreFruits = Arrays.asList("Mango", "Pineapple");
        list.addAll(moreFruits);
        System.out.println("After addAll(): " + list); 
        // Output: [Apple, Blueberry, Banana, Cherry, Mango, Pineapple]
        // 4. addAll(int index, Collection<? extends E> c) : inserts collection at given index
        list.addAll(2, Arrays.asList("Orange", "Grapes"));
        System.out.println("After addAll(index, collection): " + list); 
        // Output: [Apple, Blueberry, Orange, Grapes, Banana, Cherry, Mango, Pineapple]
        // 5. get(int index) : returns element at index
        System.out.println("Element at index 2: " + list.get(2)); 
        // Output: Orange
        // 6. set(int index, E element) : replaces element at index
        list.set(3, "Blackberry");
        System.out.println("After set(): " + list); 
        // Output: [Apple, Blueberry, Orange, Blackberry, Banana, Cherry, Mango, Pineapple]
        // 7. remove(Object o) : removes first occurrence of object
        list.remove("Apple");
        System.out.println("After remove(Object): " + list); 
        // Output: [Blueberry, Orange, Blackberry, Banana, Cherry, Mango, Pineapple]
        // 8. remove(int index) : removes element at index
        list.remove(0);
        System.out.println("After remove(index): " + list); 
        // Output: [Orange, Blackberry, Banana, Cherry, Mango, Pineapple]
        // 9. removeAll(Collection<?> c) : removes all elements in given collection
        list.removeAll(Arrays.asList("Mango", "Cherry"));
        System.out.println("After removeAll(): " + list); 
        // Output: [Orange, Blackberry, Banana, Pineapple]
        // 10. retainAll(Collection<?> c) : keeps only elements present in given collection
        list.retainAll(Arrays.asList("Orange", "Blackberry"));
        System.out.println("After retainAll(): " + list); 
        // Output: [Orange, Blackberry]
        // 11. clear() : removes all elements
        list.clear();
        System.out.println("List after clear(): " + list); 
        // Output: []
        // Reset list
        list.addAll(Arrays.asList("Dog", "Cat", "Parrot", "Cat", "Rabbit"));
        System.out.println("Reset List: " + list); 
        // Output: [Dog, Cat, Parrot, Cat, Rabbit]
        // 12. contains(Object o) : checks if element exists
        System.out.println("Contains Cat? " + list.contains("Cat")); 
        // Output: true
        // 13. containsAll(Collection<?> c) : checks if all elements exist
        System.out.println("Contains Dog and Rabbit? " + list.containsAll(Arrays.asList("Dog", "Rabbit"))); 
        // Output: true
        // 14. isEmpty() : checks if list is empty
        System.out.println("Is list empty? " + list.isEmpty()); 
        // Output: false
        // 15. size() : returns number of elements
        System.out.println("Size: " + list.size()); 
        // Output: 5
        // 16. indexOf(Object o) : first index of object
        System.out.println("First index of Cat: " + list.indexOf("Cat")); 
        // Output: 1
        // 17. lastIndexOf(Object o) : last index of object
        System.out.println("Last index of Cat: " + list.lastIndexOf("Cat")); 
        // Output: 3
        // 18. subList(int fromIndex, int toIndex) : returns a view between indexes
        System.out.println("Sublist (1,3): " + list.subList(1, 3)); 
        // Output: [Cat, Parrot]
        // 19. toArray() : converts list to Object array
        Object[] arr = list.toArray();
        System.out.println("Array: " + Arrays.toString(arr)); 
        // Output: [Dog, Cat, Parrot, Cat, Rabbit]
        // 20. toArray(T[] a) : converts list to typed array
        String[] strArr = list.toArray(new String[0]);
        System.out.println("String Array: " + Arrays.toString(strArr)); 
        // Output: [Dog, Cat, Parrot, Cat, Rabbit]
        // 21. equals(Object o) : checks equality with another list
        System.out.println("Equals another list? " + list.equals(Arrays.asList("Dog", "Cat", "Parrot", "Cat", "Rabbit"))); 
        // Output: true
        // 22. hashCode() : returns hash code of list
        System.out.println("HashCode: " + list.hashCode()); 
        // Output: (some integer, varies)
        // 23. sort(Comparator<? super E> c) : sorts list using comparator
        list.sort(Comparator.naturalOrder());
        System.out.println("Sorted List: " + list); 
        // Output: [Cat, Cat, Dog, Parrot, Rabbit]
        // 24. replaceAll(UnaryOperator<E> operator) : replaces each element using lambda
        list.replaceAll(e -> e.toUpperCase());
        System.out.println("Uppercase: " + list); 
        // Output: [CAT, CAT, DOG, PARROT, RABBIT]
        // 25. removeIf(Predicate<? super E> filter) : removes if condition matches
        list.removeIf(e -> e.startsWith("C"));
        System.out.println("After removeIf: " + list); 
        // Output: [DOG, PARROT, RABBIT]
        // 👉 Collections Utility Class Demo
        System.out.println("\n===== Collections Utility Class Methods Demo =====");
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 2));
        System.out.println("\nOriginal Numbers: " + numbers); 
        // Output: [5, 2, 8, 1, 9, 2]
        // 1. sort(List<T>) : sorts in natural order
        Collections.sort(numbers);
        System.out.println("Sorted (natural order): " + numbers); 
        // Output: [1, 2, 2, 5, 8, 9]
        // 2. sort(List<T>, Comparator<? super T>) : sorts using custom comparator
        Collections.sort(numbers, Comparator.reverseOrder());
        System.out.println("Sorted (reverse order): " + numbers); 
        // Output: [9, 8, 5, 2, 2, 1]
        // 3. binarySearch(List<T>, key) : searches in sorted list
        Collections.sort(numbers);
        int index = Collections.binarySearch(numbers, 8);
        System.out.println("Binary Search (find 8): Index = " + index); 
        // Output: 4
        // 4. reverse(List<?>) : reverses list order
        Collections.reverse(numbers);
        System.out.println("Reversed: " + numbers); 
        // Output: [9, 8, 5, 2, 2, 1]
        // 5. shuffle(List<?>) : random shuffle
        Collections.shuffle(numbers);
        System.out.println("Shuffled: " + numbers); 
        // Output: (random each run)
        // 6. swap(List<?>, int, int) : swaps elements at indexes
        Collections.swap(numbers, 0, numbers.size() - 1);
        System.out.println("After Swap (first <-> last): " + numbers); 
        // Output: (depends on shuffle result)
        // 7. fill(List<? super T>, T) : replaces all with given object
        Collections.fill(numbers, 7);
        System.out.println("After Fill (all 7s): " + numbers); 
        // Output: [7, 7, 7, 7, 7, 7]
        // Reset list
        numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 2));
        System.out.println("\nReset Numbers: " + numbers); 
        // Output: [5, 2, 8, 1, 9, 2]
        // 8. copy(List<? super T>, List<? extends T>) : copies source to destination
        List<Integer> dest = new ArrayList<>(Arrays.asList(new Integer[numbers.size()]));
        Collections.copy(dest, numbers);
        System.out.println("Copy: " + dest); 
        // Output: [5, 2, 8, 1, 9, 2]
        // 9. min(Collection<? extends T>) : smallest element
        System.out.println("Min: " + Collections.min(numbers)); 
        // Output: 1
        // 10. max(Collection<? extends T>) : largest element
        System.out.println("Max: " + Collections.max(numbers)); 
        // Output: 9
        // 11. frequency(Collection<?>, Object) : count occurrences
        System.out.println("Frequency of 2: " + Collections.frequency(numbers, 2)); 
        // Output: 2
        // 12. disjoint(Collection<?>, Collection<?>) : true if no common elements
        System.out.println("Disjoint with [100,200]? " + Collections.disjoint(numbers, Arrays.asList(100, 200))); 
        // Output: true
        // 13. emptyList() : immutable empty list
        System.out.println("Empty List: " + Collections.emptyList()); 
        // Output: []
        // 14. singleton(T o) : immutable set with one element
        System.out.println("Singleton: " + Collections.singleton("OnlyOne")); 
        // Output: [OnlyOne]
        // 15. nCopies(int n, T o) : immutable list with n copies
        System.out.println("nCopies(5,\"Hi\"): " + Collections.nCopies(5, "Hi")); 
        // Output: [Hi, Hi, Hi, Hi, Hi]
        // 16. synchronizedList(List<T>) : thread-safe list
        List<String> syncList = Collections.synchronizedList(new ArrayList<>(Arrays.asList("A", "B", "C")));
        System.out.println("Synchronized List: " + syncList); 
        // Output: [A, B, C]
        // 17. unmodifiableList(List<? extends T>) : read-only list
        List<String> unmodList = Collections.unmodifiableList(Arrays.asList("X", "Y", "Z"));
        System.out.println("Unmodifiable List: " + unmodList); 
        // Output: [X, Y, Z]
    }
}
