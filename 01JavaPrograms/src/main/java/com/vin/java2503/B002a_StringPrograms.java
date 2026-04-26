package com.vin.java2503;

public class B002a_StringPrograms {
    public static void main(String[] args) {
        // 1. String creation
        String str1 = "Hello";
        String str2 = new String("World");

        // 2. length()
        System.out.println("Length of str1: " + str1.length());

        // 3. charAt()
        System.out.println("Character at index 1: " + str1.charAt(1));

        // 4. concat()
        System.out.println("Concatenation: " + str1.concat(str2));

        // 5. equals() and equalsIgnoreCase()
        System.out.println("Equals: " + str1.equals("Hello"));
        System.out.println("EqualsIgnoreCase: " + str1.equalsIgnoreCase("hello"));

        // 6. compareTo() and compareToIgnoreCase()
        System.out.println("CompareTo: " + str1.compareTo("Hello"));
        System.out.println("CompareToIgnoreCase: " + str1.compareToIgnoreCase("hello"));

        // 7. contains()
        System.out.println("Contains 'll': " + str1.contains("ll"));

        // 8. indexOf() and lastIndexOf()
        System.out.println("Index of 'l': " + str1.indexOf('l'));
        System.out.println("Last index of 'l': " + str1.lastIndexOf('l'));

        // 9. startsWith() and endsWith()
        System.out.println("Starts with 'He': " + str1.startsWith("He"));
        System.out.println("Ends with 'lo': " + str1.endsWith("lo"));

        // 10. isEmpty() and isBlank() (Java 11+)
        String emptyStr = "";
        String blankStr = "   ";
        System.out.println("Is empty: " + emptyStr.isEmpty());
//        System.out.println("Is blank: " + blankStr.isBlank());

        // 11. substring()
        System.out.println("Substring (1, 4): " + str1.substring(1, 4));

        // 12. toUpperCase() and toLowerCase()
        System.out.println("Uppercase: " + str1.toUpperCase());
        System.out.println("Lowercase: " + str1.toLowerCase());

        // 13. trim(), strip(), stripLeading(), stripTrailing() (Java 11+)
        String spaced = "  Hello Java  ";
        System.out.println("Trim: '" + spaced.trim() + "'");
//        System.out.println("Strip: '" + spaced.strip() + "'");
//        System.out.println("StripLeading: '" + spaced.stripLeading() + "'");
//        System.out.println("StripTrailing: '" + spaced.stripTrailing() + "'");

        // 14. replace(), replaceAll(), replaceFirst()
        System.out.println("Replace 'l' with 'x': " + str1.replace('l', 'x'));
        System.out.println("ReplaceAll 'l+': " + str1.replaceAll("l+", "*"));
        System.out.println("ReplaceFirst 'l': " + str1.replaceFirst("l", "#"));

        // 15. split()
        String csv = "apple,banana,grape";
        String[] fruits = csv.split(",");
        System.out.print("Split: ");
        for (String fruit : fruits) {
            System.out.print(fruit + " ");
        }
        System.out.println();

        // 16. join()
        System.out.println("Join: " + String.join("-", fruits));

        // 17. toCharArray()
        char[] chars = str1.toCharArray();
        System.out.print("toCharArray: ");
        for (char c : chars) {
            System.out.print(c + " ");
        }
        System.out.println();

        // 18. getBytes()
        byte[] bytes = str1.getBytes();
        System.out.print("getBytes: ");
        for (byte b : bytes) {
            System.out.print(b + " ");
        }
        System.out.println();

        // 19. format()
        String formatted = String.format("Name: %s, Age: %d", "John", 25);
        System.out.println("Formatted: " + formatted);

        // 20. repeat() (Java 11+)
//        System.out.println("Repeat: " + str1.repeat(3));

        // 21. intern()
        String interned = str2.intern();
        System.out.println("Interned equals str2: " + (interned == str2));

        // 22. codePointAt()
        System.out.println("CodePointAt(0): " + str1.codePointAt(0));

        // 23. codePoints()
        System.out.print("CodePoints: ");
        str1.codePoints().forEach(cp -> System.out.print(cp + " "));
        System.out.println();

        // 24. hashCode()
        System.out.println("HashCode: " + str1.hashCode());

        // 25. toString()
        System.out.println("toString(): " + str1.toString());
    }
}
