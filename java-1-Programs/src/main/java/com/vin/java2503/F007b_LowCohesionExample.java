package com.vin.java2503;

//Main class
public class F007b_LowCohesionExample {
public static void main(String[] args) {
   BankAccount2 account = new BankAccount2("123456789", 1000);
   account.deposit(500);
   account.withdraw(300);
   account.generateReport(); // ❌ This should be in a separate class
   account.sendEmailNotification("Transaction successful!"); // ❌ Unrelated to account operations
}
}

//❌ Low Cohesion: One class doing too many things
class BankAccount2 {
 private String accountNumber;
 private double balance;

 public BankAccount2(String accountNumber, double balance) {
     this.accountNumber = accountNumber;
     this.balance = balance;
 }

 public void deposit(double amount) {
     balance += amount;
     System.out.println("Deposited: " + amount + ", New Balance: " + balance);
 }

 public void withdraw(double amount) {
     if (amount > balance) {
         System.out.println("Insufficient funds!");
     } else {
         balance -= amount;
         System.out.println("Withdrawn: " + amount + ", New Balance: " + balance);
     }
 }

 // ❌ Unrelated responsibility: Generates reports
 public void generateReport() {
     System.out.println("Generating account report for account: " + accountNumber);
 }

 // ❌ Unrelated responsibility: Sends notifications
 public void sendEmailNotification(String message) {
     System.out.println("Sending email: " + message);
 }
}
