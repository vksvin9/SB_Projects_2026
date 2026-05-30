package com.vin.java2508;

public class C004_GC {
    String name;
    C004_GC(String name) {
        this.name = name;
    }
    // finalize() ? Called by GC before object is destroyed
    @Override
    protected void finalize() throws Throwable {
        System.out.println("GC is destroying object: " + name); // Output example: GC is destroying object: Object1
    }
    public static void main(String[] args) {
        // 1. Assign null to reference
        C004_GC obj1 = new C004_GC("Object1");
        obj1 = null; // Eligible for GC
        // Expected output (if GC runs): GC is destroying object: Object1

        // 2. Reassign reference to another object
        C004_GC obj2 = new C004_GC("Object2");
        obj2 = new C004_GC("Object3"); // Object2 is eligible for GC
        // Expected output (if GC runs): GC is destroying object: Object2

        // 3. Object goes out of scope
        createObject(); // Object created inside method ? eligible after method ends
        // Expected output (if GC runs): GC is destroying object: TempObject

        // 4. Nullify reference inside another object
        Container1 c = new Container1();
        c.ref = new C004_GC("Object4");
        c.ref = null; // Object4 eligible for GC
        // Expected output (if GC runs): GC is destroying object: Object4

        // 5. Island of isolation
        Island a = new Island("IslandA");
        Island b = new Island("IslandB");
        a.partner = b;
        b.partner = a;
        a = null;
        b = null; // Both eligible for GC (only reference each other)
        // Expected output (if GC runs): GC is destroying object: IslandA
        // Expected output (if GC runs): GC is destroying object: IslandB

        // Suggest garbage collection
        System.out.println("Requesting garbage collection..."); // Output: Requesting garbage collection...
        System.gc();
        // Give some time for GC to run
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
    }
    // Method to demonstrate scope-based GC
    static void createObject() {
        C004_GC temp = new C004_GC("TempObject");
        // temp goes out of scope after method ends
    }
}
// Helper container class
class Container1 {
    C004_GC ref;
}
// Helper class for Island of Isolation example
class Island extends C004_GC {
    Island partner;
    Island(String name) {
        super(name);
    }
}

