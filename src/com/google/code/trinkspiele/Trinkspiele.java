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
import com.google.code.kartenOrakel.KartenOrakloid;
import com.google.code.kingsCup.Cupoid;
import com.google.code.meier.Meieroid;
import com.google.code.siebenSaeuft.siebensauft;
import com.google.code.simonSays.SimonSays;
import com.google.code.woody.woodroid;

public class Trinkspiele extends ListActivity {
	
	String trinkspieleListe[];
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        trinkspieleListe = new String[7];
        trinkspieleListe[0] = getString(R.string.assrennen_ass_rennen);
        trinkspieleListe[1] = getString(R.string.bigkingscup_big_kings_cup);
        trinkspieleListe[2] = getString(R.string.meier_meier);
        trinkspieleListe[3] = getString(R.string.orakel_karten_orakel);
        trinkspieleListe[4] = getString(R.string.siebensaeuft_siebensaeuft);
        trinkspieleListe[5] = getString(R.string.simon_says_simon_says);
        trinkspieleListe[6] = getString(R.string.woody_woody);
        
        setListAdapter(new ArrayAdapter<String>(this, 
        		android.R.layout.simple_list_item_1,
        		trinkspieleListe));
        //wieder löschen
        Spieler.getSpielerNameArrayList().add("Remo Blättler");
        Spieler.getSpielerNameArrayList().add("Simon Illi");
        Spieler.getSpielerNameArrayList().add("Raffael Affolter");
        Spieler.getSpielerNameArrayList().add("Amaury Lemaerchal");
    }
    
    public void onListItemClick(ListView parent, View v, int position, long id) {
    	if (trinkspieleListe[position].equals(getString(R.string.assrennen_ass_rennen))) {
			startActivity(new Intent(this, assRennen.class));
    	}
    	else if (trinkspieleListe[position].equals(getString(R.string.bigkingscup_big_kings_cup))) {
			if (Spieler.getSpielerNameArrayList().size() > 1)
				startActivity(new Intent(this, Cupoid.class));
			else
				zuWenigSpieler(2);
    	}
    	else if (trinkspieleListe[position].equals(getString(R.string.orakel_karten_orakel))) {
    		startActivity(new Intent(this, KartenOrakloid.class));
    	}
    	else if (trinkspieleListe[position].equals(getString(R.string.meier_meier))) {
    		if (Spieler.getSpielerNameArrayList().size() > 1)
				startActivity(new Intent(this, Meieroid.class));
			else
				zuWenigSpieler(2);
    	}
    	else if (trinkspieleListe[position].equals(getString(R.string.siebensaeuft_siebensaeuft))) {
    		startActivity(new Intent(this, siebensauft.class));
    	}
    	else if (trinkspieleListe[position].equals(getString(R.string.simon_says_simon_says))) {
    		startActivity(new Intent(this, SimonSays.class));
    	}
    	else if (trinkspieleListe[position].equals(getString(R.string.woody_woody))) {
			if (Spieler.getSpielerNameArrayList().size() > 1)
				startActivity(new Intent(this, woodroid.class));
			else
				zuWenigSpieler(2);
		}
		else {
			Toast.makeText(this, (getString(R.string.kritischer_fehler)), Toast.LENGTH_LONG).show();
		}
	}
    
    private void zuWenigSpieler(int wievieleMindestens) {
    	AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle((getString(R.string.trinkspiele_zu_wenig_spieler_vorhanden)));
		if (wievieleMindestens - Spieler.getSpielerNameArrayList().size() > 1)
			dialog.setMessage((getString(R.string.trinkspiele_du_musst_noch_mindestens))+ " " + 
					(wievieleMindestens - Spieler.getSpielerNameArrayList().size()) + " " + (getString(R.string.trinkspiele_noch_spieler_hinzufuegen)));
		else
			dialog.setMessage(getString(R.string.trinkspiele_du_musst_noch_mindestens_1_spieler_hinzufuegen));
		dialog.setPositiveButton(getString(R.string.trinkspiele_spieler_hinzufuegen), new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				AlertDialog.Builder dialog2 = spielerHinzufügen();
				dialog2.show();
			}
		});
		dialog.setNegativeButton(getString(R.string.abbrechen), null);
		dialog.show();
		Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vib.vibrate(100);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(Menu.NONE, 0, Menu.NONE, getString(R.string.trinkspiele_spieler_hinzufuegen)).setIcon(R.drawable.ic_menu_add);
    	menu.add(Menu.NONE, 1, Menu.NONE, getString(R.string.trinkspiele_spieler_entfernen)).setIcon(R.drawable.ic_menu_close);
    	menu.add(Menu.NONE, 2, Menu.NONE, getString(R.string.beenden)).setIcon(R.drawable.ic_menu_close);;
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
		
		final Toast zuVieleZeichenToast = Toast.makeText(this, getString(R.string.trinkspiele_fehler_name_darf_max_zwanzig_zeichen_enthalten), 
				Toast.LENGTH_SHORT); //Wird nur angezeigt falls Name > 20 Zeichen
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle(getString(R.string.trinkspiele_spieler_hinzufuegen));
		dialog.setMessage(getString(R.string.trinkspiele_gib_den_namen_des_neuen_spielers_ein));
		
		final EditText input = new EditText(this);
		input.setSingleLine();
		dialog.setView(input);
		
		dialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				String eingabe = input.getText().toString();
				if (eingabe.length() <= 20)
					Spieler.getSpielerNameArrayList().add(eingabe);
				else
					zuVieleZeichenToast.show();
			}
		});
		dialog.setNegativeButton(getString(R.string.abbrechen), null);
		return dialog;
	}
	
	private AlertDialog.Builder spielerEntfernen() {

			String items[] = Spieler.convertArrayListToArray();

			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle(getString(R.string.trinkspiele_spieler_entfernen));
			dialog.setItems(items, new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int item) {
			        Toast.makeText(getApplicationContext(), (Spieler.getSpielerName(item) + " " + getString(R.string.trinkspiele_wurde_entfernt)), Toast.LENGTH_SHORT).show();
			        Spieler.getSpielerNameArrayList().remove(item);
			    }
			});
			if (items.length == 0) {
				dialog = new AlertDialog.Builder(this);
				dialog.setMessage(getString(R.string.trinkspiele_es_sind_keine_spieler_vorhanden));
				dialog.setPositiveButton(getString(R.string.ok), null);
			}
			return dialog;
	}
}