package com.haljenswehoncartinery.diningin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuGrill extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_grill);
	}
	
	public void Back(View view) {
		Intent intent = new Intent(this, Menu.class);
		startActivity(intent);
	}
}
