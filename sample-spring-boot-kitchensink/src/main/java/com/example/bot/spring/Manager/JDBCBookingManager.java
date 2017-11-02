package com.example.bot.spring;

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


public class JDBCBookingManager extends bookingManager {

	/**
	 *
	 * @param LineUserId : Line ID to search
	 * @return the result array list of Booking
     */
	public ArrayList<Booking> getBookingByLineUserId ( String LineUserId) {


		String SQLstatement = " SELECT * FROM Booking WHERE LineUserId = " + LineUserId + ";";
		ResultSet rs = null ; 
		try {
			rs = SelectionQuery(SQLstatement);
		} catch( Exception e) {
			
		}
		
		ArrayList<Booking> result = new ArrayList<Booking>();
		try {
			while (rs.next()) {
				int noOfAdults, noOfChildern, noOfToodler;
				double tourFee, amountPaid, serviceCharge;
				boolean confirmed;
				String date, customerID, bookingID, tourID, specialRequest;

				bookingID = rs.getString("BookTableID");
				customerID = rs.getString("CUSTOMER ID");
				date = rs.getString("Date");
				tourID = rs.getString("Tour ID");
				specialRequest = rs.getString("Special Request");

				noOfAdults = rs.getInt("Number of Adults");
				noOfChildern = rs.getInt("Number of Children");
				noOfToodler = rs.getInt("Number of Toodler");

				tourFee = rs.getDouble("Tour Fee");
				amountPaid = rs.getDouble("Amount Paid");
				serviceCharge = rs.getDouble("service charge");

				confirmed = rs.getBoolean("confirmed");


				Booking booking = new Booking(date, bookingID, customerID, tourID, noOfAdults,
						noOfChildern, noOfToodler, tourFee, specialRequest,
						amountPaid, confirmed, serviceCharge);
				result.add(booking);
			}
		}catch (SQLException e){

		}
		return result; 
	}
	public void insertBooking(Booking booking) {

	String SQLstatrment = "INSERT INTO BOOKING VALUES ( " + booking.getDate() + " ,"+ booking.getBookingID() + " ," + booking.getCustomerID() + " ," + booking.getTourID() + " ," + booking.getNoOfAdults()
	+ " ," + booking.getNoOfChildrens() + " ," + booking.getNoOfToodlers() + " ," + booking.getTourFee() + " ," + booking.getAmountPaid() + " ," + booking.getSpecialRequest() + " ," + booking.isConfirm()
	+ " ," + booking.getServiceCharge() + ") ;";

	insertDeleteQuery(SQLstatrment);
		
}; 
	
		
	public double calculateBookingFee ( Booking booking ) {
		int noOfChildern = booking.getNoOfChildrens();
		double tourFee = booking.getTourFee();
		double totalAmount = booking.getNoOfAdults() * tourFee + noOfChildern * (tourFee * 0.8);

		return totalAmount;

	} 
		
	public void deleteBookingByLineIdAndTourName ( String lineId , String bookingID ) {
		String SQLstatement = " DELETE FROM BOOKING WHERE BookTableID = " + bookingID + "AND CUSTOMER ID = " + lineId + ";";

		insertDeleteQuery(SQLstatement);
	}

}
