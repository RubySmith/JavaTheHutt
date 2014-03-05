package com.example.potato;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper {
	
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "profileManager";
 
    // Profiles table name
    private static final String TABLE_PROFILES = "profiles";
    
    // Profiles Table Columns names
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    //Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_PROFILES_TABLE = "CREATE TABLE " + TABLE_PROFILES + "("
                + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_PROFILES_TABLE);
	}

	// Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);
 
        // Create tables again
        onCreate(db);
    }
    
    // Adding new contact
	public void addProfile(Profile profile) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_USERNAME, profile.getUsername()); // Profile Username
		values.put(KEY_PASSWORD, profile.getPassword()); // Profile Password

		// Inserting Row
		db.insert(TABLE_PROFILES, null, values);
		db.close(); // Closing database connection
	}
	
	// Getting single profile
	public Profile getProfile(String username) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_PROFILES, new String[] {
				KEY_USERNAME, KEY_PASSWORD }, KEY_USERNAME + "=?", //accepts username as parameter
				new String[] { String.valueOf(username) }, null, null, null, null); //check SQL validity
		if (cursor != null)
			cursor.moveToFirst();

		Profile profile = new Profile(cursor.getString(0), cursor.getString(1)); //hmmmmmm
		// return profile
		return profile;
	}

}
