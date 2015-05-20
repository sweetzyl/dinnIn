package com.haljenswehoncartinery.diningin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class MainActivity extends Activity {

	VideoView vid;
	SharedPreferences sharedPreferences;
	private static final String STATE = "state";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		vid = (VideoView) findViewById(R.id.video);
		String urlpath = "android.resource://" + getPackageName() + "/"
				+ R.raw.dining_2v1;
		vid.setVideoURI(Uri.parse(urlpath));
		vid.start();

		sharedPreferences = getSharedPreferences(STATE, this.MODE_PRIVATE);
		boolean session = sharedPreferences.getBoolean("transitions", false);
		sharedPreferences = getSharedPreferences("fk", this.MODE_PRIVATE);
		String usename = sharedPreferences.getString("name", null);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
//		openDB();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
//		closeDB();
	}

	public void signUp(View v) {
		Intent i = new Intent(this, Signup.class);
		startActivity(i);
	}

	public void signIn(View v) {
		Intent i = new Intent(this, Signin.class);
		startActivity(i);
	}

//	public void openDB() {
//		phoneBookDBAdapter = new PhoneBookDBAdapter(this);
//		phoneBookDBAdapter.open();
//	}
//
//	public void closeDB() {
//		phoneBookDBAdapter.close();
//	}
}
