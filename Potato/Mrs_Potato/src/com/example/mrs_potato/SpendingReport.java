package com.example.mrs_potato;

import java.util.ArrayList;

public class SpendingReport {
	private ArrayList<Transaction> trans;
	
	public SpendingReport(){
		trans = new ArrayList<Transaction>();
	}
	
	public void addTransactionToList(Transaction transaction){
		trans.add(transaction);
	}
	public ArrayList<Transaction>getTransactionList(){
		return trans;
	}
	
	public String getFirst(ArrayList<Transaction> trans){
		return trans.get(0).getName();
	}

}
