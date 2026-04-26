package com.vin.java2503;

import java.util.Arrays;

public class B003a_Arrays {
    public static void main(String[] args) {
        // 1. Declaration and Initialization
        int[] numbers = {5, 2, 8, 1, 3};
        String[] fruits = {"Apple", "Banana", "Mango"};

        // 2. toString() - Convert array to string representation
        System.out.println("Array toString: " + Arrays.toString(numbers));

        // 3. sort() - Sort array in ascending order
        Arrays.sort(numbers);
        System.out.println("Sorted numbers: " + Arrays.toString(numbers));

        // 4. copyOf() - Copy first n elements
        int[] copy = Arrays.copyOf(numbers, 3);
        System.out.println("Copied first 3: " + Arrays.toString(copy));

        // 5. copyOfRange() - Copy a range
        int[] rangeCopy = Arrays.copyOfRange(numbers, 1, 4);
        System.out.println("Copied range (1-4): " + Arrays.toString(rangeCopy));

        // 6. equals() - Compare arrays
        int[] numbers2 = {1, 2, 3, 5, 8};
        System.out.println("Arrays equal: " + Arrays.equals(numbers, numbers2));

        // 7. fill() - Fill array with a value
        int[] filledArray = new int[5];
        Arrays.fill(filledArray, 7);
        System.out.println("Filled with 7: " + Arrays.toString(filledArray));

        // 8. binarySearch() - Search for element (requires sorted array)
        int index = Arrays.binarySearch(numbers, 3);
        System.out.println("Index of 3: " + index);

        // 9. setAll() - Set values with a lambda (Java 8+)
        int[] squares = new int[5];
        Arrays.setAll(squares, i -> i * i);
        System.out.println("Squares: " + Arrays.toString(squares));

        // 10. parallelSort() - Sort using multiple threads (Java 8+)
        int[] largeArray = {9, 2, 7, 4, 6};
        Arrays.parallelSort(largeArray);
        System.out.println("Parallel sorted: " + Arrays.toString(largeArray));

        // 11. stream() - Convert array to stream (Java 8+)
        System.out.println("Sum using stream: " + Arrays.stream(numbers).sum());

        // 12. deepToString() - For multi-dimensional arrays
        int[][] matrix = {{1, 2}, {3, 4}};
        System.out.println("Matrix: " + Arrays.deepToString(matrix));

        // 13. deepEquals() - Compare multi-dimensional arrays
        int[][] matrix2 = {{1, 2}, {3, 4}};
        System.out.println("Matrix equals: " + Arrays.deepEquals(matrix, matrix2));

        // 14. mismatch() - First differing index (Java 9+)
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 4};
//        System.out.println("Mismatch index: " + Arrays.mismatch(a, b));
    }
}
