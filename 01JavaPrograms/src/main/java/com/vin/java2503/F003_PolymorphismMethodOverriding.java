package com.vin.java2503;

public class F003_PolymorphismMethodOverriding {
	public static void main(String[] args) {
        Parent obj = new Child();  // Parent reference, Child object
        obj.show();  // Calls Child's show() method
    }
}

class Parent {
    void show() {
        System.out.println("This is Parent class");
    }
}

class Child extends Parent {
    @Override
    void show() {
        System.out.println("This is Child class");
    }
}
