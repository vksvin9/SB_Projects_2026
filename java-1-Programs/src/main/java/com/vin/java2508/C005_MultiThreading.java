package com.vin.java2508;
public class C005_MultiThreading {
    // Shared volatile flag to control thread termination
    private static volatile boolean isRunning = true;
    // Shared counter for synchronized example
    private static int sharedCounter = 0;
    // Object for wait/notify demonstration
    private static final Object lock = new Object();
    // --------------------------
    // 1. Thread by Extending Thread
    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                // Synchronized block ensures only one thread can update sharedCounter at a time
                synchronized (C005_MultiThreading.class) {
                    sharedCounter++;
                    System.out.println(getName() + " (Extends Thread) incremented counter: " + sharedCounter);
                    // Output example: MyThread-Extends (Extends Thread) incremented counter: 1
                }
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
            System.out.println(getName() + " (Extends Thread) terminated.");
        }
    }
    // 2. Thread by Implementing Runnable
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (char c = 'A'; c <= 'E'; c++) {
                System.out.println(Thread.currentThread().getName() + " (Runnable): " + c);
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
            System.out.println(Thread.currentThread().getName() + " (Runnable) terminated.");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== MultiThreading Demo Started ===");
        // --------------------------
        // Extending Thread
        MyThread t1 = new MyThread();
        t1.setName("MyThread-Extends");
        t1.start();
        // Implementing Runnable
        Thread t2 = new Thread(new MyRunnable());
        t2.setName("MyRunnable-1");
        t2.start();
        // Anonymous class
        Thread anonymousThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " started (Anonymous)");
                for (int i = 1; i <= 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " working: " + i);
                    try { Thread.sleep(500); } catch (InterruptedException e) {}
                }
                System.out.println(Thread.currentThread().getName() + " terminated (Anonymous)");
            }
        });
        anonymousThread.setName("Anonymous-Worker");
        anonymousThread.start();
        // Lambda thread
        Thread lambdaThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started (Lambda)");
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " working (Lambda): " + i);
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
            System.out.println(Thread.currentThread().getName() + " terminated (Lambda)");
        });
        lambdaThread.setName("Lambda-Worker");
        lambdaThread.start();
        // --------------------------
        // Synchronized example
        // Description/Usage: Ensures only one thread can execute the block at a time
        Thread syncThread1 = new Thread(() -> {
            synchronized (C005_MultiThreading.class) {
                System.out.println(Thread.currentThread().getName() + " acquired lock for synchronized block");
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
                System.out.println(Thread.currentThread().getName() + " released lock");
            }
        });
        syncThread1.setName("SyncThread-1");
        Thread syncThread2 = new Thread(() -> {
            synchronized (C005_MultiThreading.class) {
                System.out.println(Thread.currentThread().getName() + " acquired lock for synchronized block");
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
                System.out.println(Thread.currentThread().getName() + " released lock");
            }
        });
        syncThread2.setName("SyncThread-2");
        syncThread1.start();
        syncThread2.start();
        // --------------------------
        // Wait/Notify demonstration
        Thread waitThread = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread().getName() + " waiting...");
                    lock.wait(); // waits until notified
                    System.out.println(Thread.currentThread().getName() + " resumed after notify!");
                } catch (InterruptedException e) {}
            }
        });
        waitThread.setName("WaitThread");
        waitThread.start();
        Thread.sleep(1000); // Ensure waitThread starts and waits
        Thread notifyThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " sending notify...");
                lock.notify(); // wakes up waiting thread
            }
        });
        notifyThread.setName("NotifyThread");
        notifyThread.start();
        // --------------------------
        // Wait for all threads
        t1.join();
        t2.join();
        anonymousThread.join();
        lambdaThread.join();
        syncThread1.join();
        syncThread2.join();
        waitThread.join();
        notifyThread.join();
        System.out.println("Final sharedCounter value: " + sharedCounter);
        System.out.println("=== MultiThreading Demo Finished ===");
    }
}
