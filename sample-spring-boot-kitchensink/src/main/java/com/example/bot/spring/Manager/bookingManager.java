package com.example.bot.spring;

import java.util.*;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;

public abstract class bookingManager extends Manager{
	
	/* This method is to get all the  
	 * booking records based on line user id
	 * 
	 * 
	 * 
	 * 
	 **/
	
	public abstract ArrayList<Booking> getBookingByLineUserId (String LineUserId) ; 
	public abstract void insertBooking(Booking booking) ; 
	public abstract void deleteBookingByLineIdAndTourName ( String lineId , String tourName );
}
