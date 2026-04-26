package com.vin.java2503;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class A003_Java8_Programs {
	// Java 8 Interview Coding Questions And Answers :
	public static void main(String[] args) {
		List<Integer> listOfIntegers = Arrays.asList(71, 18, 42, 21, 67, 32, 95, 14, 56, 87, 10, 10, 10);
		System.out.println("Input String :- " + listOfIntegers);
		List<String> listOfStrings = Arrays.asList("Java", "Python", "C#", "Java", "Kotlin", "Python");
		System.out.println("Input String :- " + listOfStrings);

//		//Check Odd OR Even
//		Predicate<Integer> p = a -> a%2==0;
//		boolean test = p.test(9);
//		System.out.println("Given Number is "+(test?"Even":"Odd"));

//		//Get List of odd number from 1 to 10
//		System.out.println("Even numbers:");
//        IntStream.rangeClosed(1, 10).filter(n -> n % 2 == 0).forEach(System.out::println);
//        System.out.println("\nOdd numbers:");
//        IntStream.rangeClosed(1, 10).filter(n -> n % 2 != 0).forEach(System.out::println);

//		//Given a list of integers, separate odd and even numbers collect using partition by?
//		List<Integer> oddsSimple = listOfIntegers.stream().filter(i -> i % 2 != 0).collect(Collectors.toList());
//		System.out.println(oddsSimple);
//		Map<Boolean, List<Integer>> partitionBy = listOfIntegers.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));
//		System.out.println(partitionBy);
//		for (boolean bool : partitionBy.keySet()) {
//			String str = (bool)?"Even :- ":"Odd :- ";
//			System.out.println(str + partitionBy.get(bool));
//		}

//		// How do you remove duplicate elements from a list using Java 8 streams?
//		List<Integer> distinct = listOfIntegers.stream().distinct().collect(Collectors.toList());
//		System.out.println("distinct->"+distinct);
//		Set<Integer> set = listOfIntegers.stream().collect(Collectors.toSet());
//		System.out.println("set->"+set);

		// How do you find frequency of each element in an array or a list?
//		Map<String, Long> stationeryCountMap = listOfStrings.stream()
//				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//		System.out.println(stationeryCountMap);

		// How do you find frequency of each character in a string using Java 8 streams?
//		String inputString = "Java Concept Of The Day";
//		Map<Character, Long> charCountMap = inputString.chars().mapToObj(c -> (char) c)
//				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//		System.out.println(charCountMap);

		// Sort the given a list of strings
//		List<String> sorted = listOfStrings.stream().sorted().collect(Collectors.toList());
//		System.out.println("Sorted String :- "+sorted);

		// Given a list of strings, sort them according to increasing order of their length?
//		System.out.println("Sorted as Length :- ");
//		listOfStrings.stream().sorted(Comparator.comparing(String::length)).forEach(System.out::println);

		// How do you sort the given list of decimals in reverse order?
//		List<Double> decimalList = Arrays.asList(12.45, 23.58, 17.13, 42.89, 33.78, 71.85, 56.98, 21.12);
//		decimalList.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
//		System.out.println("=====================================");
//		decimalList.stream().sorted(Comparator.reverseOrder()).forEach(d -> System.out.println(d));
//		System.out.println("=====================================");

		// Given a list of strings, join the strings with ‘{‘ as prefix, ‘}’ as suffix and ‘,’ as delimiter?
//		String joinedString = listOfStrings.stream().collect(Collectors.joining(", ", "{", "}"));
//		System.out.println("Joined String :-" +joinedString);

		// From the given list of integers, print the numbers which are multiples of 5?
//		List<Integer> muliple5 = listOfIntegers.stream().filter(i -> i%5 == 0).collect(Collectors.toList());
//		System.out.println("Muliple of 5 :-" +muliple5);
//		listOfIntegers.stream().filter(i -> i % 5 == 0).forEach(System.out::println);

		// Given a list of integers, find maximum and minimum of those numbers?
//		Integer max = listOfIntegers.stream().max(Comparator.naturalOrder()).get();
//		System.out.println("Max = "+max);
//		Integer min = listOfIntegers.stream().min(Comparator.naturalOrder()).get();
//		System.out.println("Max = "+min);

		// Find second largest number in an integer array?
//        Integer secondLargestNumber = listOfIntegers.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
//        System.out.println(secondLargestNumber);

		// How do you get three maximum numbers and three minimum numbers from the  given list of integers?
//		System.out.println("Minimum 3 Numbers");
//        listOfIntegers.stream().sorted().limit(3).forEach(System.out::println);
//        System.out.println("Maximum 3 Numbers");
//        listOfIntegers.stream().sorted(Comparator.reverseOrder()).limit(3).forEach(System.out::println);

		// How do you find sum of first 10 natural numbers?
//		int sum = IntStream.range(1, 11).sum();
//        System.out.println(sum);

		// Print first 10 even numbers
//		IntStream.rangeClosed(1, 10).map(i -> i * 2).forEach(System.out::println);

		// How do you merge two unsorted arrays into single sorted array using Java8 streams?
//		int[] a = new int[] { 4, 2, 7, 1, 2 };
//		int[] b = new int[] { 8, 3, 9, 5 };
//		int[] c = IntStream.concat(Arrays.stream(a), Arrays.stream(b)).sorted().toArray();
//		System.out.println(Arrays.toString(c));

		// How do you merge two unsorted arrays into single sorted array without duplicates?
//		int[] a = new int[] { 4, 2, 5, 1 };
//		int[] b = new int[] { 8, 1, 9, 5 };
//		int[] c = IntStream.concat(Arrays.stream(a), Arrays.stream(b)).sorted().distinct().toArray();
//		System.out.println(Arrays.toString(c));

		// ***Reverse an integer array***
//		int[] array = new int[] { 5, 1, 7, 3, 9, 6 };
//		int[] reversedArray = IntStream.rangeClosed(1, array.length).map(i -> array[array.length - i]).toArray();
//		System.out.println(Arrays.toString(reversedArray));

		// Java 8 program to check if two strings are anagrams or not?
//		String s1 = "RaceCar";
//        String s2 = "CarRace";
//        s1 = Stream.of(s1.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
//        s2 = Stream.of(s2.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
//        System.out.println((s1.equals(s2))?"Anagram":"Not Anagram");

		// ***Find sum of all digits of a number in Java 8?***
//		int num = 12345;
//		Integer sum = Stream.of(String.valueOf(num).split("")).collect(Collectors.summingInt(Integer::parseInt));
//		System.out.println("Sum :- " + sum);

		// How do you find common elements between two arrays?
//		List<Integer> list1 = Arrays.asList(71, 21, 34, 89, 56, 28);
//		List<Integer> list2 = Arrays.asList(12, 56, 17, 21, 94, 34);
//		list1.stream().filter(list2::contains).forEach(System.out::println);

		// Reverse each word of a string using Java 8 streams?
//		String str = "Java Concept Of The Day";
//		String reversedStr = Arrays.stream(str.split(" ")).map(word -> new StringBuffer(word).reverse())
//				.collect(Collectors.joining(" "));
//		System.out.println(reversedStr);

		// ***How do you find the most repeated element in an array?***
//		Map<String, Long> elementCountMap = listOfStrings.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//		Entry<String, Long> mostFrequentElement = elementCountMap.entrySet().stream().max(Map.Entry.comparingByValue()).get();
//		System.out.println("Most Frequent Element : " + mostFrequentElement.getKey());
//		System.out.println("Count : " + mostFrequentElement.getValue());

		// Palindrome program using Java 8 streams
//		String str = "ROTATOR";
//        boolean isItPalindrome = IntStream.range(0, str.length()/2).noneMatch(i -> str.charAt(i) != str.charAt(str.length() - i -1));
//        System.out.println(isItPalindrome?" is a palindrome":" is not a palindrome");

		// Given a list of strings, find out those strings which start with a number?
//		List<String> listOfStrings1 = Arrays.asList("One", "2wo", "3hree", "Four", "5ive", "Six");
//		listOfStrings1.stream().filter(str -> Character.isDigit(str.charAt(0))).forEach(System.out::println);

		// How do you extract duplicate elements from an array?
//		List<Integer> listOfIntegers2 = Arrays.asList(111, 222, 333, 111, 555, 333, 777, 222);
//		Set<Integer> uniqueElements = new HashSet<>();
//		Set<Integer> duplicateElements = listOfIntegers2.stream().filter(i -> !uniqueElements.add(i))
//				.collect(Collectors.toSet());
//		System.out.println(duplicateElements);

		// Print duplicate characters in a string?
//		String inputString = "Java Concept Of The Day".replaceAll("\\s+", "").toLowerCase();
//		Set<String> uniqueChars = new HashSet<>();
//		Set<String> duplicateChars = Arrays.stream(inputString.split("")).filter(ch -> !uniqueChars.add(ch))
//				.collect(Collectors.toSet());
//		System.out.println(duplicateChars);

		// Find first repeated character in a string?
//		String inputString = "Java Concept Of The Day".replaceAll("\\s+", "").toLowerCase();
//		Map<String, Long> charCountMap = Arrays.stream(inputString.split(""))
//				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
//		String firstRepeatedChar = charCountMap.entrySet().stream().filter(entry -> entry.getValue() > 1)
//				.map(entry -> entry.getKey()).findFirst().get();
//		System.out.println(firstRepeatedChar);

		// Find first non-repeated character in a string?
//		String inputString = "Java Concept Of The Day".replaceAll("\\s+", "").toLowerCase();
//		Map<String, Long> charCountMap = Arrays.stream(inputString.split(""))
//				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
//		String firstNonRepeatedChar = charCountMap.entrySet().stream().filter(entry -> entry.getValue() == 1)
//				.map(entry -> entry.getKey()).findFirst().get();
//		System.out.println(firstNonRepeatedChar);

		// Fibonacci series
//		Stream.iterate(new int[] { 0, 1 }, f -> new int[] { f[1], f[0] + f[1] }).limit(10).map(f -> f[0])
//				.forEach(i -> System.out.print(i + " "));

		// How do you get last element of an array?
//        String lastElement = listOfStrings.stream().skip(listOfStrings.size() - 1).findFirst().get();
//        System.out.println(lastElement);

		// Find the age of a person in years if the birthday has given?
//        LocalDate birthDay = LocalDate.of(1985, 01, 23);
//        LocalDate today = LocalDate.now();
//        System.out.println(ChronoUnit.YEARS.between(birthDay, today));
	}
}
