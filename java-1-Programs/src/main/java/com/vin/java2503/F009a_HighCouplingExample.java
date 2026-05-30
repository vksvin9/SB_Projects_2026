package com.vin.java2503;

//Main Class
public class F009a_HighCouplingExample {
public static void main(String[] args) {
   Car3 car = new Car3();
   car.drive();
}
}


//Engine class
class Engine3 {
 public void start() {
     System.out.println("Engine is starting...");
 }
}

//Car class (Tightly coupled with Engine)
class Car3 {
 Engine3 engine = new Engine3(); // Direct dependency ❌

 public void drive() {
     engine.start(); // Car directly controls Engine
     System.out.println("Car is driving...");
 }
}