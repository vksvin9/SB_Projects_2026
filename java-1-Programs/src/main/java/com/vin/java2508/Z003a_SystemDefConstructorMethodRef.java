package com.vin.java2508;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Z003a_SystemDefConstructorMethodRef {
	public static void main(String[] args) {
		// 1. Static method reference (Integer::parseInt)
		List<String> numString = Arrays.asList("5", "4", "1", "2", "3");
		System.out.println("numString"+numString);
		List<Integer> numIntMethodRef = numString.stream().map(Integer::parseInt).collect(Collectors.toList());
		System.out.println("numIntMethodRef"+numIntMethodRef);
		List<Integer> numIntLambdaExp =numString.stream().map(in -> Integer.parseInt(in)).collect(Collectors.toList());
		System.out.println("numIntMethodRef"+numIntLambdaExp);
		// 2. Instance method reference (arbitrary object - String::toUpperCase)
		List<String> names = Arrays.asList("Dipranjana", "Reshmi", "Vikas", "Anuradha", "Thanulal", "Vinayak");
		System.out.println("names"+names);
		List<String> nameToUCMethodRef = names.stream().map(String::toUpperCase).collect(Collectors.toList());
		System.out.println("nameToUCMethodRef"+nameToUCMethodRef);
		List<String> nameToUCLamdaExp = names.stream().map(str -> str.toUpperCase()).collect(Collectors.toList());
		System.out.println("nameToUCLamdaExp"+nameToUCLamdaExp);
		// 3. String::length
		List<Integer> nameLenMethodRef = names.stream().map(String::length).collect(Collectors.toList());
		System.out.println("nameLenMethodRef"+nameLenMethodRef);
		List<Integer> nameLenLamdaExp = names.stream().map(str -> str.length()).collect(Collectors.toList());
		System.out.println("nameLenLamdaExp"+nameLenLamdaExp);
		// 4. forEach with System.out::println
		System.out.println("forEach MethodRef");
		names.stream().forEach(System.out::println);
		System.out.println("forEach LambdaExp");
		names.stream().forEach(str -> System.out.println(str));
		// 5. Integer::compare
		List<Integer> nums = Arrays.asList(5,4,1,2,3);
		Integer maxMethodRef = nums.stream().max(Integer::compare).orElse(null);
		System.out.println("maxMethodRef"+maxMethodRef);
		Optional<Integer> maxLambdaExp = nums.stream().max( (a,b) -> a.compareTo(b));
		System.out.println("maxLambdaExp"+maxLambdaExp);
		// 6. Constructor reference (StringBuilder::new)
		Supplier<StringBuilder> sb1 = StringBuilder::new;
		StringBuilder sbConstructorRef = sb1.get();
		sbConstructorRef.append("Vikas").append("Thanulal").append("Vinayak");
		System.out.println("sbConstructorRef"+sbConstructorRef);
		Supplier<StringBuilder> sb2 = () -> new StringBuilder();
		StringBuilder sbLambdaExp = sb2.get();
		sbLambdaExp.append("Vikas").append("Thanulal").append("Vinayak");
		System.out.println("sbLambdaExp"+sbLambdaExp);
		// 7. Collect into ArrayList
		ArrayList<String> aLConstructorRef = names.stream().collect(Collectors.toCollection(ArrayList::new));
		System.out.println("aLConstructorRef"+aLConstructorRef);
		ArrayList<String> aLLambdaExp = names.stream().collect(Collectors.toCollection(() -> new ArrayList<>()));
		System.out.println("aLLambdaExp"+aLLambdaExp);
		// 8. String::new
		Supplier<String> strSupM = String::new;
		String strMethodRef = strSupM.get();
		System.out.println("strMethodRef Empty String"+strMethodRef);
		Supplier<String> strSupL = () -> new String();
		String strLamdaExp = strSupL.get();
		System.out.println("strLambdaExp Empty String"+strLamdaExp);
		// 9. Array constructor (ArrayList to String[])
		String[] strArrConstructorRef = names.stream().toArray(String[]::new);
		System.out.println("strArrConstructorRef"+strArrConstructorRef);
		String[] strArrLambdaExp = names.stream().toArray(size -> new String[size]);
		System.out.println("strArrLambdaExp"+strArrLambdaExp);
		// 10. Sorting with method reference
		List<String> sortedNamesMethodRef = names.stream().sorted(String::compareToIgnoreCase).collect(Collectors.toList());
		System.out.println("sortedNamesMethodRef"+sortedNamesMethodRef);
		List<String> sortedNamesLambdExp = names.stream().sorted((str1,str2) -> str1.compareToIgnoreCase(str2)).collect(Collectors.toList());
		System.out.println("sortedNamesLambdExp"+sortedNamesLambdExp);
		// 11. GroupingBy
		Map<Integer, List<String>> gbMethodRef = names.stream().collect(Collectors.groupingBy(String::length));
		System.out.println("gbMethodRef"+gbMethodRef);
		Map<Integer, List<String>> gbLambdaExp = names.stream().collect(Collectors.groupingBy(str -> str.length()));
		System.out.println("gbLambdaExp"+gbLambdaExp);
		// 12. Reducing with method reference
		Integer sumMethodRef = nums.stream().collect(Collectors.reducing(Integer::sum)).orElse(null);
		System.out.println("sumMethodRef"+sumMethodRef);
		Integer sumLambdaExp = nums.stream().collect(Collectors.reducing((a,b) -> a+b)).orElse(null);
		System.out.println("sumLambdaExp"+sumLambdaExp);
		// 13. Using Optional max
		Optional<String> maxLenNameMethodRef = names.stream().max(Comparator.comparing(String::length));
		System.out.println("maxLenNameMethodRef"+maxLenNameMethodRef);
		Optional<String> maxLenNameLambdaExp = names.stream().min((str1, str2) -> Integer.compare(str1.length(),str2.length()));
		System.out.println("maxLenNameLambdaExp"+maxLenNameLambdaExp);
		// 14. Collecting into Set
		Set<String> nameSetSimple = names.stream().collect(Collectors.toSet());
		System.out.println("Names in a Set (simple): " + nameSetSimple);
		Set<String> nameSet = names.stream().collect(Collectors.toCollection(HashSet::new));
        System.out.println("Names in a Set (method ref): " + nameSet);
        Set<String> nameSetLambda = names.stream().collect(Collectors.toCollection(() -> new HashSet<>()));
        System.out.println("Names in a Set (lambda): " + nameSetLambda);
		// 15. Mapping to StringBuilder
        List<StringBuilder> sbMethodRef = names.stream().map(StringBuilder::new).collect(Collectors.toList());
        System.out.println("sbMethodRef"+sbMethodRef);
        List<StringBuilder> sbLambdaExp1 = names.stream().map(str -> new StringBuilder()).collect(Collectors.toList());
        System.out.println("sbLambdaExp1"+sbLambdaExp1);
		// 16. Map replaceAll
		// Method reference
		// 17. toArray
		// 18. FlatMap
		// 19. Comparator.comparing
		// 20. Counting elements

	}
}
