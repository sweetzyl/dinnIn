package com.haljenswehoncartinery.diningin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class Signin extends Activity {

	DiningInAdapter diningInAdapter;
	EditText username, password;
	SharedPreferences sharedPreferences;
	SharedPreferences.Editor editor;
	public static final String FILENAME = "signInCredentials";
	private static final String STATE = "state";
	InputStream is = null;
	String line = null;
	String result = null;
	String temp = "";
	String[] arr;

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);

		username = (EditText) findViewById(R.id.edtUserName);
		password = (EditText) findViewById(R.id.edtPass);

		diningInAdapter = new DiningInAdapter(this);
		
//		StrictMode.ThreadPolicy policy = new ThreadPolicy.Builder().permitAll().build();
//		StrictMode.setThreadPolicy(policy);
		
		try{
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://172.17.1.129/webservice/verify.php");
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			
		}catch(Exception e){
			System.out.println("Exception 1 caught");
		}try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			while((line = reader.readLine())!=null){
				sb.append(line+"\n");
				
				result = sb.toString();
				is.close();
				System.out.println("-------data--------");
				System.out.println(result);
			}
		}catch(Exception e){
			System.out.println("Exception 2 caught");
			try{
				JSONArray jArray = new JSONArray(result);
				int count = jArray.length();
				
				for(int i =0;i<count;i++){
					JSONObject json_data = jArray.getJSONObject(i);
					temp += json_data.getString("names")+":";
					arr = temp.split(":");		
				}
			}catch(Exception ex){
				System.out.println("Exception caught");
			}
		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.sign_in_main, menu);
//		return true;
//	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void signIn(View v) {
		String user = username.getText().toString();
		String pass = password.getText().toString();
		
		String data = diningInAdapter.getCredentials(user, pass);
		
		String nme = data.substring(0, data.indexOf(" "));
		String psw = data.substring(0, data.indexOf(" ") + 1);

		if (user.equals("") || pass.equals("")) {
			Toast.makeText(this, "Please fill out all the fields.", Toast.LENGTH_SHORT).show();
		} else {
			String checkUser = diningInAdapter.checkUsername(user);

			if (!user.equals(checkUser)) {
				Toast.makeText(this, "Account does not exist. Sign Up First.",Toast.LENGTH_SHORT).show();
			} else if (user.equals(checkUser)) {
				String checkPass = diningInAdapter.checkPassword(user);
				if (pass.equals(checkPass)) {
					Toast.makeText(this, "Credentials Correct", Toast.LENGTH_SHORT).show();
					sharedPreferences = getSharedPreferences(FILENAME, this.MODE_PRIVATE);
					editor = sharedPreferences.edit();
					editor.putString("username", nme);
					editor.putString("password", psw);
					editor.commit();
					
					sharedPreferences = getSharedPreferences("fk", this.MODE_PRIVATE);
					editor = sharedPreferences.edit();
					editor.putString("name", user);
					editor.commit();
					
					sharedPreferences = getSharedPreferences("fk", this.MODE_PRIVATE);
					String usname = sharedPreferences.getString("name", null);
					Intent i = new Intent(this, History.class);
					startActivity(i);
				} else {
					Toast.makeText(this, "Password does not match. Try Again", Toast.LENGTH_SHORT).show();
				}
			}
			
		}
	}
}
