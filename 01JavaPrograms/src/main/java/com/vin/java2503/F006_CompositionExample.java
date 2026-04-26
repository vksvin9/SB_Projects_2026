package com.vin.java2503;

//Main class
public class F006_CompositionExample {
public static void main(String[] args) {
   // Creating a Car object (Creates Engine internally)
   Car1 car1 = new Car1("Tesla Model S", "Electric");
   car1.startCar();

   System.out.println();

   Car1 car2 = new Car1("Ford Mustang", "V8");
   car2.startCar();
}
}

//Engine class (Strongly tied to Car)
class Engine {
 private String type;

 public Engine(String type) {
     this.type = type;
 }

 public void start() {
     System.out.println("Engine (" + type + ") is starting...");
 }

 @Override
 public String toString() {
     return type + " Engine";
 }
}

//Car class (Composition - Contains Engine)
class Car1 {
 private String model;
 private Engine engine; // Composition (Has-A Relationship)

 public Car1(String model, String engineType) {
     this.model = model;
     this.engine = new Engine(engineType); // Engine is created inside Car (Composition)
 }

 public void startCar() {
     System.out.println("Starting Car: " + model);
     engine.start(); // Car controls Engine's lifecycle
 }

 @Override
 public String toString() {
     return "Car Model: " + model + ", Engine: " + engine;
 }
}
