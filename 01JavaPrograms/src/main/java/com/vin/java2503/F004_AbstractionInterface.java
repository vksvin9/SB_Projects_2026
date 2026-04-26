package com.vin.java2503;

public class F004_AbstractionInterface {
	 public static void main(String[] args) {
	        Animal2 myCat = new Cat2();
	        myCat.sound();
	    }
}

interface Animal2 {
    void sound();  // Abstract method (by default)
}

class Cat2 implements Animal2 {
    public void sound() {
        System.out.println("Meow Meow");
    }
}

