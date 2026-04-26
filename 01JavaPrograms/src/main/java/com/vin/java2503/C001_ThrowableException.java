package com.vin.java2503;

public class C001_ThrowableException {
	public static void main(String[] args) throws InvalidAgeException   {
//		tryCatchWithExceptionMethods();
		//here InvalidAgeException is checked exception needs throws declaration or Try catch
		//where as CustomRunTimeException need not to check at compile time
		throwCustomExceptionIfAgeIsLessThanOrEqualTo18(19);
		
	}

	private static void tryCatchWithExceptionMethods() {
		try {
			int i = 1/0;
			System.out.println(i);
		} catch (Exception e) {
			System.out.println("e.getCause()-> "+e.getCause());
			System.out.println("e.getMessage()-> "+e.getMessage());
			System.out.println("e.toString->()"+e.toString());
			System.out.println("e-> "+e);
			System.out.println("e.printStackTrace-> ");
			e.printStackTrace();
		}
	}

	private static void throwCustomExceptionIfAgeIsLessThanOrEqualTo18(int i) throws InvalidAgeException  {
		if (i < 18) {
			throw new InvalidAgeException("You are not eligible"); 
		} else if (i == 18){
			throw new CustomRunTimeException("I am not checked & dont needs the throws");
		} else {
			System.out.println("You are eligible");
		}
	}
}

class InvalidAgeException extends Exception {
	public InvalidAgeException(String str) {
		super(str);
	}
}
class CustomRunTimeException extends RuntimeException{
	public CustomRunTimeException (String str) {
		super(str);
	}
}