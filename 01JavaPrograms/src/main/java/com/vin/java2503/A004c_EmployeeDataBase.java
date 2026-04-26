package com.vin.java2503;

import java.util.Arrays;
import java.util.List;

public class A004c_EmployeeDataBase {

    public static List<A004a_Employee> getAllEmployees() {
        A004b_Project p1 = new A004b_Project("P001", "Alpha", "ABC Corp", "Alice");
        A004b_Project p2 = new A004b_Project("P002", "Beta", "XYZ Ltd", "Bob");
        A004b_Project p3 = new A004b_Project("P003", "Gamma", "ABC Corp", "Alice");
        A004b_Project p4 = new A004b_Project("P004", "Delta", "TechWorld", "Charlie");
        A004b_Project p5 = new A004b_Project("P005", "Epsilon", "MoneyMatters", "Daniel");
        A004b_Project p6 = new A004b_Project("P006", "Zeta", "SmartTech", "Eva");
        A004b_Project p7 = new A004b_Project("P007", "Eta", "BrandBoost", "George");
        A004b_Project p8 = new A004b_Project("P008", "Theta", "InnoSoft", "Hannah");
        A004b_Project p9 = new A004b_Project("P009", "Iota", "FastTrack", "Ian");
        A004b_Project p10 = new A004b_Project("P010", "Kappa", "DigitalWave", "Jessica");

        // A004a_Employee instances
        A004a_Employee e1 = new A004a_Employee(1, "John Doe", "Development", Arrays.asList(p1, p2), 80000, "Male");
        A004a_Employee e2 = new A004a_Employee(2, "Jane Smith", "Development", Arrays.asList(p3), 80000, "Female");
        A004a_Employee e3 = new A004a_Employee(3, "Robert Brown", "Sales", Arrays.asList(p4), 60000, "Male");
        A004a_Employee e4 = new A004a_Employee(4, "Lisa White", "HR", Arrays.asList(p1), 55000, "Female");
        A004a_Employee e5 = new A004a_Employee(5, "Michael Green", "Finance", Arrays.asList(p5), 90000, "Male");
        A004a_Employee e6 = new A004a_Employee(6, "Sophia Brown", "Development", Arrays.asList(p6), 85000, "Female");
        A004a_Employee e7 = new A004a_Employee(7, "James Wilson", "Marketing", Arrays.asList(p7), 72000, "Male");
        A004a_Employee e8 = new A004a_Employee(8, "Olivia Harris", "Development", Arrays.asList(p8), 88000, "Female");
        A004a_Employee e9 = new A004a_Employee(9, "William Lee", "Sales", Arrays.asList(p9), 78000, "Male");
        A004a_Employee e10 = new A004a_Employee(10, "Emily Clark", "Development", Arrays.asList(p10), 95000, "Female");

        // Print employee details (just for testing)
        return Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
    }
}