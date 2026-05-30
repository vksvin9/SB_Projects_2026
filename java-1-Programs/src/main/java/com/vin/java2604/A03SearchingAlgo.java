package com.vin.java2604;

public class A03SearchingAlgo {

    public static void main(String[] args) {

        int[] arr = {10, 20, 30, 40, 50};

        int key1 = 30;
        int key2 = 25;

        // Linear Search Test
        System.out.println("Linear Search (30): " + linearSearch(arr, key1)); // 2
        System.out.println("Linear Search (25): " + linearSearch(arr, key2)); // -1

        // Binary Search Test (array must be sorted)
        System.out.println("Binary Search (30): " + binarySearch(arr, key1)); // 2
        System.out.println("Binary Search (25): " + binarySearch(arr, key2)); // -1
    }

    // 1. Linear Search - Checks each element sequentially until match is found or array ends
    // Time Complexity: O(n)
    // Works on both sorted and unsorted arrays
    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) return i;
        }
        return -1;
    }

    // 2. Binary Search - Divides sorted array into halves and searches efficiently
    // Time Complexity: O(log n)
    // Condition: Array must be sorted
    public static int binarySearch(int[] arr, int key) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == key) return mid;
            else if (arr[mid] < key) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}
