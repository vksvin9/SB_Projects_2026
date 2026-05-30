package com.vin.java2508;

public class A001_MainMethod {
//  public static void main(String[] args) { // Simple
//  public static void main(String... args) {  // varargs form
//  public static void main(String []args) { // brackets after variable
//  public static void main(String args[]) { // brackets after variable
     static public void main(String[] args) { // Still valid
  	for(String arg : args) {
  		System.out.println(arg);
  	}
  }
}
