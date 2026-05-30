package com.vin.java2503;

import java.util.ArrayList;
import java.util.List;

public class G004_ObserverPattern {
	public static void main(String[] args) {
		Subject subject = new Subject();
		Observer observer1 = new ConcreteObserver("Observer 1");
		Observer observer2 = new ConcreteObserver("Observer 2");
		subject.addObserver(observer1);
		subject.addObserver(observer2);
		subject.setState("New state!");
	}
}

//Subject class
class Subject {
	private List<Observer> observers = new ArrayList<>();
	private String state;
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(state);
		}
	}
	public void setState(String state) {
		this.state = state;
		notifyObservers();
	}
}

//Observer interface
interface Observer {
	void update(String state);
}

//Concrete Observer
class ConcreteObserver implements Observer {
	private String name;
	public ConcreteObserver(String name) {
		this.name = name;
	}
	@Override
	public void update(String state) {
		System.out.println(name + " received state update: " + state);
	}
}