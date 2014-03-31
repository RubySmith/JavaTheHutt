package com.example.europeplans;
import android.database.sqlite.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.GregorianCalendar;
public class DataBaseHandler extends SQLiteOpenHelper{
	
	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "EuropePlansDatabase";
	private static final String TABLE_PROFILES = "users";
	private static final String USERS_NAME = "name";
	private static final String USERS_PASSWORD = "password";
	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static final String TABLE_PLANS = "plans";
	private static final String PLANS_USER = "user";
	private static final String PLANS_NAME = "name";
	private static final String PLANS_NUMBER = "number";
	private static final String PLANS_BALANCE = "balance";
    private static final String PLANS_DISPLAY = "display";
    private static final String PLANS_INTEREST = "interest";
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String TABLE_TRANSACTIONS = "transactions";
    private static final String TRANS_CAT = "category";
    private static final String TRANS_USER = "user";
    private static final String TRANS_ACCNAME = "account_name";
    private static final String TRANS_ACCNUM = "account_number";
    private static final String TRANS_NAME = "name";
    private static final String TRANS_AMOUNT = "amount";
    private static final String TRANS_ISDEPOSIT = "is_deposit";
    private static final String TRANS_USERTIME = "user_time";
    private static final String TRANS_SYSTIME = "system_time";
	public DataBaseHandler(Context context, String name, CursorFactory factory,
		int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	public DataBaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	 }
	@Override
	public void onCreate(SQLiteDatabase db) {
		// Database creation sql statement
		String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_PROFILES + "("
	         + USERS_NAME + " TEXT PRIMARY KEY," + USERS_PASSWORD + " TEXT NOT NULL)";
		db.execSQL(CREATE_USERS_TABLE);

		 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String CREATE_PLANS_TABLE = String.format("CREATE TABLE %s " +
                "(%s TEXT, %s TEXT, %s TEXT, %s TEXT, %s REAL, %s REAL, " +
                "PRIMARY KEY (%s, %s, %s), " +
                "FOREIGN KEY (%s) REFERENCES %s(%s))", TABLE_PLANS, PLANS_USER,
                PLANS_NAME,
                PLANS_NUMBER, PLANS_DISPLAY, PLANS_BALANCE, PLANS_INTEREST,
                PLANS_USER, PLANS_NAME, PLANS_NUMBER, PLANS_USER, TABLE_PROFILES,
                USERS_NAME);
		db.execSQL(CREATE_PLANS_TABLE);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 String CREATE_TRANSACTIONS_TABLE = String.format("CREATE TABLE %s" +
	                "(%s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s REAL, %s INTEGER, %s INTEGER, %s INTEGER, " +
	                "FOREIGN KEY (%s, %s, %s) REFERENCES %s(%s, %s, %s))",
	                TABLE_TRANSACTIONS, TRANS_CAT, TRANS_USER, TRANS_ACCNAME, TRANS_ACCNUM, TRANS_NAME,
	                TRANS_AMOUNT, TRANS_ISDEPOSIT, TRANS_USERTIME, TRANS_SYSTIME,
	                TRANS_USER, TRANS_ACCNAME, TRANS_ACCNUM,
	                TABLE_PLANS, PLANS_USER, PLANS_NAME, PLANS_NUMBER);
	        db.execSQL(CREATE_TRANSACTIONS_TABLE);
		 // add default user
		ContentValues values = new ContentValues();
		values.put(USERS_NAME, "admin"); // User name
		values.put(USERS_PASSWORD, "pass123"); // User password
		 // insert username and password into table
		 db.insert(TABLE_PROFILES, null, values);	

	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANS);
        // Create tables again
        onCreate(db);
			
	}
		
	// SQL operations
	public void addProfile(Profile user) throws InvalidUserException {
		// get database reference
		SQLiteDatabase db = this.getWritableDatabase();
		// read in username and password
	   ContentValues values = new ContentValues();
	   values.put(USERS_NAME, user.getUsername()); // User name in the table
	   values.put(USERS_PASSWORD, user.getPassword()); // User password
	   // insert username and password into table
	   try {
		   db.insertOrThrow(TABLE_PROFILES, null, values);
	   } catch (Exception ex) {
		   throw new InvalidUserException("User already exists in database");
	   } finally {
		   db.close(); // Closing database connection
	   }
	}
	
	public Profile getProfile(String username, String password) throws InvalidUserException, InvalidPasswordException {
		// get database
		SQLiteDatabase db = this.getReadableDatabase();
		// generate query and send it off
		String whereClause = String.format("%s=?", USERS_NAME);
		Cursor cursor = db.query(TABLE_PROFILES, new String[] {USERS_NAME, USERS_PASSWORD},
		                        whereClause, new String[] {username}, null, null, null);
		cursor.moveToFirst();
		// check query results
		Profile user = null;
		// no matching username
		if (cursor.getCount() == 0) {
		   throw new InvalidUserException("Username not found in database");
		   
		}
		// matching username
		else {
		   // matching password
		   if (cursor.getString(1).equals(password)) {
		       user = new Profile(cursor.getString(0), cursor.getString(1));
		   }
		   
		   // mismatched password
		   else {
		       throw new InvalidPasswordException("Password is incorrect");
		   }
		}
		// close out and return
		cursor.close();
		db.close();
		user.setPlans(getPlans(user));
		return user;
	}
	
    public void addPlan(Profile user, Plan plan) throws InvalidAccountException {
		// get database reference
		SQLiteDatabase db = this.getWritableDatabase();
		// read in values
		ContentValues values = new ContentValues();
		values.put(PLANS_USER, user.getUsername()); // User name
		values.put(PLANS_NAME, plan.getName());
		values.put(PLANS_NUMBER, plan.getPlanNumber());
		values.put(PLANS_DISPLAY, plan.getDisplayName() );
		values.put(PLANS_BALANCE, plan.getBalance());
		values.put(PLANS_INTEREST, plan.getInterest());
		// insert username and password into table
		try {
		db.insertOrThrow(TABLE_PLANS, null, values);
		} catch (Exception ex) {
		throw new InvalidAccountException("Identical account already exists for user");
		} finally {
		db.close(); // Closing database connection
		}
	}
	
    public ArrayList<Plan> getPlans(Profile user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = String.format("%s=?", PLANS_USER);
        String[] fields = {PLANS_NAME, PLANS_DISPLAY, PLANS_NUMBER, PLANS_INTEREST, PLANS_BALANCE};
        Cursor cursor = db.query(TABLE_PLANS, fields, whereClause, new String[]{user.getUsername()},
                null, null, null);
//        cursor.moveToFirst();
        ArrayList<Plan> plans = new ArrayList<Plan>();
//        while (!cursor.isAfterLast()) {
//            Plan currentPlan = new Plan(cursor.getFloat(2), cursor.getString(0));
//            currentPlan.setPlanNumber(cursor.getString(1));
//            currentPlan.setTransactions(getTransactions(user, currentPlan));
//            plans.add(currentPlan);
//            cursor.moveToNext();
//        }
        return plans;
    }
	    
	    
	@SuppressWarnings("serial")
	public class InvalidUserException extends Exception {
		public InvalidUserException() {
	    	 super();
	 	 }
		public InvalidUserException(String message) {
	   	 	super(message);
	 	  }
	 }
	    
	@SuppressWarnings("serial")
	public class InvalidPasswordException extends Exception {
		 public InvalidPasswordException() {
			 super();
		 }
	  	 public InvalidPasswordException(String message) {
	      	  super(message);
	 	  }
	  }
	
	@SuppressWarnings("serial")
	public class InvalidAccountException extends Exception {
        public InvalidAccountException() {
            super();
        }
        public InvalidAccountException(String message) {
            super(message);
        }
    }
}
