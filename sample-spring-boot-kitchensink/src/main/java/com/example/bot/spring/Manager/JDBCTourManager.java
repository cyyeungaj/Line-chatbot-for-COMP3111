package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

@Slf4j
public class JDBCTourManager extends tourManager {
	private final String TOUR_ID = "tour_id";
	private final String TOUR_NAME = "tour_name";
	private final String TOUR_DEC = "tour_shortdec";
	private final String HOT = "hotel";
	private final String DEPART_DATE = "departure_date";
	private final String TG_NAME = "tour_guide";
	private final String TG_LINE = "tour_guide_line_ac";
	private final String COUNTRY_ID = "country_id";
	
	private final String REGION_ID = "region_id";
	private final String DUR = "duration";
	private final String TOUR_CAP = "tour_cap";
	private final String MIN_REQ_CAP = "min_req_cap";
	private final String FEE = "tour_fee";
	
	
	/**
	 *
	 * @param low : lower boundary of range
	 * @param high : upper boundary of proce
     * @return List of tours that price ranged (low,high) from the database
     */
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
				result.add(getRecord(rs));
			}

		}catch (SQLException e){

		}
		
		
		return result;
	}

	/**
	 *
	 * @param low : lower boundary of range
	 * @param high : upper boundary of proce
	 * @param tours : List of selected tours
     * @return List of tours that price ranged (low,high) from the list tours
     */
	public ArrayList<Tour> getToursByPriceRange( int low , int high , ArrayList<Tour> tours) {
		ArrayList<Tour> result = new ArrayList<Tour>();
		for(Tour tour: tours){
			double price = tour.getPrice();
			if( price > low && price < high)
				result.add(tour);
		}
		return result;
	}

	/**
	 *
	 * @param place : Target place
	 * @return : list of tour with the target place
     */
	public ArrayList<Tour> getTourByPlace( String place ) {

		//Case that place is a Country
		int countryCode = -1;
		String searchCountry = "SELECT * FROM COUNTRY WHERE COUNTRY_NAME = " + place + ";";
		ResultSet rs = null ;  
		
		try{
			rs = SelectionQuery(searchCountry);
		} catch ( Exception e) {
			
		}
		
		ArrayList<Tour> result = new ArrayList<Tour>();
		try{
			countryCode = rs.getInt("COUNTRY_ID");
		}catch(SQLException e){
			log.info("SQLException while connecting to sql server: {}", e.toString());
		}
		if(countryCode == -1) {
			String SQLstatement = "SELECT * FROM TOURLIST WHERE COUNTRY_ID = " + countryCode + ";";
			
			try {
				rs = SelectionQuery(SQLstatement);
			} catch ( Exception e) {
				log.info("Exceptino occur in getTourByPlace function") ; 
			}
			try {
				while (rs.next()) {
					result.add(getRecord(rs));
				}

			} catch (SQLException e) {
				log.info("SQLException while connecting to sql server: {}", e.toString());
			}
       
        
		if(result != null)
			return result;
		}else // Case that place is a region
		{
			int regionCode = -1;
			String searchRegion = "SELECT * FROM COUNTRY WHERE COUNTRY_NAME = " + place + ";";
			try{
				rs = SelectionQuery(searchRegion);
			} catch ( Exception e ) {
				
			}
			try{
				regionCode = rs.getInt("COUNTRY_ID");
			}catch(SQLException e){
				log.info("SQLException while connecting to sql server: {}", e.toString());
			}
			String SQLstatement = "SELECT * FROM TOURLIST WHERE REGION_ID = " + regionCode + ";";
			try{
				rs = SelectionQuery(SQLstatement);
			} catch ( Exception e) {
				
			}
			
			try {
				while (rs.next()) {
					result.add(getRecord(rs));
				}

			} catch (SQLException e) {
				log.info("SQLException while connecting to sql server: {}", e.toString());
			}
         
         
			if(result != null)
				return result;
		}
		return null ;
	}

	/**
	 *
	 * @param place : Target place
	 * @param tours : List of selected tours
     * @return : list of tour with the target place
     */
	public ArrayList<Tour> getTourByPlace( String place , ArrayList<Tour> tours) {

		ArrayList<Tour> result = new ArrayList<Tour>();
		int countryCode = -1, regionCode = -1;
		String searchCountryCode = "SELECT * FROM COUNTRY WHERE COUNTRY_NAME = " + place + ";";
		String searchRegionCode = "SELECT * FROM REGION WHERE REGION_NAME = " + place + ";";
		ResultSet countrySet = null ; 
		try {
			countrySet = SelectionQuery(searchCountryCode);
		} catch( Exception e) {
			log.info("Exception e in calling SelectionQuery from  countrySet ", e.toString());
		}
		
		ResultSet regionSet = null ; 
		try{
			regionSet = SelectionQuery(searchRegionCode);
		} catch( Exception e) {
			
		}
		
		try {
			countryCode = countrySet.getInt("");
		}catch (SQLException e){
			log.info("SQLException while connecting to sql server: {}", e.toString());
		}
		
		try {
			regionCode = regionSet.getInt("");
		}catch (SQLException e){
			log.info("SQLException while connecting to sql server: {}", e.toString());
		}

		for(Tour tour : tours){
			if(tour.getCountryId() == countryCode)
				result.add(tour);
			else if(tour.getRegionId() == regionCode)
				result.add(tour);
		}
		return result;
	}

	/**
	 *
	 * @param startTime
	 * @param endTime
     * @return
     */
	public ArrayList<Tour> getToursByTime( String startTime , String endTime ) {

		String SQLstatement = "SELECT * FROM TOURLIST WHERE DEPARTURE_DATE >= '"
				+ startTime + "' AND DEPARTURE_DATE <= '" + endTime + "' ;" ;
		ResultSet rs = null ; 
		try{ 
			rs=  SelectionQuery(SQLstatement);
		} catch ( Exception e) {
			log.info("Exception occur when rs = SelectionQuery(SQLstatement) in getToursByTime function {}", e.toString());
		}
		ArrayList<Tour> result = new ArrayList<Tour>();
		try{
			while (rs.next()) 
			{
				result.add(getRecord(rs));
			}

		}catch (SQLException e){
			log.info("SQLException while connecting to sql server: {}", e.toString());
		}
		
		
		return result;
	}

	/**
	 *
	 * @param startTime
	 * @param endTime
	 * @param tours
     * @return
     */
	public ArrayList<Tour> getToursByTime( String startTime , String endTime , ArrayList<Tour> tours) {

		ArrayList<Tour> result = new ArrayList<Tour>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = null, end = null;
		try {
			start = sdf.parse(startTime);
			end = sdf.parse(endTime);
		}catch (Exception e){

		}
		for(Tour tour: tours){
			Date departureDate = null ; 
			try {
				departureDate = sdf.parse(tour.getDepartureDate());
			} catch ( Exception e) {
				log.info(" Exception occur in function getToursByTime on statement Date departureDate = sdf.parse(tour.getDepartureDate())");
			}
			
			if(departureDate.after(start) && departureDate.before(end))
				result.add(tour);
		}
		return result ;
	}

	/**
	 * Insert new Tour to database
	 * @param tour : tour to be inserted
     */
	public void insertTour(Tour tour) {
		String SQLstatement = "INSERT INTO TOUR VALUES ( );";
		try {
			insertDeleteQuery(SQLstatement);
		} catch(Exception e) {
			
		}
	};
	
	public ArrayList<Tour> getPromotedTour() {
		String SQLstatement = "SELECT * FROM tourlist inner join promotion on tourlist.tour_id = promotion.tour_id; ";
		ResultSet rs = null;
		 ArrayList<Tour> result = new  ArrayList<Tour>();
	   try{ 
		   rs =  SelectionQuery(SQLstatement);
		}  catch ( Exception e) {
		   log.info("Exception occur when rs = SelectionQuery(SQLstatement); in getPromotionTour ()", e.toString());
		}
	  
	    try {
	    while (rs.next())
	    {
	    	result.add(getRecord(rs));
	    }	
	    }catch (SQLException e)
	    {log.info("SQLException while connecting to sql server: {}", e.toString());}
	   
	    
		
		return result;
		
	}

	public ArrayList<Tour> getToursGroupedByName () {
		ArrayList<Tour> result = new ArrayList<Tour>();
		String SQLstatement = "Select * from tourlist group by  tour_name;";
		ResultSet rs = null;
		try{ 
			rs =  SelectionQuery(SQLstatement);
		}  
		catch ( Exception e) 
		{
		   log.info("Exception occur when rs = SelectionQuery(SQLstatement); in getToursGroupedByName ()", e.toString());
		}
	  
		try 
			{
				while(rs.next()) 
					{
						result.add(getRecord(rs));
						}
				}
		catch (SQLException e)
			{
				log.info("SQLException while connecting to sql server: {}", e.toString());
				}
					
		
		
		
		return result; 
	}
	
	public ArrayList<Tour> getToursByName ( String tourName ) {
		ArrayList<Tour> result = new ArrayList<Tour>();
		String SQLstatement = "Select * from tourlist where tour_name = tourName;";
		ResultSet rs = null;
		try{ 
	    	 rs =  SelectionQuery(SQLstatement);
		}  catch (Exception e) 
		{
		   log.info("Exception occur when rs = SelectionQuery(SQLstatement); in getToursByName ()", e.toString());
		}
	  
		try 
		{
		while(rs.next()) 
		{
			result.add(getRecord(rs));
		}
		}
		catch (SQLException e)
		{
		 log.info("SQLException while connecting to sql server: {}", e.toString());
		}
		
		
		
		
		return result;
		
	}
	
	
	public Tour getRecord(ResultSet rs){
				
		try {
			String tourID, tourName, tourDescription, hotel, departureDate, tourGuideName, tourGuideLine;
			int countryID, regionID, duration, tourCapacity, minimumCustomerRequired ;
			double price;

		tourID = rs.getString(TOUR_ID);
		tourName = rs.getString(TOUR_NAME);
		tourDescription = rs.getString(TOUR_DEC );
		hotel = rs.getString(HOT);
		departureDate = rs.getString(DEPART_DATE);
		tourGuideName = rs.getString(TG_NAME);
		tourGuideLine = rs.getString(TG_LINE);
		countryID = rs.getInt(COUNTRY_ID);
		regionID = rs.getInt(REGION_ID);
		duration = rs.getInt(DUR);
		tourCapacity = rs.getInt(TOUR_CAP);
		minimumCustomerRequired = rs.getInt(MIN_REQ_CAP);
		price = rs.getDouble(FEE);
	
		Tour tour = new Tour(tourID, countryID, regionID, tourName, tourDescription,
				hotel, duration, departureDate, tourCapacity, minimumCustomerRequired,
				price, tourGuideName, tourGuideLine);
		
		return tour;
		
		}catch (Exception e) {}
		
		return null;
	
	}
	
	
	
}