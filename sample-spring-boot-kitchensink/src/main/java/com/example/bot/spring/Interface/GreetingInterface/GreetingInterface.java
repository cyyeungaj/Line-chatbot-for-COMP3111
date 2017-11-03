package com.example.bot.spring;

import java.lang.StringBuilder;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.model.event.BeaconEvent;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.UnfollowEvent;

public class GreetingInterface extends UserInterface {
	
	public GreetingInterface () {
		super.setManager(null) ; 
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Hi I am your intelligent travel agent.\n") ; 
        messageBuilder.append("Basically, I am here to answer your queries related to our tours and help you book for tours you want\n");
        messageBuilder.append("Apart from it, I would provide many other features!") ; 
        super.setMessage(messageBuilder.toString()) ; 
	}
	
	public void processInput( chatbotController controller, String userReply, Event event) {
		switch(userReply) {
			default:
				break ; 
		}
	} 

	
}
