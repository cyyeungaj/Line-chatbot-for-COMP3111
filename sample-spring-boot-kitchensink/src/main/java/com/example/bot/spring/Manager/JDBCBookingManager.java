package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;
import java.util.*;


public class JDBCBookingManager extends bookingManager {
	public ArrayList<Booking> getBookingByLineUserId ( String LineUserId) { return null ; }  
	public void insertBooking(Booking booking) {}; 
	public void deleteBookingByLineIdAndTourName ( String lineId , String tourName ) {}

}
