package com.google.code.kartenOrakel;

import android.content.Context;

import com.google.code.trinkspiele.KartenSpiel;
import com.google.code.trinkspiele.R;

public class KartenOrakel extends KartenSpiel {
	
	private boolean keinFrischesSpiel;
	private String[] karten;
	private int anzahlRichtigeTreffer;
	private int anzahlFalscheTreffer;
	Context context;
	
	public KartenOrakel(Context context) {
		super(context);
		this.context = context;
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
		
		String symbol = kartenSymbolBestimmen(karte);
		String wert = karte.substring(karte.indexOf(" "));
		
		//Nicht ins xml verlagert, da sonst im englischen der Kartenwert und symbol vertauscht sind
		if (karte.contains("Diamonds") || karte.contains("Hearts") ||
			karte.contains("Spades") || karte.contains("Clubs")) {
				ausgabe += "You've drawn the \"" + wert + " of " + symbol + "\"\n\n";	
		}
		else
			ausgabe += "Sie haben \"" + karte + "\" gezogen.\n\n";
		
		if (keinFrischesSpiel) {
			if (super.kartenWertBestimmen(karten[0]) == super.kartenWertBestimmen(karten[1])) {
				ausgabe += context.getString(R.string.orakel_Diese_und_die_vorige_karte_sind_gleichwertig_nichts_passiert);
			}
			else if (hoeherButtonGeklickt) {
				if (super.istHoeher(karten[0], karten[1])) {
					ausgabe += context.getString(R.string.orakel_karte_tatsaechlich_hoeher);
					anzahlRichtigeTreffer++;
				}
				else {
					ausgabe += context.getString(R.string.orakel_karte_leider_tiefer);
					anzahlFalscheTreffer++;
				}
			}
			else {
				if (!super.istHoeher(karten[0], karten[1])) {
					ausgabe += context.getString(R.string.orakel_karte_tatsaechlich_tiefer);
					anzahlRichtigeTreffer++;
				}
				else {
					ausgabe += context.getString(R.string.orakel_karte_leider_hoeher);
					anzahlFalscheTreffer++;
				}
			}
		}
		keinFrischesSpiel = true;
		if (super.getDeck().size() != 0) {
			ausgabe += context.getString(R.string.orakel_glauben_sie_karte_hoeher_oder_tiefer);
		}
		return ausgabe;
	}	
	
	@Override
	public String getHelpMessage() {
		return context.getString(R.string.orakel_help_message);
	}
}
