package com.google.code.assrennen;

import com.google.code.trinkspiele.KartenSpiel;

public class AssRennenLogik extends KartenSpiel {
	private int aceCounter;
	public String[] kartenHalter;
	private String aktuellesKartenSymbol;
	private int aktuellerKartenWert;
	
	public AssRennenLogik() {
		super.geordnetesDeckGenerieren();
		super.deckMischen();
		aceCounter = 0;
	}
	
	public int getAceCounter() {
		return aceCounter;
	}
	public String getAktuellesKartenSymbol()
	{
		return aktuellesKartenSymbol;
	}
	public int getAktuellerKartenWert()
	{
		return aktuellerKartenWert;
	}
	
	public String assRennenkarteZiehen() {
		String ausgabe = "";
		
		while (aceCounter < 4) {
				String[] karte = super.kartenZiehen(1);
				aktuellesKartenSymbol = kartenSymbolBestimmen(karte[0]);
				aktuellerKartenWert = kartenWertBestimmen(karte[0]);
				kartenHalter = karte;
			if(karte[0].contains("Ass")) {
				aceCounter++;
				String[] welchesAss = { "erste", "zweite", "dritte", "vierte" };
				ausgabe += "Sie haben \"" + karte[0] + "\" gezogen.\nDies ist das " + welchesAss[aceCounter - 1] + " Ass.\nEs muss " + aceCounter + "/4 getrunken werden.\n";
				if (aceCounter == 4)
					ausgabe += "Es wurden alle Asse aus dem Deck gezogen\nDas Spiel ist beendet";		
			}
			else
				ausgabe += "Sie haben \"" + karte[0] + "\" gezogen\nNichts passiert und der nächste Spieler ist dran";
			return ausgabe;
		}
		
		return ausgabe;
	}
	public String help()
	{
		String ausgabe = 	"Es wird nacheinander eine Karte gezogen. Wenn ein Ass gezogen wurde muss getrunken werden." +
							" Die Menge die getrunken werden muss, hängt von der Anzahl gezogener Asse ab." +
							"Beim ersten Ass wird 1/4 getrunken und bei jedem weiteren Ass wird zusätzlich 1/4 getrunken.";
		return ausgabe;
	}
}
