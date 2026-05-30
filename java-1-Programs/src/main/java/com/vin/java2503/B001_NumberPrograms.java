package com.vin.java2503;

/*Logic for below are same
=============================
a)ReverseNumber
	while (num > 0) {
		int rem = num % 10;
		rev = (rev * 10) + rem;//changes will made here 
		num = num / 10;
	}	
b)Palinedrome
	(original == rev)?palinedrome:notPalinedrome;
c)Sum
	while (num > 0) {
		int rem = num % 10;
		rev = rev + rem;//changes will made here 
		num = num / 10;
	}	
d)Product
	while (num > 0) {
		int rem = num % 10;
		rev = rev + rem;//changes will made here 
		num = num / 10;
	}	
e)Count
	int count = 0;
	while (num > 0) {
		count++;
		num = num / 10;
	}
f)Armstrong
	//get count i.e pow
	int count = 0; temp = num;
	while (num > 0) {
		count++;
		num = num / 10;
	}
	//rev Formula
	while (temp > 0) {
			rem = temp % 10;// last digit
			as = (int) (as + Math.pow(rem, pow));//changes are here
			temp = temp / 10;
	}
=============================
a)swap
	temp = a;
	a = b;
	b = temp;
b)fibonacci
	for (int i = 2; i <= count; i++) {
		c = a + b;
		System.out.print(" " + c);
		a = b;
		b = c;
	}
c)sort
	for (int i = 0; i < a.length; i++) {
		for (int j = i + 1; j < a.length; j++) {
			if (a[i] > a[j]) {
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}
	}
=============================*/
import java.util.Scanner;

public class B001_NumberPrograms {
	public static void main(String[] args) {
//		standardInput();
//		numberConversion("25");
//		checkPositive(-1);
//		checkIfPerfectSquare(20);
//		checkOddEvenWithScanner();
//		getOddAndEvenUptoN(8);
//		countOddsNEvens(new int[] {1,2,3,4,5,6,7});
//		checkPrimeNumber(7);
//		getPrimeNumberUptoN(100);
//		findLargestUsingTernary(10,5,9);
//		isLeapYear(2004);
//		findHCF(10,20);
//		findLCM(10,20);
//		swapNumerWithT(10, 20);
//		swapNumerWithoutT(10, 20);
//		getFibonacci(10);
//		getFactorialWhileDec(5);
//		getFactorialWhileInc(5);
//		getFactorialForLoop(5);
//		getCountWhile(12345);
//		getCountStingVal(12345);
//		getSum(12345);
//		getProduct(12345);
//		reverseString("Raja");
//		checkPalinedrome("NAYAN");
//		reverseNumWhile(12345);
//		reverseNumStringValForLoop(12345);
//		checkArmstrong(153);
//		getSI(100, 10, 3);
//		getCI(100, 10, 3);
//		wrapperClass();
//		mathClass(10, 20);
	}

	private static void standardInput() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Name :- ");
		String name = sc.nextLine();
		System.out.println("Welcome " + name);

		System.out.println("Enter Nick Name :- ");
		String nickName = sc.next();
		System.out.println("Welcome " + nickName);

		System.out.println("Enter Age :- ");
		int age = sc.nextInt();
		System.out.println("Your Age in int is " + age);

