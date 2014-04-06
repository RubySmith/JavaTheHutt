package com.example.mrs_potato;

import java.util.ArrayList;

public class Account {
	private String name;
	private String number;
	private double balance;
	private String display;
	private float interest;
	private ArrayList<Transaction> trans = new ArrayList<Transaction>();
	
	public Account(double balance, String name){
		this.balance = balance;
		this. name = name;
	}
	public String getName(){
		return name;
	}
	public String getAccountNumber(){
		return number;
	}
	public void setAccountNumber(String number){
		this.number = number;
	}
	public String getPlanNumber(){
		return number;
	}
	public double getBalance(){
		return balance;
	}
	public void setBalance(double balance){
		this.balance = balance;
	}
	public float getInterest(){
		return interest;
	}
	public void setInterest(float interest){
		this.interest=interest;
	}
	public String getDisplayName(){
		return display;
	}
	public void setDisplayName(String display){
		this.display=display;
		
	}
	public void setTransactions(ArrayList<Transaction> trans){
		this.trans= trans;
	}
	public ArrayList<Transaction> getTransaction(){	
		return trans;
	}
}
