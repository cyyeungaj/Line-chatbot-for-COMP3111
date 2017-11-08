package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;
import java.util.*;


public abstract class tourManager extends Manager {
	public abstract ArrayList<Tour> getToursByPriceRange( double low , double high);
	public abstract ArrayList<Tour> getToursByPriceRange( double low , double high , ArrayList<Tour> tours) ;
	public abstract ArrayList<Tour> getTourByPlace( String place) ;
	public abstract ArrayList<Tour> getTourByPlace( String place , ArrayList<Tour> tours) ;
	public abstract ArrayList<Tour> getToursByTime( String startTime , String endTime ) ;
	public abstract ArrayList<Tour> getToursByTime( String startTime , String endTime , ArrayList<Tour> tours) ;
	public abstract ArrayList<Tour> getPromotedTour() ; 
	public abstract ArrayList<Tour> getToursGroupedByName();
	public abstract void insertTour(Tour tour);
}



