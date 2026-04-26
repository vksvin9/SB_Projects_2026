package com.vin.java2503;

public class F002_Ineritance {
	public static void main(String[] args) {
		Employee1 emp = new Employee1("John", 30, 50000);
		emp.showDetails(); // Display Employee details
	}
}

//Parent Class
class Person {
	String name;
	int age;

	// Constructor of Parent Class
	Person(String name, int age) {
		this.name = name; // Using 'this' to refer to instance variable
		this.age = age;
	}

	void display() {
		System.out.println("Name: " + name + ", Age: " + age);
	}
}

//Child Class (inherits Person)
class Employee1 extends Person {
	double salary;

	// Constructor of Child Class
	Employee1(String name, int age, double salary) {
		super(name, age); // Calling Parent Class Constructor using 'super'
		this.salary = salary; // Using 'this' for current class variable
	}

	void showDetails() {
		super.display(); // Calling Parent Class Method using 'super'
		System.out.println("Salary: " + salary);
	}
}