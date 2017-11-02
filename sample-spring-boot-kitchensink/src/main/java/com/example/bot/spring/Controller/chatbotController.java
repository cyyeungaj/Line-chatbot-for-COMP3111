package com.example.bot.spring;

public class chatbotController {
	private Manager manager ; 
	private UserInterface currentInterface ; 
	
	public chatbotController ( Manager manager , UserInterface currentInterface) {
		this.manager = manager ; 
		this.currentInterface = currentInterface ; 
	}
	
	public void setInterface ( UserInterface currentInterface) {
		this.currentInterface = currentInterface ; 
	}
	
	public void processInput ( String userInput ) {
		currentInterface.processInput(this , userInput) ; 
	}
	
}
