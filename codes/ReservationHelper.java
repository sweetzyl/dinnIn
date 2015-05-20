package com.haljenswehoncartinery.diningin;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReservationHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "restaurvation";
	public static final String TABLE_NAME = "reserve";
	private static final int DB_VERSION = 4;

	public static final String UID = "_id";
	public static final String NAME = "name";
	public static final String TABLE = "table";
	public static final String TIME = "time";
	public static final String CONTACT = "contact";
	
	private static final String CREATE_RESERVATION_TABLE = "CREATE TABLE "
			+ TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(50), " + TABLE + " INT(50), " + TIME + " VARCHAR(10), " + CONTACT + " VARCHAR(11));";

	private static final String DROP_TABLE = "DROP TABLE IF EXISTS "
			+ TABLE_NAME; 

	private Context context;

	public ReservationHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(CREATE_RESERVATION_TABLE);
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
