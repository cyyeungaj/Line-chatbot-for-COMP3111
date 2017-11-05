
package com.example.bot.spring;
import java.lang.StringBuilder;


import java.util.* ;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter ; 

import java.sql.ResultSet;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.model.event.BeaconEvent;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import java.util.ArrayList;



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

@Slf4j
public class AskForTourChoiceState extends UserInterface {
	
	private ArrayList<Tour> selectedTourList ; 
	private Booking currentBooking ;
	
	public AskForTourChoiceState ( ArrayList<Tour> TourList ) {
		currentBooking = new Booking()  ;
		this.selectedTourList = new ArrayList<Tour> () ; 
		for(Tour tour : TourList)
			selectedTourList.add(tour);
		
		StringBuilder messageBuilder = new StringBuilder();
		int index = 1 ; 
		for( Tour currentTour : selectedTourList ) {
			messageBuilder.append(index + ". ") ; 
			messageBuilder.append(currentTour.getTourName() + "\n") ;
			messageBuilder.append(currentTour.gettourDescription() + "\n") ; 
			messageBuilder.append(currentTour.getHotel() + "\n") ; 
			messageBuilder.append(currentTour.getDepartureDate() + "\n") ; 
			index ++ ; 
		}
 		messageBuilder.append("\nWhich tour would you join?") ; 
		super.setMessage(messageBuilder.toString()) ;
	}
	
	public void processInput ( chatbotController controller, String userReply , Event event ) {
		
		int userNoInput = 0 ; 
		try {
			userNoInput = Integer.parseInt(userReply) ; 
		} catch ( Exception e ) {
			log.info("NumberFormat Exception ") ; 
		}
		
		if(userNoInput < 1 || userNoInput >= selectedTourList.size()) 
			return ; 
		
		currentBooking.setTour(selectedTourList.get(userNoInput-1)) ; 
		
		
		controller.setInterface(new AskForAdultsNumState(this.currentBooking)) ;		
	}
	
}