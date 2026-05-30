package com.vin.java2503;

public class F001_Encapsulation {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        account.deposit(500);
        System.out.println("Current Balance: " + account.getBalance());
    }
}
class BankAccount {
    private double balance;  // Private variable (data hiding)
    public BankAccount(double balance) {
        this.balance = balance;
    }
    public double getBalance() {  // Public method to access private data
        return balance;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }
}

