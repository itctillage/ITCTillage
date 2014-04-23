package com.itct.android.map;


import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.MapActivity;

@SuppressWarnings("unused")
public class Checkin extends ListActivity implements OnClickListener
{

	private static final DBLocation DBLocation = null;
	private TextView textView;
	private DBDataSource datasource;
	private View saveInBut;
	private View delBut;
	private Double lat,lng;
	private Button bt;
	ArrayList<DBLocation> values;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
      super.onCreate(savedInstanceState);
      setContentView(R.layout.checkin);
      
      textView =  (TextView) findViewById(R.id.textcheckin);
      Bundle b = this.getIntent().getExtras();
      lat = b.getDouble("latitude");
      lng = b.getDouble("longitude");
      textView.setText("Save current coordinate ? "+lat+","+lng);
      
      saveInBut = findViewById(R.id.saveinbt);
      saveInBut.setOnClickListener(this);
      
      delBut = findViewById(R.id.delbt);
      delBut.setOnClickListener(this);
      
      //data source controller     
      datasource = new DBDataSource(this);
      datasource.open();
    
      // output all locations 
      values = datasource.getAllLocation();
      
      // Display listview
      ArrayAdapter<DBLocation> adapter = new ArrayAdapter<DBLocation>(this,
    	        android.R.layout.simple_list_item_1, values);
      setListAdapter(adapter);
      
      bt = (Button) findViewById(R.id.showallinmap);
      bt.setOnClickListener(this);
    }
		
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
		super.onListItemClick(l, v, position, id);
		String text = " Move to location: " + values.get(position).getId();
		DBLocation landLocation = datasource.getLocation(position+1);
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
		
		Intent i = new Intent(this, ITCTMaps.class);
    	Bundle b = new Bundle();
    	b.putDouble("latitude", Double.valueOf(landLocation.getLat()));
    	b.putDouble("longitude", Double.valueOf(landLocation.getLng()));
        i.putExtras(b);
    	startActivity(i);
	}	     
	
	//create new location when checkin button onclick	
	public void onClick(View v) 
	{
		@SuppressWarnings("unchecked")
		ArrayAdapter<DBLocation> adapter = (ArrayAdapter<DBLocation>) getListAdapter();
		DBLocation landLocation = null;
		switch (v.getId())
		{
			case R.id.saveinbt:
				landLocation = datasource.createLocation(lat,lng);
				if(landLocation !=null&&adapter!=null)
				{
					adapter.add(landLocation);
				}
				else
				{
					Toast.makeText(this, "NULL", Toast.LENGTH_LONG).show();
				}
				break;
				
			case R.id.showallinmap: 
				ArrayList<DBLocation> landLocation2 = datasource.getAllLocation();
				Bundle b = new Bundle();
				Intent i = new Intent(this,ITCTMaps.class);
		    	b.putParcelableArrayList("List", landLocation2);
		    	i.putExtras(b);
		    	startActivity(i);
		    	break;
		    	
			case R.id.delbt:
			long ids;
			//landLocation = datasource.deleteLocation();
			
		}
		adapter.notifyDataSetChanged();
		
	}

	@Override
	  protected void onResume() 
	{
	    datasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() 
	  {
	    datasource.close();
	    super.onPause();
	  }
	
}
