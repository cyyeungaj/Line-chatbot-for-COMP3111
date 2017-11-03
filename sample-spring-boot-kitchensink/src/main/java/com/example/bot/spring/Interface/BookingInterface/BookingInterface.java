package com.example.bot.spring;
import java.lang.StringBuilder;


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

public class BookingInterface extends UserInterface {
	
	private final static int INITIAL_STATE = 0 ; 
	
	private final static int ASK_FOR_TOUR_STATE = 1 ;
	private final static int ASK_FOR_TOUR_CONFIRM_STATE = 2 ; 
	private final static int ASK_FOR_DATE_STATE = 3 ; 
	private final static int ASK_FOR_ADULTSNO_STATE = 4 ; 
	private final static int ASK_FOR_TOODLERNO_STATE = 5 ; 
	private final static int ASK_FOR_FEECONFIRM_STATE = 6 ; 
	private final static int SHOW_ASSEMBLY_POINT_STATE = 7 ;

	private int currentState ;
	private ArrayList<Tour> tourList ; 
	


	public BookingInterface () {
		//JDBCTourManager manager = new JDBCTourManager() ; 

	}
	
	public void processInput( chatbotController controller, String userReply, Event event) {

		
	}

	
}
