package com.example.potato;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * SQLite implementation for
 * user database
 * 
 * @author Shehmeer Jiwani	
 */


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
		try{
			if (cursor==null||cursor.getString(0)==null||cursor.getString(1)==null) return null;
		}catch (Exception E){
			return null;
		}
		Profile profile = new Profile(cursor.getString(0), cursor.getString(1)); //hmmmmmm
		
		// return profile
		return profile;
	}
	
	// Getting all profiles
	public List<Profile> getAllProfiles() {
		List<Profile> profileList = new ArrayList<Profile>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_PROFILES;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Profile profile = new Profile(cursor.getString(0),
						cursor.getString(1));
				// Adding profile to list
				profileList.add(profile);
			} while (cursor.moveToNext());
		}

		// return profile list
		return profileList;
	}
	
	// Getting profiles count
	public int getProfilesCount() {
		String countQuery = "SELECT  * FROM " + TABLE_PROFILES;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}
	
	// Updating single profile
	public int updateProfile(Profile profile) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_USERNAME, profile.getUsername());
		values.put(KEY_PASSWORD, profile.getPassword());

		// updating row
		return db.update(TABLE_PROFILES, values, KEY_USERNAME + " = ?", //check SQL validity
				new String[] { String.valueOf(profile.getUsername()) });
	}
	
	// Deleting single profile
	public void deleteProfile(Profile profile) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PROFILES, KEY_USERNAME + " = ?",
				new String[] { String.valueOf(profile.getUsername()) });
		db.close();
	}

}