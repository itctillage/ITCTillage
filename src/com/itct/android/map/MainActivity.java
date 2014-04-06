package com.itct.android.map;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity implements OnClickListener {
	 
    // Google Map
    private GoogleMap googleMap;
    private Button stdBtn, satBtn;
    //private MapView mapView;
         
    
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mapView = (MapView) findViewById(R.id.map);              
     
        stdBtn = (Button) findViewById(R.id.stdBtn);
        satBtn = (Button) findViewById(R.id.satBtn);
        
        stdBtn.setOnClickListener ((OnClickListener) this);
        satBtn.setOnClickListener ((OnClickListener) this); 
        
        try {
            	//Loading map
            	initilizeMap();
            } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * function to load map. If map is not created it will create it for you
     * class MapFragment from Google Play Services Library
     * */
    private void initilizeMap() 
    {
       	if (googleMap == null) 
    		{
            	googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
             	// check if map is created successfully or not
            	if (googleMap == null) 
            	{
            		Toast.makeText(getApplicationContext(),
                     "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
            	}
    		}
    
       	googleMap.addMarker(new MarkerOptions().position(new LatLng(52.841007,-6.91864)).title("Me !!"));
     }
    
   
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
        //googleMap.setMyLocationEnabled(true);
    }
    
    @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if (v==satBtn) 
		{
			googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);           
        }
		if (v==stdBtn) 
		{
			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL );           
        }		
	}

	@SuppressWarnings("unused")
	private void enableLocationUpdate() {
		// TODO Auto-generated method stub
		
	} 
}