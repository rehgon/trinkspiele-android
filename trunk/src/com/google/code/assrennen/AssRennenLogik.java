package com.google.code.assrennen;

import com.google.code.trinkspiele.KartenSpiel;

public class AssRennenLogik extends KartenSpiel {
	private int aceCounter;
	public String[] kartenHalter;
	
	public AssRennenLogik() {
		super.geordnetesDeckGenerieren();
		super.deckMischen();
		aceCounter = 0;
	}
	
	public int getAceCounter() {
		return aceCounter;
	}
	
	public String assRennenkarteZiehen() {
		String ausgabe = "";
		
		while (aceCounter < 4) {
				String[] karte = super.kartenZiehen(1);
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
}
