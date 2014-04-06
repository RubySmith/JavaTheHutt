/**
 * 
 */
package com.example.mrs_potato;

import java.util.GregorianCalendar;

/**
 * @author katpham
 *
 */
public class Transaction {
	private String category;
	private String name;
	private double amount;
	private long  id;
	private GregorianCalendar userTime;
//	private GregorianCalendar sysTime;
	private boolean isDeposit;
	public Transaction(String name, double amount, boolean isDeposit, 
			String category, GregorianCalendar userTime2, GregorianCalendar sysTime2){
		this.name=name;
		this.amount = amount;
		this.isDeposit = isDeposit;
		this.category = category;
		this.userTime = userTime2;
//		this.sysTime = sysTime2;
	}
	public String getCategory(){
		return category;
	}
	public String getName(){
		return name;
	}
	public double getAmount(){
		return amount;
	}
	
	public void setID(long id){
		this.id=id;
	}
	
	public long getId(){
		return id;
	}
	
	public GregorianCalendar getUserTimeStamp() {
		return userTime;
	}
	
	public boolean isDeposit() {
		return isDeposit;
	}
}
