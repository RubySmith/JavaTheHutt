package com.example.europeplans;

import java.util.ArrayList;

public class Profile {
	String username;
	String password;
	ArrayList<Plan> listOFplans;
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
	
	public void setPlans(ArrayList<Plan> plans){ // array of travel plans to show up
		listOFplans  = plans;
	}
}
