package com.vin.java2508;

import java.util.function.Supplier;

@FunctionalInterface
interface MyInterface{
	// Abstract method: implemented via lambda, method reference, or anonymous class
	String iAbstractGreet(String str);
	// Default method
	default String iGreetdefault(String str) {
		return "Interface Defalut Method " + str;
	};
	// Static method
	static String iGreetStatic(String str) {
		return "Interface Static Method " + str;
	}
}
class HelperClass{
	// Constructor (constructor reference example)
	HelperClass(){
		System.out.println("HelperClass Constructor called!");
	}
	// Instance method (method reference example)
	String cInstanceGreet(String str) {
		return "Class Instance Method " + str;
	}
	// Static method (method reference example)
	static String cStaticGreet(String str) {
		return "Class Static Method " + str;
	}
	// Static method (string processing)
	static String toUpperCase(String str) {
		return "Class static UC Method " + str.toUpperCase();
	}
	// Instance method (string processing)
	String toLowerCase(String str) {
		return "Class instance LC Method " + str.toLowerCase();
	}
}

public class Z003_UserDefConstructorMethodRef{
	public static void main(String[] args) {
		// 0. Anonymous inner class implementing interface
		MyInterface myint1 = new MyInterface() {
			@Override
			public String iAbstractGreet(String str) {
				return "Interface Abstarct Method using anonymous mthod" + str;
			}
		};
		System.out.println(myint1.iAbstractGreet("Called."));
		// 1. Lambda expression implementing MyInterface Greet
		MyInterface myint2 = str -> "Interface Abstarct Method using Lambda Exp" + str;
		System.out.println(myint2.iAbstractGreet("Called."));
		// 2. Static method call from interface 
		System.out.println(MyInterface.iGreetStatic("Called."));
		// 3. Default method call from interface
		System.out.println(myint2.iGreetdefault("Called"));
		// 4. Static method reference (className::staticMethod)
		MyInterface myint4 = HelperClass::cStaticGreet;
		System.out.println(myint4.iAbstractGreet("iCalled Static Method Ref"));
		// 5. Class Static method via lambda assigned to Interface
		MyInterface myint5 = str -> HelperClass.cStaticGreet(str);
		System.out.println(myint5.iAbstractGreet("iCalled Static Lambda Exp"));
		// 6. Instance method reference (objectRef::instanceMethod)
		HelperClass help = new HelperClass();
		MyInterface myint6 = help::cInstanceGreet;
		System.out.println(myint6.iAbstractGreet("iCalled Instance Method Ref"));
		// 7. Instance method via lambda
		MyInterface myint7 = str -> help.cInstanceGreet(str);
		System.out.println(myint7.iAbstractGreet("iCalled Instance Lambda Exp"));
		// 8. Method reference for string processing (Class Static method for UC using Method Reference)
		MyInterface myint8 = HelperClass::toUpperCase;
		System.out.println(myint8.iAbstractGreet("iCalled Static Method for UC  using Method Ref"));
		// 8a. Lambda equivalents for string processing (Class Static method for UC using Method Reference)
		MyInterface myint8a = str -> HelperClass.toUpperCase(str);
		System.out.println(myint8a.iAbstractGreet("iCalled Static Method for UC  using Lambda Exp"));
		// 9. Method reference for string processing (Class Static method for LC using Method Reference)
		HelperClass help2 = new HelperClass();
		MyInterface myint9 = help2::toLowerCase;
		System.out.println(myint9.iAbstractGreet("iCalled instace Method for LC using Method Ref"));
		// 9a. Lambda equivalents for string processing (Class Static method for UC using Method Reference)
		MyInterface myint9a = str -> help2.toLowerCase(str);
		System.out.println(myint9a.iAbstractGreet("iCalled instace Method for LC using Lambda Exp"));
		// 10. Constructor reference (className::new)
		Supplier<HelperClass> helpCons = HelperClass::new;
		HelperClass help3 = helpCons.get();
		System.out.println(help3.cInstanceGreet("Constructor Called using Constructor Ref"));
		// 10a. Constructor via lambda
		Supplier<HelperClass> helpCons1 = () -> new HelperClass();
		HelperClass help4 = helpCons1.get();
		System.out.println(help4.cInstanceGreet("Constructor Called using Lambda Exp"));
	}
}