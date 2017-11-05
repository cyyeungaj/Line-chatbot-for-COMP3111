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
>>>>>>> SearchInterface Filter done
import java.util.HashMap;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;
import java.util.*;

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
	private double minPrice = -1, maxPrice = -1;
	private int currentS = 0;
	private ArrayList<Tour> searchingResult = null;
	private ArrayList<Tour> srForTime = null;
	private ArrayList<Tour> srForPlace = null;
	private ArrayList<Tour> srForPrice = null;
	private JDBCTourManager tourManager = null;
	private Filter filter = null;

	@Autowired

	private LineMessagingClient lineMessagingClient;

	public SearchingTourInterface() {
		StringBuilder messageBuilder = new StringBuilder();
		super.setManager(null);
		messageBuilder.append("1.Search by time \n");
		messageBuilder.append("2.Search by place \n");
		messageBuilder.append("3.Search by price \n");

		super.setMessage(messageBuilder.toString()) ; 

		tourManager = new JDBCTourManager();
		filter = new Filter();
		currentS = 0 ; 
		/*filter.setPriceFilter("500");
		filter.setRegionFilter(1);
		filter.setTimeFilter("2017-11-15", "2017-12-03");
		ArrayList<Tour> tours = tourManager.getToursByFilter(filter.getWhere());
		for(int i = 0; i < tours.size(); ++i){
			log.info(Double.toString(tours.get(i).getPrice()) + ", " + tours.get(i).getDepartureDate());
		}*/
		

	}

	public void processInput(chatbotController controller, String userReply, Event event) {
		//this.event  = event;
		StringBuilder messageBuilder = new StringBuilder();
		super.setManager(null);

		
		if( (timeF || placeF || priceF) && userReply.compareTo("yes") == 0 ){
			controller.setInterface(new AskForTourChoiceState(searchingResult)) ; 
		}
		
		if (userReply.compareTo("1") == 0  && currentS == 0) {

			currentS = 1;
			messageBuilder.append("Please enter starting Date(YYYY-MM-DD): ");
			super.setMessage(messageBuilder.toString());
			return ; 
		}

		if (userReply.compareTo("2") == 0 && currentS == 0) {
			currentS = 2;
			messageBuilder.append("Please enter Place: ");
			super.setMessage(messageBuilder.toString());
			return ; 

		}

		if (userReply.compareTo("3") == 0 && currentS == 0) {
			messageBuilder.append("Please enter minimum Price: ");
			super.setMessage(messageBuilder.toString());
			//lineMessagingClient.pushMessage(new PushMessage(userId, new TextMessage(super.getMessage())));
			return ; 
		}
		
		
		if (  currentS == 1) {
			//not yet do checking for date-format
			currentS = 11; //ask for end date
			startD = userReply;
			messageBuilder.append("Please enter ending Date(YYYY-MM-DD): ");
			super.setMessage(messageBuilder.toString());
			//lineMessagingClient.pushMessage(new PushMessage(userId, new TextMessage(super.getMessage())));
			return ; 
		}

		if (currentS == 2) {
			place = userReply;
			
			int region_id = tourManager.getRegionId(place);
			int country_id = tourManager.getCountryId(place);
			if(region_id != -1){
				filter.setRegionFilter(region_id);
			}else if(country_id != -1){
				filter.setCountryFilter(country_id);
			}else{
				/*result not found*/
				log.info("place filter:: invalid placename input");
				return;
			}
			currentS = 0;
			searchByPlace(userReply);
			placeF = true;
			searchByPlace();
			refreshPage("2");
			return ; 
		}

		if (currentS == 3) {
			currentS = 13; // ask for max price
			minPrice = Double.parseDouble(userReply);
			messageBuilder.append("Please enter maximum Price: ");
			super.setMessage(messageBuilder.toString());
			//lineMessagingClient.pushMessage(new PushMessage(userId, new TextMessage(super.getMessage())));
			return ;
		}

		// After entering end date, do search

		if (currentS == 11) {
			currentS = 0;
			endD = userReply;
			filter.setTimeFilter(startD, endD);
			searchByTime();
			timeF = true;
			refreshPage("1");
			return ; 
		}

		// After entering max Price, do search

		if (currentS == 13) {
			currentS = 0;
			maxPrice = Double.parseDouble(userReply);
			filter.setPriceRangeFilter(Double.toString(minPrice), Double.toString(maxPrice));
			searchByPrice() ; 
			priceF = true;
			refreshPage("3");
			return ; 
		}
		// turn off time filter
		if (currentS == 0 && userReply == "4") {
			filter.removeTimeFilter();
			timeF = false;
			startD = null;
			endD = null;
			searchByTime() ; 
			refreshPage("4");
			return ; 
		}
		// turn off place filter
		if (currentS == 0 && userReply == "5") {
			filter.removePlaceFilter();
			placeF = false;
			place = null;
			searchByPlace() ; 
			refreshPage("5");
			return ; 
		}
		// turn off price filter
		if (currentS == 0 && userReply == "6") {
			filter.removePriceFilter();
			priceF = false;
			minPrice = -1;
			maxPrice = -1;
			searchByPrice() ; 
			refreshPage("6");

			return ; 

		}
		
	}

			
		private void searchByTime() {
			// if startD and endD = null, then search all date
			this.searchingResult = new JDBCTourManager().getToursByTime(startD, endD);
			this.srForTime = searchingResult ; 
		}
		private void searchByPlace(String p) {
			// if minPrice and place = null, then search all place
			
		}


	}

			



			
	private void searchByTime() {
		;
		// if startD and endD = null, then search all date
		
		this.searchingResult = new JDBCTourManager().getToursByFilter(filter.getWhere()) ; 
		this.srForTime = new ArrayList<Tour> (); 
		for( Tour tour : searchingResult ) 
			srForTime.add(tour) ; 
	}
	private void searchByPlace() {
		// if minPrice and place = null, then search all place
		this.searchingResult = new JDBCTourManager().getToursByFilter(filter.getWhere()) ; 
		srForPlace = new ArrayList<Tour>() ; 
		for( Tour tour : searchingResult ) 
			srForPlace.add(tour) ; 
	}
	private void searchByPlace(String p) {
		;
		// if minPrice and place = null, then search all place
	}

	private void searchByPrice() {
		// if minPrice and maxPrice = -1, then search all price
		this.searchingResult = new JDBCTourManager().getToursByFilter(filter.getWhere()) ; 
		srForPrice = new ArrayList<Tour>() ; 
		for( Tour tour : searchingResult ) 
			srForPrice.add(tour) ; 
	}

	

	private void mapping() {
		if(searchingResult != null)
			searchingResult.clear();
		//for (Tour t:srForPrice)
		//	if (!searchingResult.contains(t))
		//		searchingResult.add(t);
		searchingResult = tourManager.getToursByFilter(filter.getWhere());
	}

	private void refreshPage(String option) {
		StringBuilder messageBuilder = new StringBuilder();
		super.setManager(null);
		// Refresh the page if user select to search not enter to see description of tour (if (-1))
		if (option.indexOf("T") == -1) {
			String timeFChecker = "Off", placeFChecker = "Off", priceFChecker = "Off";
			if (timeF == true)
				timeFChecker = "On";
			if (placeF == true )
				placeFChecker = "On";
			if (priceF == true )
				priceFChecker = "On";

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
			
			if(timeF || placeF || priceF)
				messageBuilder.append("Do you want to book one of the tour?\nType yes to enter into booking procedure ") ; 
			
		}
		else if (option.indexOf("T") == 0) {
			;
		}	

		//Source src = event.getSource() ; 
		//String userId = src.getUserId() ;
		super.setMessage(messageBuilder.toString());
		//lineMessagingClient.pushMessage(new PushMessage(userId, new TextMessage(super.getMessage())));	
	}	
	
	
	//select region_id from region where region like '%Guangdong%';
}


