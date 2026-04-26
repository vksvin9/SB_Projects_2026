package com.vin.java2508;

//Step 1: Define Iterator interface
interface Iterator<T> {
 boolean hasNext();
 T next();
}
//Step 2: Define Aggregate interface (collection)
interface Container<T> {
 Iterator<T> getIterator();
}
//Step 3: Concrete Collection
class NameRepository implements Container<String> {
 private String[] names = {"Alice", "Bob", "Charlie", "David"};
 // Return iterator for names
 @Override
 public Iterator<String> getIterator() {
     return new NameIterator();
 }
 // Inner class implementing Iterator
 private class NameIterator implements Iterator<String> {
     int index = 0;
     @Override
     public boolean hasNext() {
         return index < names.length;
     }
     @Override
     public String next() {
         if (this.hasNext()) {
             return names[index++];
         }
         return null;
     }
 }
}
//Step 4: Test the Iterator Pattern
public class B006_IteratorDP {
 public static void main(String[] args) {
     NameRepository repo = new NameRepository();
     // Get iterator from repository
     Iterator<String> it = repo.getIterator();
     // Iterate through collection
     while (it.hasNext()) {
         System.out.println("Name: " + it.next());
     }
     /*
     ===== Output =====
     Name: Alice
     Name: Bob
     Name: Charlie
     Name: David
     */
 }
}
