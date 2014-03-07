package com.example.potato;

/**
 * stores a transaction keeping track of the source destination and amount
 * 
 * @author Michael Glover	
 */
public class Transaction {
	private String source;
	private String destination;
	private double ammount;
	public Transaction(String source, String destination, double ammount){
		this.source = source;
		this.destination = destination;
		this.ammount = ammount;
	}
	
	public String getSource(){ 
		return source;
	}
	
	public String getDestination(){
		return destination;
	}
	
	public double getAmmount(){
		return ammount;
	}
	
	public boolean isDeposit(){
		return (ammount > 0);
	}
}
