package com.vin.java2508;

import java.util.regex.Pattern;

public class C006_Regex {
	public static void main(String[] args) {
		String input = "test123@example.com";
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,6}$";
		boolean isMatch = Pattern.matches(regex, input);
		System.out.println("Is valid email? " + isMatch);
	}
}