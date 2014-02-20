package com.example.potato;
import java.util.HashMap;

public class Database {

	private static HashMap<String, String> map=new HashMap<String, String>();
	
	public static boolean add(String userName, String password){
		if (checkAdd(userName)){
			map.put(userName, password);
			return true;
		}
		return false;
	}
	
	public static boolean checkAdd(String userName){
		return !(map.containsKey(userName));
	}
	
	public static boolean check(String userName, String password){
		if (!map.containsKey(userName)) return false;
		if (!map.get(userName).equals(password)) return false;
		else return true;
	}
	
	public static void remove(String userName){
		if (map.containsKey(userName)){
			map.remove(userName);
		}
	}
	
}
