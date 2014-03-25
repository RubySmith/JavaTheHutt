package com.example.potato;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class DateRangeSelectionActivity extends Activity {

	DatabaseHandler db=new DatabaseHandler(this);
	private DatePicker startDateEntry;
	private DatePicker endDateEntry;
	protected static Date startDate;
	protected static Date dayB4;
	protected static Date endDate;
	protected static Date dayAfter;
	static String reportType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_range_selection);
		startDateEntry = (DatePicker)findViewById(R.id.dateEntryStart);
		endDateEntry = (DatePicker)findViewById(R.id.dateEntryEnd);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.date_range_selection, menu);
		return true;
	}
	public void onConfirm(View V){
		int day = startDateEntry.getDayOfMonth();
		int month = startDateEntry.getMonth();
		int year = startDateEntry.getYear()-1900;
		startDate = new Date(year,month,day);
		dayB4=new Date(year, month, day-1);
		
		day = endDateEntry.getDayOfMonth();
		month = endDateEntry.getMonth();
		year = endDateEntry.getYear()-1900;
		endDate = new Date(year,month,day);
		dayAfter= new Date(year,month,day+1);
		//db.generateReport(startDate,endDate);
		Intent intent= new Intent(this, GenReportActivity.class);
		startActivity(intent);
		finish();
	}
	public void onCancel(View v){
		Intent intent = new Intent(this, AccountsActivity.class);
		startActivity(intent);
		finish();
	}

}
