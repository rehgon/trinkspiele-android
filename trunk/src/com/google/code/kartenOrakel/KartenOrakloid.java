package com.google.code.kartenOrakel;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.code.trinkspiele.R;

public class KartenOrakloid extends Activity {
	private static final int ID_HELP = 0;
	private static final int ID_BEENDEN = 1;
	
	private TextView ausgabe, richtigLabel, falschLabel;
	private ImageButton image;
	private Button tieferButton, hoeherButton;
	private KartenOrakel orakel;
	private String[] karte;
	private ProgressBar progress;

	private boolean istNeuesSpiel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kartenorakel);

		orakel = new KartenOrakel(getApplicationContext());
		ausgabe = (TextView) findViewById(R.id.kartenOrakelAusgabe);
		tieferButton = (Button) findViewById(R.id.kartenOrakeltieferButton);
		hoeherButton = (Button) findViewById(R.id.kartenOrakelHoeherButton);
		richtigLabel = (TextView) findViewById(R.id.kartenOrakelRichtigTextView);
		falschLabel = (TextView) findViewById(R.id.kartenOrakelFalschTextView);
		image = (ImageButton) findViewById(R.id.kartenOrakelImage);
		progress = (ProgressBar) findViewById(R.id.progressBarKarten);
		istNeuesSpiel = true;

		//onclick listener für Kartenstapel
		image.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (istNeuesSpiel) {
					karte = orakel.kartenZiehen(1);
					String symbol = orakel.kartenSymbolBestimmen(karte[0]);
					int wert = orakel.kartenWertBestimmen(karte[0]);
					orakel.paint(orakel, image, symbol, wert);
					ausgabe.setText(orakel.spieleKartenOrakel(karte[0], true)); // bool hier noch unrelevant
					hoeherButton.setVisibility(0);
					tieferButton.setVisibility(0);
					progress.incrementProgressBy(1);
					istNeuesSpiel = false;
				} else {
					// onClick macht nichts mehr sobald das Spiel läuft
				}
			}
		});

		// onclick listener für tiefer Button
		tieferButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				karte = orakel.kartenZiehen(1);
				if (karte[0].equals(getString(R.string.karten_deck_enthaelt_zu_wenig_karten)))
					reset();
				else
					zugMachen(false);
			}
		});

		// onclick listener für höher Button
		hoeherButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				karte = orakel.kartenZiehen(1);
				if (karte[0].equals(getString(R.string.karten_deck_enthaelt_zu_wenig_karten)))
					reset();
				else
					zugMachen(true);
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	orakel.wirklichBeendenDialog(this);
	    	return true;
	    }
	    return false;
	}

	private void zugMachen(boolean hoeherButtonGedrückt) {
		String symbol = orakel.kartenSymbolBestimmen(karte[0]);
		int wert = orakel.kartenWertBestimmen(karte[0]);
		orakel.paint(orakel, image, symbol, wert);
		ausgabe.setText(orakel.spieleKartenOrakel(karte[0], hoeherButtonGedrückt));

		richtigLabel.setText(getString(R.string.orakel_richtige) + " " + orakel.getAnzahlRichtigeTreffer());
		falschLabel.setText(getString(R.string.orakel_falsche) + " " + orakel.getAnzahlFalscheTreffer());

		progress.incrementProgressBy(1);
	}

	private void reset() {
		resultatAnzeigen();
		ausgabe.setText(getString(R.string.orakel_karten_durch_stapel_um_neustart));
		istNeuesSpiel = true;
		image.setImageResource(R.drawable.deckblatt);
		hoeherButton.setVisibility(4);
		tieferButton.setVisibility(4);
		progress.setProgress(0);

		orakel = new KartenOrakel(getApplicationContext());
		richtigLabel.setText(getString(R.string.orakel_richtige) + " 0");
		falschLabel.setText(getString(R.string.orakel_falsche) + " 0");
	}
	
	private void resultatAnzeigen() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle(getString(R.string.orakel_resultat));
		String message = 
			getString(R.string.orakel_richtige) + " " + orakel.getAnzahlRichtigeTreffer() + "\n" +
			getString(R.string.orakel_falsche) + " " + orakel.getAnzahlFalscheTreffer();
		dialog.setMessage(message);
		dialog.setPositiveButton(getString(R.string.ok), null);
		dialog.show();
	}
	
	//Erstellt die Optionen für das Menü
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ID_HELP, Menu.NONE, getString(R.string.hilfe)).setIcon(R.drawable.ic_menu_info);
		menu.add(Menu.NONE, ID_BEENDEN, Menu.NONE, getString(R.string.zum_hauptmenue)).setIcon(R.drawable.ic_menu_close);
		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return (applyMenuChoice(item) || super.onOptionsItemSelected(item));
	}

	//Bestimmt, was passiert, wenn auf eine bestimmte Option im Menü geklickt wird
	private boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
			case ID_BEENDEN:
				orakel.wirklichBeendenDialog(this);
				return true;
			case ID_HELP:
				orakel.createHelperDialog(this, orakel.getHelpMessage());
			}
		return false;
	}
}
