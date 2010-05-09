package com.google.code.trinkspiele;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
    }
    
    public void onListItemClick(ListView parent, View v, int position, long id) {
		if (trinkspieleListe[position] == "Woody") {
			if (Spieler.getSpielerNameArrayList().size() > 1)
				startActivity(new Intent(this, woodroid.class));
			else {
				Toast.makeText(this, "Es müssen mindestens 2 Spieler angemeldet sein", Toast.LENGTH_LONG).show();
				Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				vib.vibrate(100);
			}
				
		}
		else if (trinkspieleListe[position] == "Ass Rennen")
			startActivity(new Intent(this, assRennen.class));
		else
			Toast.makeText(this, "failed to load", Toast.LENGTH_LONG).show();
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(Menu.NONE, 0, Menu.NONE, "Spieler hinzufügen");
    	menu.add(Menu.NONE, 1, Menu.NONE, "Spieler entfernen");
    	menu.add(Menu.NONE, 2, Menu.NONE, "Beenden");
    	return (super.onCreateOptionsMenu(menu));
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return (applyMenuChoice(item) || super.onOptionsItemSelected(item));
    }
	
	private boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			showDialog(0);
			return true;
		case 1:
			showDialog(1);
			return true;
		case 2:
			System.exit(0);
			return true;
		}
		return (false);
	}
	
	protected Dialog onCreateDialog(int id) {
	    AlertDialog.Builder dialog;
	    switch(id) {
	    case 0:
	    	dialog = spielerHinzufügen();
	    	break;
	    case 1:
	        dialog = spielerEntfernen();
	        break;
	    default:
	        dialog = null;
	    }
	    
	    AlertDialog creator = dialog.create();
	    onResume();
	    return creator;
	}
	
	private AlertDialog.Builder spielerHinzufügen() {
		
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Spieler hinzufügen");
		dialog.setMessage("Geben sie den Namen des neuen Spielers ein:");
		
		final EditText input = new EditText(this);
		dialog.setView(input);
		
		dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				String eingabe = input.getText().toString();
				Spieler.getSpielerNameArrayList().add(eingabe);
			}
		});
		
		dialog.setNegativeButton("Abbrechen", null);
		return dialog;
	}
	
	//Funktioniert noch nicht. Man kann zwar auswählen, aber der Name verschwindet nicht
	private AlertDialog.Builder spielerEntfernen() {

			String items[] = Spieler.convertArrayListToArray();

			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle("Spieler entfernen");
			dialog.setItems(items, new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int item) {
			        Toast.makeText(getApplicationContext(), (Spieler.getSpielerName(item) + " wurde entfernt"), Toast.LENGTH_SHORT).show();
			        Spieler.getSpielerNameArrayList().remove(item);
			    }
			});
			return dialog;
	}
}