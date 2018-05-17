package com.example.joldf_000.pointsofinterest;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.OverlayItem;

public class AddPOI extends Activity implements View.OnClickListener {


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_poi);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.place_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        EditText placeName = (EditText)findViewById(R.id.poiName);
        EditText placeDesc = (EditText)findViewById(R.id.poiDesc);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        Intent intent = new Intent();
        Bundle bundle=new Bundle();

        String poiName = placeName.getText().toString();
        String poiDesc = placeDesc.getText().toString();
        String placeType = spinner.getSelectedItem().toString();

        OverlayItem newItem = new OverlayItem(poiName, placeType, new GeoPoint(52.8140, -1.6371));

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
            startActivity(intent);
            return true;
        }
        if(item.getItemId() == R.id.listPOI)
        {
            // react to the menu item being selected...
            Intent intent = new Intent(this,ListPOI.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId() == R.id.saveFile)
        {
            // react to the menu item being selected...
            Toast.makeText(this, "Option Not Available", Toast.LENGTH_LONG).show();
        }
        if(item.getItemId() == R.id.loadFile)
        {
            // react to the menu item being selected...
            Toast.makeText(this, "Option Not Available", Toast.LENGTH_LONG).show();
        }
        if(item.getItemId() == R.id.saveWeb)
        {
            // react to the menu item being selected...
            Toast.makeText(this, "Option Not Available", Toast.LENGTH_LONG).show();
        }
        if(item.getItemId() == R.id.loadWeb)
        {
            // react to the menu item being selected...
            Toast.makeText(this, "Option Not Available", Toast.LENGTH_LONG).show();
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