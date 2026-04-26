package com.vin.java2503;

import java.util.List;

public class A004a_Employee {
	private int id;
	private String name;
	private String dept;
	private List<A004b_Project> a004b_Projects;
	private double salary;
	private String gender;

	public A004a_Employee() {
		super();
	}

	public A004a_Employee(int id, String name, String dept, List<A004b_Project> a004b_Projects, double salary,
			String gender) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.a004b_Projects = a004b_Projects;
		this.salary = salary;
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public List<A004b_Project> getProjects() {
		return a004b_Projects;
	}

	public void setProjects(List<A004b_Project> a004b_Projects) {
		this.a004b_Projects = a004b_Projects;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "A004a_Employee [id=" + id + ", name=" + name + ", dept=" + dept + ", a004b_Projects=" + a004b_Projects + ", salary="
				+ salary + ", gender=" + gender + "]";
	}
}
