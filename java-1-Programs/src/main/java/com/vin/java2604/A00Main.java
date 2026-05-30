package com.vin.java2604;

import java.util.ArrayList;
import java.util.List;

public class A00Main {

	public static void main(String[] args) throws InterruptedException {
		consumerProducerProblem();


	}
	
	public static void consumerProducerProblem() throws InterruptedException{
		List<Integer> aL = new ArrayList<Integer>();
		Runnable writer = () ->{
			for(int i=0; i<10; i++) {
				aL.add(i);
				System.out.println("item produced -> " + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread reader = new Thread(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int i : aL) {	
				System.out.println("item consumed -> " + i);
			}
		}); 
		Thread write = new Thread(writer);
		write.start();
		write.join();
		reader.start();
	}

}
