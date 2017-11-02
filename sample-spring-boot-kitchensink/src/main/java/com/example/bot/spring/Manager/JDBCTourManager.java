package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;
import java.util.*;


public class JDBCTourManager extends tourManager {

	public ArrayList<Tour> getToursByPriceRange( int low , int high) {

		String SQLstatement = "SELECT * FROM TOUR WHERE PRICE BETWEEN " + low + " AND " + high + " ;" ;
		ResultSet rs = null ; 
		try{
			rs = SelectionQuery(SQLstatement);
		} catch ( Exception e) {
			
		}
		ArrayList<Tour> result = new ArrayList<Tour>();

		try{
			while(rs.next()){
				String tourID, tourName, tourDescription, hotel, departureDate, tourGuideName, tourGuideLine;
				int countryID, regionID, duration, tourCapacity, minimumCustomerRequired;
				double price;

				tourID = rs.getString("");
				tourName = rs.getString("");
				tourDescription = rs.getString("");
				hotel = rs.getString("");
				departureDate = rs.getString("");
				tourGuideName = rs.getString("");
				tourGuideLine = rs.getString("");

				countryID = rs.getInt("");
				regionID = rs.getInt("");
				duration = rs.getInt("");
				tourCapacity = rs.getInt("");
				minimumCustomerRequired = rs.getInt("");

				price = rs.getDouble("");

				Tour tour = new Tour(tourID, countryID, regionID, tourName, tourDescription,
						hotel, duration, departureDate, tourCapacity, minimumCustomerRequired,
						price, tourGuideName, tourGuideLine);
				result.add(tour);
			}

		}catch (SQLException e){

		}
		return result;

	}
	public ArrayList<Tour> getToursByPriceRange( int low , int high , ArrayList<Tour> tours) {

		String SQLstatement = "SELECT * FROM TOUR WHERE PRICE BETWEEN " + low + " AND " + high + " ;" ;

		return null ;
	}
	
	public ArrayList<Tour> getTourByPlace( String place ) { return null ;}


	public ArrayList<Tour> getTourByPlace( String place , ArrayList<Tour> tours) { 
		return null ;
	}
	
	public ArrayList<Tour> getToursByTime( String startTime , String endTime ) { 
		return null ;
	}
	
	public ArrayList<Tour> getToursByTime( String startTime , String endTime , ArrayList<Tour> tours) { 
		return null ;
	}
	
	public void insertTour(Tour tour) {
		String SQLstatement = "INSERT INTO TOUR VALUES ( );";
		insertDeleteQuery(SQLstatement);
	};
	
	public ArrayList<Tour> getPromotedTour() {
		return null ; 
	}

}
