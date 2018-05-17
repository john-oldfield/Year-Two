package com.example.joldf_000.pointsofinterest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by joldf_000 on 07/04/2017.
 */

public class Preferences extends Activity
{

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);
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