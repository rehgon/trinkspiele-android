package com.google.code.trinkspiele;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.code.assrennen.assRennen;
import com.google.code.woody.woodroid;

public class Trinkspiele extends ListActivity {
	
	String trinkspieleListe[] = { "Ass Rennen", "Woody", "test" };;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setListAdapter(new ArrayAdapter<String>(this, 
        		android.R.layout.simple_list_item_1,
        		trinkspieleListe));
    }
    
    public void onListItemClick(ListView parent, View v, int position, long id) {
		if (trinkspieleListe[position] == "Woody")
			startActivity(new Intent(this, woodroid.class));
		else if (trinkspieleListe[position] == "Ass Rennen")
			startActivity(new Intent(this, assRennen.class));
		else
			Toast.makeText(this, "failed to load", Toast.LENGTH_LONG).show();
	}
}