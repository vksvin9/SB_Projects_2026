package com.vin.java2503;

//Main Class
public class F008c_ManyToOneExample {
	public static void main(String[] args) {
		College college = new College("ABC University");

		Student1 s1 = new Student1("Mike", college);
		Student1 s2 = new Student1("Emma", college);

		s1.display();
		s2.display();
	}
}

//College class
class College {
	String name;

	public College(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}

//Student class
class Student1 {
	String studentName;
	College college; // Many-to-One Association

	public Student1(String studentName, College college) {
		this.studentName = studentName;
		this.college = college;
	}

	public void display() {
		System.out.println(studentName + " studies at " + college);
	}
}
