package com.vin.java2508;

import java.util.ArrayList;
import java.util.List;

//---------------- Observer Interface ----------------
interface Observer {
 void update(String msg);  // Called when Channel sends update
}
//---------------- Concrete Observer ----------------
class Subscriber implements Observer {
 private String name;
 public Subscriber(String name) {
     this.name = name;
 }
 @Override
 public void update(String msg) {
     System.out.println(name + " received: " + msg);
     // Example: "Vikas received: New video uploaded!"
 }
}
//---------------- Subject (Publisher) ----------------
class Channel {
 private List<Observer> subs = new ArrayList<>();
 // Add subscriber
 void subscribe(Observer o) {
     subs.add(o);
 }
 // Remove subscriber
 void unsubscribe(Observer o) {
     subs.remove(o);
 }
 // Notify all subscribers
 void notifySubs(String msg) {
     for (Observer o : subs) {
         o.update(msg);
     }
 }
}
//---------------- Main Class (Test) ----------------
public class B004_ObserverDP {
 public static void main(String[] args) {
     System.out.println("=== Observer Pattern Demo ===");
     // Output: === Observer Pattern Demo ===
     // Create subject
     Channel myChannel = new Channel();
     // Create observers (subscribers)
     Observer s1 = new Subscriber("Vikas");
     Observer s2 = new Subscriber("Amit");
     Observer s3 = new Subscriber("Ravi");
     // Subscribe observers
     myChannel.subscribe(s1);
     myChannel.subscribe(s2);
     myChannel.subscribe(s3);
     // Notify all
     myChannel.notifySubs("New video uploaded!");
     /*
         Vikas received: New video uploaded!
         Amit received: New video uploaded!
         Ravi received: New video uploaded!
     */
     System.out.println("\n--- Amit unsubscribed ---");
     // Output: --- Amit unsubscribed ---
     myChannel.unsubscribe(s2);
     // Notify again
     myChannel.notifySubs("Second video uploaded!");
     /*
         Vikas received: Second video uploaded!
         Ravi received: Second video uploaded!
     */
 }
}