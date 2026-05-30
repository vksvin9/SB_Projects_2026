package com.vin.java2503;

import java.util.ArrayList;
import java.util.List;

//Main Class
public class F008b_OneToManyExample {
 public static void main(String[] args) {
     Bank bank = new Bank("XYZ Bank");

     Customer c1 = new Customer("Alice");
     Customer c2 = new Customer("Bob");

     bank.addCustomer(c1);
     bank.addCustomer(c2);

     bank.displayCustomers();
 }
}


// Customer class
class Customer {
    String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

// Bank class
class Bank {
    String bankName;
    List<Customer> customers; // One-to-Many Association

    public Bank(String bankName) {
        this.bankName = bankName;
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void displayCustomers() {
        System.out.println("Bank: " + bankName + " has customers: " + customers);
    }
}

