package com.vin.java2503;

public class C002b_ThreadConcurrencyProblemSol extends Thread {
	static int count = 0;

	public void run() {
		for (int i = 0; i <= 5; i++) {
			System.out.println("countRun = "+count);
			count++;
		}
	}

	public static void main(String[] args) {
		C002b_ThreadConcurrencyProblemSol threadConcurrencyProblemSolThread = new C002b_ThreadConcurrencyProblemSol();
		threadConcurrencyProblemSolThread.start();
		while (threadConcurrencyProblemSolThread.isAlive()) {
			System.out.println("waiting...");
		}
		for (int i = 0; i <= 5; i++) {
			System.out.println("countmain = " +count);
			count++;
		}
	}

}
