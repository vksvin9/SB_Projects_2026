package com.vin.java2604;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class B04ThreadSafeAL {
	public static void main(String[] args) throws InterruptedException {
		// consumerProducerProblemExample();
		// solJoinExample();
		// solWaitNotifyExample();
		blockingQueueExample(); // ------------***PROD USE
	}

	public static void consumerProducerProblemExample() {
		List<String> list = new ArrayList<>();
		Runnable writerTask = () -> {
			for (int i = 0; i < 5; i++) {
				list.add("Item-" + i);
				System.out.println("[Normal] Added: Item-" + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException ignored) {
				}
			}
		};
		Thread reader = new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ignored) {
			}
			for (String item : list) { // ⚠ May throw ConcurrentModificationException
				System.out.println("[Normal] Read: " + item);
			}
		});
		new Thread(writerTask).start();
		reader.start();
	}

	public static void solJoinExample() throws InterruptedException {
		List<String> list = new ArrayList<>();
//      List<String> list = new CopyOnWriteArrayList<>(); //Using CopyOnWriteArrayList (Thread-Safe)
		Runnable writerTask = () -> {
			for (int i = 0; i < 5; i++) {
				list.add("Item-" + i);
				System.out.println("[Normal] Added: Item-" + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException ignored) {
				}
			}
		};
		Thread reader = new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ignored) {
			}
			for (String item : list) {
				System.out.println("[Normal] Read: " + item);
			}
		});
		Thread write = new Thread(writerTask);
		write.start();
		write.join();
		reader.start();
	}

	public static void solWaitNotifyExample() {
		Object lock = new Object();
		boolean[] hasItem = { false };
		int[] buffer = new int[1];
		Thread producer = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				synchronized (lock) {
					while (hasItem[0]) {
						try {
							lock.wait();
						} catch (InterruptedException ignored) {
						}
					}
					buffer[0] = i;
					hasItem[0] = true;
					System.out.println("P: " + i);
					lock.notify();
				}
			}
		});
		Thread consumer = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				synchronized (lock) {
					while (!hasItem[0]) {
						try {
							lock.wait();
						} catch (InterruptedException ignored) {
						}
					}
					System.out.println("C: " + buffer[0]);
					hasItem[0] = false;
					lock.notify();
				}
			}
		});
		producer.start();
		consumer.start();
	}

	public static void blockingQueueExample() {
		BlockingQueue<String> queue = new LinkedBlockingQueue<>();
		// Writer (Producer)
		Thread writer = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				try {
					String item = "Item-" + i;
					queue.put(item); // add
					System.out.println("[BQ Write] " + item);
					Thread.sleep(500);
				} catch (InterruptedException ignored) {
				}
			}
		});
		// Reader (Consumer)
		Thread reader = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				try {
					String item = queue.take(); // read (waits if empty)
					System.out.println("[BQ Read] " + item);
				} catch (InterruptedException ignored) {
				}
			}
		});
		writer.start();
		reader.start();
	}
}