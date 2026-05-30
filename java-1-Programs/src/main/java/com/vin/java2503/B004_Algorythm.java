package com.vin.java2503;

import java.util.Arrays;
import java.util.TreeSet;

public class B004_Algorythm {
	public static void main(String[] args) {
		int[] intArr = new int[]{9,8,7,1,2,3,4,6,5,5,4,3};
//		linearSearch(intArr, 7);
//		binarySearchUsingArraysClass(intArr, 7);
//		binarySearch2(intArr, 7);
//		sortingUsingArraysClass(new int[]{9,8,7,1,2,3,4,6,5});
//		uniqueSort(intArr);
//		bubbleSortAsc(intArr);
//		bubbleSortDesc(intArr);
	}	  

	public static void linearSearch(int intArr[], int val) {
		boolean b = false;
		for (int i = 0; i < intArr.length; i++) {
			for (int j = i + 1; j < intArr.length; j++) {
				if (intArr[i] == val) {
					b = true;
					System.out.println(val + " found at index " + i);
					break;
				}
			}
		}
		if (!b)
			System.out.println(val + " not Found");
    }
	
	private static void binarySearchUsingArraysClass(int[] intArr, int val) {
		Arrays.sort(intArr);
		System.out.print("Sorted Array = ");
		for (int a : intArr)
			System.out.print(a + " ");
		int res = Arrays.binarySearch(intArr, val);
		if (res != -1)
			System.out.println("\nValue '" + val + "' found at index " + res + " in gievn Array");
		else
			System.out.println("\nValue '" + val + "' not found in given Array.");
	}
	
	private static void binarySearch2(int[] intArr, int val) {
        Arrays.sort(intArr); // Sort the array before searching
        System.out.print("Sorted Array = "+Arrays.toString(intArr));
        boolean found = false;
        int start = 0, end = intArr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2; // Avoid integer overflow
            if (val == intArr[mid]) {
                found = true;
                System.out.println("\nValue '" + val + "' found at index " + mid + " in gievn Array");
                break;
            } else if (val < intArr[mid]) {
                end = mid - 1; // Fix: Move end pointer
            } else {
                start = mid + 1; // Move start pointer
            }
        }

        if (!found) {
            System.out.println(val + " not found");
        }
	}
        
	private static void uniqueSort(int[] intArr) {
		TreeSet<Integer> t = new TreeSet<>();
		for (int i = 0; i < intArr.length; i++) {
			t.add(intArr[i]);
		}
		System.out.println(t);
		
	}

	private static void sortingUsingArraysClass(int[] intArr) {
		//***Arrays.sort & Arrays.toString
		System.out.print("input -> ");
		for(int i : intArr) {
			System.out.print(i + " ");
		}
		Arrays.sort(intArr);
		System.out.println("\nSorted -> "+Arrays.toString(intArr));
	}
	
	public static void bubbleSortAsc(int[] a) {
		System.out.print("\nInput -> ");
		for (int a1 : a)
			System.out.print(a1 + " ");
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] > a[j]) {
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		System.out.print("\nOutput -> ");
		for (int a1 : a)
			System.out.print(a1 + " ");
	}

	public static void bubbleSortDesc(int[] a) {
		System.out.print("\nInput -> ");
		for (int a1 : a)
			System.out.print(a1 + " ");
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] < a[j]) {
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		System.out.print("\nOutput -> ");
		for (int a1 : a)
			System.out.print(a1 + " ");
	}
}
