package com.vin.java2508;

//---------------- State Interface ----------------
interface State {
 void handle(); // Each concrete state will implement its own behavior
}
//---------------- Concrete States ----------------
class StartState implements State {
 @Override
 public void handle() {
     System.out.println("Starting...");
     // Output: Starting...
 }
}
class StopState implements State {
 @Override
 public void handle() {
     System.out.println("Stopping...");
     // Output: Stopping...
 }
}
//---------------- Context ----------------
class Context {
 private State state;
 // Set current state
 public void setState(State s) {
     this.state = s;
 }
 // Delegate behavior to current state
 public void execute() {
     state.handle();
 }
}
//---------------- Main (Test Class) ----------------
public class B005_StateDP {
 public static void main(String[] args) {
     System.out.println("=== State Pattern Demo ===");
     // Output: === State Pattern Demo ===
     Context context = new Context();
     // Set StartState
     context.setState(new StartState());
     context.execute();
     // Output: Starting...
     // Change to StopState
     context.setState(new StopState());
     context.execute();
     // Output: Stopping...
 }
}