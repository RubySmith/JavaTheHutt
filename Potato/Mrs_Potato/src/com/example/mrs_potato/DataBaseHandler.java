package com.example.mrs_potato;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

public class DataBaseHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "Mr_PotatoDatabase";

    // tables and their fields in format: TABLE_($NAME), ($NAME)_FIELD
    private static final String TABLE_USERS = "users";
    private static final String USERS_NAME = "name";
    private static final String USERS_PASSWORD = "password";
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String TABLE_ACCOUNTS = "accounts";
    private static final String ACCOUNTS_USER = "user";
    private static final String ACCOUNTS_NAME = "name";
    private static final String ACCOUNTS_NUMBER = "number";
    private static final String ACCOUNTS_DISPLAY = "display";
    private static final String ACCOUNTS_BALANCE = "balance";
    private static final String ACCOUNTS_INTEREST = "interest";
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
	
    
    // child constructor
    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // On database creation
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create tables
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                                    + USERS_NAME + " TEXT PRIMARY KEY," + USERS_PASSWORD + " TEXT NOT NULL)";
        db.execSQL(CREATE_USERS_TABLE);
        String CREATE_ACCOUNTS_TABLE = String.format("CREATE TABLE %s " +
                                       "(%s TEXT, %s TEXT, %s TEXT, %s TEXT, %s REAL, %s REAL, " +
                                       "PRIMARY KEY (%s, %s, %s), " +
                                       "FOREIGN KEY (%s) REFERENCES %s(%s))", TABLE_ACCOUNTS, ACCOUNTS_USER,
                                       ACCOUNTS_NAME,
                                       ACCOUNTS_NUMBER, ACCOUNTS_DISPLAY, ACCOUNTS_BALANCE, ACCOUNTS_INTEREST,
                                       ACCOUNTS_USER, ACCOUNTS_NAME, ACCOUNTS_NUMBER, ACCOUNTS_USER, TABLE_USERS,
                                       USERS_NAME);
        db.execSQL(CREATE_ACCOUNTS_TABLE);
        String CREATE_TRANSACTIONS_TABLE = String.format("CREATE TABLE %s" +
                "(%s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s REAL, %s INTEGER, %s INTEGER, %s INTEGER, " +
                "FOREIGN KEY (%s, %s, %s) REFERENCES %s(%s, %s, %s))",
                TABLE_TRANSACTIONS, TRANS_CAT, TRANS_USER, TRANS_ACCNAME, TRANS_ACCNUM, TRANS_NAME,
                TRANS_AMOUNT, TRANS_ISDEPOSIT, TRANS_USERTIME, TRANS_SYSTIME,
                TRANS_USER, TRANS_ACCNAME, TRANS_ACCNUM,
                TABLE_ACCOUNTS, ACCOUNTS_USER, ACCOUNTS_NAME, ACCOUNTS_NUMBER);
        db.execSQL(CREATE_TRANSACTIONS_TABLE);
        
        // add default user
        ContentValues values = new ContentValues();
        values.put(USERS_NAME, "admin"); // User name
        values.put(USERS_PASSWORD, "pass123"); // User password
        // insert username and password into table
        db.insert(TABLE_USERS, null, values); //code that inserts into dattabase
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop tables if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
        onCreate(db);         // Create tables again
    }
    
    // SQL operations
    public void addProfile(Profile user) throws InvalidUserException {
        // get database reference
        SQLiteDatabase db = this.getWritableDatabase();
        // read in username and password
        ContentValues values = new ContentValues();
        values.put(USERS_NAME, user.getUsername()); // User name
        values.put(USERS_PASSWORD, user.getPassword()); // User password
        // insert username and password into table
        try {
            db.insertOrThrow(TABLE_USERS, null, values);
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
		Cursor cursor = db.query(TABLE_USERS, new String[] {USERS_NAME, USERS_PASSWORD},
		                     whereClause, new String[] {username}, null, null, null);
		cursor.moveToFirst();
		// check query results
		Profile user = null;
		// no matching username
		if (cursor.getCount() == 0) {
		throw new InvalidUserException();
		
		}
		// matching username
		else {
		// matching password
//			System.out.println("BOOOOOB");
		if (cursor.getString(1).equals(password)) {
//			System.out.println("BOOB");
		    user = new Profile(cursor.getString(0), cursor.getString(1));
//		    System.out.println("user: " + user);
		}
		// mismatched password
		else {
		    throw new InvalidPasswordException();
		}
		}
		// close out and return
		cursor.close();
		db.close();
		
		user.setAccounts(getAccounts(user));
//		System.out.println("WOOOO");
		return user;
	}
    
    public void addAccount(Profile user, Account account) throws InvalidAccountException {
		// get database reference
		SQLiteDatabase db = this.getWritableDatabase();
		// read in values
		ContentValues values = new ContentValues();
		values.put(ACCOUNTS_USER, user.getUsername()); // User name
		values.put(ACCOUNTS_NAME, account.getName());
		values.put(ACCOUNTS_NUMBER, account.getAccountNumber());
		values.put(ACCOUNTS_DISPLAY, account.getDisplayName());
		values.put(ACCOUNTS_BALANCE, account.getBalance());
		values.put(ACCOUNTS_INTEREST, account.getInterest());
		// insert username and password into table
		try {
			db.insertOrThrow(TABLE_ACCOUNTS, null, values);
		} catch (Exception ex) {
			throw new InvalidAccountException("Identical account already exists for user");
		} finally {
			db.close(); // Closing database connection
		}
    }

    public ArrayList<Account> getAccounts(Profile user) {
//        System.out.println("MAAAAA");
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = String.format("%s=?", ACCOUNTS_USER);
        String[] fields = {ACCOUNTS_NAME, ACCOUNTS_NUMBER, ACCOUNTS_DISPLAY, ACCOUNTS_BALANCE, ACCOUNTS_INTEREST};
        Cursor cursor = db.query(TABLE_ACCOUNTS, fields, whereClause, new String[]{user.getUsername()},
                null, null, null);
        cursor.moveToFirst();
        ArrayList<Account> accounts = new ArrayList<Account>();
        while (!cursor.isAfterLast()) {
            Account currentAccount = new Account(cursor.getFloat(3), cursor.getString(0));
            currentAccount.setAccountNumber(cursor.getString(1));
            currentAccount.setDisplayName(cursor.getString(2));
            currentAccount.setInterest(cursor.getFloat(4));
//            System.out.println("LAAAAA");
            currentAccount.setTransactions(getTransactions(user, currentAccount));
//            System.out.println("MOOO");
            accounts.add(currentAccount);
            cursor.moveToNext();
        }
//        System.out.println("WAAAAA");
        return accounts;
    }
    
    public void addTransaction(Profile user, Account account, Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TRANS_USER, user.getUsername());
        values.put(TRANS_ACCNAME, account.getName());
        values.put(TRANS_ACCNUM, account.getAccountNumber());
        values.put(TRANS_CAT, transaction.getCategory());
        values.put(TRANS_NAME, transaction.getName());
        values.put(TRANS_AMOUNT, transaction.getAmount());
        values.put(TRANS_ISDEPOSIT, transaction.isDeposit() ? 1 : 0);
        values.put(TRANS_USERTIME, transaction.getUserTimeStamp().getTimeInMillis());
        values.put(TRANS_SYSTIME, transaction.getUserTimeStamp().getTimeInMillis());
        transaction.setID(db.insert(TABLE_TRANSACTIONS, null, values));

        // need to update stored account balance too
        ContentValues account_vals = new ContentValues();
        account_vals.put(ACCOUNTS_BALANCE, account.getBalance());
        String whereClause = String.format("%s=? AND %s=? AND %s=?", ACCOUNTS_USER, ACCOUNTS_NAME, ACCOUNTS_NUMBER);
        db.update(TABLE_ACCOUNTS, account_vals, whereClause, new String[] {user.getUsername(), account.getName(), account.getAccountNumber()});

        db.close();
    }
	
	public ArrayList<Transaction> getTransactions(Profile user, Account account) {
//		System.out.println("TRANS");
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = String.format("%s=? AND %s=? AND %s=?", TRANS_USER, TRANS_ACCNAME, TRANS_ACCNUM);
        String[] fields = {TRANS_NAME, TRANS_CAT, TRANS_AMOUNT, TRANS_ISDEPOSIT, TRANS_USERTIME, TRANS_SYSTIME, "rowid"};
//        System.out.println("LOOOOOSER");
        Cursor cursor = db.query(TABLE_TRANSACTIONS, fields, whereClause, new String[]{user.getUsername(), account.getName(), account.getAccountNumber()},
                null, null, null);
        cursor.moveToFirst();
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(0);
            String category = cursor.getString(1);
            float amount = cursor.getFloat(2);
            boolean isDeposit = cursor.getInt(3) == 1;
            GregorianCalendar userTime = new GregorianCalendar();
            userTime.setTimeInMillis(cursor.getLong(4));
            GregorianCalendar sysTime = new GregorianCalendar();
            sysTime.setTimeInMillis((cursor.getLong(5)));
            long id = cursor.getLong(6);
            Transaction newTransaction = new Transaction(name, amount, isDeposit, category, userTime, sysTime);
            newTransaction.setID(id);

            transactions.add(newTransaction);
            cursor.moveToNext();
        }
        return transactions;
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
