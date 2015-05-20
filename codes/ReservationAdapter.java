package com.haljenswehoncartinery.diningin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;

public class ReservationAdapter {

	ReservationHelper helper;
	private SQLiteDatabase db;

	public ReservationAdapter(Context context) {
		helper = new ReservationHelper(context);
	}

	public long insertReservation(String name, String table, String time, String contact) {
		db = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(helper.NAME, name);
		cv.put(helper.TABLE, table);
		cv.put(helper.TIME, time);
		cv.put(helper.CONTACT, contact);
		long id = db.insert(helper.TABLE_NAME, null, cv);
		return id;
	}

	public String getCredentials(String name, String table, String time, String contact)  {
		SQLiteDatabase db = helper.getWritableDatabase();
		StringBuffer stringBuffer = new StringBuffer();
		String[] columns = { helper.NAME, helper.TABLE, helper.TIME, helper.CONTACT };
		Cursor cursor = db.query(helper.TABLE_NAME, columns, helper.NAME + " = '" + name + "'" + " and "
		+ helper.TABLE + " ='" + table + "'" + " and "
		+ helper.TIME + " ='" + time + "'" + " and "
		+ helper.CONTACT + " ='" + contact, null, null, null, null);
		while (cursor.moveToNext()) {
			int index1 = cursor.getColumnIndex(helper.NAME);
			int index2 = cursor.getColumnIndex(helper.TABLE);
			int index3 = cursor.getColumnIndex(helper.TIME);
			int index4 = cursor.getColumnIndex(helper.CONTACT);
			String nem = cursor.getString(index1);
			String tbl = cursor.getString(index2);
			String tim = cursor.getString(index3);
			String cont = cursor.getString(index4);
			
			stringBuffer.append(nem + " " + tbl + " " + tim + " " + cont);
		}

		return stringBuffer.toString();
	}

	public String getAlldata() {
		SQLiteDatabase db = helper.getWritableDatabase();
		String[] columns = { helper.UID, helper.NAME, helper.TABLE, helper.TIME, helper.CONTACT };
		Cursor cursor = db.query(helper.TABLE_NAME, columns, null, null, null, null, null);
		StringBuffer buffer = new StringBuffer();
		while (cursor.moveToNext()) {
			int cid = cursor.getInt(0);
			String s1 = cursor.getString(1);
			int s2 = cursor.getInt(2);
			int s3 = cursor.getInt(3);
			int s4 = cursor.getInt(4);
			
			buffer.append(s1 + " " + s2 + " " + s3 + " " + s4 + "\n");
		};
		return buffer.toString();
	}

}
