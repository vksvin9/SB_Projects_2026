package com.vin.java2508;

//Step 1: Old interface (USB)
interface USB {
 void connect();
}
//Step 2: Old implementation
class USBImpl implements USB {
 @Override
 public void connect() {
     System.out.println("USB connected");
 }
}
//Step 3: New interface (TypeC)
interface TypeC {
 void plug();
}
//Step 4: Adapter (converts TypeC requests to USB)
class Adapter implements TypeC {
 private USB usb;
 Adapter(USB usb) {
     this.usb = usb;
 }
 @Override
 public void plug() {
     // Adapter translates 'plug()' call into 'connect()'
     usb.connect();
 }
}
//Step 5: Client code
public class B008_AdapterDP {
 public static void main(String[] args) {
     USB usbDevice = new USBImpl();           // existing USB device
     TypeC typeCAdapter = new Adapter(usbDevice); // wrap in adapter
     typeCAdapter.plug(); // client uses TypeC but internally USB works
     /*
     ===== Output =====
     USB connected
     */
 }
}