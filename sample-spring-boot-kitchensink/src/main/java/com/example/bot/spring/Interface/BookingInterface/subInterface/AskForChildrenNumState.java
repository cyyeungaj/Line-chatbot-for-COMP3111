package com.example.bot.spring;
import java.lang.StringBuilder;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.PushMessage ; 
import com.linecorp.bot.model.Multicast ; 
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.action.URIAction;
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

import lombok.NonNull;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.* ;

@Slf4j
public class AskForChildrenNumState extends UserInterface {
	
	private Booking currentBooking ;
	
	public  AskForChildrenNumState ( Booking currentBooking)  {
		this.currentBooking = currentBooking ;
		super.setMessage("How many children (Age 4 to 11) ?");
	}
	
	public void processInput ( chatbotController controller, String userReply , Event event ) {
		int userNoInput = 0 ; 
		
		try {
			userNoInput = Integer.parseInt(userReply) ; 
		} catch ( Exception e ) {
			log.info("NumberFormat Exception ") ; 
		}
		currentBooking.setNoOfChildrens(userNoInput) ; 
		controller.setInterface(new AskForFeeConfirmState(currentBooking)) ; 
	}
	
}