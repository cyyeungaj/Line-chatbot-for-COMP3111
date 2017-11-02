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
	public ArrayList<Tour> getToursByPriceRange( int low , int high , ArrayList<Tour> tours) {  return null ;  }  
	public ArrayList<Tour> getTourByPlace( String place , ArrayList<Tour> tours) { return null ;}
	public ArrayList<Tour> getToursByTime( String startTime , String endTime , ArrayList<Tour> tours) { return null ;}
	public void insertTour(Tour tour) {
		String SQLstatement = "INSERT INTO TOUR VALUES ( );";
		insertDeleteQuery(SQLstatement);
	};
}
