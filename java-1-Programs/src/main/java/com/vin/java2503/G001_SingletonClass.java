package com.vin.java2503;

class Test1{
	private static Test1 t = new Test1();
	private Test1(){}
		public static Test1 getTest(){
	return t;
	}
}

class Test2{
	private static Test2 t = null; 
	private Test2(){}
	public static Test2 getTest(){ 
		if(t==null){
			t = new Test2();
		}
		return t;
	}
}

public class G001_SingletonClass {
	public static void main(String[] args) {
		Test1 t11 = Test1.getTest();
		Test1 t12 = Test1.getTest();
		Test2 t21 = Test2.getTest();
		Test2 t22 = Test2.getTest();
		System.out.println("t11 is " + t11.getClass() + " & Hashcode = " + t11.hashCode());
		System.out.println("t12 is " + t12.getClass() + " & Hashcode = " + t12.hashCode());
		System.out.println("t21 is " + t21.getClass() + " & Hashcode = " + t21.hashCode());
		System.out.println("t22 is " + t22.getClass() + " & Hashcode = " + t22.hashCode());
		
	}
}
