package com.vin.java2508;

//Demonstrating Lazy vs Eager Singleton
public class B001_SingletonDP {
 // ----------------- Lazy Initialization -----------------
 // Instance is created only when first requested
 private static B001_SingletonDP lazyInstance;
 //Constructor
 private B001_SingletonDP() {
     System.out.println("Lazy Singleton instance created!");
 }
 public static B001_SingletonDP getLazyInstance() {
     if (lazyInstance == null) {
         lazyInstance = new B001_SingletonDP();
     }
     return lazyInstance;
 }
 // ----------------- Eager Initialization -----------------
 // Instance is created at the time of class loading
 private static final B001_SingletonDP eagerInstance = new B001_SingletonDP("EAGER");
 //Constructor
 private B001_SingletonDP(String type) {
     System.out.println(type + " Singleton instance created!");
 }
 public static B001_SingletonDP getEagerInstance() {
     return eagerInstance;
 }
 // Example method
 public void showMessage(String msg) {
     System.out.println("Message: " + msg);
 }
 // ----------------- Main for Testing -----------------
 public static void main(String[] args) {
     System.out.println("=== Testing Lazy Singleton ===");
     // First call will create object
     B001_SingletonDP obj1 = B001_SingletonDP.getLazyInstance();
     obj1.showMessage("Hello from Lazy 1");
     // Second call will return the same object
     B001_SingletonDP obj2 = B001_SingletonDP.getLazyInstance();
     obj2.showMessage("Hello from Lazy 2");
     System.out.println("obj1 == obj2 ? " + (obj1 == obj2));
     System.out.println("\n=== Testing Eager Singleton ===");
     // Already created at class loading
     B001_SingletonDP e1 = B001_SingletonDP.getEagerInstance();
     e1.showMessage("Hello from Eager 1");
     B001_SingletonDP e2 = B001_SingletonDP.getEagerInstance();
     e2.showMessage("Hello from Eager 2");
     System.out.println("e1 == e2 ? " + (e1 == e2));
 }
}