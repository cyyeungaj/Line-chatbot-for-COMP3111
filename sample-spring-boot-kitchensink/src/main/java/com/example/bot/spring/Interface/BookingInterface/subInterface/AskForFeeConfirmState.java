package com.example.bot.spring;
import java.lang.StringBuilder;


import java.util.* ;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter ; 


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

@Slf4j
public class AskForFeeConfirmState extends UserInterface {
	private Booking currentBooking ;
	
	public AskForFeeConfirmState ( Booking currentBooking ) {
		this.currentBooking = currentBooking ; 
		this.currentBooking.setTourFee() ; 
		this.currentBooking.setServiceCharge(); 
		super.setMessage("It will be $" + currentBooking.getTourFee() + 
						 "Also, you need to pay $" + this.currentBooking.getServiceCharge()+ 
						 "after the tour\nAre you confirmed to book this tour?");
	}
	
	public void processInput ( chatbotController controller, String userReply , Event event ) {
		if(userReply.compareTo("yes") == 0 ) {
			String date = null ; 
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.now();
			date = localDate.format(formatter) ; 
			currentBooking.setDate(date);
			currentBooking.setSpecialRequest("");
			currentBooking.setAmountPaid(0.0) ; 
			currentBooking.setConfirm(false);
			controller.setInterface(new AskForHKIDState(currentBooking)) ; 
		} else {
			controller.setInterface(new BookingInterface()) ; 
		}
	}
	
	
}
