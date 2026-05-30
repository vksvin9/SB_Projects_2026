package com.vin.java2508;

//Step 1: Common interface
interface Service {
 void run();
}
//Step 2: Real Service (actual implementation)
class RealService implements Service {
 @Override
 public void run() {
     System.out.println("Service running");
 }
}
//Step 3: Proxy Service (controls access to RealService)
class ProxyService implements Service {
 private RealService realService = new RealService();
 @Override
 public void run() {
     System.out.println("Access check..."); // Proxy adds extra logic
     realService.run(); // Delegate call to real service
 }
}
//Step 4: Client Code
public class B007_ProxyDP {
 public static void main(String[] args) {
     Service service = new ProxyService(); // client only knows about interface
     service.run();
     /*
     ===== Output =====
     Access check...
     Service running
     */
 }
}
