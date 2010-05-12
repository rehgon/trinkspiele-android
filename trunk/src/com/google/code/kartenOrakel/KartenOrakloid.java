package com.google.code.kartenOrakel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.code.trinkspiele.R;

public class KartenOrakloid extends Activity {
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
		
		orakel = new KartenOrakel();
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
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (istNeuesSpiel) {
					karte = orakel.kartenZiehen(1);
					String symbol = orakel.kartenSymbolBestimmen(karte[0]);
					int wert = orakel.kartenWertBestimmen(karte[0]);
					orakel.paint(orakel, image, symbol, wert);
					ausgabe.setText(orakel.spieleKartenOrakel(karte[0], true)); //bool hier noch unrelevant
					
					hoeherButton.setVisibility(0);
					tieferButton.setVisibility(0);
					
					progress.incrementProgressBy(1);
					istNeuesSpiel = false;
				}
				else {
					//deaktiviert wenn das Spiel läuft
				}
			}
		});
		
		//onclick listener für tiefer Button
		tieferButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				karte = orakel.kartenZiehen(1);
				
				if (karte[0].equals("Deck enthält zu wenig Karten")) {
					ausgabe.setText("Alle Karten durch, klicken sie auf den Stapel " +
							"um neu zu starten");
					istNeuesSpiel = true;
					image.setImageResource(R.drawable.deckblatt);
					hoeherButton.setVisibility(4);
					tieferButton.setVisibility(4);
					progress.setProgress(0);
					
					orakel = new KartenOrakel();
					richtigLabel.setText("Richtige: 0");
					falschLabel.setText("Falsche: 0");
				}
				else {
					karte = orakel.kartenZiehen(1);
					String symbol = orakel.kartenSymbolBestimmen(karte[0]);
					int wert = orakel.kartenWertBestimmen(karte[0]);
					orakel.paint(orakel, image, symbol, wert);
					ausgabe.setText(orakel.spieleKartenOrakel(karte[0], false));
	
					richtigLabel.setText("Richtige: " +orakel.getAnzahlRichtigeTreffer());
					falschLabel.setText("Falsche: " +orakel.getAnzahlFalscheTreffer());
					
					progress.incrementProgressBy(1);
				}
			}
		});
		
		//onclick listener für höher Button
		hoeherButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				karte = orakel.kartenZiehen(1);
				
				if (karte[0].equals("Deck enthält zu wenig Karten")) {
					ausgabe.setText("Alle Karten durch, klicken sie auf den Stapel" +
							"um neu zu starten");
					istNeuesSpiel = true;
					image.setImageResource(R.drawable.deckblatt);
					hoeherButton.setVisibility(4);
					tieferButton.setVisibility(4);
					progress.setProgress(0);
					
					orakel = new KartenOrakel();
					richtigLabel.setText("Richtige: 0");
					falschLabel.setText("Falsche: 0");
				}
				else {
					String symbol = orakel.kartenSymbolBestimmen(karte[0]);
					int wert = orakel.kartenWertBestimmen(karte[0]);
					orakel.paint(orakel, image, symbol, wert);
					ausgabe.setText(orakel.spieleKartenOrakel(karte[0], true));
					
					richtigLabel.setText("Richtige: " +orakel.getAnzahlRichtigeTreffer());
					falschLabel.setText("Falsche: " +orakel.getAnzahlFalscheTreffer());
					
					progress.incrementProgressBy(1);
				}
			}
		});
	}
}
