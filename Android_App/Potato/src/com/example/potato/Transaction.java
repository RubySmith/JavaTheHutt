package com.example.potato;

import java.util.Date;

import java.text.DateFormat;

import android.util.Log;


/**
 * stores a transaction keeping track of the source destination and amount
 * 
 * @author Michael Glover	
 */
public class Transaction {
	private String category;
	private double ammount;
	private Date date;
	public Transaction(String category, double ammount,Date date){
		this.category = category;
		this.ammount = ammount;
		this.date =date;
	}
	
	public String getCategory(){
		return category;
	}
	
	public double getAmmount(){
		return ammount;
	}
	
	public Date getDate(){
		return date;
	}
	
	public String getDateString(){
		DateFormat df= DateFormat.getDateInstance();
		String dateString=df.format(date);
		Log.d("Debug", "In Transaction: "+dateString);
		return dateString;
	}
	public boolean isDeposit(){
		return (ammount > 0);
	}
}
