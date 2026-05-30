package com.vin.java2508;

//Demonstration of Factory Design Pattern in Java
//-----------------------------------------------
//✅ Factory Pattern is used to create objects without exposing the creation logic.
//✅ The client asks the factory for an object, and the factory decides which subclass to return.
interface Animal {
 void sound();  // common method
}
//------------------ Dog class ------------------
class Dog implements Animal {
 public void sound() {
     System.out.println("Woof");
 }
}
//------------------ Cat class ------------------
class Cat implements Animal {
 public void sound() {
     System.out.println("Meow");
 }
}
//------------------ Factory Class ------------------
class AnimalFactory {
 // Factory method
 public Animal getAnimal(String type) {
     if (type == null) return null;
     if (type.equalsIgnoreCase("dog")) return new Dog();
     if (type.equalsIgnoreCase("cat")) return new Cat();
     return null; // if unknown type
 }
}
//------------------ Main Class ------------------
public class B003_FactoryDP {
 public static void main(String[] args) {
     System.out.println("=== Factory Pattern Demo ===");
     AnimalFactory factory = new AnimalFactory();
     // Create Dog using factory
     Animal a1 = factory.getAnimal("dog");
     a1.sound();  
     // Output: Woof
     // Create Cat using factory
     Animal a2 = factory.getAnimal("cat");
     a2.sound();  
     // Output: Meow
     // Unknown type
     Animal a3 = factory.getAnimal("lion");
     System.out.println("a3 = " + a3);
     // Output: a3 = null
 }
}
