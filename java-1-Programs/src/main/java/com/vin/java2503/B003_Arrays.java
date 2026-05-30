package com.vin.java2503;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class B003_Arrays {
	public static void main(String[] args) {
		int[] intArr = { 2, 3, 4, 5, 3, 6, 3, 8, 4, 9, 4, 9, 4, 8, 7, 0, 1 };
//		printOddPlacedNum(intArr);
//		printInPair(intArr);
//		printConsdcutivePair(intArr);
//		getMax(intArr);
//		getMin(intArr);
//		getArrayClone(intArr);
//		getArrayCopy(intArr);
//		removeDuplicates(intArr);
//		rotateArrayLeft(intArr, 3);
//		sliceArray1(intArr, 2, 5);
//		sliceArray2(intArr, 2, 5);
//		getUniqueByHashSet(intArr);
//		getUniqueByHashMap(intArr);
//		getUniqueBySorting(intArr);
		
	}

	private static void printOddPlacedNum(int[] intArr) {
		for (int i = 1; i < intArr.length; i = i + 2) {
			System.out.println(intArr[i]);
		}
	}

	private static void printInPair(int[] intArr) {
		for (int i = 0; i < intArr.length; i++) {
			for (int j = i + 1; j < intArr.length; j++) {
				System.out.println("(" + intArr[i] + ", " + intArr[j] + ")");
			}
		}

	}

	private static void printConsdcutivePair(int[] intArr) {
		int n = intArr.length;
		for (int i = 0; i < n - 1; i += 2) { // Step by 2 to form pairs
			System.out.println(intArr[i] + " & " + intArr[i + 1]);
		}
		// If the array has an odd number of elements, print the last element separately
		if (n % 2 != 0) {
			System.out.println(intArr[n - 1] + " has no pair.");
		}
	}

	public static void getMax(int[] intArr) {
		int max = 0;
		for (int i = 0; i < intArr.length; i++) {
			if (intArr[i] > max) {
				max = intArr[i];
			}
		}
		System.out.println("max -> " + max);
	}

	public static void getMin(int[] intArr) {
		int min = intArr[0];
		for (int i = 0; i < intArr.length; i++) {
			if (intArr[i] < min) {
				min = intArr[i];
			}
		}
		System.out.println("min -> " + min);
	}
	
	private static void getUniqueByHashSet(int[] intArr) {
		LinkedHashSet<Object> lhs = new LinkedHashSet<>();
		System.out.print("Duplicate = ");
		for (int i : intArr) {
			if (!lhs.add(i)) {
				System.out.print(i + " ");
			}
		}
		System.out.print("\nUnique byHashSet = " + lhs);
	}

	private static void getUniqueByHashMap(int[] intArr) {
		LinkedHashMap<Integer, Integer> lhm = new LinkedHashMap<>();
		for (int i : intArr) {
			if(lhm.containsKey(i)) {
				int j = lhm.get(i);
				lhm.put(i, j+1);
			}else {
				lhm.put(i, 1);
			}
		}
		System.out.println("Duplicate with Count :- "+lhm);
		System.out.print("Unique by HashMap :- ");
		for(int i : lhm.keySet()) {
			System.out.print(i+ " ");
		}
	}

	private static void getUniqueBySorting(int[] intArr) {
		Arrays.sort(intArr);
		ArrayList<Integer> uniqueList = new ArrayList<>();
		System.out.print("Duplicates :- ");
		for(int i = 0; i<intArr.length-1; i++) {
			if(intArr[i]!=intArr[i+1]) {
				uniqueList.add(intArr[i]);
			}
			else {
				System.out.print(intArr[i]+" ");
			}
		}
		System.out.print("\nUnique bySorting :- "+uniqueList);
	}

	private static void getArrayClone(int[] intArr) {
		System.out.println("Arr :- " + Arrays.toString(intArr));
		int[] cpArr = new int[intArr.length];
		cpArr = intArr.clone();
		System.out.println("ArrClone :- " + Arrays.toString(cpArr));
	}

	private static void getArrayCopy(int[] intArr) {
		System.out.println("Arr :- " + Arrays.toString(intArr));
		for (int i : intArr) {
			System.out.print(i + " ");
		}
		int[] cpArr = new int[intArr.length];
		int j = 0;
		for (int i = 0; i < intArr.length; i++) {
			cpArr[j++] = intArr[i];
		}
		System.out.println("ArrClone :- " + Arrays.toString(cpArr));
	}

	private static void removeDuplicates(int[] intArr) {
		System.out.println("Duplicate & unsorted : " + Arrays.toString(intArr));
		Arrays.sort(intArr);
		int len = intArr.length;
		int[] temp = new int[len];
		int j = 0;
		for (int i = 0; i < len - 1; i++) {
			if (intArr[i] != intArr[i + 1]) {
				temp[j++] = intArr[i];
			}
		}
		System.out.print("Without Duplicate & sorted: ");
		for (int i = 0; i < j; i++) {
			System.out.print(temp[i] + " ");
		}
	}

	private static void rotateArrayLeft(int[] intArr, int range) {
		System.out.println("input :- " + Arrays.toString(intArr));
		for (int i = 0; i < range; i++) {
			for (int j = i + 1; j < intArr.length; j++) {
				if (intArr[i] < intArr[j]) {
					int temp = intArr[i];
					intArr[i] = intArr[j];
					intArr[j] = temp;
				}
			}
		}
		System.out.println("output :- " + Arrays.toString(intArr));
	}

	private static void sliceArray1(int[] intArr, int i, int j) {
		System.out.println("input :- " + Arrays.toString(intArr));
		int[] arr2 = Arrays.copyOfRange(intArr, i, j);
		System.out.println("output :- " + Arrays.toString(arr2));
	}

	private static void sliceArray2(int[] intArr, int i, int j) {
		System.out.println("input :- " + Arrays.toString(intArr));
		int[] arr2 = new int[j - i];
		int l = 0;
		for (int k = i; k < j; k++) {
			arr2[l++] = intArr[k];
		}
		System.out.println("output :- " + Arrays.toString(arr2));
	}

}
