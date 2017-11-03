package com.example.bot.spring;
import com.linecorp.bot.client.LineMessagingClientImpl;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.StringBuilder;
import java.sql.ResultSet;

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



import java.util.Date;
import java.text.SimpleDateFormat;


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

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.nio.channels.SelectableChannel;
import java.sql.*;
import java.util.ArrayList;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;
import java.util.*;

import java.net.URI;

@Slf4j

public class SearchingTourInterface extends UserInterface {



	private final static int SEARCH_BY_TIME_FILTER = 1;
	private boolean timeF = false;
	private final static int SEARCH_BY_PLACE_FILTER = 2;
	private boolean placeF = false;
	private final static int SEARCH_BY_PRICE_FILTER = 3;
	private boolean priceF = false;
	private Event event = null;
	
	private String startD, endD, place;
	private int minPrice = -1, maxPrice = -1;
	private int currentS = 0;
	private ArrayList<Tour> searchingResult = null;
	private ArrayList<Tour> srForTime = null;
	private ArrayList<Tour> srForPlace = null;
	private ArrayList<Tour> srForPrice = null;
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
			startD = null;
			endD = null;
		}
		// turn off place filter
		if (currentS == 0 && userReply == "5") {
			placeF = false;
			place = null;
		}
		// turn off price filter
		if (currentS == 0 && userReply == "6") {
			priceF = false;
			minPrice = -1;
			maxPrice = -1;
		}
	}
			
	
	private void searchByTime() {
		;
		// if startD and endD = null, then search all date
	}
	
	private void searchByPlace(String p) {
		;
		// if minPrice and place = null, then search all place
	}
	
	private void searchByPrice() {
		// if minPrice and maxPrice = -1, then search all price
		String SQLStatement = "SELECT * from tourlist WHERE tour_fee BETWEEN " + minPrice + " AND " + maxPrice + ";";
		ResultSet rs = null;
		try {
			Manager manager = new Manager();
			rs = manager.SelectionQuery(SQLStatement);
		} catch(Exception e) {
			
		}
		srForPrice = new ArrayList<Tour>();
		try {
			while (rs.next()) {
				Tour tour = new Tour(rs.getString("tour_id"), rs.getInt("country_id"), rs.getInt("region_id"), rs.getString("tour_name"), rs.getString("tour_shortdec"), rs.getString("hotel"), rs.getInt("duration"), rs.getDate("departure_date").toString(), rs.getInt("tour_cap"), rs.getInt("min_req_cap"), rs.getDouble("tour_fee"), rs.getString("tour_guide"), rs.getString("tour_guide_line_ac"));
				srForPrice.add(tour);
			}
		} catch (SQLException e) {
			
		}
	}
	
	private void mapping() {
		searchingResult.clear();
		for (Tour t:srForPrice)
			if (!searchingResult.contains(t))
				searchingResult.add(t);
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