		System.out.println("Enter Salary :- ");
		float b = sc.nextFloat();
		System.out.println("You Salary in float is " + b);
		sc.close();
	}

	private static void numberConversion(String str) {
		int number = Integer.valueOf(str);
		System.out.println("Integer: " + number);
		System.out.println("Decimal: " + number);
		System.out.println("Binary: " + Integer.toBinaryString(number));
		System.out.println("Octal: " + Integer.toOctalString(number));
		System.out.println("Hexadecimal: " + Integer.toHexString(number).toUpperCase());
	}

	private static void checkPositive(int i) {
		System.out.println((i > 0) ? i + " is +ve" : i + " is -ve");
	}

	private static void checkIfPerfectSquare(int i) {
		double sqrt = Math.sqrt(i);
		double floor = Math.floor(sqrt);
		double diff = sqrt - floor;
		System.out.println("sqrt -> " + sqrt + ", floor -> " + floor + ", diff -> " + diff);
		System.out.println((diff == 0) ? "perfect square" : "not perfect square");
	}

	private static void findLargestUsingTernary(int a, int b, int c) {
		int i = (a > b) ? ((a > c) ? a : b) : ((b > c) ? b : c);
		System.out.println(i + " is largest.");
	}

	private static void checkOddEvenWithScanner() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number: ");
		int in = sc.nextInt();
		System.out.println((in % 2 == 0) ? "Even" : "Odd");
		sc.close();
	}

	private static void getOddAndEvenUptoN(int in) {
		String Odds = "", Evens = "";
		for (int i = 1; i <= in; i++) {
			if (i % 2 == 0) {
				Evens = Evens + i + ", ";
			} else {
				Odds = Odds + i + ", ";
			}
		}
		System.out.println("Evens = " + Evens);
		System.out.println("Odds = " + Odds);
	}

	private static void countOddsNEvens(int[] inArr) {
		int OddCount = 0, EvenCount = 0;
		for (int i = 0; i < inArr.length; i++) {
			if (inArr[i] % 2 == 0) {
				EvenCount = EvenCount + 1;
			} else {
				OddCount = OddCount + 1;
			}
		}
		System.out.println("EvenCount = " + EvenCount);
		System.out.println("OddCount = " + OddCount);
	}

	private static void checkPrimeNumber(int in) {
		int rem = 0;
		boolean flag = false;
		for (int i = 2; i <= in / 2; i++) {
			rem = in % i;
			if (rem == 0) {
				flag = true;
				break;
			}
		}
		if (flag == true)
			System.out.println("not prime");
		else
			System.out.println("prime");
	}

	private static void getPrimeNumberUptoN(int in) {
		System.out.print("Prime numbers up to " + in + ": ");
		for (int i = 2; i <= in; i++) {
			if (isPrime(i)) {
				System.out.print(i + " ");
			}
		}
	}

	static boolean isPrime(int num) {
		if (num < 2)
			return false;
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	static void isLeapYear(int year) {
		int isLeapYear = 0;
		if (year % 4 == 0) {
			isLeapYear = 1;
			if (year % 100 == 0) {
				if (year % 400 != 0) {
					isLeapYear = 0;
				}
			}
		}
		System.out.println((isLeapYear == 1) ? "Leap Year" : "Not Leap Year");
	}
	
	// Method to find HCF (GCD) using Euclidean Algorithm
    static void findHCF(int a, int b) {
        int originalA = a, originalB = b;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        System.out.println("HCF of " + originalA + " and " + originalB + " is: " + a);
    }

    // Method to find LCM using formula: LCM(a, b) = (a * b) / HCF(a, b)
    static void findLCM(int a, int b) {
        int gcd = a, x = a, y = b;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        gcd = a;  // Final GCD value
        int lcm = (x * y) / gcd;
        System.out.println("LCM of " + x + " and " + y + " is: " + lcm);
    }

	private static void swapNumerWithT(int a, int b) {
		System.out.println("Before swapping :- a = " + a + ", b = " + b);
		int t = 0;
		t = a;
		a = b;
		b = t;
		System.out.println("Before swapping :- a = " + a + ", b = " + b);
	}

	private static void swapNumerWithoutT(int a, int b) {
		System.out.println("Before swapping :- a = " + a + ", b = " + b);
		int c = 0;
		a = a + b;
		b = a - b;
		a = a - b;
		System.out.println("Before swapping :- a = " + a + ", b = " + b);
	}

	private static void getFibonacci(int count) {
		// The Fibonacci sequence is the series of numbers: 0, 1, 1, 2, 3, 5...
		// 0,1,0+1,1+1,1+2,2+3...
		int a = 0, b = 1, c = 0;
		count = 5;
		System.out.print(a + " " + b);

		for (int i = 2; i <= count; i++) {
			c = a + b;
			System.out.print(" " + c);
			// swaping number
			a = b;
			b = c;
		}
	}

	private static void getFactorialWhileDec(int in) {
		int fact = 1;
		while (in > 0) {
			fact = fact * in;
			in--;
		}
		System.out.println("Factorial = " + fact);
	}

	private static void getFactorialWhileInc(int in) {
		int fact = 1;
		while (in > 0) {
			fact = fact * in;
			in--;
		}
		System.out.println("Factorial = " + fact);
	}

	private static void getFactorialForLoop(int in) {
		int fact = 1, i = 1;
		while (i <= in) {
			fact = fact * i;
			i++;
		}
		System.out.println("Factorial = " + fact);
	}

	private static void getCountWhile(int in) {
		int count = 0;
		while (in > 0) {
			count = count + 1;
			in = in / 10;
		}
		System.out.println("Count = " + count);
	}

	private static void getCountStingVal(int in) {
		String inStr = String.valueOf(in);
		System.out.println("Count = " + inStr.length());
	}

	private static void getSum(int in) {
		int sum = 0;
		while (in > 0) {
			int rem = in % 10;
			sum = sum + rem;
			in = in / 10;
		}
		System.out.println("Sum = " + sum);
	}

	private static void getProduct(int in) {
		int product = 1;
		while (in > 0) {
			int rem = in % 10;
			product = product * rem;
			in = in / 10;
		}
		System.out.println("Product = " + product);
	}

	private static void reverseString(String str) {
		String rev = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			rev = rev + str.charAt(i);
		}
		System.out.println(rev);
	}

	private static void checkPalinedrome(String str) {
		String rev = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			rev = rev + str.charAt(i);
		}
		System.out.println("input -> " + str + ", reverese -> " + rev);
		System.out.println((str.equals(rev)) ? "Palindrome" : "Not Palindrome");
	}

	private static void reverseNumWhile(int in) {
		System.out.println("in = " + in);
		int rev = 0;
		while (in > 0) {
			int rem = in % 10;
			rev = (rev * 10) + rem;
			in = in / 10;
		}
		System.out.println("rev = " + rev);
	}

	private static void reverseNumStringValForLoop(int in) {
		System.out.println("in = " + in);
		System.out.print("rev = ");
		String inStr = String.valueOf(in);
		for (int i = inStr.length() - 1; i >= 0; i--) {
			System.out.print(inStr.charAt(i));
		}

	}

	private static void checkArmstrong(int in) {
		// same as reverse change in formula only

//				int pow = String.valueOf(in).length();
//				System.out.println("pow = "+ pow);
		int pow = 0, temp = in;
		while (temp > 0) {
			pow++;
			temp = temp / 10;
		}
		System.out.println("pow = " + pow);
		int rem = 0;
		int as = 0;

		temp = in;
		while (temp > 0) {
			rem = temp % 10;// last digit
			as = (int) (as + Math.pow(rem, pow));// changes are here
			temp = temp / 10;
		}
		if (as == in) {
			System.out.println("Armstrong Number");
		} else {
			System.out.println("Not Armstrong Number");
		}
	}
	
	private static void getSI(int P, int R, int N) {
		float SI = (P * R * N) / 100;
		float Amt = P + SI; 
		System.out.println("Simple Interest is " + SI);
		System.out.println("Total Amount is " + Amt);
	}

	private static void getCI(double  P, double R, double N) {
		double Amt = P * Math.pow((1 + R / 100), N);
        double CI = Amt - P; 
		System.out.println("Compound Interest is " + CI);
		System.out.println("Total Amount is " + Amt);
	}
	
	private static void wrapperClass() {
		byte b=10;  
		short s=20;  
		int i=30;  
		long l=40;  
		float f=50.0F;  
		double d=60.0D;  
		char c='a';  
		boolean b2=true;  
		  
		//Autoboxing: Converting primitives into objects  
		Byte byteobj=b;  
		Short shortobj=s;  
		Integer intobj=i;  
		Long longobj=l;  
		Float floatobj=f;  
		Double doubleobj=d;  
		Character charobj=c;  
		Boolean boolobj=b2;  
		  
		//Printing objects  
		System.out.println("---Printing object values---");  
		System.out.println("Byte object: "+byteobj);  
		System.out.println("Short object: "+shortobj);  
		System.out.println("Integer object: "+intobj);  
		System.out.println("Long object: "+longobj);  
		System.out.println("Float object: "+floatobj);  
		System.out.println("Double object: "+doubleobj);  
		System.out.println("Character object: "+charobj);  
		System.out.println("Boolean object: "+boolobj);  
		  
		//Unboxing: Converting Objects to Primitives  
		byte bytevalue=byteobj;  
		short shortvalue=shortobj;  
		int intvalue=intobj;  
		long longvalue=longobj;  
		float floatvalue=floatobj;  
		double doublevalue=doubleobj;  
		char charvalue=charobj;  
		boolean boolvalue=boolobj;  
		  
		//Printing primitives  
		System.out.println("---Printing primitive values---");  
		System.out.println("byte value: "+bytevalue);  
		System.out.println("short value: "+shortvalue);  
		System.out.println("int value: "+intvalue);  
		System.out.println("long value: "+longvalue);  
		System.out.println("float value: "+floatvalue);  
		System.out.println("double value: "+doublevalue);  
		System.out.println("char value: "+charvalue);  
		System.out.println("boolean value: "+boolvalue);		
	}


	private static void mathClass(int x, int y) {
		 // return the maximum of two numbers  
        System.out.println("Maximum number of x and y is: " +Math.max(x, y));   
          
        // return the square root of y   
        System.out.println("Square root of y is: " + Math.sqrt(y));   
          
        //returns 28 power of 4 i.e. 28*28*28*28    
        System.out.println("Power of x and y is: " + Math.pow(x, y));      
  
        // return the logarithm of given value       
        System.out.println("Logarithm of x is: " + Math.log(x));   
        System.out.println("Logarithm of y is: " + Math.log(y));  
          
        // return the logarithm of given value when base is 10      
        System.out.println("log10 of x is: " + Math.log10(x));   
        System.out.println("log10 of y is: " + Math.log10(y));    
          
        // return the log of x + 1  
        System.out.println("log1p of x is: " +Math.log1p(x));    
  
        // return a power of 2    
        System.out.println("exp of a is: " +Math.exp(x));    
          
        // return (a power of 2)-1  
        System.out.println("expm1 of a is: " +Math.expm1(x));  
		
	}

}
