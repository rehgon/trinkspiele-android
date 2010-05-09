package com.google.code.trinkspiele;

import android.app.AlertDialog;
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
	
	String trinkspieleListe[] = { "Ass Rennen", "Woody" };
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
				zuWenigSpieler();
			}
				
		}
		else if (trinkspieleListe[position] == "Ass Rennen")
			startActivity(new Intent(this, assRennen.class));
		else
			Toast.makeText(this, "failed to load", Toast.LENGTH_LONG).show();
	}
    
    private void zuWenigSpieler() {
    	AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Zu wenig Spieler vorhanden");
		dialog.setMessage("Es müssen mindestens 2 Spieler vorhanden sein");
		dialog.setPositiveButton("Spieler hinzufügen", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				AlertDialog.Builder dialog2 = spielerHinzufügen();
				dialog2.show();
			}
		});
		dialog.setNegativeButton("Abbrechen", null);
		dialog.show();
		Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vib.vibrate(100);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(Menu.NONE, 0, Menu.NONE, "Spieler hinzufügen").setIcon(R.drawable.add);
    	menu.add(Menu.NONE, 1, Menu.NONE, "Spieler entfernen").setIcon(R.drawable.remove);
    	menu.add(Menu.NONE, 2, Menu.NONE, "Beenden").setIcon(R.drawable.close);;
    	return (super.onCreateOptionsMenu(menu));
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return (applyMenuChoice(item) || super.onOptionsItemSelected(item));
    }
	
	private boolean applyMenuChoice(MenuItem item) {
		AlertDialog.Builder dialog;
		
		switch (item.getItemId()) {
		case 0:
			dialog = spielerHinzufügen();
			dialog.show();
			return true;
		case 1:
			dialog = spielerEntfernen();
			dialog.show();
			return true;
		case 2:
			System.exit(0);
			return true;
		}
		return (false);
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
			if (items.length == 0) {
				dialog = new AlertDialog.Builder(this);
				dialog.setMessage("Es sind keine Spieler vorhanden");
				dialog.setPositiveButton("Ok", null);
			}
			return dialog;
	}
}