package com.haljenswehoncartinery.diningin;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DiningInHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "restaurvation";
	public static final String TABLE_NAME = "login";
	private static final int DB_VERSION = 1;

	public static final String UID = "_id";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";


	private static final String CREATE_SIGNUP_TABLE = "CREATE TABLE "
			+ TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ USERNAME + " VARCHAR(50), " + PASSWORD + " VARCHAR(50));";

	private static final String DROP_TABLE = "DROP TABLE IF EXISTS "
			+ TABLE_NAME; 

	private Context context;

	public DiningInHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(CREATE_SIGNUP_TABLE);
			//db.execSQL(DROP_TABLE);
		} catch (SQLException e) {
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			db.execSQL(DROP_TABLE);
			onCreate(db);
		} catch (SQLException e) {
		}
	}
}
