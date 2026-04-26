package com.vin.java2503;
import java.util.*;
import java.util.stream.Collectors;

public class D000d_EmployeeManagementSystem {
    public static void main(String[] args) {
        // 1️⃣ List - Store Employees
        List<Employee3> employees = new ArrayList<>();
        employees.add(new Employee3(103, "Alice", "IT", 70000));
        employees.add(new Employee3(101, "Bob", "HR", 50000));
        employees.add(new Employee3(104, "David", "Finance", 65000));
        employees.add(new Employee3(102, "Charlie", "IT", 60000));

        // 2️⃣ Sorting Employees by Salary (Ascending)
        employees.sort(Comparator.comparingDouble(Employee3::getSalary));
        System.out.println("\n🔹 Employees Sorted by Salary:\n" + employees);

        // 3️⃣ Filtering Employees (Only IT Department)
        List<Employee3> itEmployees = employees.stream()
                .filter(emp -> emp.department.equals("IT"))
                .collect(Collectors.toList());
        System.out.println("\n🔹 IT Department Employees:\n" + itEmployees);

        // 4️⃣ Set - Store Unique Employee Names
        Set<String> employeeNames = new HashSet<>();
        for (Employee3 emp : employees) {
            employeeNames.add(emp.name);
        }
        System.out.println("\n🔹 Unique Employee Names:\n" + employeeNames);

        // 5️⃣ Map - Store Employees by ID
        Map<Integer, Employee3> employeeMap = new HashMap<>();
        for (Employee3 emp : employees) {
            employeeMap.put(emp.id, emp);
        }

        // Retrieve Employee by ID
        int searchId = 102;
        System.out.println("\n🔹 Employee with ID " + searchId + ": " + employeeMap.get(searchId));
    }
}

class Employee3 {
    int id;
    String name;
    String department;
    double salary;

    public Employee3(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', dept='" + department + "', salary=" + salary + "}";
    }
}