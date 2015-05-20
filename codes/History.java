package com.haljenswehoncartinery.diningin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class History extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
}
	
	public void menus(View view){
		Intent intent = new Intent(this, com.haljenswehoncartinery.diningin.Menu.class);
		startActivity(intent);
	}
	
	public void load(){
		String s = "Established in August 2002, CASA VERDE is a chain of family-owned restaurants in Cebu City. " +
				"Spanish for green house, CASA VERDE's name was influenced by the owners' Spanish roots and the color " +
				"of the Ramos Branch, which used to be one of the family's ancestral homes. "+
                 "Originally, the Ramos Branch was supposed to be just a small canteen that catered to the residents of " +
                 "the 2nd floor dormitory and some students from nearby colleges. Through word-of-mouth and recommendations " +
                 "by family and friends, the humble canteen soon became a full-scale restaurant. After almost a decade and three" +
                 " branches later, CASA VERDE has grown into one of Cebu's most popular dining destinations."+
                 "Value for Money has always been the restaurant's philosophy. CASA VERDE believes that everyone deserves " +
                 "to enjoy great food and quality service at reasonable prices in a comfortable atmosphere. Its casual dining at its best. ";
	}
	
	 public boolean onOptionsItemSelected(MenuItem item) {
	     
	        int id = item.getItemId();
	        if (id == R.id.action_settings) {
	            return true;
	        }
	        return super.onOptionsItemSelected(item);	    
	 }
}
