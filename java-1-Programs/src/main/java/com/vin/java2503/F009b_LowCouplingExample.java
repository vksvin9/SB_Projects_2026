package com.vin.java2503;

//Main Class
public class F009b_LowCouplingExample {
	public static void main(String[] args) {
		Engine2 petrolEngine = new PetrolEngine();
		Car2 car1 = new Car2(petrolEngine);
		car1.drive();

		Engine2 dieselEngine = new DieselEngine();
		Car2 car2 = new Car2(dieselEngine);
		car2.drive();
	}
}

//Interface for Engine (Abstraction)
interface Engine2 {
	void start();
}

//PetrolEngine class (Implements Engine)
class PetrolEngine implements Engine2 {
	public void start() {
		System.out.println("Petrol Engine is starting...");
	}
}

//DieselEngine class (Implements Engine)
class DieselEngine implements Engine2 {
	public void start() {
		System.out.println("Diesel Engine is starting...");
	}
}

//Car class (Loosely coupled with Engine)
class Car2 {
	private Engine2 engine; // No direct dependency ✅

	public Car2(Engine2 engine) { // Injecting dependency
		this.engine = engine;
	}

	public void drive() {
		engine.start(); // Uses abstraction (interface)
		System.out.println("Car is driving...");
	}
}