class Filter{
	HashMap<String, String> hm = new HashMap<>();
	
	private final String COL_COUNTRY_ID = "country_id";
	private final String COL_COUNTRY = "country";
	private final String COL_REGION_ID = "region_id";
	private final String COL_REGION = "region";
	
	private final String COL_DEP_DATE = "departure_date";
	private final String COL_FEE = "tour_fee";
	
	
	private final String TIME_TAG = "time";
	private final String PLACE_TAG = "place";
	private final String PRICE_TAG = "price";
	
	private ArrayList<String> keys = new ArrayList<>();
	
	/**
	 * @param startDate : start of tour date
	 * @param endDate : end of tour date
     * @return 
     */
	
	public void setTimeFilter(String startDate, String endDate) {
		if(hm.containsKey(TIME_TAG))
			removeTimeFilter();
		hm.put(TIME_TAG, COL_DEP_DATE + " BETWEEN '" + startDate + "' AND '" + endDate + "' ");
		keys.add(TIME_TAG);
	}
	
	
	/**
	 * @param region_id: region id
     * @return 
     */
	public void setRegionFilter(int region_id){
		if(hm.containsKey(PLACE_TAG))
			removePlaceFilter();
		hm.put(PLACE_TAG, COL_REGION_ID + " = " + Integer.toString(region_id));
		keys.add(PLACE_TAG);
	}
	/**
	 * @param country_id: country id
     * @return 
     */
	public void setCountryFilter(int country_id){
		if(hm.containsKey(PLACE_TAG))
			removePlaceFilter();
		hm.put(PLACE_TAG, COL_COUNTRY_ID + " = " + Integer.toString(country_id));
		keys.add(PLACE_TAG);
	}
	/**
	 * @param placeName: name of the country
     * @return 
     */
	public void setPlaceFilter(String placeName) {
		if(hm.containsKey(PLACE_TAG))
			removePlaceFilter();
		int country_id = 1;
		if(country_id != 0)
			hm.put(PLACE_TAG, COL_COUNTRY_ID + " = " + Integer.toString(country_id));
		else{
			int region_id = 1;//getRegionId(placeName);
			if(region_id != 0){
				hm.put(PLACE_TAG, COL_REGION_ID + " = " + Integer.toString(region_id));
			}else{
				return; //no matching place name
			}

		}
//		hm.put(PLACE_TAG, " LOWER(" + COL_COUNTRY + ") = LOWER('" + placeName + "')");
		
		keys.add(PLACE_TAG);

		}	
		Source src = event.getSource() ; 
		String userId = src.getUserId() ;
		super.setMessage(messageBuilder.toString());
		lineMessagingClient.pushMessage(new PushMessage(userId, new TextMessage(super.getMessage())));	

	}
	
	/**
	 * @param price: max price of the tour
     * @return 
     */
	 
	public void setPriceFilter(String price) {
		if(hm.containsKey(PRICE_TAG))
			removePriceFilter();
		hm.put(PRICE_TAG, COL_FEE + " < " + price);
		keys.add(PRICE_TAG);
	}
	
	public void setPriceRangeFilter(String min, String max){
		if(hm.containsKey(PRICE_TAG))
			removePriceFilter();
		hm.put(PRICE_TAG, COL_FEE + " BETWEEN " + min + " AND " + max);
	//select region_id from region where region like '%Guangdong%';
}


