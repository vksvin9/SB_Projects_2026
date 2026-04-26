package com.vin.java2508;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class C003_StringClass {
    public static void main(String[] args) {
        // 1. Checking Length & Characters
        // int length() → returns number of characters
        System.out.println("hello".length());                     // 5
        // char charAt(int index) → returns char at given index
        System.out.println("java".charAt(2));                     // v
        // int codePointAt(int index) → Unicode code point at index
        System.out.println("A".codePointAt(0));                   // 65
        // int codePointBefore(int index) → Unicode code point before index
        System.out.println("ABC".codePointBefore(1));             // 65
        // int codePointCount(int beginIndex, int endIndex) → number of Unicode code points between indexes
        System.out.println("hello".codePointCount(0, 5));         // 5
        // 2. Comparison
        // boolean equals(Object another) → checks if two strings are exactly equal
        System.out.println("cat".equals("cat"));                  // true
        // boolean equalsIgnoreCase(String another) → compares ignoring case
        System.out.println("Hi".equalsIgnoreCase("hi"));          // true
        // int compareTo(String another) → compares lexicographically
        System.out.println("a".compareTo("b"));                   // -1
        // int compareToIgnoreCase(String another) → compares lexicographically ignoring case
        System.out.println("Apple".compareToIgnoreCase("apple")); // 0
        // boolean contentEquals(CharSequence cs) → checks equality with CharSequence
        System.out.println("test".contentEquals(new StringBuffer("test"))); // true
        // boolean regionMatches(int toffset, String other, int ooffset, int len) → compares specific regions
        System.out.println("hello".regionMatches(0,"heaven",0,2)); // true
        // boolean startsWith(String prefix) → checks if string starts with prefix
        System.out.println("java".startsWith("ja"));              // true
        // boolean startsWith(String prefix, int offset) → checks if substring from index starts with prefix
        System.out.println("java".startsWith("va",2));           // true
        // boolean endsWith(String suffix) → checks if string ends with suffix
        System.out.println("world".endsWith("ld"));               // true
        // 3. Searching
        // int indexOf(int ch) → returns first index of given character
        System.out.println("banana".indexOf('a'));                // 1
        // int indexOf(String str) → returns first index of given substring
        System.out.println("banana".indexOf("na"));               // 2
        // int indexOf(int ch, int fromIndex) → returns index of character from given index
        System.out.println("banana".indexOf('a',2));             // 3
        // int indexOf(String str, int fromIndex) → returns index of substring from given index
        System.out.println("banana".indexOf("na",3));            // 4
        // int lastIndexOf(int ch) → returns last index of character
        System.out.println("banana".lastIndexOf('a'));            // 5
        // int lastIndexOf(String str) → returns last index of substring
        System.out.println("banana".lastIndexOf("na"));           // 4
        // int lastIndexOf(int ch, int fromIndex) → returns last index of character before given index
        System.out.println("banana".lastIndexOf('a',4));         // 3
        // int lastIndexOf(String str, int fromIndex) → returns last index of substring before given index
        System.out.println("banana".lastIndexOf("na",3));        // 2
        // boolean contains(CharSequence s) → checks if string contains given sequence
        System.out.println("hello".contains("ell"));             // true
        // 4. Substrings & Extraction
        // String substring(int beginIndex) → returns substring from given index to end
        System.out.println("universe".substring(3));             // verse
        // String substring(int beginIndex, int endIndex) → returns substring between begin and end index
        System.out.println("universe".substring(0,3));           // uni
        // 5. Case Conversion
        // String toUpperCase() → converts all characters to uppercase
        System.out.println("java".toUpperCase());                // JAVA
        // String toUpperCase(Locale locale) → converts to uppercase using locale rules
        System.out.println("i".toUpperCase(Locale.forLanguageTag("tr"))); // İ
        // String toLowerCase() → converts all characters to lowercase
        System.out.println("JAVA".toLowerCase());                // java
        // String toLowerCase(Locale locale) → converts to lowercase using locale rules
        System.out.println("İ".toLowerCase(Locale.forLanguageTag("tr"))); // i
        // 6. Trimming & Empty Checks
        // String trim() → removes leading/trailing spaces
        System.out.println("  hi  ".trim());                     // hi
        // boolean isEmpty() → returns true if string length is 0
        System.out.println("".isEmpty());                        // true
//        // boolean isBlank() → returns true if string is empty or only whitespace (Java 11+)
//        System.out.println("   ".isBlank());                     // true
        // 7. Modification (returns new String)
        // String replace(char oldChar, char newChar) → replaces all occurrences of a char
        System.out.println("car".replace('c','b'));              // bar
        // String replace(CharSequence target, CharSequence replacement) → replaces all occurrences of a sequence
        System.out.println("hello".replace("l","x"));            // hexxo
        // String replaceAll(String regex, String replacement) → replaces all matches of regex
        System.out.println("a1b2".replaceAll("\\d",""));         // ab
        // String replaceFirst(String regex, String replacement) → replaces first match of regex
        System.out.println("123abc".replaceFirst("\\d","X"));    // X23abc
        // String concat(String str) → joins given string to end
        System.out.println("hello".concat(" world"));            // hello world
        // String join(CharSequence delimiter, CharSequence... elements) → joins elements with delimiter
        System.out.println(String.join("-", "a","b","c"));       // a-b-c
//        // String repeat(int count) → repeats string given times (Java 11+)
//        System.out.println("ha".repeat(3));                      // hahaha
        // 8. Splitting & Joining
        // String[] split(String regex) → splits string around matches of regex
        System.out.println(Arrays.toString("a,b,c".split(",")));      // [a, b, c]
        // String[] split(String regex, int limit) → splits string with limit on number of parts
        System.out.println(Arrays.toString("a,b,c".split(",",2)));    // [a, b,c]
        // 9. Conversion
        // byte[] getBytes() → converts string to byte array using platform default charset
        System.out.println(Arrays.toString("ABC".getBytes()));                       // [65, 66, 67]
        // byte[] getBytes(Charset charset) → converts string to byte array with given charset
        System.out.println(Arrays.toString("ABC".getBytes(StandardCharsets.UTF_8))); // [65, 66, 67]
        // char[] toCharArray() → converts string to character array
        System.out.println(Arrays.toString("java".toCharArray()));                   // [j, a, v, a]
        // static String valueOf(int i) → converts primitive to string
        System.out.println(String.valueOf(100));                                      // 100
        // static String valueOf(char[] data) → converts char array to string
        System.out.println(String.valueOf(new char[]{'h','i'}));                      // hi
        // static String format(String format, Object... args) → returns formatted string
        System.out.println(String.format("Hi %s, %d!", "Bob", 5));                    // Hi Bob, 5!
//        // static String join(CharSequence delimiter, Iterable<? extends CharSequence> elements) → joins elements with delimiter
//        System.out.println(String.join(",", List.of("x","y","z")));                    // x,y,z
        // 10. Interning
        // String intern() → returns a canonical representation of the string from the string pool
        System.out.println(new String("java").intern() == "java");                     // true
    }
}