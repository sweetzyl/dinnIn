package com.haljenswehoncartinery.diningin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}
	
	public void FloorPlan(View view){
		Intent intent = new Intent(this, FloorPlan1.class);
		startActivity(intent);
	}
	
	public void onFavo(View view){
		Intent intent = new Intent(this, MenuFavorites.class);
		startActivity(intent);
	}
	
	public void onGrill(View view){
		Intent intent = new Intent(this, MenuGrill.class);
		startActivity(intent);
	}
	
	public void onApp(View view){
		Intent intent = new Intent(this, MenuAppetizer.class);
		startActivity(intent);
	}
	
	public void onDessert(View view){
		Intent intent = new Intent(this, MenuDessert.class);
		startActivity(intent);
	}
	
	public void onDrinks(View view){
		Intent intent = new Intent(this, MenuDrinks.class);
		startActivity(intent);
	}
}
