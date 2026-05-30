package com.vin.java2503;

public class G002a_Computer {
	    private String CPU;
	    private String RAM;
	    private String HDD;
	    private String GPU;
	    private boolean isBluetoothEnabled;
	    private boolean isWiFiEnabled;
	    // Private constructor to prevent direct instantiation
	    private G002a_Computer(Builder builder) {
	        this.CPU = builder.CPU;
	        this.RAM = builder.RAM;
	        this.HDD = builder.HDD;
	        this.GPU = builder.GPU;
	        this.isBluetoothEnabled = builder.isBluetoothEnabled;
	        this.isWiFiEnabled = builder.isWiFiEnabled;
	    }
	    @Override
	    public String toString() {
	        return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", HDD=" + HDD + ", GPU=" + GPU + ", Bluetooth=" 
	                + isBluetoothEnabled + ", WiFi=" + isWiFiEnabled + "]";
	    }
	    // Static nested Builder class
	    public static class Builder {
	        private String CPU;
	        private String RAM;
	        private String HDD;
	        private String GPU;
	        private boolean isBluetoothEnabled;
	        private boolean isWiFiEnabled;
	        public Builder(String CPU, String RAM, String HDD) {
	            this.CPU = CPU;
	            this.RAM = RAM;
	            this.HDD = HDD;
	        }
	        public Builder setGPU(String GPU) {
	            this.GPU = GPU;
	            return this;
	        }
	        public Builder setBluetoothEnabled(boolean isBluetoothEnabled) {
	            this.isBluetoothEnabled = isBluetoothEnabled;
	            return this;
	        }
	        public Builder setWiFiEnabled(boolean isWiFiEnabled) {
	            this.isWiFiEnabled = isWiFiEnabled;
	            return this;
	        }
	        // Build the final object
	        public G002a_Computer build() {
	            return new G002a_Computer(this);
	        }
	    }
	}
