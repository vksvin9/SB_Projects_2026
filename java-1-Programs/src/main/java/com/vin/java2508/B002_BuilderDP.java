package com.vin.java2508;

//Demonstration of Builder Design Pattern in Java
//-----------------------------------------------
//✅ Used to create complex objects step by step.
//✅ Helps when an object has many optional fields.
//✅ Makes object creation more readable & flexible.
public class B002_BuilderDP {
 // ----------- Required parameters -----------
 private final String name;
 private final String email;
 // ----------- Optional parameters -----------
 private final int age;
 private final String phone;
 // Private constructor (can only be called via Builder)
 private B002_BuilderDP(Builder builder) {
     this.name = builder.name;
     this.email = builder.email;
     this.age = builder.age;
     this.phone = builder.phone;
 }
 // ----------- Static Nested Builder Class -----------
 public static class Builder {
     // Required fields
     private final String name;
     private final String email;
     // Optional fields with default values
     private int age = 0;
     private String phone = "N/A";
     // Constructor for required fields
     public Builder(String name, String email) {
         this.name = name;
         this.email = email;
     }
     // Setter for optional field "age"
     public Builder age(int age) {
         this.age = age;
         return this;  // return Builder for chaining
     }
     // Setter for optional field "phone"
     public Builder phone(String phone) {
         this.phone = phone;
         return this;  // return Builder for chaining
     }
     // Build method -> returns the final User object
     public B002_BuilderDP build() {
         return new B002_BuilderDP(this);
     }
 }
 // Example method to display user details
 public void print() {
     System.out.println("Name: " + name + ", Email: " + email + ", Age: " + age + ", Phone: " + phone);
 }
 // ----------- Main Method for Testing -----------
 public static void main(String[] args) {
     System.out.println("=== Builder Pattern Demo ===");
     // Creating user with all fields (required + optional)
     B002_BuilderDP user1 = new B002_BuilderDP.Builder("Vikas", "vikas@example.com")
             .age(30)
             .phone("1234567890")
             .build();
     user1.print();
     // Output: Name: Vikas, Email: vikas@example.com, Age: 30, Phone: 1234567890
     // Creating user with only required fields
     B002_BuilderDP user2 = new B002_BuilderDP.Builder("Anjali", "anjali@example.com")
             .build();
     user2.print();
     // Output: Name: Anjali, Email: anjali@example.com, Age: 0, Phone: N/A
 }
}
