package com.google.code.kingsCup;

import android.content.Context;

import com.google.code.trinkspiele.KartenSpiel;
import com.google.code.trinkspiele.R;
import com.google.code.trinkspiele.Spieler;

public class BigKingsCup extends KartenSpiel {
	int kingCounter;
	Context context;
	
	public BigKingsCup(Context context) {
		super(context);
		this.context = context;
		kingCounter = 1;
		deckMischen();
	}
	
	public int getKingCounter() {
		return kingCounter;
	}
	
	public void setKingCounter(int newCount) {
		kingCounter = newCount;
	}
	
	public String werHatGezogen(String karte) {
		
		String symbol = kartenSymbolBestimmen(karte);
		String wert = karte.substring(karte.indexOf(" ") + 1);
		
		if (karte.contains("Diamonds") || karte.contains("Hearts") ||
			karte.contains("Spades") || karte.contains("Clubs")) {
					return Spieler.getAktuellerSpieler() + " has drawn the \"" + 
						   wert + " of " + symbol + "\", that means:\n";	
		}
		else
			return Spieler.getAktuellerSpieler() + " hat " + karte + " gezogen, das bedeutet:\n";
	}
	
	public String KategorieBestimmen(String karte) {

		int wert = kartenWertBestimmen(karte);
		
		switch (wert) {
		case 1:
			return context.getString(R.string.bigkingscup_alle_trinken);
		case 2:
			return context.getString(R.string.bigkingscup_kategorie);
		case 3:
			return context.getString(R.string.bigkingscup_reim);
		case 4:
			return context.getString(R.string.bigkingscup_question_master);
		case 5:
			return context.getString(R.string.bigkingscup_counter);
		case 6:
			return context.getString(R.string.bigkingscup_klokarte);
		case 7:
			return context.getString(R.string.bigkingscup_fettnaepfchen);
		case 8:
			return context.getString(R.string.bigkingscup_thumb_master);
		case 9:
			return context.getString(R.string.bigkingscup_regel_ausdenken);
		case 10:
			return context.getString(R.string.bigkingscup_zehn_schluecke_verteilen);
		case 11:
			return context.getString(R.string.bigkingscup_maenner_trinken);
		case 12:
			return context.getString(R.string.bigkingscup_maedels_trinken);
		case 13:
			return context.getString(R.string.bigkingscup_big_kings_cup);
		default:
			return context.getString(R.string.bigkingscup_kritischer_fehler);
		}
	}

	public String erklaerungZuKategorie(String karte) {
		
		if (karte.equals(context.getString(R.string.karten_deck_enthaelt_zu_wenig_karten))) {
			return "";
		}
		
		int wert = kartenWertBestimmen(karte);
		
		switch (wert) {
		case 1:
			return context.getString(R.string.bigkingscup_case_eins);
		case 2:
			return context.getString(R.string.bigkingscup_case_zwei);
		case 3:
			return context.getString(R.string.bigkingscup_case_drei);
		case 4:
			return context.getString(R.string.bigkingscup_case_vier);
		case 5:
			return context.getString(R.string.bigkingscup_case_fuenf);
		case 6:
			return context.getString(R.string.bigkingscup_case_sechs);
		case 7:
			return context.getString(R.string.bigkingscup_case_sieben);
		case 8:
			return context.getString(R.string.bigkingscup_case_acht);
		case 9:
			return context.getString(R.string.bigkingscup_case_neun);
		case 10:
			return context.getString(R.string.bigkingscup_case_zehn);
		case 11:
			return context.getString(R.string.bigkingscup_case_elf);
		case 12:
			return context.getString(R.string.bigkingscup_case_zwoelf);
		case 13:
			if (kingCounter == 4) {
				return context.getString(R.string.bigkingscup_case_dreizehn_eins);
			}
			else {
				return context.getString(R.string.bigkingscup_case_dreizehn_zwei);
			}
		default:
			return	context.getString(R.string.bigkingscup_kritischer_fehler);
		}
	}
	
	public String werAlsNaechstesDran() {
		Spieler.incrementAktuellerSpieler();
		return context.getString(R.string.bigkingscup_als_naechstes_ist) + " " + 
			Spieler.getAktuellerSpieler() + context.getString(R.string.bigkingscup_dran);
	}
	
	//Setzt den Text für die Spielerklärung
	public String getHelpMessage() {
		return context.getString(R.string.bigkingscup_help_message);
	}
}
