package com.example.potato;

public class Current {
	
	
	private static Account currAcnt;
	private static Profile loggedIn;
	
	
	public static Account getAccount(){
		return currAcnt;
	}
	
	public static Profile getProfile(){
		return loggedIn;
	}
	
	public static void setAccount(Account a){
		currAcnt=a;
	}
	
	public static void setProfile(Profile p){
		loggedIn=p;
	}
}
