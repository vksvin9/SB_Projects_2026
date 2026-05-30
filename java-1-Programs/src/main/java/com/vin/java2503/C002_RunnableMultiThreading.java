package com.vin.java2503;
public class C002_RunnableMultiThreading{
	public static void main(String[] args) {
//		ExtendingThreadClass extendingThreadClass = new ExtendingThreadClass();
//		extendingThreadClass.start();
//		System.out.println("main thread");
		
//		ImplementingRunnableInterface implementingRunnableInterface =new ImplementingRunnableInterface();
//		Thread thread = new Thread(implementingRunnableInterface);
//		thread.start();
//		System.out.println("main thread");
	}
}

class ExtendingThreadClass extends Thread{
	public void run() {
		System.out.println("run thread");
	}
}

class ImplementingRunnableInterface implements Runnable {
	@Override
	public void run() {
		System.out.println("run thread");
	}
}