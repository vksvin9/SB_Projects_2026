package com.vin.java2604;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class A01Arrays {
    public static void main(String[] args) {

        // Different Ways to Create Arrays - multiple initialization approaches

        // 1. Static initialization - declare and assign values directly
        int[] arr1 = {10, 20, 30, 40, 50};
        System.out.println(Arrays.toString(arr1)); 
        // Output: [10, 20, 30, 40, 50]

        // 2. Dynamic initialization with fixed size - create empty array then assign
        String[] arr2 = new String[5]; 
        arr2[0] = "zero"; 
        arr2[1] = "one"; 
        arr2[2] = "two"; 
        arr2[3] = "three"; 
        arr2[4] = "four";

        // 3. Anonymous array - create without reference variable
        int[] arr3 = new int[]{100, 200, 300};

        // 4. Using Arrays.copyOf - clone existing array
        int[] arr4 = Arrays.copyOf(arr1, arr1.length);

        // 5. Using Arrays.setAll - initialize using lambda/function
        int[] arr5 = new int[5];
        Arrays.setAll(arr5, i -> i * 2); // [0, 2, 4, 6, 8]

        // 6. Using IntStream (range) - generate sequence
        int[] arr6 = IntStream.range(1, 6).toArray(); // [1, 2, 3, 4, 5]

        // 7. Multi-dimensional array - 2D array initialization
        int[][] arr7 = {{1,2}, {3,4}, {5,6}};
        System.out.println(Arrays.deepToString(arr7)); 
        // Output: [[1, 2], [3, 4], [5, 6]]


        // Different Ways to Print Arrays - multiple printing techniques
        System.out.println("\n--- Printing Arrays ---");

        // 1. Using Arrays.toString() - simple readable format
        System.out.println(Arrays.toString(arr1)); 
        // Output: [10, 20, 30, 40, 50]

        // b. Using for loop - index-based iteration
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println(); 
        // Output: 10 20 30 40 50 

        // 3. Using enhanced for loop - direct element iteration
        for (int n : arr1) {
            System.out.print(n + " ");
        }
        System.out.println(); 
        // Output: 10 20 30 40 50 

        // 4. Using Streams (boxed) - convert to List
        System.out.println(Arrays.stream(arr1).boxed().collect(Collectors.toList())); 
        // Output: [10, 20, 30, 40, 50]

        // 5. Using Streams (forEach) - functional iteration
        Arrays.stream(arr1).forEach(n -> System.out.print(n + " "));
        System.out.println(); 
        // Output: 10 20 30 40 50

        Arrays.stream(arr1).forEach(System.out::println);
        // Output:
        // 10
        // 20
        // 30
        // 40
        // 50

        // 6. Using StringJoiner - custom formatted string
        StringJoiner joiner = new StringJoiner(", ");
        for (int n : arr1) {
            joiner.add(String.valueOf(n));
        }
        System.out.println("[" + joiner + "]"); 
        // Output: [10, 20, 30, 40, 50]

        // 7. Using Collectors.joining - stream-based join
        String result = Arrays.stream(arr1)
                              .mapToObj(String::valueOf)
                              .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(result); 
        // Output: [10, 20, 30, 40, 50]

        // 8. Printing multi-dimensional arrays - nested iteration
        System.out.println(Arrays.deepToString(arr7)); 
        // Output: [[1, 2], [3, 4], [5, 6]]

        for(int[] arr : arr7){
            for (int a : arr) {
                System.out.println(a);
            }
        }
        // Output:
        // 1
        // 2
        // 3
        // 4
        // 5
        // 6
    }
}