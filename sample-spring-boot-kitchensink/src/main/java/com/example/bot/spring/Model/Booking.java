package com.example.bot.spring;

import java.awt.print.Book;

public class Booking {

	private String date ;
	private String bookingID ;
	private String customerID ;
	private String tourID ;
	private int noOfAdults ;
	private int noOfChildrens ;
	private int noOfToodlers ;
	private double tourFee ;
	private double amountPaid ;
	private String specialRequest ;
	private boolean isConfirm ;
	private double serviceCharge ;

	public Booking(String date, String bookTableId, String customerID, String tourId,
				   int noOfAdults, int noOfChildrens, int noOfToodlers, double tourFee,
				   String specialRequest, double amountPaid, boolean confirm, double serviceCharge) {
		this.date = date;
		this.bookingID = bookTableId;
		this.customerID = customerID;
		this.tourID = tourId;
		this.noOfAdults = noOfAdults;
		this.noOfChildrens = noOfChildrens;
		this.noOfToodlers = noOfToodlers;
		this.tourFee = tourFee;
		this.specialRequest = specialRequest;
		this.amountPaid = amountPaid;
		this.isConfirm = confirm;
		this.serviceCharge = serviceCharge;
	}
	
	public String getDate() { return this.date ; }
	public String getCustomerID () { return this.customerID  ;}
	public String getTourId () { return this.tourID ;} 
	public int getNoOfAdults () { return this.noOfAdults ; } 
	public int getNoOfChildrens () { return this.noOfChildrens ; } 
	public int getnoOfToodlers () { return this.noOfToodlers ; }
	public double getTourFee () { return this.tourFee ; } 
	public String getSpecialRequest () { return this.specialRequest ; }
	public double getAmountPaid () { return this.amountPaid ; } 
	public boolean isConfirm () { return this.isConfirm ; }
	public double getServiceCharge () { return this.serviceCharge ; } 
	

}
