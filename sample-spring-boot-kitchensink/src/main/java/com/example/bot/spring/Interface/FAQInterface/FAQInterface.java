package com.example.bot.spring;
import java.util.* ; 

public class FAQInterface extends UserInterface {
	private final int SHOW_FAQ_STATE = 0; 
	private final int ASK_ANY_QUESTION_ELSE_STATE = 1;
	private final int ASK_FOR_QUESTION_STATE = 2 ;
	private final int QUIT_STATE = -1;
	private final String SELECT_ASK_FOR_QUESTION_ACTION = "yes" ;
	private final String SELECT_NO_QUESTION_ACTION = "no" ; 
	 
	
	private int currentState; 
	private String message = "";
	private StringBuilder messageBuilder = null;
	
	public FAQInterface (chatbotController controller) {
		currentState = SHOW_FAQ_STATE;
	}
	
	public void processInput( chatbotController controller, String userReply, Event event) {
		String lowerCaseUserReply = userReply.toLowerCase() ; 
		
		switch(currentState){
			case SHOW_FAQ_STATE:
				message = getFAQMessage(userReply);
			break;
			case ASK_ANY_QUESTION_ELSE_STATE:
				message = getAnyQuestionMessage(controller, userReply);
			break;
			case ASK_FOR_QUESTION_STATE:
				message = getAskForQuestionMessage(userReply);
			break;
			case QUIT_STATE:
				message = "Quit FAQ section\n";
				quitInterface(controller);
			default:
				message = "something go wrong\n";
		}
	} 
	
	private String getFAQMessage(String userReply){
		currentState = ASK_ANY_QUESTION_ELSE_STATE;
		return getFAQList();
	}
	private String getAnyQuestionMessage(chatbotController controller, String userReply){
		if(userReply == SELECT_ASK_FOR_QUESTION_ACTION){
			currentState = ASK_FOR_QUESTION_STATE;
			return "Please tell me your question. \n";
			
		}else if(userReply == SELECT_NO_QUESTION_ACTION){
			currentState = QUIT_STATE;
			quitInterface(controller, userReply);
			return "End of FAQ section\n";
			
		}else{
			return "Invalid input, please try again. \n";
			
		}
	}
	private String getAskForQuestionMessage(String userReply){
		currentState = ASK_ANY_QUESTION_ELSE_STATE;
		
		return processQuestion(userReply);	
	}
	
	private String getFAQList(){
		if(messageBuilder == null){
			ArrayList<FAQ> FAQlist = new JDBCFaqManager().getAllFAQ() ;
			StringBuilder messageBuilder = new StringBuilder();
			for(int i = 0 ; i < FAQlist.size() ; i ++ ) {
				messageBuilder.append(Integer.toString(i) + ". ");
				messageBuilder.append(FAQlist.get(i).getQuestion()+ "\n\n"); 
				messageBuilder.append(FAQlist.get(i).getAnswer()+"\n") ; 
			}

			messageBuilder.append("Could I answer you question?\n");
		}
		
		return messageBuilder.toString();
	}
	
	private String processQuestion(String userReply){
//		if(NLPAdapter.process(user_input)) /*not null, mean expected*/
//		{
//			
//		}else{
//			
//		}
		String result = "NLP process\n";
		return result;
	}
	

	
	public String getMessage(){
		return message;
	}
	
	public void quitInterface(chatbotController controller) {
		/*  quit FAQ interface   */
	}
}


