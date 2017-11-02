package com.example.bot.spring;


public class UserInterface {
	
	private String message ;
	private Manager manager ; 
	public String getMessage() { return message ; }
	public abstract void showMessage() {}
	private void processInput(String userReply) ; 
}
