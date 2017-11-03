package com.example.bot.spring;
import com.linecorp.bot.model.event.BeaconEvent;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.UnfollowEvent;


public abstract class UserInterface {
	
	private String message ;
	private Manager manager ; 
	public String getMessage() { return message ; }
	public abstract void processInput( chatbotController controller, String userReply, Event event) ; 
	protected  void setManager ( Manager manager ) {
		this.manager = manager ; 
	}
	protected void setMessage(String message) {
		this.message = message ; 
	}
}
