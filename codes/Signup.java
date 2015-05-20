package com.haljenswehoncartinery.diningin;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class Signup extends Activity {

	DiningInAdapter diningInAdapter;
	EditText un, pw1, pw2;
	Button btnUp;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//		StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.activity_signup);
		diningInAdapter = new DiningInAdapter(this);
		un = (EditText) findViewById(R.id.edtUserName);
		pw1 = (EditText) findViewById(R.id.edtPass);
		pw2 = (EditText) findViewById(R.id.edtconfirm);
		btnUp = (Button) findViewById(R.id.btnSignU);

		btnUp.setOnClickListener(new View.OnClickListener() {
			InputStream is = null;

			@Override
			public void onClick(View v) {

				String user = un.getText().toString().trim();
				String pass1 = pw1.getText().toString().trim();
				String pass2 = pw2.getText().toString().trim();

				if (user.equals("") || pass1.equals("") || pass2.equals("")) {
					Toast.makeText(getApplicationContext(),"Please fill out all the fields.", Toast.LENGTH_LONG).show();
				} else if (!pass1.equals(pass2)) {
					Toast.makeText(getApplicationContext(),"Passwords should match", Toast.LENGTH_LONG).show();
				} else {
					String checkUser = diningInAdapter.checkUsername(user);
					if (user.equals(checkUser)) {
						Toast.makeText(getApplicationContext(), "Username has already been taken. Choose another.", Toast.LENGTH_SHORT).show();
					} else {
						// local database
						long indicator = diningInAdapter.insertCredentials(user, pass1);
						if (indicator != 0) {
							
							Toast.makeText(getApplicationContext(), "Successfully saved! Thank you for Signing up.", Toast.LENGTH_SHORT).show();
							//web database
							List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
							nameValuePairs.add(new BasicNameValuePair("username", user));
							nameValuePairs.add(new BasicNameValuePair("password", pass1));
							try {
								HttpClient httpClient = new DefaultHttpClient();
								HttpPost httpPost = new HttpPost("http://172.17.1.129/webservice/credentials.php");
								httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
								HttpResponse response = httpClient.execute(httpPost);
								HttpEntity entity = response.getEntity();
								is = entity.getContent();

								String msg = "Data entered successfully";
								Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_SHORT).show();
							} catch (ClientProtocolException e) {
								Log.e("ClientProtocol", "Log_tag");
								e.printStackTrace();
							} catch (IOException e) {
								Log.e("Log_tag", "IOException");
								e.printStackTrace();
							}

							Intent intent = new Intent(getApplicationContext(), MainActivity.class);
							startActivity(intent);
							finish();

						} else {
							Toast.makeText(getApplicationContext(),"Could not save. Try again",Toast.LENGTH_SHORT).show();
						}
					}
				}
			}
		});
	}

	public void cancel(View v) {
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}
}
