package com.example.bot.spring;

import java.awt.print.Book;

public class Booking {


    private String date;
    private String BookingID;
    private String CustomerID;
    private String tourID;
    private int noOfAdults;
    private int noOfChildrens;
    private int noOfToodlers;
    private double tourFee;
    private double amountPaid;
    private String specialRequest;
    private boolean confirm;
    private double serviceCharge;

    public Booking(String date, String bookTableId, String clientId, String tourId,
                   int noOfAdults, int noOfChildrens, int noOfToodlers, double tourFee,
                   String specialRequest, double amountPaid, boolean confirm, double serviceCharge) {
    	
        this.date = date;
        this.BookingID = bookTableId;
        this.CustomerID = clientId;
        this.tourID = tourId;
        this.noOfAdults = noOfAdults;
        this.noOfChildrens = noOfChildrens;
        this.noOfToodlers = noOfToodlers;
        this.tourFee = tourFee;
        this.specialRequest = specialRequest;
        this.amountPaid = amountPaid;
        this.confirm = confirm;
        this.serviceCharge = serviceCharge;
        
    }
    
    public Booking () {
    	
    }

    public String getDate() {
        return date;
    }
    
    public void setDate( String date ) {
        this.date = date ;
    }
    
    public String getBookingID() {
        return BookingID;
    }
    
    public void setBookingID( int counter ) {
    	this.BookingID = tourID+date+counter ;
    }

    public String getCustomerID() {
        return CustomerID;
    }
    
    public void setCustomerID(String CustomerID) {
    	this.CustomerID = CustomerID ; 
    }
    
    public String getTourID() {
        return tourID;
    }
    
    public void setTourID( String tourID ) {
    	this.tourID = tourID ; 
    }

    public int getNoOfAdults() {
        return noOfAdults;
    }
    
    public void setNoOfAdults(int NoOfAdults) {
    	this.noOfAdults = NoOfAdults ; 
    }

    public int getNoOfChildrens() {
        return noOfChildrens;
    }
    
    public void setNoOfChildrens( int NoOfChildrens) {
    	this.noOfChildrens = NoOfChildrens ; 
    }

    public int getNoOfToodlers() {
        return noOfToodlers;
    }
    
    public void setNoOfToodlers( int noOfToodlers) {
    	this.noOfToodlers = noOfToodlers ; 
    }

    public double getTourFee() {
        return tourFee;
    }
    
    public void setTourFee ( Tour tour ) {
    	this.tourFee = getNoOfAdults() * tour.getPrice() + noOfChildrens * ( tour.getPrice() * 0.8);
    }

    public double getAmountPaid() {
        return amountPaid;
    }
    
    public void setAmountPaid ( double amountPaid) {
    	this.amountPaid = amountPaid ; 
    }
    
    public String getSpecialRequest() {
        return specialRequest;
    }
    
    public void setSpecialRequest( String specialRequest ) {
    	this.specialRequest = specialRequest ; 
    }

    public boolean isConfirm() {
        return confirm;
    }
    
    public void setConfirm(boolean confirm) {
    	this.confirm = confirm ; 
    }

    public double getServiceCharge() {
        return serviceCharge;
    }
    
    public void SetServiceCharge( Tour tour ) {
    	this.serviceCharge = (noOfAdults + noOfChildrens) * tour.getDuration() ; 
    }
    
}
