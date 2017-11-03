package com.example.bot.spring;
import com.linecorp.bot.client.LineMessagingClientImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.StringBuilder;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import com.linecorp.bot.model.profile.UserProfileResponse;
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

public class SearchingTourInterface extends UserInterface {



	private final static int SEARCH_BY_TIME_FILTER = 1;
	private boolean timeF = false;
	private final static int SEARCH_BY_PLACE_FILTER = 2;
	private boolean placeF = false;
	private final static int SEARCH_BY_PRICE_FILTER = 3;
	private boolean priceF = false;
	private Event event = null;
	
	private String startD, endD, place;
	private int minPrice, maxPrice;
	private int currentS = 0;
	private ArrayList<Tour> searchingResult = null;
	@Autowired
	private LineMessagingClient lineMessagingClient;
	
	public SearchingTourInterface() {
		StringBuilder messageBuilder = new StringBuilder();
		super.setManager(null);
		
		messageBuilder.append("1.Search by time \n");
		messageBuilder.append("2.Search by place \n");
		messageBuilder.append("3.Search by pricen \n");

		super.setMessage(messageBuilder.toString()) ; 
	}
	

	public void processInput(chatbotController controller, String userReply, Event event) {
		this.event  = event;
		StringBuilder messageBuilder = new StringBuilder();
		super.setManager(null);
		Source src = event.getSource(); 
		String userId = src.getUserId();
		if (userReply == "1" && currentS == 0) {
			currentS = 1;
			messageBuilder.append("Please enter starting Date(YYYY-MM-DD): ");
			super.setMessage(messageBuilder.toString());
			lineMessagingClient.pushMessage(new PushMessage(userId, new TextMessage(super.getMessage())));
		}
		if (userReply == "2" && currentS == 0) {
			currentS = 2;
			messageBuilder.append("Please enter Place: ");
			super.setMessage(messageBuilder.toString());
			lineMessagingClient.pushMessage(new PushMessage(userId, new TextMessage(super.getMessage())));
		}
		if (userReply == "3" && currentS == 0) {
			currentS = 3;
			messageBuilder.append("Please enter minimum Price: ");
			super.setMessage(messageBuilder.toString());
			lineMessagingClient.pushMessage(new PushMessage(userId, new TextMessage(super.getMessage())));
		}
		if (currentS == 1) {
			currentS = 11; //ask for end date
			startD = userReply;
			messageBuilder.append("Please enter ending Date(YYYY-MM-DD): ");
			super.setMessage(messageBuilder.toString());
			lineMessagingClient.pushMessage(new PushMessage(userId, new TextMessage(super.getMessage())));
		}
		if (currentS == 2) {
			currentS = 0;
			place = userReply;
			searchByPlace(userReply);
			placeF = true;
			refreshPage("2");
		}
		if (currentS == 3) {
			currentS = 13; // ask for max price
			minPrice = Integer.parseInt(userReply);
			messageBuilder.append("Please enter maximum Price: ");
			super.setMessage(messageBuilder.toString());
			lineMessagingClient.pushMessage(new PushMessage(userId, new TextMessage(super.getMessage())));
		}
		// After entering end date, do search
		if (currentS == 11) {
			currentS = 0;
			endD = userReply;
			searchByTime();
			timeF = true;
			refreshPage("1");
		}
		// After entering max Price, do search
		if (currentS == 13) {
			currentS = 0;
			maxPrice = Integer.parseInt(userReply);
			searchByPrice();
			priceF = true;
			refreshPage("3");
		}
		// turn off time filter
		if (currentS == 0 && userReply == "4") {
			timeF = false;
		}
		// turn off place filter
		if (currentS == 0 && userReply == "5") {
			placeF = false;
		}
		// turn off price filter
		if (currentS == 0 && userReply == "6") {
			priceF = false;
		}
	}
			
	
	private void searchByTime() {
		
	}
	
	private void searchByPlace(String p) {
		
	}
	
	private void searchByPrice() {
		
	}
	
	private void searchingPage(String searchingOp) {
		
	}
	private void refreshPage(String option) {
		StringBuilder messageBuilder = new StringBuilder();
		super.setManager(null);
		// Refresh the page if user select to search not enter to see description of tour (if (-1))
		if (option.indexOf("T") == -1) {
			String timeFChecker = "Off", placeFChecker = "Off", priceFChecker = "Off";
			if (timeF)
				timeFChecker = "On";
			if (placeF)
				timeFChecker = "On";
			if (priceF)
				timeFChecker = "On";
		
			messageBuilder.append("Time filter is now turn" + timeFChecker + " \n");
			messageBuilder.append("Place filter is now turn" + placeFChecker + " \n");
			messageBuilder.append("Price filter is now turn" + priceFChecker + " \n");
		
			messageBuilder.append("1.Search by time \n");
			messageBuilder.append("2.Search by place \n");
			messageBuilder.append("3.Search by pricen \n");
			if (timeF)
				messageBuilder.append("4.Turn off time filter \n");
			if (placeF)
				messageBuilder.append("5.Turn off place filter \n");
			if (priceF)
				messageBuilder.append("6.Turn off price filter \n");
			if (searchingResult != null) {
				messageBuilder.append("Result: \n");
				int i = 1;
				for (Tour tour:searchingResult) {
					messageBuilder.append("T" + i + ", " + tour.getTourName() + " Date: " + tour.getDepartureDate() + " Duration: " + tour.getDuration() + " Price: $" + tour.getPrice() + " \n");
					++i;
				}
			}
		}
		else if (option.indexOf("T") == 0) {
			;
		}
		
		Source src = event.getSource() ; 
		String userId = src.getUserId() ;
		super.setMessage(messageBuilder.toString());
		lineMessagingClient.pushMessage(new PushMessage(userId, new TextMessage(super.getMessage())));
		
	}
	
}
