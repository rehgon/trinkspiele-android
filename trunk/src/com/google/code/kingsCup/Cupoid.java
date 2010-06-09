package com.google.code.kingsCup;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.code.trinkspiele.R;
import com.google.code.trinkspiele.Spieler;

public class Cupoid extends Activity {
	private static final int ID_KLOKARTE_ENTFERNEN = 0;
	private static final int ID_HELP = 1;
	private static final int ID_BEENDEN = 2;

	BigKingsCup cup;
	ImageView image;
	TextView werHatGezogen, kategorie, werAlsNaechstes, questionMaster, daumenMaster;
	Button erklaerung, button;
	int wert;
	String symbol;
	ProgressBar progress;
	ArrayList<String> klokartenBesitzer;
	AlertDialog.Builder dialog;
	String[] karte;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bigkingcup);
		
		cup = new BigKingsCup(getApplicationContext());
		image = (ImageView) findViewById(R.id.bigKingImage);
		werHatGezogen = (TextView) findViewById(R.id.bigKingWerHatGezogen);
		kategorie = (TextView) findViewById(R.id.bigKingKategorie);
		werAlsNaechstes = (TextView) findViewById(R.id.bigKingWerAlsNaechstes);
		questionMaster = (TextView) findViewById(R.id.bigKingQuestionMasterTextView);
		daumenMaster = (TextView) findViewById(R.id.bigKingDaumenMasterTextView);
		progress = (ProgressBar) findViewById(R.id.progressBarKarten);
		klokartenBesitzer = new ArrayList<String>();
		dialog = new AlertDialog.Builder(this);
		erklaerung = (Button) findViewById(R.id.bigKingErklaerungButton);
		erklaerung.setOnClickListener(new OnClickListener() {

			//Klick auf den "Erklärung" Button
			public void onClick(View v) {
				
				// Eine nicht editierbare Seek Bar soll angezeigt werden, wenn ein König
				// erscheint und auf den Erklärung Button geklickt wird
				if (kategorie.getText().equals(getString(R.string.bigkingscup_kings_cup))) {
					SeekBar cupProgress = new SeekBar(getApplicationContext());
					cupProgress.setMax(4);
					cupProgress.setProgress(cup.getKingCounter());
					dialog.setView(cupProgress);
				}
				dialog.setTitle(getString(R.string.bigkingscup_erklaerung) + " " + cup.KategorieBestimmen(symbol + " " + wert));
				dialog.setMessage(cup.erklaerungZuKategorie(symbol + " " + wert));
				dialog.setPositiveButton(getString(R.string.ok), null);
				dialog.show();
				//nötig um die evtl. hinzugefügte Seekbar(nachdem Karte == König) wieder zu entfernen 
				dialog.setView(null);
			}
		});

		button = (Button) findViewById(R.id.bigKingButton);
		button.setOnClickListener(new OnClickListener() {

			//Klick auf den "ziehen" Button
			public void onClick(View v) {
				
				//Progress Bars aktualisieren
				progress.incrementProgressBy(1);
				if (kategorie.getText().equals(getString(R.string.bigkingscup_big_kings_cup))) 
					cup.setKingCounter(cup.getKingCounter() + 1);

				karte = cup.kartenZiehen(1);
				
				//Wenn keine Karten mehr vorhanden sind
				if (karte[0].equals(getString(R.string.karten_deck_enthaelt_zu_wenig_karten))) {
					werHatGezogen.setText("");
					kategorie.setText(getString(R.string.bigkingscup_alle_karten_durch));
					erklaerung.setVisibility(4);
					werAlsNaechstes.setText(getString(R.string.bigkingscup_klicke_auf_den_button_um_nue_zu_starten));
					button.setText(getString(R.string.neustart));
					image.setImageResource(R.drawable.deckblatt);
					klokartenBesitzer = new ArrayList<String>();
					questionMaster.setText("");
					daumenMaster.setText("");
					cup = new BigKingsCup(getApplicationContext());
					cup.setKingCounter(1);
					progress.setProgress(0);
				//Wenn noch Karten vorhanden sind
				} else {
					symbol = cup.kartenSymbolBestimmen(karte[0]);
					wert = cup.kartenWertBestimmen(karte[0]);
					
					cup.paint(cup, image, symbol, wert);
					cup.werHatGezogen(karte[0]);
					werHatGezogen.setText(cup.werHatGezogen(karte[0]));
					kategorie.setText(cup.KategorieBestimmen(karte[0]));
					kategorie.setTextSize(20);
					erklaerung.setVisibility(0);
					werAlsNaechstes.setText(cup.werAlsNaechstesDran());
					button.setText(getString(R.string.bigkingscup_karte_ziehen));
					// Wenn eine Klokarte gezogen wurde
					if (cup.KategorieBestimmen(karte[0]).equals(getString(R.string.bigkingscup_klokarte))) {
						klokartenBesitzer.add(Spieler.getVorigerSpieler());
					}
					//Daumenmaster und Questionmaster TextViews aktualisieren
					if(cup.KategorieBestimmen(karte[0]).equals(getString(R.string.bigkingscup_question_master)))
						questionMaster.setText(Spieler.getVorigerSpieler());
					if(cup.KategorieBestimmen(karte[0]).equals(getString(R.string.bigkingscup_thumb_master)))
						daumenMaster.setText(Spieler.getVorigerSpieler());
				}
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	cup.wirklichBeendenDialog(this);
	    	return true;
	    }
	    return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ID_KLOKARTE_ENTFERNEN, Menu.NONE, getString(R.string.bigkingscup_klokarte_entfernen)).setIcon(R.drawable.ic_menu_close);
		menu.add(Menu.NONE, ID_HELP, Menu.NONE, getString(R.string.hilfe)).setIcon(R.drawable.ic_menu_info);
		menu.add(Menu.NONE, ID_BEENDEN, Menu.NONE, getString(R.string.zum_hauptmenue)).setIcon(R.drawable.ic_menu_close);
		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return (applyMenuChoice(item) || super.onOptionsItemSelected(item));
	}

	private boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case ID_KLOKARTE_ENTFERNEN:
			klokarteEntfernen();
			return true;
		case ID_HELP:
			cup.createHelperDialog(this, cup.getHelpMessage());
			return true;
		case ID_BEENDEN:
			cup.wirklichBeendenDialog(this);
			return true;
		}
		return false;
	}

	private void klokarteEntfernen() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle(getString(R.string.bigkingscup_klokarte_entfernen));
		String klokartenBesitzerString[] = new String[klokartenBesitzer.size()];
		for (int i = 0; i < klokartenBesitzerString.length; i++) {
			klokartenBesitzerString[i] = klokartenBesitzer.get(i);
		}

		if (klokartenBesitzer.size() == 0) {
			dialog.setMessage(getString(R.string.bigkingscup_keine_klokarten_vorhanden));
			dialog.setPositiveButton(getString(R.string.ok), null);
		} else {
			dialog.setItems(klokartenBesitzerString,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int item) {
							Toast.makeText( getApplicationContext(),(getString(R.string.bigkingscup_klokarte_von) + " " + 
									klokartenBesitzer.get(item) + " " + getString(R.string.bigkingscup_wurde_entfernt)), Toast.LENGTH_SHORT).show();
							klokartenBesitzer.remove(item);
						}
					});
		}
		dialog.show();
	}
}
