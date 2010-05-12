package com.google.code.kingsCup;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.code.trinkspiele.Trinkspiele;

public class Cupoid extends Activity {
	private static final int ID_KLOKARTE_ENTFERNEN = 0;
	private static final int ID_BEENDEN = 1;

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
		
		cup = new BigKingsCup();
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

			@Override
			public void onClick(View v) {
				
				// Eine nicht editierbare Seek Bar soll angezeigt werden, falls wenn ein König
				// erscheint und auf den Erklärung Button geklickt wird
				if (kategorie.getText().equals("KING'S CUP")) {
					SeekBar cupProgress = new SeekBar(getApplicationContext());
					cupProgress.setMax(4);
					cupProgress.setProgress(cup.getKingCounter());
					dialog.setView(cupProgress);
				}
				dialog.setTitle("Erklärung: " + cup.KategorieBestimmen(symbol + " " + wert));
				dialog.setMessage(cup
						.erklaerungZuKategorie(symbol + " " + wert));
				dialog.setPositiveButton("Ok", null);
				dialog.show();
				//nötig um die evtl. hinzugefügte Seekbar(nachdem Karte == König) wieder zu entfernen 
				dialog.setView(null);
			}
		});

		button = (Button) findViewById(R.id.bigKingButton);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				//Progress Bars aktualisieren
				progress.incrementProgressBy(1);
				if (kategorie.getText().equals("KING'S CUP")) 
					cup.setKingCounter(cup.getKingCounter() + 1);

				karte = cup.kartenZiehen(1);
				if (karte[0].equals("Deck enthält zu wenig Karten")) {
					werHatGezogen.setText("");
					kategorie.setText("ALLE KARTEN DURCH");
					erklaerung.setVisibility(4);
					werAlsNaechstes.setText("Klick auf den Button um neu zu starten");
					button.setText("Neustart");
					image.setImageResource(R.drawable.deckblatt);
					klokartenBesitzer = new ArrayList<String>();
					questionMaster.setText("");
					daumenMaster.setText("");
					cup = new BigKingsCup();
					cup.setKingCounter(1);
					progress.setProgress(0);
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
					button.setText("Karte ziehen");
					// Wenn eine Klokarte gezogen wurde
					if (cup.KategorieBestimmen(karte[0]).equals("KLOKARTE")) {
						klokartenBesitzer.add(Spieler.getVorigerSpieler());
					}
					//Daumenmaster und Questionmaster TextViews aktualisieren
					if(cup.KategorieBestimmen(karte[0]).equals("QUESTION MASTER"))
						questionMaster.setText(Spieler.getVorigerSpieler());
					if(cup.KategorieBestimmen(karte[0]).equals("THUMB MASTER"))
						daumenMaster.setText(Spieler.getVorigerSpieler());
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ID_KLOKARTE_ENTFERNEN, Menu.NONE,
				"Klokarte entfernen").setIcon(R.drawable.remove);
		menu.add(Menu.NONE, ID_BEENDEN, Menu.NONE, "Zum Hauptmenü").setIcon(R.drawable.close);
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
		case ID_BEENDEN:
			startActivity(new Intent(this, Trinkspiele.class));
			return true;
		}
		return false;
	}

	private void klokarteEntfernen() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Klokarte entfernen");
		String klokartenBesitzerString[] = new String[klokartenBesitzer.size()];
		for (int i = 0; i < klokartenBesitzerString.length; i++) {
			klokartenBesitzerString[i] = klokartenBesitzer.get(i);
		}

		if (klokartenBesitzer.size() == 0) {
			dialog.setMessage("Keine Klokarten vorhanden");
			dialog.setPositiveButton("Ok", null);
		} else {
			dialog.setItems(klokartenBesitzerString,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int item) {
							Toast.makeText( getApplicationContext(),("Klokarte von " + 
									klokartenBesitzer.get(item) + " wurde entfernt"), Toast.LENGTH_SHORT).show();
							klokartenBesitzer.remove(item);
						}
					});
		}
		dialog.show();
	}
}
