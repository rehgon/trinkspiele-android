package com.google.code.kingsCup;

import android.content.Context;

import com.google.code.trinkspiele.KartenSpiel;
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
		return Spieler.getAktuellerSpieler() + " hat " + karte + " gezogen, das bedeutet:\n";
	}
	
	public String KategorieBestimmen(String karte) {

		int wert = kartenWertBestimmen(karte);
		
		switch (wert) {
		case 1:
			return "ALLE TRINKEN";
		case 2:
			return "KATEGORIE";
		case 3:
			return "REIM";
		case 4:
			return "QUESTION MASTER";
		case 5:
			return "COUNTER";
		case 6:
			return "KLOKARTE";
		case 7:
			return "FETTNÄPFCHEN";
		case 8:
			return "THUMB MASTER";
		case 9:
			return "REGEL AUSDENKEN";
		case 10:
			return "10 SCHLÜCKE VERTEILEN";
		case 11:
			return "MÄNNER TRINKEN";
		case 12:
			return "MÄDELS TRINKEN";
		case 13:
			return "KING'S CUP";
		default:
			return "CRITICAL ERROR";
		}
	}

	public String erklaerungZuKategorie(String karte) {
		
		if (karte.equals("Deck enthaelt zuwenig Karten")) {
			return "";
		}
		
		int wert = kartenWertBestimmen(karte);
		
		switch (wert) {
		case 1:
			return 	"Schlichtes Hochpuschen des Kollektivpegels.";
		case 2:
			return 	"Der Spieler sucht sich ein Genre aus (z.B. Obstarten, Getränkemarken, Zigaretten) und " +
					"nennt einen Begriff daraus. Nun muss reihum jeweils ein weiterer genannt werden, " +
					"solange bis einem Spieler kein Begriff mehr einfällt, einen bereits genannten " +
					"Begriff noch einmal nennt oder sich sonstwie vertut. Dieser muss dann trinken.";
		case 3:
			return	"Der Spieler sucht sich ein beliebiges Wort aus (z.B. Tisch) und nun geht es wieder " +
					"reihum. Jeder muss ein sich darauf reimendes Wort (z.B. Fisch,..) nennen. Der erste," +
					"dem keins mehr einfällt trinkt";
		case 4:
			return  "Dem Spieler, der eine 4 zieht, dürfen keine Fragen mehr beantwortet werden, egal " +
					"welcher Art. Dies kann und soll er natürlich ausnutzen, um die anderen zum " +
					"trinken zu bringen, denn wer antwortet, muss trinken. Der Spieler hat den " +
					"Status Questionmaster solange, bis ein anderer Spieler eine 4 zieht.";
		case 5:
			return  "Der Spieler sucht sich eine Zahl zwischen 3 und 9 aus. Nun geht es reihum und es " +
					"wird gezählt. Jedoch muss bei jeder Zahl, welche durch die ausgewählte teilbar " +
					"ist oder in der diese Ziffer vorkommt, nicht die Zahl sondern ein vorher " +
					"festzulegendes Wort genannt werden (Empfehlung: FICKEN). Das sieht dann " +
					"folgendermassen aus (bei der gewählten Zahl 3): 1, 2, ficken, 4, 5, " +
					"ficken, 7, 8, ficken, 10, 11, ficken, ficken, 14, usw. Wer sich vertut, " +
					"inwiefern auch immer, muss trinken.";
		case 6:
			return "Da vor allem Bier gut auf die Blase geht, ist es sehr nützlich, sich auf der Toilette " +
					"dem Harndrangs zu entledigen. Dies ist aber nur im Besitz einer Klokarte erlaubt. " +
					"Klokarten können übrigens über das Optionsmenü wieder entfernt werden, sobald jemand " +
					"eine einlösen möchte.";
		case 7:
			return "Trinke selber.";
		case 8:
			return  "Der Spieler ist nun analog zum Questionmaster der Thumbmaster. Dies bedeutet, " +
					"dass er beliebig oft und nach Lust und Laune seinen Daumen auf die Tischkante " +
					"legen kann. Nun müssen alle anderen ebenfalls ihren Daumen auf die Tischkante " +
					"legen. Der letzte, der es checkt, muss trinken.";
		case 9:
			return  "Die 9 ist mit die beliebteste Karte, denn hier darf sich der Spieler selbst eine " +
					"beliebige Regel ausdenken, die dann das gesamte restliche Spiel über gilt. " +
					"Sehr zu empfehlende Vorschläge sind:\n " +
					"- Man darf generell keine Namen mehr nennen oder alle Leute heissen McLovin\n" +
					"- Man darf auf niemanden mehr zeigen\n" +
					"- (beliebig ausbaufähig)";
		case 10:
			return 	"Der Spieler darf an die übrigen Mitspieler zehn Schlücke verteilen.";
		case 11:
			return 	"Alle Kerle müssen trinken.";
		case 12:
			return 	"Alle Mädels müssen trinken.";
		case 13:
			if (kingCounter == 4) {
				return	Spieler.getVorigerSpieler() + " muss seinen eigenen Glasinhalt noch in den Big King's " +
				 		"Cup dazuschütten und dann alles ausexen. HOPP!!!";
			}
			else {
				return 	"Der Spieler füllt das grosse Glas in der Mitte mit einem beliebigen Getränk seiner " +
						"Wahl, so dass es im Anschluss zu einem Viertel gefüllt ist. Der Spieler, welcher " +
						"den zweiten König zieht, füllt das Glas bis zur Hälfte, der Dritte zu drei " +
						"Vierteln. Der vierte König muss seinen eigenen Glasinhalt dazukippen und das " +
						"grosse Glas (den Big King's Cup) leer-exen!";
			}
		default:
			return	"CRITICAL ERROR";
		}
	}
	
	public String werAlsNaechstesDran() {
		Spieler.incrementAktuellerSpieler();
		return "Als naechstes ist " + Spieler.getAktuellerSpieler() + " dran";
	}
	
	//Setzt den Text für die Spielerklärung
	public String getHelpMessage() {
		String s =
			"Es wird reihum gezogen." +
			"Es kann solange gespielt werden wie gewünscht; Jedoch werden, " +
			"sobald alle Karten durch sind die Werte wieder zurückgesetzt. " +
			"Die Bedeutung der einzelnen Karten können über den \"Erklärung\" Button eingesehen werden. ";
		return s;
	}
}
