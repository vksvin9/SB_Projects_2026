package com.vin.java2508;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Z002_FunctionalInterface {
	public static void main(String[] args) {
		//User Define Functional Interface covered in previous example
		//System Defined Functional Interface
		//Predicate - returns boolean, use for condition 
		Predicate<Integer> pre = in -> in%2 ==0;
		System.out.println("pre = " + pre.test(10));
		//Function - returns boolean, use for transformation
		Function<String, Integer> fun = str -> str.length();
		System.out.println("fun = " +fun.apply("Vikas"));
		//Consumer - return nothing, use for action like logging
		Consumer<String> con = str -> System.out.println("Hello " + str); 
		con.accept("Vikas");
		//Supplier - return value but no input, used for providing values 
		Supplier<Float> sup = () -> 3.14F;
		System.out.println("sup = " +sup.get());
		//Unary Operator - accept one input and return one output of same datatype
		UnaryOperator<String> une = str -> str.concat(" Vinayak");
		System.out.println("une = " + une.apply("Vikas"));
		//Binary Operator - accept two input and return one output of same datatype
		BinaryOperator<String> bin = (str1, str2) -> str1.concat(" " + str2);
		System.out.println("bin = " + bin.apply("Vikas", "Vinayak"));
		//Bi-Predicate - two input
		BiPredicate<String, Integer> bip = (str, in) -> str.length() == in;
		System.out.println("bip = " + bip.test("Vikas",5));
		//Bi-Function - two input
		BiFunction<String, String, Integer> bif = (str1, str2) -> str1.length()+str2.length();
		System.out.println("bif = " +bif.apply("Vikas", "Vinayak"));
		//Bi-Consumer - two input
		BiConsumer<String, String> bic = (str1, str2) -> System.out.println(("Hello " + str1 + " " + str2));
		bic.accept("Vikas", "Vinayak");
		//IntPredicate
		IntPredicate inp = in -> in%2 !=0;
		System.out.println("inp = " + inp.test(10));
		
	}
}
