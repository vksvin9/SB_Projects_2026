package com.vin.java2508;

@FunctionalInterface
interface Adder{
	int add(int a, int b);
}
class Adderimpl implements Adder{
	@Override
	public int add(int a, int b) {
		return a+b;
	}
}
public class Z001_Lambda {
	public static void main(String[] args) {
		//It is a implementation of functional interface
		//a) By implementing interface in a separate class
		Adder adder1 = new Adderimpl();
		System.out.println("Adder1 = " + adder1.add(10, 20));	
		// b) Without Lambda (Anonymous Inner Class)
		Adder adder2 = new Adder() {
			@Override
			public int add(int a, int b) {
				return a+b;
			}
		};
		System.out.println("Adder2 = " + adder2.add(10, 20));
		// c) With Lambda Expression
		Adder adder3 = (a, b) -> a+b;
		System.out.println("Adder3 = " + adder3.add(10, 20));
		
	}
}
