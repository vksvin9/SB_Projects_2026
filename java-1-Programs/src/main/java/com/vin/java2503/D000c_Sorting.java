package com.vin.java2503;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class D000c_Sorting {
    public static void main(String[] args) {
        List<Employee2> employees = new ArrayList<>();
        employees.add(new Employee2(103, "Alice", 50000));
        employees.add(new Employee2(101, "Charlie", 70000));
        employees.add(new Employee2(102, "Bob", 60000));

        // 1️⃣ Default Sorting (Using Comparable - Sort by ID)
        Collections.sort(employees);
        System.out.println("\n🔹 Sorted by ID (Comparable):\n" + employees);

        // 2️⃣ Sorting Using Comparator (By Name)
        employees.sort(new NameComparator());
        System.out.println("\n🔹 Sorted by Name (Comparator):\n" + employees);

        // 3️⃣ Sorting Using Comparator (By Salary - Descending)
        employees.sort(new SalaryComparator());
        System.out.println("\n🔹 Sorted by Salary (Descending) (Comparator):\n" + employees);

        // 4️⃣ Sorting Using Lambda (By Name)
        employees.sort((e1, e2) -> e1.name.compareTo(e2.name));
        System.out.println("\n🔹 Sorted by Name (Lambda):\n" + employees);

        // 5️⃣ Sorting Using Stream API (By Salary)
        List<Employee2> sortedBySalaryStream = employees.stream()
                .sorted(Comparator.comparingDouble(Employee2::getSalary))
                .collect(Collectors.toList()); // ✅ Works in Java 8+
        System.out.println("\n🔹 Sorted by Salary (Stream API):\n" + sortedBySalaryStream);
    }
}

// 1️⃣ Employee class implementing Comparable
class Employee2 implements Comparable<Employee2> {
    int id;
    String name;
    double salary;

    public Employee2(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // 🔥 Getter method for salary (Required for Stream API sorting)
    public double getSalary() {
        return salary;
    }

    // 🔥 Sorting by ID (Default Sorting) - Implementing Comparable
    @Override
    public int compareTo(Employee2 other) {
        return Integer.compare(this.id, other.id); // Ascending order
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + "}";
    }
}

// 2️⃣ Custom Comparator - Sort by Name
class NameComparator implements Comparator<Employee2> {
    @Override
    public int compare(Employee2 e1, Employee2 e2) {
        return e1.name.compareTo(e2.name); // Ascending order
    }
}

// 3️⃣ Custom Comparator - Sort by Salary (Descending)
class SalaryComparator implements Comparator<Employee2> {
    @Override
    public int compare(Employee2 e1, Employee2 e2) {
        return Double.compare(e2.salary, e1.salary); // Descending order
    }
}