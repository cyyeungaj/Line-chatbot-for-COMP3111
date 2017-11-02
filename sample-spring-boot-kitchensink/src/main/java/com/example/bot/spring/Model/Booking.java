package com.example.bot.spring;

import java.awt.print.Book;

public class Booking {
	private String date ;
	private String BookingID ;
	private String CustomerID ;
	private String tourID ;
	private int noOfAdults ;
	private int noOfChildrens ;
	private int noOfToodlers ;
	private double tourFee ;
	private double amountPaid ;
	private String specialRequest ;
	private boolean confirm ;
	private double serviceCharge ;

	public Booking(String date, String bookTableId, String clientId, String tourId,
				   int noOfAdults, int noOfChildrens, int noOfToodlers, double tourFee,
				   String specialRequest, double amountPaid, boolean confirm, double serviceCharge) {
		this.date = date;
		BookingID = bookTableId;
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

	public String getDate() {
		return date;
	}

	public String getBookingID() {
		return BookingID;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public String getTourID() {
		return tourID;
	}

	public int getNoOfAdults() {
		return noOfAdults;
	}

	public int getNoOfChildrens() {
		return noOfChildrens;
	}

	public int getNoOfToodlers() {
		return noOfToodlers;
	}

	public double getTourFee() {
		return tourFee;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public String getSpecialRequest() {
		return specialRequest;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public double getServiceCharge() {
		return serviceCharge;
	}
}
