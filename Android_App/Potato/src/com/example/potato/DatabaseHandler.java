package com.example.potato;
import android.content.Context;
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

}
