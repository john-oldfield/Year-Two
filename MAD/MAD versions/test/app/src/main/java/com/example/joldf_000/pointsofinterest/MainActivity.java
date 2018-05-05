package com.example.joldf_000.pointsofinterest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainActivity extends Activity implements LocationListener
{
    MapView mv;
    String curLat;
    String curLon;
    ItemizedIconOverlay<OverlayItem> items;
    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> markerGestureListener;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager mgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }

        mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);

        mv = (MapView) findViewById(R.id.map1);
        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(18);

        markerGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>()
        {
            public boolean onItemLongPress(int i, OverlayItem item)
            {
                Toast.makeText(MainActivity.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }

            public boolean onItemSingleTapUp(int i, OverlayItem item)
            {
                Toast.makeText(MainActivity.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }
        };
    }

    public void onLocationChanged(Location newLoc)
    {
        mv.getController().setCenter(new GeoPoint(newLoc.getLatitude() , newLoc.getLongitude()));
        curLat = Double.toString(newLoc.getLatitude());
        curLon = Double.toString(newLoc.getLongitude());
    }

    public void onProviderDisabled(String provider)
    {
        Toast.makeText(this, "Provider " + provider +
                " disabled", Toast.LENGTH_LONG).show();
    }

    public void onProviderEnabled(String provider)
    {
        Toast.makeText(this, "Provider " + provider +
                " enabled", Toast.LENGTH_LONG).show();
    }

    public void onStatusChanged(String provider,int status,Bundle extras)
    {

        Toast.makeText(this, "Status changed: " + status,
                Toast.LENGTH_SHORT).show();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.map)
        {
            // react to the menu item being selected...
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId() == R.id.addPOI)
        {
            // react to the menu item being selected...
            Intent intent = new Intent(this,AddPOI.class);
            Bundle coords = new Bundle();

            coords.putString("curLat", curLat);
            coords.putString("curLon", curLon);

            intent.putExtras(coords);
            startActivity(intent);

            return true;
        }
        if(item.getItemId() == R.id.saveFile)
        {
            // react to the menu item being selected...
            try
            {
                PrintWriter pw =
                        new PrintWriter( new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/items.csv"));
                pw.close(); // close the file to ensure data is flushed to file
            }
            catch(IOException e)
            {
                System.out.println ("I/O Error: " + e);
            }

            Toast.makeText(this, "File Saved", Toast.LENGTH_LONG).show();
        }
        if(item.getItemId() == R.id.loadFile)
        {
            items = new ItemizedIconOverlay<OverlayItem>(this, new ArrayList<OverlayItem>(), markerGestureListener);
            OverlayItem burton = new OverlayItem("Burton on Trent", "My Home Town", new GeoPoint(52.8140, -1.6371));
            OverlayItem southampton = new OverlayItem("Southampton", "My Uni Town", new GeoPoint(50.9097, -1.4044));
            items.addItem(burton);
            items.addItem(southampton);
            mv.getOverlays().add(items);
            Toast.makeText(this, "File Loaded", Toast.LENGTH_LONG).show();
        }
        if(item.getItemId() == R.id.saveWeb)
        {
            // react to the menu item being selected...
            Toast.makeText(this, "Uploaded", Toast.LENGTH_LONG).show();
        }
        if(item.getItemId() == R.id.loadWeb)
        {
            // react to the menu item being selected...
            Toast.makeText(this, "Downloaded", Toast.LENGTH_LONG).show();
        }
        if(item.getItemId() == R.id.listPOI)
        {
            // react to the menu item being selected...
            Intent intent = new Intent(this,ListPOI.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId() == R.id.preferences)
        {
            // react to the menu item being selected...
            Intent intent = new Intent(this,Preferences.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
