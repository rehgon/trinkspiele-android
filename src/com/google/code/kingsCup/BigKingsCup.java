package com.google.code.kingsCup;

import com.google.code.trinkspiele.KartenSpiel;
import com.google.code.trinkspiele.Spieler;

public class BigKingsCup extends KartenSpiel {
	int kingCounter;
	
	public BigKingsCup() {
		super();
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
		return Spieler.getAktuellerSpieler() + " hat " + karte + " gezogen, das bedeutet:\n";
	}
	
	public String KategorieBestimmen(String karte) {

		String ausgabe = "";
		int wert = kartenWertBestimmen(karte);
		
		if (wert == 2) {
			ausgabe += "KATEGORIE";
		}
		else if (wert == 3) {
			ausgabe += "REIM";
		}
		else if (wert == 4) {
			ausgabe += "QUESTIONMASTER";
		}
		else if (wert == 5) {
			ausgabe += "COUNTER";
		}
		else if (wert == 6) {
			ausgabe += "KLOKARTE";
		}
		else if (wert == 7) {
			ausgabe += "FETTNÄPFCHEN";
		}
		else if (wert == 8) {
			ausgabe += "DAUMENMASTER";
		}
		else if (wert == 9) {
			ausgabe += "REGEL AUSDENKEN";
		}
		else if (wert == 10) {
			ausgabe += "10 SCHLÜCKE VERTEILEN";
		}
		else if (wert == 11) {
			ausgabe += "MÄNNER TRINKEN";
		}
		else if (wert == 12) {
			ausgabe += "MÄDELS TRINKEN";
		}
		else if (wert == 13) {
			ausgabe += "KING'S CUP";
		}
		else if (wert == 1) {
			ausgabe += "ALLE TRINKEN";
		}
		else {
			ausgabe += "CRITICAL ERROR";
		}
		
		return ausgabe;
	}

	public String erklaerungZuKategorie(String karte) {
		
		String ausgabe = "";
		if (karte.equals("Deck enthaelt zuwenig Karten")) {
			return "";
		}
		
		int wert = kartenWertBestimmen(karte);
		
		if (wert == 1) {
			ausgabe =
					"Schlichtes Hochpuschen des Kollektivpegels.";
		}
		else if (wert == 2) {
			ausgabe =
					"Der Spieler sucht sich ein Genre aus (z.B. Obstarten, Getränkemarken, Zigaretten) und " +
					"nennt einen Begriff daraus. Nun muss reihum jeweils ein weiterer genannt werden, " +
					"solange bis einem Spieler kein Begriff mehr einfällt, einen bereits genannten " +
					"Begriff noch einmal nennt oder sich sonstwie vertut. Dieser muss dann trinken.";
		}
		else if (wert == 3) {
			ausgabe = 
					"Der Spieler sucht sich ein beliebiges Wort aus (z.B. Tisch) und nun geht es wieder " +
					"reihum. Jeder muss ein sich darauf reimendes Wort (z.B. Fisch,..) nennen. Der erste," +
					"dem keins mehr einfällt trinkt";
		}
		else if (wert == 4) {
			ausgabe =
					"Dem Spieler, der eine 4 zieht, dürfen keine Fragen mehr beantwortet werden, egal " +
					"welcher Art. Dies kann und soll er natürlich ausnutzen, um die anderen zum " +
					"trinken zu bringen, denn wer antwortet, muss trinken. Der Spieler hat den " +
					"Status Questionmaster solange, bis ein anderer Spieler eine 4 zieht.";
		}
		else if (wert == 5) {
			ausgabe =
					"Der Spieler sucht sich eine Zahl zwischen 3 und 9 aus. Nun geht es reihum und es " +
					"wird gezählt. Jedoch muss bei jeder Zahl, welche durch die ausgewählte teilbar " +
					"ist oder in der diese Ziffer vorkommt, nicht die Zahl sondern ein vorher " +
					"festzulegendes Wort genannt werden (Empfehlung: FICKEN). Das sieht dann " +
					"folgendermassen aus (bei der gewählten Zahl 3): 1, 2, ficken, 4, 5, " +
					"ficken, 7, 8, ficken, 10, 11, ficken, ficken, 14, usw. Wer sich vertut, " +
					"inwiefern auch immer, muss trinken.";
		}
		else if (wert == 6) {
			ausgabe =
					"Da vor allem Bier gut auf die Blase geht, ist es sehr nützlich, sich auf der Toilette " +
					"dem Harndrangs zu entledigen. Dies ist aber nur im Besitz einer Klokarte erlaubt. " +
					"Klokarten können übrigens über das Optionsmenü wieder entfernt werden, sobald jemand " +
					"eine einlösen möchte.";
		}
		else if (wert == 7) {
			ausgabe =
					"Trinke selber.";
		}
		else if (wert == 8) {
			ausgabe =
					"Der Spieler ist nun analog zum Questionmaster der Thumbmaster. Dies bedeutet, " +
					"dass er beliebig oft und nach Lust und Laune seinen Daumen auf die Tischkante " +
					"legen kann. Nun müssen alle anderen ebenfalls ihren Daumen auf die Tischkante " +
					"legen. Der letzte, der es checkt, muss trinken.";
		}
		else if (wert == 9) {
			ausgabe =
					"Die 9 ist mit die beliebteste Karte, denn hier darf sich der Spieler selbst eine " +
					"beliebige Regel ausdenken, die dann das gesamte restliche Spiel über gilt. " +
					"Sehr zu empfehlende Vorschläge sind:\n " +
					"- Man darf generell keine Namen mehr nennen oder alle Leute heissen McLovin\n" +
					"- Man darf auf niemanden mehr zeigen\n" +
					"- (beliebig ausbaufähig)";
		}
		else if (wert == 10) {
			ausgabe =
					"Der Spieler darf an die übrigen Mitspieler zehn Schlücke verteilen.";
		}
		else if (wert == 11) {
			ausgabe =
					"Alle Kerle müssen trinken.";
		}
		else if (wert == 12) {
			ausgabe =
					"Alle Mädels müssen trinken.";
		}
		else if (wert == 13) {
			ausgabe =
				"Der Spieler füllt das grosse Glas in der Mitte mit einem beliebigen Getränk seiner " +
				"Wahl, so dass es im Anschluss zu einem Viertel gefüllt ist. Der Spieler, welcher " +
				"den zweiten König zieht, füllt das Glas bis zur Hälfte, der Dritte zu drei " +
				"Vierteln. Der vierte König muss seinen eigenen Glasinhalt dazukippen und das " +
				"grosse Glas (den Big King's Cup) leer-exen!";
			
			if (kingCounter == 4) {
				ausgabe = 
					Spieler.getVorigerSpieler() + " muss seinen eigenen Glasinhalt noch in den Big King's " +
				 	"Cup dazuschütten und dann alles ausexen. HOPP!!!";
			}	
		}
		else {
			ausgabe = 
					"CRITICAL ERROR";
		}
		return ausgabe;
	}
	
	public String werAlsNaechstesDran() {
		Spieler.incrementAktuellerSpieler();
		return "Als naechstes ist " + Spieler.getAktuellerSpieler() + " dran";
	}
}
