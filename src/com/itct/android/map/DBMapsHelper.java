package com.itct.android.map;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

@SuppressWarnings("unused")
public class DBMapsHelper extends SQLiteOpenHelper
{

	public static final String TABLE_NAME = "data_location";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_LAT = "lat";
	public static final String COLUMN_LONG = "long";
	private static final String db_name ="location.db";
	private static final int db_version=1;
	
	// Database creation sql statement
	  private static final String db_create = "create table "
	      + TABLE_NAME + "(" 
	      + COLUMN_ID +" integer primary key autoincrement, " 
	      + COLUMN_LAT+ " varchar(50) not null, " 
	      + COLUMN_LONG+ " varchar(50) not null);";
	
	public DBMapsHelper(Context context) {
		super(context, db_name, null, db_version);		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(db_create);	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// TODO Auto-generated method stub
		Log.w(DBMapsHelper.class.getName(),"Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	    onCreate(db);
		
	}
	

}
