package com.vin.java2503;

public class G002_BuilderPatternComputer {
	public static void main(String[] args) {
        // Create a Computer object using the Builder pattern
		G002a_Computer myComputer = new G002a_Computer.Builder("Intel i7", "16GB", "1TB")
                                    .setGPU("NVIDIA GTX 1660")
                                    .setBluetoothEnabled(true)
                                    .setWiFiEnabled(true)
                                    .build();
        System.out.println(myComputer);
        // Creating another computer with a different configuration
        G002a_Computer officeComputer = new G002a_Computer.Builder("AMD Ryzen 5", "8GB", "500GB")
                                    .setWiFiEnabled(true)
                                    .build();
        System.out.println(officeComputer);
    }

}
