package com.example.potato;

import java.util.Date;

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
	
	public Date getdate(){
		return date;
	}
	public boolean isDeposit(){
		return (ammount > 0);
	}
}
