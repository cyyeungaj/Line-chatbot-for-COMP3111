package com.example.bot.spring;

public class chatbotController {
	
	private UserInterface currentInterface ; 
	
	public chatbotController ( UserInterface currentInterface) { 
		this.currentInterface = currentInterface ; 
	}
	
	public void setInterface ( UserInterface currentInterface) {
		this.currentInterface = currentInterface ; 
	}
	
	public void processInput ( String userInput ) {
		currentInterface.processInput(this , userInput) ; 
	}
	
	public String getCurrentInterfaceMessage () {
		return currentInterface.getMessage() ; 
	}
	
	
}
