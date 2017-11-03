package com.example.bot.spring;

import com.linecorp.bot.client.LineMessagingClientImpl;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import com.linecorp.bot.model.profile.UserProfileResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.io.ByteStreams;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.PushMessage ; 
import com.linecorp.bot.model.Multicast ; 
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.action.URIAction;
import java.util.* ; 
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.model.event.BeaconEvent;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.model.event.message.AudioMessageContent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.source.GroupSource;
import com.linecorp.bot.model.event.source.RoomSource;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.message.AudioMessage;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.ImagemapMessage;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.imagemap.ImagemapArea;
import com.linecorp.bot.model.message.imagemap.ImagemapBaseSize;
import com.linecorp.bot.model.message.imagemap.MessageImagemapAction;
import com.linecorp.bot.model.message.imagemap.URIImagemapAction;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.NonNull;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;

public class FAQInterface extends UserInterface {
	private final int SHOW_FAQ_STATE = 0; 
	private final int ASK_ANY_QUESTION_ELSE_STATE = 1;
	private final int ASK_FOR_QUESTION_STATE = 2 ;
	private final int QUIT_STATE = -1;
	private final String SELECT_ASK_FOR_QUESTION_ACTION = "yes" ;
	private final String SELECT_NO_QUESTION_ACTION = "no" ; 
	 
	
	private int currentState; 
	private StringBuilder messageBuilder = null;
	
	@Autowired
	private LineMessagingClient lineMessagingClient;

	public FAQInterface () {
		currentState = SHOW_FAQ_STATE;
		//super.setMessage(getFAQList()) ; 
		
		super.setMessage("testing") ; 
		
		//lineMessagingClient.pushMessage(new PushMessage("U2336052073d2a3192d088b3215554ee1", new TextMessage("testing for push")));
	}
	

	public void processInput( chatbotController controller, String userReply, Event event) {
		String lowerCaseUserReply = userReply.toLowerCase() ; 
		
		switch(currentState){
			case SHOW_FAQ_STATE:
				message = getFAQMessage(userReply);
			break;
			case ASK_ANY_QUESTION_ELSE_STATE:
				message = getAnyQuestionMessage(controller, userReply);
				break ; 
			
			case ASK_FOR_QUESTION_STATE:
				message = getAskForQuestionMessage(userReply);
				break ; 
			case QUIT_STATE:
				message = "Quit FAQ section\n";
				quitInterface(controller);
				break;  
			default:
				message = "something go wrong\n";
				break ; 
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
			quitInterface(controller);
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
	
	public void quitInterface(chatbotController controller ) {
		/*  quit FAQ interface   */
    }

	
	
}


