//package com.rev.java2503;
//
//import org.json.JSONObject;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//
//public class C003_JSONObject {
//	public static void main(String[] args) {
//		String jsonStr = "{\"id\":1,\"firstName\":\"Vikas\",\"lastName\":\"Vinayak\"}";
//		//JSON Object
//		jsonObj(jsonStr);
//		
//		Employee emp = new Employee();
//		emp.setId(01);
//		emp.setFirstName("Vikas");
//		emp.setLastName("Vinayak");
//
//		//GSON			
//		gsonObj(emp);
//		//Jackson
//		jacksonObj(emp);
//	}
//
//	private static void jsonObj(String str) {
//		System.out.println("A004a_Employee Obj = "+str);
//		JSONObject jsonObj = new JSONObject(str); 
//		System.out.println("JSON Obj = "+jsonObj);
//		System.out.println("JSONObj.getId = "+jsonObj.get("id"));
//		System.out.println("JSONObj.getFirstName = "+jsonObj.get("firstName"));
//		System.out.println("JSONObj.getLastName = "+jsonObj.get("lastName"));
//	}
//
//	private static void gsonObj(Employee emp) {
//		System.out.println("Gson.jsonStr = "+new Gson().toJson(emp));
//		String jsonStr = new Gson().toJson(emp);
//		System.out.println("Gson.javaObj = "+new Gson().fromJson(jsonStr, Employee.class));
//	}
//
//	private static void jacksonObj(Employee emp) {
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//            String jsonString = objectMapper.writeValueAsString(emp);
//            System.out.println("JSON String: " + jsonString);
//            Employee emp1 = objectMapper.readValue(jsonString, Employee.class);
//            System.out.println("Id: " + emp1.getId());
//            System.out.println("FisrtName: " + emp1.getFirstName());
//            System.out.println("LastName: " + emp1.getFirstName());
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//	}
//}
//class Employee {
//	private int id;
//	private String firstName;
//	private String lastName;
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	@Override
//	public String toString() {
//		return "A004a_Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
//	}
//
//}


