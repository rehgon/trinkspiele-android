package com.google.code.kartenOrakel;

import com.google.code.trinkspiele.KartenSpiel;

public class KartenOrakel extends KartenSpiel {
	
	private boolean keinFrischesSpiel;
	private String[] karten;
	private int anzahlRichtigeTreffer;
	private int anzahlFalscheTreffer;
	
	public KartenOrakel() {
		super();
		keinFrischesSpiel = false;
		karten = new String[2];
		anzahlRichtigeTreffer = 0;
		anzahlFalscheTreffer = 0;
		super.geordnetesDeckGenerieren();
		super.deckMischen();
	}
	
	public int getAnzahlRichtigeTreffer() {
		return anzahlRichtigeTreffer;
	}
	public int getAnzahlFalscheTreffer() {
		return anzahlFalscheTreffer;
	}
	public void increaseAnzahlRichtigeTrefferBy1() {
		anzahlRichtigeTreffer++;
	}
	public void increaseAnzahlFalscheTrefferBy1() {
		anzahlFalscheTreffer++;
	}
	
	
	public String spieleKartenOrakel(String karte, boolean hoeherButtonGeklickt) {
		String ausgabe = "";
		karten[1] = karten[0];
		karten[0] = karte;
				
		ausgabe += "Sie haben \"" + karte + "\" gezogen.\n\n";
		if (keinFrischesSpiel) {
			if (super.kartenWertBestimmen(karten[0]) == super.kartenWertBestimmen(karten[1])) {
				ausgabe += "Diese und die vorige Karte sind gleichwertig,\nnichts passiert.\n\n";
			}
			else if (hoeherButtonGeklickt) {
				if (super.istHoeher(karten[0], karten[1])) {
					ausgabe += "Die Karte ist tatsächlich höher,\nsie lagen richtig.\n\n";
					anzahlRichtigeTreffer++;
				}
				else {
					ausgabe += "Die Karte ist leider tiefer,\nsie lagen falsch.\n\n";
					anzahlFalscheTreffer++;
				}
			}
			else {
				if (!super.istHoeher(karten[0], karten[1])) {
					ausgabe += "Die Karte ist tatsächlich tiefer,\nsie lagen richtig.\n\n";
					anzahlRichtigeTreffer++;
				}
				else {
					ausgabe += "Die Karte ist leider höher,\nsie lagen falsch.\n\n";
					anzahlFalscheTreffer++;
				}
			}
		}
		keinFrischesSpiel = true;
		if (super.getDeck().size() != 0) {
			ausgabe += "Glauben sie die nächste Karte, die sie ziehen\n" + 
				"wird höher oder tiefer sein als diese?";
		}
		return ausgabe;
	}	
	
	@Override
	public String getHelpMessage() {
		String s =
			"Simples Spiel für zwischendurch. " +
			"Es geht einfach darum zu raten ob die nächste Karte " +
			"höher oder tiefer sein wird als die aktuelle";
		return s;
	}
}
