package com.vin.java2503;

public class F004_AbstractionAbstractClass {
    public static void main(String[] args) {
        Vehicle myCar = new Car();
        myCar.start();
        myCar.stop();
    }
}

abstract class Vehicle {
    abstract void start();  // Abstract method (no body)
    void stop() {  // Concrete method
        System.out.println("Vehicle stopped.");
    }
}

class Car extends Vehicle {
    @Override
    void start() {
        System.out.println("Car starts with a key.");
    }
}
