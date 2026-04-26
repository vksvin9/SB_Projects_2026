package com.vin.java2508;
class Person1 {
    String name;
    public Person1(String name) {
        this.name = name;
    }
    // Overriding toString() method
    // Description: Returns a string representation of the object.
    // Default is <ClassName>@<hashCode>. Can be overridden to provide meaningful info.
    @Override
    public String toString() {
        return "Person{name='" + name + "'}"; // Output example: Person{name='Alice'}
    }
    // Overriding equals() method
    // Description: Compares objects for equality.
    // Default checks reference equality; can override to compare content logically.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same reference
        if (obj == null || getClass() != obj.getClass()) return false;
        Person1 person = (Person1) obj;
        return this.name.equals(person.name); // Output: true if names are equal
    }
    // Overriding hashCode() method
    // Description: Returns an integer hash code for the object.
    // Must be consistent with equals() – equal objects must return same hashCode.
    @Override
    public int hashCode() {
        return name.hashCode(); // Output example: 63281949
    }
    // finalize() method (called by GC before object is destroyed - deprecated in Java 9+)
    // Description: Called by garbage collector before object destruction.
    // Deprecated because GC timing is unpredictable; use try-with-resources or explicit cleanup instead.
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize() called on: " + this.name); // Output example: finalize() called on: Alice
    }
}
public class C002_ObjectClass {
    public static void main(String[] args) throws InterruptedException {
        Person1 p1 = new Person1("Alice");
        Person1 p2 = new Person1("Alice");
        Person1 p3 = p1;
        // 1. toString()
        // Description: Prints string representation of the object
        System.out.println("toString(): " + p1); // Output: toString(): Person{name='Alice'}
        // 2. equals()
        // Description: Compares objects by reference or content (if overridden)
        System.out.println("equals() with same content: " + p1.equals(p2)); // Output: equals() with same content: true
        System.out.println("equals() with same reference: " + p1.equals(p3)); // Output: equals() with same reference: true
        // 3. hashCode()
        // Description: Returns an integer hash code, used in collections like HashMap/HashSet
        System.out.println("hashCode() of p1: " + p1.hashCode()); // Output example: hashCode() of p1: 63281949
        System.out.println("hashCode() of p2: " + p2.hashCode()); // Output example: hashCode() of p2: 63281949
        // 4. getClass()
        // Description: Returns runtime class of the object; useful for reflection/debugging
        System.out.println("getClass(): " + p1.getClass().getName()); // Output: getClass(): com.rev.java2508.Person1
        // 5. wait/notify demonstration
        // Description:
        // - wait(): Causes current thread to wait until another thread invokes notify()/notifyAll() on the same object.
        // - notify(): Wakes up one waiting thread.
        // Must be used inside synchronized blocks.
        Person1 p1ForThread = p1; // effectively final variable for lambda
        Thread thread = new Thread(() -> {
            synchronized (p1ForThread) {
                try {
                    System.out.println("Thread waiting..."); // Output: Thread waiting...
                    p1ForThread.wait();
                    System.out.println("Thread resumed!"); // Output: Thread resumed!
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        // Give the thread time to start and wait
        Thread.sleep(1000);
        synchronized (p1ForThread) {
            System.out.println("Main thread notifying..."); // Output: Main thread notifying...
            p1ForThread.notify(); // wakes the waiting thread
        }
        // Wait for thread to finish
        thread.join();
        // 6. finalize() demonstration
        // Description: Called before object is garbage collected (optional, deprecated)
        p1 = null;
        p2 = null;
        p3 = null;
        // Request garbage collection
        System.gc(); // Output example (optional): finalize() called on: Alice
        // Allow time for GC to run finalize()
        Thread.sleep(1000);
        System.out.println("Main method finished."); // Output: Main method finished.
    }
}