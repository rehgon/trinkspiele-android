package com.google.code.assrennen;

import android.content.Context;

import com.google.code.trinkspiele.KartenSpiel;
import com.google.code.trinkspiele.R;

public class AssRennenLogik extends KartenSpiel {
	private int aceCounter;
	public String[] kartenHalter;
	private String aktuellesKartenSymbol;
	private int aktuellerKartenWert;
	Context context;
	
	public AssRennenLogik(Context context) {
		super(context);
		this.context = context;
		super.geordnetesDeckGenerieren();
		super.deckMischen();
		aceCounter = 0;
	}
	
	public int getAceCounter() {
		return aceCounter;
	}

	public String getAktuellesKartenSymbol() {
		return aktuellesKartenSymbol;
	}
	public int getAktuellerKartenWert() {
		return aktuellerKartenWert;
	}
	
	public String assRennenkarteZiehen() {
		String ausgabe = "";
		
		while (aceCounter < 4) {
			
				String[] karte = super.kartenZiehen(1);
				aktuellesKartenSymbol = kartenSymbolBestimmen(karte[0]);
				aktuellerKartenWert = kartenWertBestimmen(karte[0]);
				

				aktuellesKartenSymbol = kartenSymbolBestimmen(karte[0]);
				aktuellerKartenWert = kartenWertBestimmen(karte[0]);
				kartenHalter = karte;
			if(karte[0].contains(("Ass"))||(karte[0].contains("Ace"))) {
				aceCounter++;
				String[] welchesAss = {context.getString(R.string.assrennen_erste), context.getString(R.string.assrennen_zweite), context.getString(R.string.assrennen_dritte),context.getString(R.string.assrennen_vierte) };
				ausgabe += context.getString(R.string.assrennen_siehaben) + " \"" + karte[0] + "\" " +context.getString(R.string.assrennen_gezogendiesist) +" " + welchesAss[aceCounter - 1] +" "+ context.getString(R.string.assrennen_ass)+" "+ context.getString(R.string.assrennen_esmuss) +" "+ aceCounter + "/4 " +context.getString(R.string.assrennen_getrunkenwerden);
				if (aceCounter == 4)
					ausgabe += context.getString(R.string.assrennen_spielende);		
			}
			else
				ausgabe += context.getString(R.string.assrennen_siehaben)+" \"" + karte[0] + "\" " + context.getString(R.string.assrennen_gezogennichtspassiert);
			return ausgabe;
		}
		
		return ausgabe;
	}
	public String help()
	{
		String ausgabe = context.getString(R.string.assrennen_help);	/*"Es wird nacheinander eine Karte gezogen. Wenn ein Ass gezogen wurde muss getrunken werden." +
							" Die Menge die getrunken werden muss, hängt von der Anzahl gezogener Asse ab." +
							"Beim ersten Ass wird 1/4 getrunken und bei jedem weiteren Ass wird zusätzlich 1/4 getrunken.";*/
		return ausgabe;
	}
}
