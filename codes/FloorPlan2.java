package com.haljenswehoncartinery.diningin;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class FloorPlan2 extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_floor_plan2);
	}

	public void Reserve(View view) {
		Intent i = new Intent(this, Reserve.class);
		startActivity(i);
	}
	
	public void Back(View v){
		Intent i = new Intent(this, FloorPlan1.class);
		startActivity(i);
	}
}
