package com.itct.android.map;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


public class DBLocation implements Parcelable
{
	private long id;
	private String lat;
	private String lng;
	
	public DBLocation()
	{
		
	}
	
	public DBLocation(Parcel in)
	{
	    lat = in.readString();
	    lng = in.readString();
	    id = in.readLong();
	}
	
	public long getId() 
	{
	    return id;
	}

	  public void setId(long id) 
	  {
	    this.id = id;
	  }

	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() 
	  {
	    return "Land Location no: "+id+" ("+lat+" , "+lng+")";
	  }

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public static final Parcelable.Creator<DBLocation> CREATOR = new Parcelable.Creator<DBLocation>() 
   {
         public DBLocation createFromParcel(Parcel in) 
         {
            Log.d ("TAG", "createFromParcel()");
             return new DBLocation(in);
         }

         public DBLocation[] newArray (int size) 
         {
            Log.d ("TAG", "createFromParcel() newArray ");
             return new DBLocation[size];
         }
    };
	
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel parcel, int index) 
	{
		// TODO Auto-generated method stub
		parcel.writeString(lat);
		parcel.writeString(lng);
		parcel.writeLong(id);
	}
}
