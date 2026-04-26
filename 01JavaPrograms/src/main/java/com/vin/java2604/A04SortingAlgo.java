package com.vin.java2604;

import java.util.Arrays;

public class A04SortingAlgo {
	public static void main(String[] args) {
		int[] arr1 = { 5, 3, 8, 4, 2 };
		bubbleSort(arr1);
		System.out.println("Bubble Sort: " + Arrays.toString(arr1)); // [2,3,4,5,8]
		int[] arr2 = { 5, 3, 8, 4, 2 };
		insertionSort(arr2);
		System.out.println("Insertion Sort: " + Arrays.toString(arr2)); // [2,3,4,5,8]
		int[] arr3 = { 5, 3, 8, 4, 2 };
		selectionSort(arr3);
		System.out.println("Selection Sort: " + Arrays.toString(arr3)); // [2,3,4,5,8]
		int[] arr4 = { 5, 3, 8, 4, 2 };
		mergeSort(arr4, 0, arr4.length - 1);
		System.out.println("Merge Sort: " + Arrays.toString(arr4)); // [2,3,4,5,8]
		int[] arr5 = { 5, 3, 8, 4, 2 };
		quickSort(arr5, 0, arr5.length - 1);
		System.out.println("Quick Sort: " + Arrays.toString(arr5)); // [2,3,4,5,8]
		int[] arr6 = { 5, 3, 8, 4, 2 };
		countingSort(arr6);
		System.out.println("Counting Sort: " + Arrays.toString(arr6)); // [2,3,4,5,8]
		int[] arr7 = { 5, 3, 8, 4, 2 };
		heapSort(arr7);
		System.out.println("Heap Sort: " + Arrays.toString(arr7)); // [2,3,4,5,8]
	}

	// Bubble Sort: Repeatedly compares and swaps adjacent elements if they are in
	// the wrong order, pushing the largest to the end each round (O(n²))
	public static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++)
			for (int j = 0; j < arr.length - i - 1; j++)
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
	}

	// 2. Insertion Sort: Builds the final sorted array one item at a time by
	// comparing each new element with those already sorted and inserting it at the
	// correct position. (O(n²))
	public static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int key = arr[i], j = i - 1;
			while (j >= 0 && arr[j] > key)
				arr[j + 1] = arr[j--];
			arr[j + 1] = key;
		}
	}

	// 3. Selection Sort - Finds minimum element and places it at correct position
	// (O(n²))
	public static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++)
				if (arr[j] < arr[min])
					min = j;
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}
	}

	// 4. Keep dividing the list into halves until each part has only one item. Then
	// start joining the parts back together in sorted order (O(n log n))
	public static void mergeSort(int[] arr, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);
			merge(arr, left, mid, right);
		}
	}

	public static void merge(int[] arr, int l, int m, int r) {
		int[] temp = new int[r - l + 1];
		int i = l, j = m + 1, k = 0;
		while (i <= m && j <= r)
			temp[k++] = (arr[i] <= arr[j]) ? arr[i++] : arr[j++];
		while (i <= m)
			temp[k++] = arr[i++];
		while (j <= r)
			temp[k++] = arr[j++];
		System.arraycopy(temp, 0, arr, l, temp.length);
	}

	// 5.  Quick Sort: Pick one item (called a "pivot"). Put all smaller items
	// before it, and all bigger items after it. Then do the same with the left and
	// right parts. (O(n log n) avg)
	public static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}

	public static int partition(int[] arr, int low, int high) {
		int pivot = arr[high], i = low - 1;
		for (int j = low; j < high; j++)
			if (arr[j] < pivot)
				swap(arr, ++i, j);
		swap(arr, i + 1, high);
		return i + 1;
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// 6. Counting Sort - Counts frequency and rebuilds sorted array (O(n + k))
	public static void countingSort(int[] arr) {
		int max = Arrays.stream(arr).max().getAsInt();
		int[] count = new int[max + 1];
		for (int val : arr)
			count[val]++;
		int i = 0;
		for (int val = 0; val <= max; val++)
			while (count[val]-- > 0)
				arr[i++] = val;
	}

	// 7. Heap Sort - Build max heap and extract max repeatedly (O(n log n))
	public static void heapSort(int[] arr) {
		int n = arr.length;
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(arr, n, i);
		for (int i = n - 1; i > 0; i--) {
			swap(arr, 0, i);
			heapify(arr, i, 0);
		}
	}

	public static void heapify(int[] arr, int n, int i) {
		while (true) {
			int largest = i, l = 2 * i + 1, r = 2 * i + 2;
			if (l < n && arr[l] > arr[largest])
				largest = l;
			if (r < n && arr[r] > arr[largest])
				largest = r;
			if (largest == i)
				break;
			swap(arr, i, largest);
			i = largest;
		}
	}
}