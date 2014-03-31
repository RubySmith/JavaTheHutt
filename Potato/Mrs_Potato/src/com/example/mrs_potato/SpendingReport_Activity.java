package com.example.mrs_potato;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SpendingReport_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spending_report_);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spending_report_, menu);
		return true;
	}

}
