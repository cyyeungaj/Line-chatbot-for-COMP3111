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
public class BookingInterface extends UserInterface {
	
	private final static int INITIAL_STATE = 0 ; 
	
	private final static int ASK_FOR_TOUR_STATE = 1 ;
	private final static int ASK_FOR_TOUR_CHOICE_STATE = 2 ; 
	private final static int ASK_FOR_ADULTSNO_STATE = 3 ; 
	private final static int ASK_FOR_TOODLERNO_STATE = 4 ; 
	private final static int ASK_FOR_CHILDREN_STATE = 5 ; 
	private final static int ASK_FOR_FEECONFIRM_STATE = 6 ; 
	private final static int ASK_FOR_HKID_STATE = 7 ;
	
	private String ASK_FOR_TOUR_MESSSAGE = "Welcome. Here is the choices of the tours for your to choose:" ;   
	private String ASK_FOR_TOUR_CHOICE_MESSAGE = "" ;
	private String ASK_FOR_ADULTSNO_STATE_MESSAGE = "How many adults?" ; 
	private String ASK_FOR_TOODLERNO_STATE_MESSAGE = "How many children (Age 0 - 3) ?" ; 
	private String ASK_FOR_CHILDREN_STATE_MESSAGE = "How many children (Age 4 to 11) ?" ;
	private String ASK_FOR_FEECONFIRM_STATE_MESSAGE = "It will be $" ; 
	private String SHOW_ASSEMBLY_POINT_MESSAGE = "" ; 
	private String ASK_FOR_HKID_STATE_MESSAGE = "What is your HKID?" ; 
	

	private int currentState ;
	private String currentMessage ; 
	private ArrayList<Tour> tourList ; 
	private Booking currentBooking ; 
	private static int counter ; 
	private Tour selectedTour  ; 
	


	public BookingInterface () {
			init() ; 
	}
		
	private void init () {
		counter = 0 ;
		selectedTour = null ; 
		currentBooking = new Booking () ; 
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(ASK_FOR_TOUR_MESSSAGE) ; 
		currentState = ASK_FOR_TOUR_STATE ; 
		tourList=new JDBCTourManager().getToursGroupedByName () ; 
		int index = 1 ; 
		for( Tour currentTour : tourList ) {
			messageBuilder.append(index + ". ") ; 
			messageBuilder.append(currentTour.getTourName() + "\n") ; 
		}
		messageBuilder.append("\n") ; 
		ASK_FOR_TOUR_MESSSAGE = messageBuilder.toString() ; 
		setMessage(ASK_FOR_TOUR_MESSSAGE) ; 
	}
	
	
	public void processInput( chatbotController controller, String userReply , Event event) {
		int userNoInput = Integer.parseInt(userReply) ; 
        StringBuilder messageBuilder = new StringBuilder();
		switch(currentState) {
			case ASK_FOR_TOUR_STATE: 
				selectedTour = tourList.get(userNoInput) ; 
				tourList = new JDBCTourManager().getToursByName( selectedTour.getTourName()) ;
				currentState ++ ; 
				int index = 1 ; 
				for( Tour currentTour : tourList ) {
					messageBuilder.append(index + ". ") ; 
					messageBuilder.append(currentTour.getTourName() + "\n") ;
					messageBuilder.append(currentTour.gettourDescription() + "\n") ; 
					messageBuilder.append(currentTour.getHotel() + "\n") ; 
					messageBuilder.append(currentTour.getDepartureDate() + "\n") ; 
				}
				messageBuilder.append("\n") ; 
				super.setMessage(messageBuilder.toString()) ; 
				break ;
			case ASK_FOR_TOUR_CHOICE_STATE:
				selectedTour =  tourList.get(userNoInput);
				currentBooking.setTourID(selectedTour.getTourId()); 
				currentState ++ ;
				super.setMessage(ASK_FOR_ADULTSNO_STATE_MESSAGE); 
				break ;
			case ASK_FOR_ADULTSNO_STATE:
				currentBooking.setNoOfAdults(userNoInput) ; 
				currentState ++ ; 
				super.setMessage(ASK_FOR_TOODLERNO_STATE_MESSAGE) ; 
				break ;
			case ASK_FOR_TOODLERNO_STATE: 
				currentBooking.setNoOfToodlers(userNoInput) ; 
				currentState ++ ; 
				super.setMessage(ASK_FOR_CHILDREN_STATE_MESSAGE) ; 
				break ;
			case ASK_FOR_CHILDREN_STATE: 
				currentBooking.setNoOfChildrens(userNoInput) ; 
				currentState ++ ; 
				super.setMessage(ASK_FOR_FEECONFIRM_STATE_MESSAGE) ; 
				currentBooking.setTourFee(selectedTour) ; 
				break ;
			case ASK_FOR_FEECONFIRM_STATE: 
				if(userReply.toLowerCase() == "yes") {
					currentState ++ ; 
					String date = null ; 
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate localDate = LocalDate.now();
					date = localDate.format(formatter) ; 
					currentBooking.setDate(date);
					currentBooking.setSpecialRequest("");
					currentBooking.setAmountPaid(0.0) ; 
					currentBooking.setConfirm(false);
					currentBooking.SetServiceCharge(selectedTour); 
					super.setMessage(ASK_FOR_HKID_STATE_MESSAGE) ; 
				} else {
					controller.setInterface(new MenuInterface()) ; 
				}
				break ;
			case ASK_FOR_HKID_STATE: 
				currentBooking.setCustomerID(userReply) ; 
				new JDBCBookingManager().insertBooking(currentBooking) ; 
				controller.setInterface(new MenuInterface()) ; 
				break ;
		}


	}
	


	
}
