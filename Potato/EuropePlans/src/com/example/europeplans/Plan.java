package com.example.europeplans;

public class Plan {
	private Profile profile;
	private String name;
	private String number;
	private double balance;
	private String display;
	private double interest;
	
	public Plan(double balance, String name){
		this.balance = balance;
		this. name = name;
	}
	public Profile getProfile(){
		return profile;
	}
	public String getName(){
		return name;
	}
	
	public void setPlanNumber(String number){
		this.number = number;
	}
	public String getPlanNumber(){
		return number;
	}
	public double getBalance(){
		return balance;
	}
	public double getInterest(){
		return interest;
	}
	public String getDisplayName(){
		return display;
	}
}
