package com.vin.java2508;

public class D002_SearchingAlgo {
    // Linear Search: Goes through each element in the array one by one and checks if it matches the target value
    static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == key) return i; // return index if found
        return -1; // not found
    }
    // Binary Search: It repeatedly divides the sorted arrays in half and compares the middle value to the target 
    // [If equal > found || If target is smaller >> search the left half || If larger → search the right half]
    static int binarySearch(int[] arr, int key) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == key) return mid;        // found
            else if (arr[mid] < key) left = mid + 1; // search right
            else right = mid - 1;                   // search left
        }
        return -1; // not found
    }
    // Main Method: Test both search algorithms
    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10, 12, 14}; // sorted array
        int key1 = 10, key2 = 7;
        // Linear Search
        int result1 = linearSearch(arr, key1);
        System.out.println("Linear Search: key=" + key1 + " → index=" + result1); 
        // Output: Linear Search: key=10 → index=4
        // Binary Search (sorted array)
        int result2 = binarySearch(arr, key1);
        System.out.println("Binary Search: key=" + key1 + " → index=" + result2); 
        // Output: Binary Search: key=10 → index=4
        // Not found case
        int result3 = binarySearch(arr, key2);
        System.out.println("Binary Search: key=" + key2 + " → index=" + result3); 
        // Output: Binary Search: key=7 → index=-1
    }
}