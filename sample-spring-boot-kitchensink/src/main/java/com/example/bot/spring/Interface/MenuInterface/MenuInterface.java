package com.example.bot.spring;
import java.lang.StringBuilder;

public class MenuInterface extends UserInterface {
	private final static int SELECT_BOOKING_ACTION = 1 ; 
	private final static int SELECT_QUERY_ACTION = 2 ; 
	private final static int SELECT_FAQ_ACTION = 3; 
	private final static int SELECT_PROMOTED_ACTION = 4;
	private final static int SELECT_RECOMMANDATION_ACTION = 5 ; 
	
	public MenuInterface () {
        StringBuilder messageBuilder = new StringBuilder();
		super.setManager(null);
		messageBuilder.append("What do you want?\n"); 
		messageBuilder.append("1.Book a tour \n"); 
		messageBuilder.append("2.Search for some tours \n"); 
		messageBuilder.append("3.Ask for some question \n"); 
		messageBuilder.append("4.Look for promoted tours \n"); 
		messageBuilder.append("5.Get some recommandations of tours") ; 
		super.setMessage(messageBuilder.toString()) ; 
	}
	
	public void processInput( chatbotController controller, String userReply) {
		int userInput = 0 ; 
		try {
			userInput= Integer.parseInt(userReply) ; 
		}catch (NumberFormatException e) {
			controller.setInterface(new ErrorInterface()) ; 
		} 
		switch(userInput) {
			case SELECT_BOOKING_ACTION : 
				controller.setInterface(new BookingInterface()) ; 
				break ; 
			case SELECT_QUERY_ACTION : 
				controller.setInterface(new QueryInterface()) ; 
				break ; 
			case SELECT_FAQ_ACTION : 
				controller.setInterface(new FAQInterface()) ; 
				break ; 
			case SELECT_PROMOTED_ACTION : 
				controller.setInterface(new PromotedToursInterface()) ; 
				break ; 
			case SELECT_RECOMMANDATION_ACTION : 
				controller.setInterface(new RecommandationInterface()) ; 
				break ; 
			default:
				controller.setInterface(new ErrorInterface()) ; 
				break ; 
		}
	}

	
}
