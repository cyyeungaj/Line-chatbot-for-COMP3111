package com.example.bot.spring;

import com.linecorp.bot.client.LineMessagingClientImpl;
import java.lang.StringBuilder;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.model.event.BeaconEvent;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.UnfollowEvent;

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
	
	//@Autowired
	//private LineMessagingClient lineMessagingClient;

	public void processInput( chatbotController controller, String userReply, Event event) {
		int userInput = 0 ; 
		try {
			userInput= Integer.parseInt(userReply) ; 
		}catch (NumberFormatException e) {
			controller.setInterface(new ErrorInterface()) ; 
		}
		
		/*
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
		*/
		if( userInput == SELECT_BOOKING_ACTION ) {
			controller.setInterface(new BookingInterface()) ; 
		} else if ( userInput == SELECT_FAQ_ACTION ) {
			controller.setInterface(new FAQInterface()) ; 

		} 
	}

	
}
