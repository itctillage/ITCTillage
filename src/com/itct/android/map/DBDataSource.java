package com.itct.android.map;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBDataSource 
{
  // Inisiate database fields
  private SQLiteDatabase database;
  private DBMapsHelper dbHelper;
  
  private String[] allColumns = { DBMapsHelper.COLUMN_ID,
      DBMapsHelper.COLUMN_LAT, DBMapsHelper.COLUMN_LONG };
  
  // Inisiate DBMapsHelper
  public DBDataSource(Context context) 
  {
    dbHelper = new DBMapsHelper(context);
  }

  public void open() throws SQLException 
  {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  //Function to create new location & inserted to database
  public DBLocation createLocation(Double dlat, Double dlng) 
  {
    ContentValues values = new ContentValues();
    String lat = Double.toString(dlat);
    String lng = Double.toString(dlng);
    values.put(DBMapsHelper.COLUMN_LAT, lat);
    values.put(DBMapsHelper.COLUMN_LONG, lng);
    long insertId = database.insert(DBMapsHelper.TABLE_NAME, null,
        values);
    Cursor cursor = database.query(DBMapsHelper.TABLE_NAME,
        allColumns, DBMapsHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    cursor.moveToFirst();
    DBLocation newLocation = cursorToLocation(cursor);
    cursor.close();
    Log.v("info", "The lat "+lat+", "+dlat);
    Log.v("info", "The lng "+lng+", "+dlng);
    return newLocation;
  }
  
  // Function to delete location by ID
  public void deleteLocation(DBLocation landLocation, long ids) 
  {
    long id = landLocation.getId();
    System.out.println("Location deleted with id: " + id);
    database.delete(DBMapsHelper.TABLE_NAME, DBMapsHelper.COLUMN_ID
        + " = " + id, null);
  }

  // Show all locations
  public ArrayList<DBLocation> getAllLocation() 
  {
    ArrayList<DBLocation> locationList = new ArrayList<DBLocation>();

    Cursor cursor = database.query(DBMapsHelper.TABLE_NAME,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) 
    {
      DBLocation landLocation = cursorToLocation(cursor);
      locationList.add(landLocation);
      cursor.moveToNext();
    }
    // Make sure to close the cursor
    cursor.close();
    return locationList;
  }

  public DBLocation getLocation(int id)
  {
	  DBLocation landLocation = new DBLocation();
	  
	  Cursor cursor = database.query(DBMapsHelper.TABLE_NAME, allColumns, "_id ="+id, null, null, null, null);
	  cursor.moveToFirst();
	  landLocation = cursorToLocation(cursor);
	  cursor.close();
	  return landLocation;
  }
  
  // create new object and inserted to database
  private DBLocation cursorToLocation(Cursor cursor) 
  {	  
	  DBLocation landLocation = new DBLocation();
	  Log.v("info", "The getLONG "+cursor.getLong(0));
      Log.v("info", "The setLatLng "+cursor.getString(1)+","+cursor.getString(2));
	  landLocation.setId(cursor.getLong(0));
	  landLocation.setLat(cursor.getString(1));
	  landLocation.setLng(cursor.getString(2));
	  return landLocation;
  }
} 