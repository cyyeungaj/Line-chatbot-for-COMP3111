package com.example.bot.spring;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.model.event.BeaconEvent;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
public class chatbotController {
	
	private UserInterface currentInterface ; 
	
	public chatbotController ( UserInterface currentInterface) { 
		this.currentInterface = currentInterface ; 
	}
	
	public void setInterface ( UserInterface currentInterface) {
		this.currentInterface = currentInterface ; 
	}
	
	public void processInput ( String userInput, Event event ) {
		currentInterface.processInput(this , userInput, event) ; 
	}
	
	public String getCurrentInterfaceMessage () {
		return currentInterface.getMessage() ; 
	}
	
	
}
