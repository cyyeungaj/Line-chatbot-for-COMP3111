package com.example.bot.spring;

import java.util.* ; 
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.model.event.BeaconEvent;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
public class FAQInterface extends UserInterface {
	
	private final static int SHOW_FAQ_STATE = 0 ; 
	private final static int ASK_FOR_QUESTION_STATE = 1 ;
	private final static int UNEXPECTED_INPUT_STATE = 2 ; 
	
	private final static String SELECT_ASK_FOR_QUESTION_ACTION = "no" ;
	private final static String SELECT_NO_QUESTION = "yes" ; 
	 
	
	private int currentState ; 
	
	
	public FAQInterface () {
		currentState = 0  ;
		ArrayList<FAQ> FAQlist = new JDBCFaqManager().getAllFAQ() ;
        StringBuilder messageBuilder = new StringBuilder();
        for(int i = 0 ; i < FAQlist.size() ; i ++ ) {
        	messageBuilder.append(FAQlist.get(i).getQuestionNum() + " ");
        	messageBuilder.append(FAQlist.get(i).getQuestion()+ "\n\n"); 
        	messageBuilder.append(FAQlist.get(i).getAnswer()+"\n") ; 
        }
        messageBuilder.append("Is your question included in the list of faq?\n") ; 
	}
	
	public void processInput( chatbotController controller, String userReply, Event event) {
		String lowerCaseUserReply = userReply.toLowerCase() ; 
		
	} 
	
	
	
	public void processInput( chatbotController controller, String userReply) 
	{
	}
}
