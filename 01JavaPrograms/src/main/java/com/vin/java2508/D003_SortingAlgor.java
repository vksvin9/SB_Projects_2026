package com.vin.java2508;

import java.util.Arrays;
public class D003_SortingAlgor {
    // Bubble Sort: Repeatedly compares and swaps adjacent elements if they are in the wrong order, pushing the largest to the end each round
    static void bubbleSort(int[] arr) {
    	//arr = {5, 1, 9, 3, 7};
        for (int i = 0; i < arr.length - 1; i++) { // Outer loop (rounds)
            for (int j = 0; j < arr.length - i - 1; j++) { // Inner loop (compare neighbors)
                if (arr[j] > arr[j + 1]) { // If left element > right element                
                	System.out.println("i="+i+"arr[i]:"+arr[i]);
                	System.out.println("j="+j+"arr[j]:"+arr[j]);          
                	int temp = arr[j]; // Swap
                	System.out.println(temp);
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    System.out.println("i="+i+"arr[i]:"+arr[i]);
                	System.out.println("j="+j+"arr[j]:"+arr[j]);                	
                	System.out.println(temp);
                }
            }
        }
    }
    // Insertion Sort: Builds the final sorted array one item at a time by comparing each new element with those already sorted and inserting  it at the correct position.
    static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) { // Start from 2nd element
            int key = arr[i]; // Current element
            int j = i - 1;
            while (j >= 0 && arr[j] > key) { // Shift larger elements
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key; // Insert at right position
        }
    }
    // Selection Sort: Finds the minimum element and swaps it with the first element.
    static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) { // Outer loop
            int min = i; // Assume min at position i
            for (int j = i + 1; j < arr.length; j++) { // Find smaller
                if (arr[j] < arr[min]) min = j;
            }
            int temp = arr[i]; // Swap min with current i
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }
    // Merge Sort: Keep dividing the list into halves until each part has only one item. Then start joining the parts back together in sorted order
    static void mergeSort(int[] arr, int left, int right) {
        if (left < right) { // At least 2 elements
            int mid = (left + right) / 2; // Midpoint
            mergeSort(arr, left, mid); // Sort left half
            mergeSort(arr, mid + 1, right); // Sort right half
            merge(arr, left, mid, right); // Merge two halves
        }
    }
    static void merge(int[] arr, int l, int m, int r) {
        int[] temp = new int[r - l + 1]; // Temp array
        int i = l, j = m + 1, k = 0;
        while (i <= m && j <= r) { // Merge while both halves have elements
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= m) temp[k++] = arr[i++]; // Copy leftovers
        while (j <= r) temp[k++] = arr[j++];
        System.arraycopy(temp, 0, arr, l, temp.length); // Copy back
    }
    // Quick Sort: Pick one item (called a "pivot"). Put all smaller items before it, and all bigger items after it.  Then do the same with the left and right parts.
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) { // More than 1 element
            int pi = partition(arr, low, high); // Partition index
            quickSort(arr, low, pi - 1); // Sort left side
            quickSort(arr, pi + 1, high); // Sort right side
        }
    }
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Choose pivot as last element
        int i = low - 1; // Smaller element index
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) { // If smaller than pivot
                swap(arr, ++i, j); // Swap
            }
        }
        swap(arr, i + 1, high); // Put pivot in right place
        return i + 1; // Return pivot index
    }
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }
    // Counting Sort: Repeatedly builds a max heap, swaps the largest element to the end,  and re-heapifies the rest until the array is sorted.
    static void countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt(); // Find max
        int[] count = new int[max + 1]; // Count array
        for (int val : arr) count[val]++; // Count frequency
        int i = 0;
        for (int val = 0; val <= max; val++) {
            while (count[val]-- > 0) { // Fill back
                arr[i++] = val;
            }
        }
    }
	    // Heap Sort: Repeatedly builds a max heap, swaps the largest element to the end, and re-heapifies the rest until the array is sorted.
    static void heapSort(int[] a) {
        int n = a.length;
        for (int i = n / 2 - 1; i >= 0; i--) heapify(a, n, i); // Build heap
        for (int i = n - 1; i > 0; i--) {
            int t = a[0]; a[0] = a[i]; a[i] = t; // Swap max with end
            heapify(a, i, 0); // Heapify reduced heap
        }
    }
    static void heapify(int[] a, int n, int i) {
        while (true) {
            int largest = i, l = 2 * i + 1, r = 2 * i + 2;
            if (l < n && a[l] > a[largest]) largest = l; // Left > root
            if (r < n && a[r] > a[largest]) largest = r; // Right > largest
            if (largest == i) break; // Root is largest
            int t = a[i]; a[i] = a[largest]; a[largest] = t; // Swap
            i = largest;
        }
    }
    // Main Method: Runs all sorting algorithms
    public static void main(String[] args) {
        int[] arr = {5, 1, 9, 3, 7};
        int[] arr1 = arr.clone();
        bubbleSort(arr1);
        System.out.println("Bubble Sort: " + Arrays.toString(arr1)); // [1,3,5,7,9]
//        int[] arr2 = arr.clone();
//        insertionSort(arr2);
//        System.out.println("Insertion Sort: " + Arrays.toString(arr2)); // [1,3,5,7,9]
//        int[] arr3 = arr.clone();
//        selectionSort(arr3);
//        System.out.println("Selection Sort: " + Arrays.toString(arr3)); // [1,3,5,7,9]
//        int[] arr4 = arr.clone();
//        mergeSort(arr4, 0, arr4.length - 1);
//        System.out.println("Merge Sort: " + Arrays.toString(arr4)); // [1,3,5,7,9]
//        int[] arr5 = arr.clone();
//        quickSort(arr5, 0, arr5.length - 1);
//        System.out.println("Quick Sort: " + Arrays.toString(arr5)); // [1,3,5,7,9]
//        int[] arr6 = arr.clone();
//        countingSort(arr6);
//        System.out.println("Counting Sort: " + Arrays.toString(arr6)); // [1,3,5,7,9]
//        int[] arr7 = arr.clone();
//        heapSort(arr7);
//        System.out.println("Heap Sort: " + Arrays.toString(arr7)); // [1,3,5,7,9]
    }
}