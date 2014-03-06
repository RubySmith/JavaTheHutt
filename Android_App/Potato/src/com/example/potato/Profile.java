package com.example.potato;
import java.util.ArrayList;
import java.util.List;


/**
 * Keeps track of all acounts for a particular user. 
 * contains their username and password.
 * 
 * @author Michael Glover	
 */
public class Profile {
	List<Account> accounts = new ArrayList<Account>();
	private String username;
	private String password;
	
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
	
	public void addAccount(String name){
		accounts.add(new Account(name));
	}
	
}
