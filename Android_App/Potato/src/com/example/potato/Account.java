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
	double total;
	List<Transaction> transactions = new ArrayList<Transaction>();

	public Account(String name, double total){
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
	
	public double getTotal(){
		return total;
	}
	
	
	public List<Transaction> getTransactions(){
		return transactions;
	}
	
	public void addTransaction(String source, String destination, double ammount){
		Transaction t=new Transaction(source, destination, ammount);
		transactions.add(t);
		total=total+ammount;
	}
	public String toString(){
		return name;
	}
}
