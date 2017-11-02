package com.example.bot.spring;

public class Client {
	private String clientID;
	private String name;
	private int phoneNumber;
	private int age;
	
	public Client(String clientID, String name, int phoneNumber, int age) {
		this.clientID = clientID;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.age = age;
	}
	
	public String getClientID() {return clientID;}
	public String getName() {return name;}
	public int getPhoneNumber() {return phoneNumber;}
	public int getAge() {return age;}
}
