
package com.itct.android.map;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
//import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.util.Log;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
import android.widget.Toast;
import android.app.Activity;
import android.app.FragmentManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.graphics.drawable.Drawable;


@SuppressWarnings("unused")
public class ITCTMaps extends Activity 
{	
	private GoogleMap map;
	
   @Override
   protected void onCreate(Bundle savedInstanceState) 
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.mapdisplay); 
      
      locMapView();
      //initMyLocation();  
   }
   private void locMapView() 
   {
	   FragmentManager myFragmentManager = getFragmentManager();
	   MapFragment myMapFragment = (MapFragment)myFragmentManager.findFragmentById(R.id.map);
	      
	   map = myMapFragment.getMap();
	   map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	   
	   LatLng latLng = new LatLng(52.826576731167734, -6.934998761862516);
	   //map.setMyLocationEnabled(true); 
	   map.addMarker(new MarkerOptions()
       .position(latLng)
       .title("IT Carlow")
       .snippet("UNUM LAB")
       .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
		map.getUiSettings().setCompassEnabled(true);
		map.getUiSettings().setZoomControlsEnabled(true);
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));

     
	}

// TODO Auto-generated method stub
   private void initilizeMap() 
   {
       if (map == null) 
       {
           map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
           // check if map is created successfully or not
           if (map == null) 
           {
               Toast.makeText(getApplicationContext(),
                       "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
           }
       }
   }
   protected void onResume() 
   {
       super.onResume();
       initilizeMap();
   }  
   
}
    
/** Start tracking the position on the map.
   private void initMyLocation() 
   {
	  int lat, lng;
	  Bundle b = this.getIntent().getExtras();
	  if(b.containsKey("longitude"))
	  {
		  
	      lat = (int)(b.getDouble("latitude")*1E6);
	      lng = (int)(b.getDouble("longitude")*1E6);
	      Log.v("info", "The lat "+lat);
	      Log.v("infoMAPS", "First Location");
	      Log.v("info", "The lng "+lng);
	      GeoPoint point = new GeoPoint(lat,lng);
	      List<Overlay> mapOverlays = map.getOverlays();
		  Drawable marker = this.getResources().getDrawable(R.drawable.marker);
		  MapOverlay itemizedOverlay = new MapOverlay(marker, this);   
	      
		  OverlayItem overlayitem = new OverlayItem(point, "Location info", "Coordinat ("+b.getDouble("latitude")+","+b.getDouble("longitude")+")");
	      itemizedOverlay.addOverlay(overlayitem);
	      controller.setZoom(16);
	      controller.animateTo(point);
	      mapOverlays.add(itemizedOverlay);
	      
	  }
	  else
	  {
		  Log.v("infoMAPS", "Second Location");
		  List<Overlay> mapOverlays = map.getOverlays();
		  Drawable marker = this.getResources().getDrawable(R.drawable.marker);
		  MapOverlay itemizedOverlay = new MapOverlay(marker, this);
		  ArrayList<DBLocation> daftarLokasi = b.getParcelableArrayList("List");
		  for(int i=0;i<daftarLokasi.size();i++)
		  {
			  Log.v("infoMAPS", "Marker no "+i);
			  DBLocation a = daftarLokasi.get(i);
			  lat = (int)(Double.valueOf(a.getLat())*1E6);
		      lng = (int)(Double.valueOf(a.getLng())*1E6);
		      GeoPoint point = new GeoPoint(lat,lng);
		      OverlayItem overlayitem = new OverlayItem(point, "Location info", "Coordinat no "+i+" ("+a.getLat()+","+a.getLng()+")");
		      itemizedOverlay.addOverlay(overlayitem);
		      controller.setZoom(16);
		      controller.animateTo(point);
		      mapOverlays.add(itemizedOverlay);
		  }
	  }   
   }

   protected boolean isRouteDisplayed() 
   {
      // Required by MapActivity
      return false;
   }
}
*/


