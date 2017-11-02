package com.example.bot.spring;

import java.lang.StringBuilder;

public class GreetingInterface extends UserInterface {
	
	private final static SELECT_BOOKING_ACTION = 1 ; 
	private final static SELECT_QUERY_ACTION = 2 ; 
	private final static SELECT_FAQ_ACTION = 3; 
	private final static SELECT_PROMOTED_ACTION = 4;
	private final static SELECT_RECOMMANDATION_ACTION = 5 ; 
	
	public GreetingInterface () {
		super.manager = null ; 
        StringBuilder messegeBuilder = new StringBuilder();
        messesgeBuilder.append("Hi I am your intelligent travel agent.\n") ; 
        messesgeBuilder.append("Basically, I am here to answer your queries related to our tours and help you book for tours you want\n");
        messegaeBuilder.append("Apart from it, I would provide many other features!") ; 
	}
	
}
