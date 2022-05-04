package javaOOP;

import java.time.LocalDate;
import java.time.LocalTime;

public class Oop2 extends Oop01 {
	private String phoneNumber;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public static void main(String[] args) {
		Oop2 myObj = new Oop2();
		myObj.setFirstName("Tam Anh");
		myObj.setLastName("Hoang");
		myObj.setPhoneNumber("09876543221");
		System.out.println("Name: " + myObj.getFirstName() + " " + myObj.getLastName());
		System.out.println("Phone Number: " + myObj.getPhoneNumber());
		
		LocalTime myObjd = LocalTime.now();
		System.out.println(myObjd);
		
		LocalDate today = LocalDate.now();
		System.out.println(today);
	}
	
	
}
