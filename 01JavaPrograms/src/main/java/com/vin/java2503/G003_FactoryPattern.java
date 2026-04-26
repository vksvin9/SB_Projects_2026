package com.vin.java2503;

public class G003_FactoryPattern {
	 public static void main(String[] args) {
	     AnimalFactory factory = new DogFactory();
	     Animal dog = factory.createAnimal();
	     dog.speak();

	     factory = new CatFactory();
	     Animal cat = factory.createAnimal();
	     cat.speak();
	 }
	}

//Product interface
interface Animal {
 void speak();
}
//Concrete Product 1
class Dog implements Animal {
 public void speak() {
     System.out.println("Woof!");
 }
}
//Concrete Product 2
class Cat implements Animal {
 public void speak() {
     System.out.println("Meow!");
 }
}
//Creator class
abstract class AnimalFactory {
 public abstract Animal createAnimal();
}
//Concrete Creator 1
class DogFactory extends AnimalFactory {
 public Animal createAnimal() {
     return new Dog();
 }
}
//Concrete Creator 2
class CatFactory extends AnimalFactory {
 public Animal createAnimal() {
     return new Cat();
 }
}
