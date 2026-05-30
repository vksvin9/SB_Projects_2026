package com.vin.java2503;

//✅ High Cohesion: Each class does only ONE thing

//Main class
public class F007a_HighCohesionExample {
	public static void main(String[] args) {
		BankAccount1 account = new BankAccount1("123456789", 1000);
		account.deposit(500);
		account.withdraw(300);

		// Using separate classes for reporting and notifications
		ReportGenerator reportGenerator = new ReportGenerator();
		reportGenerator.generateReport(account);

		NotificationService notificationService = new NotificationService();
		notificationService.sendEmail("Transaction successful!");
	}
}

//1️⃣ Account management class
class BankAccount1 {
	private String accountNumber;
	private double balance;

	public BankAccount1(String accountNumber, double balance) {
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

	public double getBalance() {
		return balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
}

//2️⃣ Separate class for report generation
class ReportGenerator {
	public void generateReport(BankAccount1 account) {
		System.out.println("Generating report for account: " + account.getAccountNumber());
	}
}

//3️⃣ Separate class for email notifications
class NotificationService {
	public void sendEmail(String message) {
		System.out.println("Sending email: " + message);
	}
}
