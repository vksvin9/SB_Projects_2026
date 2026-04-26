package com.vin.java2503;

public class F010b_StaticInnerClassExample {
	private static String str = "Javatpoint";

	public static class StaticDemo {
		public void show() {
			System.out.println(str);
		}
	}

	public static void main(String args[]) {
		F010b_StaticInnerClassExample.StaticDemo obj = new F010b_StaticInnerClassExample.StaticDemo();
		obj.show();
	}
}