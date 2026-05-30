package com.vin.java2503;

//Main class
public class F005_AggregationExample {
public static void main(String[] args) {
   // Creating Course objects (Independent objects)
   Course course1 = new Course("Java Programming", "CS101");
   Course course2 = new Course("Data Structures", "CS102");

   // Creating Student objects with Aggregation
   Student2 student1 = new Student2(1, "Alice", course1);
   Student2 student2 = new Student2(2, "Bob", course2);

   // Display Student details
   student1.display();
   System.out.println();
   student2.display();
}
}

//Course class (Independent class)
class Course {
 String courseName, courseCode;

 public Course(String courseName, String courseCode) {
     this.courseName = courseName;
     this.courseCode = courseCode;
 }

 @Override
 public String toString() {
     return courseName + " (" + courseCode + ")";
 }
}

//Student class (Contains Course - Aggregation)
class Student2 {
 int id;
 String name;
 Course course; // Aggregation (Has-A Relationship)

 public Student2(int id, String name, Course course) {
     this.id = id;
     this.name = name;
     this.course = course;
 }

 public void display() {
     System.out.println("Student ID: " + id);
     System.out.println("Student Name: " + name);
     System.out.println("Enrolled Course: " + course);
 }
}

