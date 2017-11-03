package com.example.bot.spring;

public class Client {

	private String cutstomerId;
	private String name;
	private int phoneNumber;
	private int age;
	
	public Client(String cutstomerId, String name, int phoneNumber, int age) {
		this.cutstomerId = cutstomerId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.age = age;
	}
	
	public String getClientID() {
		return cutstomerId;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public int getAge() {
		return age;
	}

}
