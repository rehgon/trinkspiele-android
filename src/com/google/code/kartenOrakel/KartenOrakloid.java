package com.google.code.kartenOrakel;

import android.app.Activity;
import android.app.AlertDialog;
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
				if (karte[0].equals("Deck enthält zu wenig Karten"))
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
				if (karte[0].equals("Deck enthält zu wenig Karten"))
					reset();
				else
					zugMachen(true);
			}
		});
	}

	private void zugMachen(boolean hoeherButtonGedrückt) {
		String symbol = orakel.kartenSymbolBestimmen(karte[0]);
		int wert = orakel.kartenWertBestimmen(karte[0]);
		orakel.paint(orakel, image, symbol, wert);
		ausgabe.setText(orakel.spieleKartenOrakel(karte[0], hoeherButtonGedrückt));

		richtigLabel.setText("Richtige: " + orakel.getAnzahlRichtigeTreffer());
		falschLabel.setText("Falsche: " + orakel.getAnzahlFalscheTreffer());

		progress.incrementProgressBy(1);
	}

	private void reset() {
		resultatAnzeigen();
		ausgabe.setText("Alle Karten durch, klicken sie auf den Stapel"
				+ "um neu zu starten");
		istNeuesSpiel = true;
		image.setImageResource(R.drawable.deckblatt);
		hoeherButton.setVisibility(4);
		tieferButton.setVisibility(4);
		progress.setProgress(0);

		orakel = new KartenOrakel();
		richtigLabel.setText("Richtige: 0");
		falschLabel.setText("Falsche: 0");
	}
	
	private void resultatAnzeigen() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Resultat");
		String message = 
			"Richtig prophezeit:\n " + orakel.getAnzahlRichtigeTreffer() + " mal\n\n" +
			"Falsch prophezeit:\n " + orakel.getAnzahlFalscheTreffer() + " mal\n\n";
		if (orakel.getAnzahlRichtigeTreffer() > 32)
			message += "Titel:\nGott";
		else if (orakel.getAnzahlRichtigeTreffer() > 26) {
			message += "Titel:\nPassables Orakel";
		}
		else {
			message += "Titel:\nScharlatan";
		}
		dialog.setMessage(message);
		dialog.setPositiveButton("Ok", null);
		dialog.show();
	}
}
