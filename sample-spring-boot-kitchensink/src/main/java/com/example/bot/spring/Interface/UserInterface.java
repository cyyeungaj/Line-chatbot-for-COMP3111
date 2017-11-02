package com.example.bot.spring;


public abstract class UserInterface {
	
	private String message ;
	private Manager manager ; 
	public String getMessage() { return message ; }
	public abstract void showMessage() ; 
	public abstract void processInput(String userReply) ; 
}
