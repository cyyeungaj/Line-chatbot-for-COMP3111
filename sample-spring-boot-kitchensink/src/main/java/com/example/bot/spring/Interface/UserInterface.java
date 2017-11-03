package com.example.bot.spring;


import java.lang.StringBuilder;
public abstract class UserInterface {
	
	private String message ;
	private Manager manager ; 
	public String getMessage() { return message ; }
	public abstract void processInput( chatbotController controller, String userReply) ; 
	protected  void setManager ( Manager manager ) {
		this.manager = manager ; 
	}
	protected void setMessage(String message) {
		this.message = message ; 
	}
}
