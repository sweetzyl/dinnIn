package com.haljenswehoncartinery.diningin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DiningInAdapter {

	DiningInHelper helper;
	private SQLiteDatabase db;

	public DiningInAdapter(Context context) {
		helper = new DiningInHelper(context);
	}

	public long insertCredentials(String user, String pass) {
		db = helper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(helper.USERNAME, user);
		contentValues.put(helper.PASSWORD, pass);
		long id = db.insert(helper.TABLE_NAME, null, contentValues);
		return id;
	}

	public String checkUsername(String username) {
		SQLiteDatabase db = helper.getWritableDatabase();
		String un = null;
		try {
			un = null;
			String[] columns = { helper.USERNAME };
			String[] selectionArgs = { username };
			Cursor cursor = db.query(helper.TABLE_NAME, columns,
					helper.USERNAME + " =?", selectionArgs, null, null, null);
			while (cursor.moveToNext()) {
				int index1 = cursor.getColumnIndex(helper.USERNAME);
				un = cursor.getString(index1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return un;
	}

	public String checkPassword(String username) {
		SQLiteDatabase db = helper.getWritableDatabase();
		String pw = null;
		try {
			pw = null;
			String[] columns = { helper.PASSWORD};
			String[] selectionArgs = { username };
			Cursor cursor = db.query(helper.TABLE_NAME, columns,
					helper.USERNAME + " =?", selectionArgs, null, null, null);
			while (cursor.moveToNext()) {
				int index1 = cursor.getColumnIndex(helper.PASSWORD);
				pw = cursor.getString(index1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pw;
	}
	
	

	public String getCredentials(String username, String password) {
		SQLiteDatabase db = helper.getWritableDatabase();
		StringBuffer stringBuffer = new StringBuffer();
		String[] columns = { helper.USERNAME, helper.PASSWORD };
		Cursor cursor = db.query(helper.TABLE_NAME, columns, helper.USERNAME
				+ " = '" + username + "'" + " and " + helper.PASSWORD + " ='"
				+ password + "'", null, null, null, null);
		while (cursor.moveToNext()) {
			int index1 = cursor.getColumnIndex(helper.USERNAME);
			int index2 = cursor.getColumnIndex(helper.PASSWORD);
			String un = cursor.getString(index1);
			String pw = cursor.getString(index2);
			stringBuffer.append(un + " " + pw);
		}

		return stringBuffer.toString();
	}

	public String getAlldata() {
		SQLiteDatabase db = helper.getWritableDatabase();
		String[] columns = { helper.UID, helper.USERNAME,
				helper.PASSWORD };
		Cursor cursor = db.query(helper.TABLE_NAME, columns, null, null, null,
				null, null);
		StringBuffer buffer = new StringBuffer();
		while (cursor.moveToNext()) {
			int cid = cursor.getInt(0);
			String s1 = cursor.getString(1);
			String s2 = cursor.getString(2);
			buffer.append(s1 + " " + s2 + "\n");
		}
		;
		return buffer.toString();
	}
}
