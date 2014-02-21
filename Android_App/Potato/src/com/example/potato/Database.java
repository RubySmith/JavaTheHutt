package com.example.potato;
import java.util.HashMap;

public class Database {

	private static HashMap<String, String> map=new HashMap<String, String>();
	
	/**
	 * Uses checkAdd function to check
	 * the username provided. If valid,
	 * username and password is added
	 * to the datastructure.
	 * 
	 * @author Will Cely	
	 */
	public static boolean add(String userName, String password){
		if (checkAdd(userName)){
			map.put(userName, password);
			return true;
		}
		return false;
	}
	
	/**
	 * Checks to see if username is
	 * already in datastructure
	 * if so it returns false. If
	 * not it returns true.
	 * 
	 * @author Will Cely	
	 */
	public static boolean checkAdd(String userName){
		return !(map.containsKey(userName));
	}
	
	/**
	 * Checks to see if the username
	 * is in the database, and that
	 * the password provided matches
	 * the password corresponding
	 * to the username provided.
	 * 
	 * @author Will Cely	
	 */
	public static boolean check(String userName, String passToChk){
		if (!map.containsKey(userName)) return false;
		if (!map.get(userName).equals(passToChk)) return false;
		else return true;
	}
	
	/**
	 * If the username provided is in
	 * the database then it will remove
	 * it.
	 * 
	 * @author Will Cely	
	 */
	public static void remove(String userName){
		if (map.containsKey(userName)){
			map.remove(userName);
		}
	}
	
}
