package com.vin.java2503;

public class C002a_ThreadConcurrencyProblem extends Thread {
	static int count = 0;

	public void run() {
		for (int i = 0; i <= 5; i++) {
			count++;
			System.out.println("countrun = " +count);
		}
	}

	public static void main(String[] args) {
		C002a_ThreadConcurrencyProblem threadConcurrencyProblemThread = new C002a_ThreadConcurrencyProblem();
		threadConcurrencyProblemThread.start();
		System.out.println(count);
		for (int i = 0; i <= 5; i++) {
			count++;
			System.out.println("countmain = " +count);
		}
		
	}

}
