package com.example.bot.spring;

import java.sql.ResultSet;
import java.util.ArrayList;

public class BookingInterface extends UserInterface {
	
	private final static int INITIAL_STATE = 1 ; 
	private final static int ASK_FOR_TOUR_STATE = 2 ;
	private final static int ASK_FOR_TOUR_CONFIRM_STATE = 3 ; 
	private final static int ASK_FOR_DATE_STATE = 4 ; 
	private final static int ASK_FOR_ADULTSNO_STATE = 5 ; 
	private final static int ASK_FOR_TOODLERNO_STATE = 6 ; 
	private final static int ASK_FOR_FEECONFIRM_STATE = 7 ; 
	private final static int SHOW_ASSEMBLY_POINT_STATE = 8 ; 
	private int currentState ;
	
	public BookingInterface ( ) {
		
	}
	
	public void processInput( chatbotController controller, String userReply) {
		
	}

	
}
