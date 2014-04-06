package com.example.mrs_potato;

import java.util.ArrayList;

public class Profile {
	String username;
	String password;
	ArrayList<Account> listOFAccounts;
	public Profile(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setAccounts(ArrayList<Account> accounts){ // array of travel plans to show up
		listOFAccounts  = accounts;
	}
}