class Filter{
	HashMap<String, String> hm = new HashMap<>();
	
	private final String COL_COUNTRY_ID = "country_id";
	private final String COL_COUNTRY = "country";
	private final String COL_DEP_DATE = "departure_date";
	private final String COL_FEE = "tour_fee";
	
	private final String TIME_TAG = "time";
	private final String PLACE_TAG = "place";
	private final String PRICE_TAG = "price";
	
	private ArrayList<String> keys = new ArrayList<>();
	
	public void setTimeFilter(String startDate, String endDate) {
		if(hm.containsKey(TIME_TAG))
			removeTimeFilter();
		hm.put(TIME_TAG, COL_DEP_DATE + " BETWEEN '" + startDate + "' AND '" + endDate + "' ");
		keys.add(TIME_TAG);
	}
	
	public int getCountryId(String placeName) {
		String sqlStatement = " LOWER(" + COL_COUNTRY + ") = LOWER('" + placeName + "')";
		//selection(sqlStatement);
		return 1;
	}
	
	public void setPlaceFilter(String placeName) {
		if(hm.containsKey(PLACE_TAG))
			removePlaceFilter();
		int country_id = getCountryId(placeName);
//		hm.put(PLACE_TAG, " LOWER(" + COL_COUNTRY + ") = LOWER('" + placeName + "')");
		hm.put(PLACE_TAG, COL_COUNTRY_ID + " = " + Integer.toString(country_id));
		keys.add(PLACE_TAG);
	}
	
	public void setPriceFilter(String price) {
		if(hm.containsKey(PRICE_TAG))
			removePriceFilter();
		hm.put(PRICE_TAG, COL_FEE + " < " + price);
		keys.add(PRICE_TAG);
	}
	
	public void removeTimeFilter() {
		if(hm.containsKey(TIME_TAG)) {
			hm.remove(TIME_TAG);
			keys.remove(TIME_TAG);
		}
	}
	
	public void removePlaceFilter() {
		if(hm.containsKey(PLACE_TAG)) {
			hm.remove(PLACE_TAG);
			keys.remove(PLACE_TAG);
		}
	}
	
	public void removePriceFilter() {
		if(hm.containsKey(PRICE_TAG)) {
			hm.remove(PRICE_TAG);
			keys.remove(PRICE_TAG);
		}
	}
	
	
	/**	 
     * @return SQL statement of WHERE constraint after filtering
     */
	public String getWhere() {
		String result = "";
		for(int i = 0; i < hm.size(); ++i) {
			if(i == 0) {
				result += " WHERE " + hm.get(keys.get(i));
			}else {
				result += " AND " + hm.get(keys.get(i));
			}
		}
		result += ";" ; 
		return result;
	}
}