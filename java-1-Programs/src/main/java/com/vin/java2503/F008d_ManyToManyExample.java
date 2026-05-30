package com.vin.java2503;

import java.util.ArrayList;
import java.util.List;

//Main Class
public class F008d_ManyToManyExample {
 public static void main(String[] args) {
     Student3 s1 = new Student3("David");
     Student3 s2 = new Student3("Sophia");

     Course1 c1 = new Course1("Math");
     Course1 c2 = new Course1("Science");

     s1.enrollCourse(c1);
     s1.enrollCourse(c2);
     s2.enrollCourse(c1);

     c1.displayStudents();
     c2.displayStudents();
 }
}


// Course class
class Course1 {
    String courseName;
    List<Student3> students; // Many-to-Many Association

    public Course1(String courseName) {
        this.courseName = courseName;
        this.students = new ArrayList<>();
    }

    public void enrollStudent(Student3 student) {
        students.add(student);
    }

    public void displayStudents() {
        System.out.println("Course: " + courseName + " has students: " + students);
    }
}

// Student class
class Student3 {
    String name;
    List<Course1> courses; // Many-to-Many Association

    public Student3(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public void enrollCourse(Course1 course) {
        courses.add(course);
        course.enrollStudent(this);
    }

    @Override
    public String toString() {
        return name;
    }
}