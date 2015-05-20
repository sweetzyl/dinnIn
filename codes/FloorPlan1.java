package com.haljenswehoncartinery.diningin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class FloorPlan1 extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_floor_plan1);
	}
	
	public void Next(View v) {
		Intent i = new Intent(this, FloorPlan2.class);
		startActivity(i);
	}
	
	public void Reserve (View view) {
		Intent i = new Intent(this, Reserve.class);
		startActivity(i);
	}
}
