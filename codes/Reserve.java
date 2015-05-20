package com.haljenswehoncartinery.diningin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Reserve extends Activity {

	ReservationAdapter reservationAdapter;
	SharedPreferences sharedPreferences;
	private static final String FILENAME = "signInCredentials";
	
	EditText edtName;
	EditText edtTime;
	EditText edtContact;
	Spinner spnTable;
	String[] tableNo = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	String selTable;
	Button btnReserve;
	InputStream is = null, is2 = null, is3= null;
	String line = null, line2 = null, result = null, temp = "", result2 = null, temp2 = "";
	String[] arr, arr2;

	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// setup the strict mode
		// StrictMode.ThreadPolicy policy = new
		// StrictMode.ThreadPolicy.Builder().permitAll().build();
		// StrictMode.setThreadPolicy(policy);

		setContentView(R.layout.activity_reserve);

		edtName = (EditText) findViewById(R.id.edtName);
		spnTable = (Spinner) findViewById(R.id.spinner);
		edtTime = (EditText) findViewById(R.id.edtTime);
		edtContact = (EditText) findViewById(R.id.edtContact);
		ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tableNo);
		adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnTable.setAdapter(adapter_state);
		btnReserve = (Button) findViewById(R.id.breserve);
		
		fieldUsername();

		btnReserve.setOnClickListener(new OnClickListener() {

			
			@Override
			public void onClick(View v) {
				String name = edtName.getText().toString().trim();
				selTable = (String) spnTable.getSelectedItem();
				String tableNo = selTable;
				String time = edtTime.getText().toString();
				String contact = edtContact.getText().toString();
				String status = "2";

				if (checkTbl(tableNo) == 1) {
					Toast.makeText(getApplicationContext(), "Table has already been reserved", Toast.LENGTH_SHORT).show();
				}
				else{
					// web database
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
					nameValuePairs.add(new BasicNameValuePair("name", name));
					nameValuePairs.add(new BasicNameValuePair("tableNo", tableNo));
					nameValuePairs.add(new BasicNameValuePair("time", time));
					nameValuePairs.add(new BasicNameValuePair("contact", contact));
					nameValuePairs.add(new BasicNameValuePair("status", status));

					try {
						HttpClient client = new DefaultHttpClient();
						HttpPost post = new HttpPost("http://172.17.1.129/webservice/reservation.php");
						post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
						HttpResponse response = client.execute(post);
						HttpEntity entity = response.getEntity();
						is = entity.getContent();

						String msg = "Data entered successfully";
						Toast.makeText(getBaseContext(), msg,Toast.LENGTH_SHORT).show();
					} catch (ClientProtocolException e) {
						Log.e("ClientProtocol", "Log_tag");
						e.printStackTrace();
					} catch (IOException e) {
						Log.e("Log_tag", "IOException");
						e.printStackTrace();
					}
				}
			}
		});
	}

	public int checkTbl(String tbl) {
		int check = 0;
		try {
			HttpClient httpClient2 = new DefaultHttpClient();
			HttpPost httpPost2 = new HttpPost("http://172.17.1.129/webservice/verifytable.php");
			HttpResponse response2 = httpClient2.execute(httpPost2);
			HttpEntity entity2 = response2.getEntity();
			is2 = entity2.getContent();

		} catch (Exception ey) {
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is2, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
				result = sb.toString();
				is2.close();
			}
		} catch (Exception ey) {
			try {
				JSONArray jArray = new JSONArray(result);
				int count = jArray.length();
				for (int i = 0; i < count; i++) {
					JSONObject json_data = jArray.getJSONObject(i);
					temp += json_data.getString("tableNo") + ":";
				}
				arr = temp.split(":");

				while (count > 0) {
					if (arr[count - 1].equals(tbl)) {
						check = 1;
					}
					count--;
				}
			} catch (Exception ex) {
			}
		}
		return check;
	}
	
	public int checkStatus(String stat) {
		int check2 = 0;
		try {
			HttpClient httpClient3 = new DefaultHttpClient();
			HttpPost httpPost3 = new HttpPost("http://172.17.1.129/webservice/verifyStat.php");
			HttpResponse response3 = httpClient3.execute(httpPost3);
			HttpEntity entity3 = response3.getEntity();
			is3 = entity3.getContent();

		} catch (Exception ey) {
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is3, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			while ((line2 = reader.readLine()) != null) {
				sb.append(line2 + "\n");
				result2 = sb.toString();
				is3.close();
			}
		} catch (Exception ey) {
			try {
				JSONArray jArray = new JSONArray(result2);
				int count2 = jArray.length();
				for (int i = 0; i < count2; i++) {
					JSONObject json_data = jArray.getJSONObject(i);
					temp2 += json_data.getString("status") + ":";
				}
				arr = temp.split(":");

				while (count2 > 0) {
					if (arr[count2 - 1].equals(stat)) {
						check2 = 1;
					}
					count2--;
				}
			} catch (Exception ex) {
			}
		}
		return check2;
	}
	
	public void fieldUsername(){
		sharedPreferences = getSharedPreferences(FILENAME, this.MODE_PRIVATE);
		String user = sharedPreferences.getString("username","");
		edtName.setText(user);
	}
	
	
	
	
	
}
