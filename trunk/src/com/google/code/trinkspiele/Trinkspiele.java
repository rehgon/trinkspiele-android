package com.google.code.trinkspiele;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.code.assrennen.assRennen;
import com.google.code.woody.woodroid;

public class Trinkspiele extends ListActivity {
	
	String trinkspieleListe[] = { "Ass Rennen", "Woody", "test" };;
	Spieler spieler;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setListAdapter(new ArrayAdapter<String>(this, 
        		android.R.layout.simple_list_item_1,
        		trinkspieleListe));
        spieler = new Spieler();
    }
    
    public Spieler getSpieler() {
    	return spieler;
    }
    
    public void onListItemClick(ListView parent, View v, int position, long id) {
		if (trinkspieleListe[position] == "Woody")
			startActivity(new Intent(this, woodroid.class));
		else if (trinkspieleListe[position] == "Ass Rennen")
			startActivity(new Intent(this, assRennen.class));
		else
			Toast.makeText(this, "failed to load", Toast.LENGTH_LONG).show();
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(Menu.NONE, 0, Menu.NONE, "Spieler hinzufügen");
    	menu.add(Menu.NONE, 1, Menu.NONE, "Spieler entfernen");
    	return (super.onCreateOptionsMenu(menu));
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return (applyMenuChoice(item) || super.onOptionsItemSelected(item));
    }
	
	private boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			return (true);
		case 1:
			return (true);
		}
		return (false);
	}
}