package com.vin.java2508;

import java.util.*;                  // Import Arrays, Spliterator, Collectors, etc.
import java.util.stream.*;           // Import IntStream
import java.util.StringJoiner;       // Import StringJoiner
import java.util.function.IntConsumer; // Needed for Spliterator method reference
public class D001_Array {
    public static void main(String[] args) {
        // ===== Array Creation =====
        // 1. Static initialization: description: Define array with values directly at declaration.
        // usage: int[] arr = {1,2,3};
        int[] arr1 = {10, 20, 30, 40, 50}; 
        // 2. Dynamic initialization: description: Create array with size only, values default to 0.
        // usage: int[] arr = new int[5];
        int[] arr2 = new int[5]; 
        for (int i = 0; i < arr2.length; i++) arr2[i] = i + 1; 
        // [1,2,3,4,5]
        // 3. Anonymous array: description: Array created inline, usually as argument or one-time use.
        // usage: new int[]{1,2,3}
        int[] arr3 = new int[]{100, 200, 300}; 
        // 4. clone(): description: Creates shallow copy of array.
        // usage: int[] copy = arr.clone();
        int[] arr4 = arr1.clone(); 
        // 5. Arrays.copyOf(): description: Copies given array into new array.
        // usage: Arrays.copyOf(arr, newLength);
        int[] arr5 = Arrays.copyOf(arr1, arr1.length);
        // 6. Arrays.setAll(): description: Initialize array elements using lambda (index -> value).
        // usage: Arrays.setAll(arr, i -> i*2);
        // Create an integer array of size 5 and Initially: [0, 0, 0, 0, 0] (default int values)
        int[] arr6 = new int[5]; 
        // Arrays.setAll(array, lambda): For each index i, the lambda provides the value to store at arr6[i]
        Arrays.setAll(arr6, i -> i * 2); 
        // i = 0 -> 0*2 = 0
        // i = 1 -> 1*2 = 2
        // i = 2 -> 2*2 = 4
        // i = 3 -> 3*2 = 6
        // i = 4 -> 4*2 = 8
        // [0,2,4,6,8]
        // 7. IntStream: description: Stream-based creation of arrays with ranges.
        // usage: IntStream.rangeClosed(1,5).toArray();
        int[] arr7 = IntStream.rangeClosed(1, 5).toArray(); 
        // 8. Multi-dimensional array: description: Rectangular arrays with equal row sizes.
        // usage: int[][] arr = {{1,2},{3,4}};
        int[][] arr8 = {{1,2}, {3,4}, {5,6}};
        // 9. Jagged array: description: Multi-dimensional with different row lengths.
        // usage: int[][] jagged = {{1},{2,3}};
        int[][] jagged = {{1,2}, {3,4,5}, {6}};
        // ===== Different Ways to Print Arrays =====
        System.out.println("\n--- Printing Arrays ---");
        // a) Arrays.toString(): description: Converts array to string representation.
        // usage: Arrays.toString(arr)
        System.out.println(Arrays.toString(arr1));
        // b) Classic for loop: description: Iterate using index.
        // usage: for(int i=0;i<arr.length;i++){}
        for (int i = 0; i < arr1.length; i++) System.out.print(arr1[i] + " ");
        System.out.println(); 
        // c) Enhanced for loop: description: Shorthand iteration for arrays.
        // usage: for(int n : arr){}
        for (int n : arr1) System.out.print(n + " ");
        System.out.println(); 
        // d) Streams (forEach): description: Use streams to iterate.
        // usage: Arrays.stream(arr).forEach(...)
        Arrays.stream(arr1).forEach(n -> System.out.print(n + " "));
        System.out.println(); 
        // e) Streams (boxed to List): description: Convert primitive array to List<Integer>.
        // usage: Arrays.stream(arr).boxed().collect(Collectors.toList())
        System.out.println(Arrays.stream(arr1).boxed().collect(Collectors.toList())); 
        // f) Parallel Stream: description: Runs elements in parallel (order not guaranteed).
        // usage: Arrays.stream(arr).parallel().forEach(...)
        Arrays.stream(arr1).parallel().forEach(n -> System.out.print(n + " "));
        System.out.println(); 
        // g) StringJoiner: description: Build custom string with delimiters.
        // usage: new StringJoiner(", ").add("a").add("b")
        StringJoiner joiner = new StringJoiner(", ");
        for (int n : arr1) joiner.add(String.valueOf(n));
        System.out.println("[" + joiner + "]");
        // h) Collectors.joining: description: Join stream values into single string.
        // usage: Arrays.stream(arr).mapToObj(...).collect(Collectors.joining(","))
        String joined = Arrays.stream(arr1)
                              .mapToObj(String::valueOf)
                              .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(joined);
        // i) Multi-dimensional printing
        // description: Ways to print 2D arrays (nested loop, enhanced loop, deepToString).
        // usage: Arrays.deepToString(matrix)
        // i.i) Classic nested loop
        for (int i = 0; i < arr8.length; i++) {           
            for (int j = 0; j < arr8[i].length; j++) {    
                System.out.print(arr8[i][j] + " ");
            }
            System.out.println();
        }
        // i.ii) Enhanced for loop
        for (int[] row : arr8) {        
            for (int val : row) {       
                System.out.print(val + " ");
            }
            System.out.println();
        }
        // i.iii) Arrays.deepToString() (rectangular)
        System.out.println(Arrays.deepToString(arr8));
        // i.iv) Arrays.deepToString() (jagged)
        System.out.println(Arrays.deepToString(jagged));
        // ===== Arrays Utility Methods =====
        System.out.println("\n--- Arrays Utility Methods ---");
        int[] arr = {5, 2, 8, 1, 3};
        // 1. sort(): description: Sorts array in ascending order.
        Arrays.sort(arr);
        System.out.println("Sorted: " + Arrays.toString(arr));
        // 2. parallelSort(): description: Uses multiple threads to sort faster for large arrays.
        int[] arrP = {9, 7, 5, 3, 1};
        Arrays.parallelSort(arrP);
        System.out.println("Parallel Sorted: " + Arrays.toString(arrP));
        // 3. binarySearch(): description: Searches sorted array, returns index or -insertionPoint.
        int index = Arrays.binarySearch(arr, 3);
        System.out.println("Index of 3: " + index);
        // 4. copyOf(): description: Creates copy of array with given new length.
        int[] copy = Arrays.copyOf(arr, arr.length);
        System.out.println("Copied: " + Arrays.toString(copy));
        // 5. fill(): description: Replace all elements with given value.
        Arrays.fill(copy, 7);
        System.out.println("Filled: " + Arrays.toString(copy));
        // 6. equals(): description: Compare arrays element by element.
        System.out.println("Equals: " + Arrays.equals(arr, copy));
//        //Java8+// 7. compare(): description: Lexicographically compare two arrays.
//        int[] arr2C = {5, 2, 8, 1, 3};
//        System.out.println("Compare: " + Arrays.compare(arr, arr2C));
//        //Java8+// 8. mismatch(): description: Finds first mismatching index.
//        System.out.println("Mismatch index: " + Arrays.mismatch(arr, arr2C));
        // 9. hashCode(): description: Returns hash based on array values.
        System.out.println("HashCode: " + Arrays.hashCode(arr));
        // 10. deepEquals(): description: Compare multi-dimensional arrays deeply.
        int[][] m1 = {{1,2}, {3,4}}, m2 = {{1,2}, {3,4}};
        System.out.println("Deep Equals: " + Arrays.deepEquals(m1, m2));
        // 11. deepHashCode(): description: Hash code for nested arrays.
        System.out.println("Deep HashCode: " + Arrays.deepHashCode(m1));
        // 12. stream(): description: Convert array to stream for operations.
        int sum = Arrays.stream(arr).sum();
        int max = Arrays.stream(arr).max().getAsInt();
        double avg = Arrays.stream(arr).average().getAsDouble();
        System.out.println("Sum=" + sum + ", Max=" + max + ", Avg=" + avg);
        // 13. toString(): description: Convert array to string form.
        System.out.println("toString: " + Arrays.toString(arr));
        // 14. Spliterator: description: Advanced iterator for arrays, allows sequential/parallel traversal.
        // usage: Spliterator.OfInt sp = Arrays.spliterator(arr); sp.forEachRemaining((int n)->{...});
        System.out.print("Spliterator: ");
        Spliterator.OfInt sp = Arrays.spliterator(arr);
        sp.forEachRemaining((int n) -> System.out.print(n + " ")); 
        System.out.println();
    }
}