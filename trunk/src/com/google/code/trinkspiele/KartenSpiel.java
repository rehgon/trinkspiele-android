package com.google.code.trinkspiele;


import java.util.ArrayList;
import java.util.Random;

import android.widget.ImageView;

public abstract class KartenSpiel extends Spiel {
	private Random generator = new Random();
	private ArrayList<String> deck = new ArrayList<String>();
	private String[] kartenSymbole;
	private String[] kartenWerte;
	
	int[] cardDrawablesLocationPik = {
			R.drawable.pikass,
			R.drawable.pik2,
			R.drawable.pik3,
			R.drawable.pik4,
			R.drawable.pik5,
			R.drawable.pik6,
			R.drawable.pik7,
			R.drawable.pik8,
			R.drawable.pik9,
			R.drawable.pik10,
			R.drawable.pik_bube,
			R.drawable.pik_dame,
			R.drawable.pik_koenig
	};
	
	int[] cardDrawablesLocationKaro = {
			R.drawable.karoass,
			R.drawable.karo2,
			R.drawable.karo3,
			R.drawable.karo4,
			R.drawable.karo5,
			R.drawable.karo6,
			R.drawable.karo7,
			R.drawable.karo8,
			R.drawable.karo9,
			R.drawable.karo10,
			R.drawable.karo_bube,
			R.drawable.karo_dame,
			R.drawable.karo_koenig
	};
	
	int[] cardDrawablesLocationHerz = {
			R.drawable.herzass,
			R.drawable.herz2,
			R.drawable.herz3,
			R.drawable.herz4,
			R.drawable.herz5,
			R.drawable.herz6,
			R.drawable.herz7,
			R.drawable.herz8,
			R.drawable.herz9,
			R.drawable.herz10,
			R.drawable.herz_bube,
			R.drawable.herz_dame,
			R.drawable.herz_koenig
	};
	
	int[] cardDrawablesLocationKreuz = {
			R.drawable.kreuzass,
			R.drawable.kreuz2,
			R.drawable.kreuz3,
			R.drawable.kreuz4,
			R.drawable.kreuz5,
			R.drawable.kreuz6,
			R.drawable.kreuz7,
			R.drawable.kreuz8,
			R.drawable.kreuz9,
			R.drawable.kreuz10,
			R.drawable.kreuz_bube,
			R.drawable.kreuz_dame,
			R.drawable.kreuz_koenig
	};
	
	
	
	public KartenSpiel() {
		generator = new Random();
		deck = new ArrayList<String>();
		kartenSymbole = new String[] { "Karo", "Kreuz", "Herz", "Pik" };
		kartenWerte = new String[] { "2", "3", "4", "5", "6", "7",
				"8", "9", "10", "Bube", "Dame", "König", "Ass"};
		geordnetesDeckGenerieren();
	}
	
	public ArrayList<String> getDeck() {
		return deck;
	}
	public void setDeck(ArrayList<String> neuesDeck) {
		this.deck = neuesDeck;
	}
	public String getKarte(int index) {
		return deck.get(index);
	}
	
	public void geordnetesDeckGenerieren() {
		deck.removeAll(deck);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++)
				deck.add(kartenSymbole[i] + " " + kartenWerte[j]);
		}
	}
	
	public void deckMischen() {
		ArrayList<String> tmpDeck = new ArrayList<String>();
		for (int i = 0; i < 52; i++) {
			int m = generator.nextInt(deck.size());
			tmpDeck.add(deck.get(m));
			deck.remove(m);
		}
		deck = tmpDeck;
	}
	
	public String deckAuslesen() {
		String output = "";
		for (int i = 0; i < deck.size(); i++) {
			output += deck.get(i);
			if (i != deck.size() -1 )
				output += ", ";
		}
		return output;
	}
	
	public String[] zufaelligeKarten(int wieviele) {
		String[] dieHand = new String[wieviele];
		for (int i=0; i<dieHand.length; i++) {
			int zufall = generator.nextInt(deck.size());
			dieHand[i] = deck.get(zufall);
			deck.remove(zufall);
		}
		return dieHand;
	}
	
	public String[] kartenZiehen(int wieviele) {
		String[] karte;
		if (wieviele <= deck.size()) {
			karte = new String[wieviele];
			for (int i = 0; i < karte.length; i++) {
					karte[i] = deck.get(i);
					deck.remove(i);
			}
		}
		else {
			 karte = new String[1];
			 karte[0] = "Deck enthält zu wenig Karten";
		}
		return karte;
	}
	
	public String handAuslesen(String[] dieHand) {
		String output = "";
		for (int i = 0; i < dieHand.length; i++) {
			output += dieHand[i];
			if (i != dieHand.length - 1)
				output += ", ";
		}
		return output;
	}
	
	public boolean istHoeher(String wertKarte1, String wertKarte2) {
		
		int karte1 = 0, karte2 = 0;
		String karteString1 = wertKarte1.trim().substring(wertKarte1.indexOf(" ") + 1);
		String karteString2 = wertKarte2.trim().substring(wertKarte2.indexOf(" ") + 1);
		
		if (karteString1.equals(kartenWerte[12]))
			karte1 = 1;
		if (karteString2.equals(kartenWerte[12]))
			karte2 = 1;
			
		for (int i = 0; i < 3; i++) {
			if (karteString1.equals(kartenWerte[i + 9]))
				karte1 = (i + 11);
			if (karteString2.equals(kartenWerte[i + 9]))
				karte2 = (i + 11);
		}
		
		if (karte1 == 0)
			karte1 = Integer.parseInt(karteString1);
		if (karte2 == 0)
			karte2 = Integer.parseInt(karteString2);
		
		if (karte1 > karte2)
			return true;
		else
			return false;
	}
	
	public String kartenSymbolBestimmen(String karte) {
		return karte.substring(0, karte.indexOf(" "));
	}
	
	public int kartenWertBestimmen(String karte) {
		String wert = karte.substring(karte.indexOf(" ") + 1);
		
		if (wert.equals(kartenWerte[9]))
			return 11;
		else if (wert.equals(kartenWerte[10]))
			return 12;
		else if (wert.equals(kartenWerte[11]))
			return 13;
		else if (wert.equals(kartenWerte[12]))
			return 1;
		else
			return Integer.parseInt(wert);
	}
	
	public void paint(KartenSpiel spiel, ImageView image, String symbol, int wert) {
		
		//Wert muss um 1 reduziert werden, da Array bei 0 beginnt
		wert--;
		
		try {
			if (symbol.equals("Pik")) {
				image.setImageResource(cardDrawablesLocationPik[wert]);			
			}
			else if (symbol.equals("Karo")) {
				image.setImageResource(cardDrawablesLocationKaro[wert]);
			}
			else if (symbol.equals("Herz")) {
				image.setImageResource(cardDrawablesLocationHerz[wert]);
			}
			else if (symbol.equals("Kreuz")) {
				image.setImageResource(cardDrawablesLocationKreuz[wert]);
			}
			else {
				image.setImageResource(R.drawable.cancel);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Wird später von den einzelnen Spielklassen überschrieben
	public String getHelpMessage() {
		return "";
	}
}
