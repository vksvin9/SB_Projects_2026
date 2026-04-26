package com.vin.java2503;

public class F003_PolymorphismMethodOverloading {
	 public static void main(String[] args) {
	        MathUtils obj = new MathUtils();
	        System.out.println(obj.add(5, 10));   // Calls int add()
	        System.out.println(obj.add(2.5, 3.5)); // Calls double add()
	    }
}
class MathUtils {
    int add(int a, int b) {
        return a + b;
    }
    
    double add(double a, double b) {
        return a + b;
    }
}
