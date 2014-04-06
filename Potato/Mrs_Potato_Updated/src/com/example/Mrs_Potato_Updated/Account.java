package com.example.Mrs_Potato_Updated;

import java.util.ArrayList;

public class Account {
	private String name;
	private double balance;
	private ArrayList<Transaction> trans = new ArrayList<Transaction>();
	
	public Account(double balance, String name){
		this.balance = balance;
		this. name = name;
	}
	public String getName(){
		return name;
	}
	public double getBalance(){
		return balance;
	}
	public void setBalance(double balance){
		this.balance = balance;
	}
	public void setTransactions(ArrayList<Transaction> trans){
		this.trans= trans;
	}
	public ArrayList<Transaction> getTransaction(){	
		return trans;
	}
}
