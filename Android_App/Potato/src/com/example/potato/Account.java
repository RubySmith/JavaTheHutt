package com.example.potato;

import java.util.ArrayList;
import java.util.List;

/**
 * contains a list of transactions and a net balance for a particular account
 * 
 * @author Michael Glover	
 */
public class Account {
	String name;
	int total;
	List<Transaction> transactions = new ArrayList<Transaction>();

	public Account(String name, int total){
		this.name = name;
		this.total = total;
	}
	public Account(String name){
		this.name = name;
		total = 0;
	}
	
	public String getName(){
		return name;
	}
	
	public int getTotal(){
		return total;
	}
	
	public void addTransaction(String source, String destination, int ammount){
		transactions.add(new Transaction(source, destination, ammount));
	}
	
}
