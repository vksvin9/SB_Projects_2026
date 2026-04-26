package com.vin.java2508;

// Parent class
class Person {
    int id = 100;         // Parent variable
    String name = "Parent-Name";
    // Default constructor
    Person() {
        System.out.println("Person default constructor called");
    }
    // Parameterized constructor
    Person(int id, String name) {
        System.out.println("Person parameterized constructor called");
        this.id = id;
        this.name = name;
    }
    // Getters and setters
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    // Parent method
    void display() {
        System.out.println("Parent display() → id=" + id + ", name=" + name);
    }
    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + "]";
    }
}
// Child class
public class A002_ConstructorStudent extends Person {
    int id = 200;         // Child variable (hides parent's id)
    String name = "Child-Name";
    String course;
    // Default constructor
    A002_ConstructorStudent() {
        super();  // calls Person()
        System.out.println("Student default constructor called");
        this.course = "Not Assigned";
    }
    // Parameterized constructor
    A002_ConstructorStudent(int id, String name, String course) {
        super(id, name);  // calls Person(int, String)
        System.out.println("Student parameterized constructor called");
        this.id = id;       // refers to child's id
        this.name = name;   // refers to child's name
        this.course = course;
        System.out.println("Inside Student constructor → this.toString() = " + this.toString());
    }
    // Getter and Setter for course
    public String getCourse() {
        return this.course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    // Override display() method
    @Override
    void display() {
        System.out.println("Child display() called");
        System.out.println("this.id = " + this.id + ", this.name = " + this.name); // child variables
        System.out.println("super.id = " + super.id + ", super.name = " + super.name); // parent variables
        super.display(); // call parent method
    }
    // Override toString
    @Override
    public String toString() {
        return "Student [child-id=" + this.id + ", child-name=" + this.name +
               ", parent-id=" + super.id + ", parent-name=" + super.name +
               ", course=" + course + "]";
    }
    // Main method
    public static void main(String[] args) {
        System.out.println("---- Using default constructor ----");
        A002_ConstructorStudent s1 = new A002_ConstructorStudent();
        // OUTPUT:
        // Person default constructor called
        // Student default constructor called
        s1.setId(1);         // sets parent's id
        s1.setName("Vikas"); // sets parent's name
        s1.setCourse("Java");
        System.out.println("s1.toString() → " + s1);
        // OUTPUT:
        // s1.toString() → Student [child-id=200, child-name=Child-Name, parent-id=1, parent-name=Vikas, course=Java]
        s1.display();
        // OUTPUT:
        // Child display() called
        // this.id = 200, this.name = Child-Name
        // super.id = 1, super.name = Vikas
        // Parent display() → id=1, name=Vikas
        System.out.println("\n---- Using parameterized constructor ----");
        A002_ConstructorStudent s2 = new A002_ConstructorStudent(2, "Thanulal", "Python");
        // OUTPUT:
        // Person parameterized constructor called
        // Student parameterized constructor called
        // Inside Student constructor → this.toString() = Student [child-id=2, child-name=Thanulal, parent-id=2, parent-name=Thanulal, course=Python]
        System.out.println("s2.toString() → " + s2);
        // OUTPUT:
        // s2.toString() → Student [child-id=2, child-name=Thanulal, parent-id=2, parent-name=Thanulal, course=Python]
        s2.display();
        // OUTPUT:
        // Child display() called
        // this.id = 2, this.name = Thanulal
        // super.id = 2, super.name = Thanulal
        // Parent display() → id=2, name=Thanulal
        System.out.println("\n---- Variable Hiding Example ----");
        System.out.println("s2.id (child) = " + s2.id);
        // OUTPUT:
        // s2.id (child) = 2
        System.out.println("((Person)s2).id (parent) = " + ((Person)s2).id);
        // OUTPUT:
        // ((Person)s2).id (parent) = 2
        System.out.println("\n---- Method Overriding Example ----");
        Person p = new A002_ConstructorStudent(3, "ParentRefToChild", "C++");
        // OUTPUT:
        // Person parameterized constructor called
        // Student parameterized constructor called
        // Inside Student constructor → this.toString() = Student [child-id=3, child-name=ParentRefToChild, parent-id=3, parent-name=ParentRefToChild, course=C++]
        p.display(); // calls Student's overridden method
        // OUTPUT:
        // Child display() called
        // this.id = 3, this.name = ParentRefToChild
        // super.id = 3, super.name = ParentRefToChild
        // Parent display() → id=3, name=ParentRefToChild
    }
}
