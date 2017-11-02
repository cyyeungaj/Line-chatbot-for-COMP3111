package com.example.bot.spring;

public class Tour {
	
	private String tourId ; 
	private int countryId ; 
	private int regionId ; 
	private String tourName ; 
	private String tourDescription ; 
	private String hotel ; 
	private int duration ; 
	private String departureDate ; 
	private int tourCapacity ; 
	private int minimumCustomerRequired ; 
	private double price ; 
	private String tourGuideName ;
	private String tourGuideLine ; 
	
	public Tour ( String tourId , int countryId , int regionId ,String tourName , 
				  String tourDescription , String hotel ,  int duration , 
				  String departureDate ,int tourCapacity ,int minimumCustomerRequired , 
				  double price ,String tourGuideName , String tourGuideLine ) {
		this.tourId = tourId ;
		this.countryId = countryId ; 
		this.regionId = regionId ;
		this.tourName = tourName ; 
		this.tourDescription = tourDescription ;
		this.hotel = hotel ;  
		this.duration = duration  ;  
		this.departureDate = departureDate ; 
		this.tourCapacity = tourCapacity ;
		this.minimumCustomerRequired = minimumCustomerRequired ;  
		this.price = price ;
		this.tourGuideName = tourGuideName ; 
		this.tourGuideLine = tourGuideLine ; 
	}
	
	public String getTourId() { 
		return this.tourId ; 
	}
	
	public String getTourName() { 
		return this.tourName ; 
	}
	
	public String gettourDescription () { 
		return this.tourDescription  ;
	}
	
	public String getHotel () { 
		return this.hotel ;
	} 
	
	public int getDuration () { 
		return this.duration ;
	} 
	
	public String getDepartureDate () { 
		return this.departureDate ;
	} 
	
	public int getTourCapacity () { 
		return this.tourCapacity ;
	} 
	
	public int getMinimumCustomerRequired () { 
		return this.minimumCustomerRequired;
	}
	
	public double getPrice () { 
		return this.price; 
	} 
	
	public String getTourGuideName () { 
		return this.tourGuideName ;
	}
	
	public String getTourGuideLine () { 
		return this.tourGuideLine ;
	} 
	
}
