package com.vin.java2503;

//Main Class
public class F008a_AssociationOneToOneExample {
public static void main(String[] args) {
   Passport p1 = new Passport("AB12345");
   Person2 person = new Person2("John Doe", p1);
   person.display();
}
}

//Passport class (Independent)
class Passport {
 String passportNumber;

 public Passport(String passportNumber) {
     this.passportNumber = passportNumber;
 }

 @Override
 public String toString() {
     return "Passport No: " + passportNumber;
 }
}

//Person class (Has a Passport)
class Person2 {
 String name;
 Passport passport; // One-to-One Association

 public Person2(String name, Passport passport) {
     this.name = name;
     this.passport = passport;
 }

 public void display() {
     System.out.println("Person: " + name + ", " + passport);
 }
}

