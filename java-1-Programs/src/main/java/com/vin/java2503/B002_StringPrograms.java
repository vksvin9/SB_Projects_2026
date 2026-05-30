package com.vin.java2503;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B002_StringPrograms {
	public static void main(String[] args) {
//		getPattern();
//		getAsciiInt(255);
//		getAsciiChar('a');
//		getAscii1toN(255);
//		findVowelsNConsonants("All the best!");
//		findSpecialCharcter("");
//		capitalizedFirstChar("how are you?");
//		checkAnagram("vikas","svika");
//		reverseEachWordSb("how are you?");
//		reverseEachWordForLoop("how are you?");
//		reverseEachWordForEachLoop("how are you?");
//		countDuplicateWord1("test one two one two three test");
//		countDuplicateWord2("test one two one two three test");
//		countDuplicateCharacter("hellow, how are you?");
		
	}

	private static void getPattern() {
		for (int i = 1; i <= 3; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	public static void getAsciiInt(int i) {
		char ch = (char) i;
		System.out.println("ASCII for " + i + " is " + ch);
	}

	public static void getAsciiChar(char ch) {
		int i = ch;
		System.out.println("ASCII for " + ch + " is " + i);
	}

	public static void getAscii1toN(int in) {
		for(int i=0; i<=in; i++) {
			char ch = (char)i;
			System.out.print(ch + " ");
		}
	}		

	private static void findVowelsNConsonants(String str) {
		String s = str.replace(" ", "");
		String vow = "";
		int v = 0;
		String con = "";
		int co = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O'
					|| c == 'U') {
				vow = vow + c;
				v++;
			} else {
				con = con + c;
				co++;
			}
		}
		System.out.println("Vowels: " + vow);
		System.out.println("vowels count " + v);
		System.out.println("Consonants: " + con);
		System.out.println("Consonants count " + co);

		
	}

	private static void capitalizedFirstChar(String str) {
//		String[] words = str.split("\\s+");
		String[] words = str.split(" ");
		StringBuilder cap = new StringBuilder();
		for(String word : words) {
			cap.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
		}
		System.out.println("str = "+str);
		System.out.println("cap = "+cap);
	}
	
	private static void checkAnagram(String s1, String s2) {
		if (s1.length() == s2.length()) {
			// To convert String into Character array
			char[] c1 = s1.toCharArray();
			char[] c2 = s2.toCharArray();
			// Arrays--utility class,u can use those predefined methods in Array variable
			Arrays.sort(c1);
			Arrays.sort(c2);
			boolean x = Arrays.equals(c1, c2);
			System.out.println(x?"":"");
		}
	}
	
	private static void reverseEachWordSb(String str) {
		String[] words = str.split(" ");
		StringBuilder rev = new StringBuilder();
		for(String word : words) {
			StringBuilder sb = new StringBuilder(word);
			rev.append(sb.reverse() + " ");
		}
		System.out.println("in : " + str);
		System.out.println("rev : " + rev);
	}
	
	public static void reverseEachWordForLoop(String str) {
		String revWord = "", revIn = "";
		String[] words = str.split(" ");
		int i = 0;
		for (i = 0; i < words.length; i++) {
			revWord = "";
			String word = words[i];
			for (int j = word.length() - 1; j >= 0; j--) {
				char ch = word.charAt(j);
				revWord = revWord + ch;
			}
			revIn = revIn + revWord + " ";
			System.out.println("in : " + str);
			System.out.println("revIn : " + revIn);
		}
	}
	
	public static void reverseEachWordForEachLoop(String str) {
		String revWord = "", revIn = "";
		String[] words = str.split(" ");
		for (String word : words) {
			revWord = "";
			for (int i = word.length() - 1; i >= 0; i--) {
				char ch = word.charAt(i);
				revWord = revWord + ch;
			}
			revIn = revIn + revWord + " ";
		}
		System.out.println("in : " + str);
		System.out.println("revIn : " + revIn);
	}
	private static void countDuplicateWord1(String str) {
		String[] words = str.split(" ");
		LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();
		for(String word : words) {
			if(lhm.containsKey(word)) {
				Integer i = lhm.get(word);
				lhm.put(word, i+1);
			}else {
				lhm.put(word, 1);
			}
		}
		System.out.println(lhm);
		
	}

	private static void countDuplicateWord2(String str) {
		LinkedHashSet<String> lhs = new LinkedHashSet<>();
		String[] words = str.split(" ");
		int count = 0;
		for (String word : words) {
			if (!lhs.add(word)) {
				count++;
				System.out.println("Duplicate = " + word);
			}
		}
		System.out.print("Count -> " + count);
	}
	
	private static void countDuplicateCharacter(String str) {
		char[] chs = str.toCharArray();
		LinkedHashMap<Character, Integer> lhm = new LinkedHashMap<>();
		for(char ch : chs ) {
			if(lhm.containsKey(ch)) {
				Integer i = lhm.get(ch);
				lhm.put(ch, i+1);
			}else {
				lhm.put(ch, 1);
			}
		}
		System.out.println(lhm);
	}
	
	
}
