package com.vin.java2503;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.LongUnaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class A002_LambdaExpression {
	public static void main(String[] args) {
		List<String> strList = Arrays.asList("Red", "Green", "Blue", "PINK");
		ArrayList<Integer> intList = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
		getSum();
		checkEmptyString();
		getLength();
		toUpperCase(strList);
		filterOddEvenUsingStream(intList);
		sort(strList);
		getAvarage(intList);
		getUnique(intList);
		getFactorial(5);
		checkPrime(8);
		concateString("abc","xyz");
		getSum(intList);
		countWord("Vikas is good MenConstructorExample");
		checkPalindrome1("nayan");
		checkPalindrome2("nayan");
		containsWord(strList, "Red");
		checkPerfectsquare(36);
		sortStudent();
		getAverage(intList);
		toUpperLowerCase(strList);
		sumOddEven(intList);
		removeDuplicate(intList);
		getWordCountStartWith(strList, 'B');
		sortStringArray(strList);
		getMaxMin(intList);
	}

	// ------------------------------------------------------------------------ //
	interface SumCalculator {
		int sum(int a, int b);
	}

	private static void getSum() {
		SumCalculator sumCalculator = (x, y) -> x + y;
		int sum = sumCalculator.sum(10, 20);
		System.out.println(sum);
	}

	// ------------------------------------------------------------------------ //
	private static void checkEmptyString() {
		Predicate<String> isEmptyString = str -> ((String) str).isEmpty();
		String str1 = ""; // empty string
		String str2 = "Java lambda expression!"; // non-empty string
		System.out.println("String 1:" + str1);
		System.out.println("String 1 is empty: " + isEmptyString.test(str1));
		System.out.println("\nString 2:" + str2);
		System.out.println("String 2 is empty: " + isEmptyString.test(str2));
	}

	// ------------------------------------------------------------------------ //
	private static void getLength() {
		Function<String, Integer> fun = (str) -> ((String) str).length();
		System.out.println(fun.apply("Vikas"));
	}

	// ------------------------------------------------------------------------ //
	private static void toUpperCase(List<String> strList) {
		System.out.println("Original strings:" + strList);
		// Convert strings to lowercase using lambda expression
		strList.replaceAll(str -> str.toLowerCase());
		System.out.println("Lowercase strings:" + strList);
		// Convert strings to uppercase using lambda expression
		strList.replaceAll(str -> str.toUpperCase());
		System.out.println("Uppercase strings:" + strList);
	}

	// ------------------------------------------------------------------------ //
	private static void filterOddEvenUsingStream(ArrayList<Integer> intList) {
		System.out.println(intList);
		List<Integer> odd = intList.stream().filter(i -> i % 2 != 0).collect(Collectors.toList());
		System.out.println("Odd -> " + odd);
		List<Integer> even = intList.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
		System.out.println("Even -> " + even);
	}

	// ------------------------------------------------------------------------ //
	private static void sort(List<String> strList) {
		System.out.println("Original strings: " + strList);
		strList.sort((str1, str2) -> str1.compareTo(str2));
		System.out.println("Sorted strings: " + strList);
	}

	// --------------strList------------------------------------------------ //
	private static void getAvarage(ArrayList<Integer> intList) {
		double average = intList.stream().mapToInt(i -> i).average().orElse(0);
		System.out.println("Average: " + average);
	}

	// ------------------------------------------------------------------------ //
	private static void getUnique(ArrayList<Integer> intList) {
		List<Integer> unique_nums = new ArrayList<>();
		intList.stream().distinct().forEach(unique_nums::add);
		System.out.println("List elements without duplicates: " + unique_nums);
	}

	// ------------------------------------------------------------------------ //
	private static void getFactorial(int in) {
		LongUnaryOperator factorial = n -> {
			long result = 1;
			for (long i = 1; i <= n; i++) {
				result *= i;
			}
			return result;
		};
		long factorial_result = factorial.applyAsLong(in);
		System.out.println(factorial_result);

	}
	// ------------------------------------------------------------------------ //

	private static void checkPrime(int in) {
		Predicate<Integer> is_Prime = n -> {
			if (n <= 1) {
				return false;
			}
			for (int i = 2; i <= n / 2; i++) {
				if (n % i == 0) {
					return false;
				}
			}
			return true;
		};
		boolean bool = is_Prime.test(in);
		System.out.println("Is Prime : " + bool);
	}

	// ------------------------------------------------------------------------ //
	private static void concateString(String str1, String str2) {
		BiFunction<String, String, String> fun = (x, y) -> x + y;
		String apply = fun.apply(str1, str2);
		System.out.println(apply);
	}

	// ------------------------------------------------------------------------ //
	private static void getSum(ArrayList<Integer> intList) {
		// Calculate the sum of the list elements using lambda expression
		int sum = intList.stream().reduce(0, (x, y) -> x + y).intValue();
		System.out.println(sum);
	}

	// ------------------------------------------------------------------------ //
	@FunctionalInterface
	interface WordCounter {
		int countWords(String text);
	}

	private static void countWord(String str) {
		WordCounter wordCounter = s -> s.split("\\s+").length;
		int countWords = wordCounter.countWords(str);
		System.out.println("Count word : " + countWords);

	}

	// ------------------------------------------------------------------------ //
	private static void checkPalindrome1(String str) {
		Predicate<String> isPalindrome = x -> {
			char[] chs = x.toCharArray();
			String rev = "";
			for (int i = chs.length - 1; i >= 0; i--) {
				rev = rev + chs[i];
			}
			if (str.equalsIgnoreCase(rev)) {
				return true;
			} else {
				return false;
			}
		};
		System.out.println("isPalindrome : " + isPalindrome.test(str));
	}

	// ------------------------------------------------------------------------ //
	private static void checkPalindrome2(String str) {
		Predicate<String> isPalindrome = x -> {
			String reversed = new StringBuilder(str).reverse().toString();
			return str.equals(reversed);
		};
		System.out.println("isPalindrome : " + isPalindrome.test(str));
	}

	// ------------------------------------------------------------------------ //
	private static void containsWord(List<String> strList, String searchColor) {
		Predicate<String> containsWord = word -> word.equals(searchColor);
		boolean flag = strList.stream().anyMatch(containsWord);
		System.out.println("Is the word " + searchColor + " present in the list? " + flag);
	}

	// ------------------------------------------------------------------------ //
	private static void checkPerfectsquare(int i) {
		Predicate<Integer> isPerfectSquare = n -> {
			int sqrt = (int) Math.sqrt(n);
			return sqrt * sqrt == n;
		};
		boolean result1 = isPerfectSquare.test(i);
		System.out.println(i + " is a perfect square? " + result1);
	}

	// ------------------------------------------------------------------------ //
	private static void sortStudent() {
		List<Student> student_list = new ArrayList<>();
		student_list.add(new Student("Adriana Jamie", 15, "X"));
		student_list.add(new Student("Felix Uisdean", 15, "X"));
		student_list.add(new Student("Conceicao Palmira", 14, "X"));
		student_list.add(new Student("Jair Camila", 14, "X"));
		student_list.add(new Student("Micaela Rosana", 15, "X"));

		// Student details
		System.out.println("Student details:" + student_list);
		for (Student Student : student_list) {
			System.out.println(Student.getName() + " - " + Student.getAge() + " - " + Student.getSClass());
		}

		// Sort the list based on age using lambda expression
		student_list.sort(Comparator.comparing(Student::getName));

		// Print the sorted list
		System.out.println("\nSorted list based on Student Name:");
		for (Student Student : student_list) {
			System.out.println(Student.getName() + " - " + Student.getAge() + " - " + Student.getSClass());
		}
	}
	// ------------------------------------------------------------------------ //
	// ------------------------------------------------------------------------ //
	private static void getWordCountStartWith(List<String> strList, char ch) {
		System.out.println("Original list of strings (colors): " + strList);
		char startingLetter = 'B';
		long ctr = strList.stream().filter(s -> s.startsWith(String.valueOf(startingLetter))).count();
		System.out.println("Number of colors starting with '" + startingLetter + "': " + ctr);
	}

	private static void getAverage(ArrayList<Integer> intList) {
		double average1 = intList.stream().mapToInt(i -> i).average().orElse(0);
		System.out.println("Average: " + average1);
		double average2 = intList.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
		System.out.println("Average: " + average2);
	}

	private static void toUpperLowerCase(List<String> strList) {
		List<String> uc = strList.stream().map(String::toUpperCase).collect(Collectors.toList());
		List<String> lc = strList.stream().map(String::toLowerCase).collect(Collectors.toList());
		System.out.println("ori : " + strList);
		System.out.println("uc : " + uc);
		System.out.println("lc : " + lc);
		System.out.println("Original strings:" + strList);
		// Convert strings to lowercase using lambda expression
		strList.replaceAll(str -> str.toLowerCase());
		System.out.println("Lowercase strings:" + strList);
		// Convert strings to uppercase using lambda expression
		strList.replaceAll(str -> str.toUpperCase());
		System.out.println("Uppercase strings:" + strList);
	}

	private static void sumOddEven(ArrayList<Integer> intList) {
		int sumOfEvens = intList.stream().filter(num -> num % 2 == 0).mapToInt(Integer::intValue).sum();
		System.out.println("Sum of even numbers: " + sumOfEvens);
		int sumOfOdds = intList.stream().filter(num -> num % 2 != 0).mapToInt(Integer::intValue).sum();
		System.out.println("Sum of odd numbers: " + sumOfOdds);
	}

	private static void removeDuplicate(ArrayList<Integer> intList) {
		System.out.println("Original List of numbers: " + intList);
		List<Integer> distinctNumbers = intList.stream().distinct().collect(Collectors.toList());
		System.out.println("After removing duplicates from the said list: " + distinctNumbers);
	}

	private static void sortStringArray(List<String> strList) {
		System.out.println("Original List of strings: " + strList);
		List<String> ascendingOrder = strList.stream().sorted().collect(Collectors.toList());
		List<String> descendingOrder = strList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println("Sorted in Ascending Order: " + ascendingOrder);
		System.out.println("Sorted in Descending Order: " + descendingOrder);
	}

	private static void getMaxMin(ArrayList<Integer> intList) {
		System.out.println("Original list of numbers: " + intList);
		Integer max_val = intList.stream().max(Integer::compare).orElse(null);
		Integer min_val = intList.stream().min(Integer::compare).orElse(null);
		System.out.println("Maximum value of the said list: " + max_val);
		System.out.println("Minimum value of the said list: " + min_val);
		Integer secondSmallest = intList.stream().distinct().sorted().skip(1).findFirst().orElse(null);
		Integer secondLargest = intList.stream().distinct().sorted((a, b) -> Integer.compare(b, a)).skip(1).findFirst().orElse(null);
		System.out.println("Second smallest element: " + secondSmallest);
		System.out.println("Second largest element: " + secondLargest);
	}

	
}

class Student {
	private String name, SClass;
	private int age;

	public Student(String name, int age, String SClass) {
		this.name = name;
		this.age = age;
		this.SClass = SClass;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getSClass() {
		return SClass;
	}
}