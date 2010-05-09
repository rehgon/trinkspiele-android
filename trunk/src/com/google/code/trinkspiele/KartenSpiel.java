package com.google.code.trinkspiele;


import java.util.ArrayList;
import java.util.Random;

import android.widget.ImageView;

public class KartenSpiel {
	private Random generator = new Random();
	private ArrayList<String> deck = new ArrayList<String>();
	private String[] kartenSymbole;
	private String[] kartenWerte;
	
	public KartenSpiel() {
		generator = new Random();
		deck = new ArrayList<String>();
		kartenSymbole = new String[] { "Karo", "Kreuz", "Herz", "Pik" };
		kartenWerte = new String[] { "2", "3", "4", "5", "6", "7",
				"8", "9", "10", "Bube", "Dame", "König", "Ass"};
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
		String[] karte = { "Deck enthält zu wenig Karten" };
		if (wieviele > deck.size())
			karte = new String[wieviele];
		for (int i = 0; i < karte.length; i++) {
				karte[i] = deck.get(i);
				deck.remove(i);
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
		
		if (symbol.equals("Pik")) {
			if (wert == 13)
				image.setImageResource(R.drawable.pik_koenig);
			else if (wert == 12)
				image.setImageResource(R.drawable.pik_dame);
			else if (wert == 11)
				image.setImageResource(R.drawable.pik_bube);
			else if (wert == 10)
				image.setImageResource(R.drawable.pik10);
			else if (wert == 9)
				image.setImageResource(R.drawable.pik9);
			else if (wert == 8)
				image.setImageResource(R.drawable.pik8);
			else if (wert == 7)
				image.setImageResource(R.drawable.pik7);
			else if (wert == 6)
				image.setImageResource(R.drawable.pik6);
			else if (wert == 5)
				image.setImageResource(R.drawable.pik5);
			else if (wert == 4)
				image.setImageResource(R.drawable.pik4);
			else if (wert == 3)
				image.setImageResource(R.drawable.pik3);
			else if (wert == 2)
				image.setImageResource(R.drawable.pik2);
			else if (wert == 1)
				image.setImageResource(R.drawable.pikass);			
		}
		else if (symbol.equals("Karo")) {
			if (wert == 13)
				image.setImageResource(R.drawable.karo_koenig);
			else if (wert == 12)
				image.setImageResource(R.drawable.karo_dame);
			else if (wert == 11)
				image.setImageResource(R.drawable.karo_bube);
			else if (wert == 10)
				image.setImageResource(R.drawable.karo10);
			else if (wert == 9)
				image.setImageResource(R.drawable.karo9);
			else if (wert == 8)
				image.setImageResource(R.drawable.karo8);
			else if (wert == 7)
				image.setImageResource(R.drawable.karo7);
			else if (wert == 6)
				image.setImageResource(R.drawable.karo6);
			else if (wert == 5)
				image.setImageResource(R.drawable.karo5);
			else if (wert == 4)
				image.setImageResource(R.drawable.karo4);
			else if (wert == 3)
				image.setImageResource(R.drawable.karo3);
			else if (wert == 2)
				image.setImageResource(R.drawable.karo2);
			else if (wert == 1)
				image.setImageResource(R.drawable.karoass);
		}
		else if (symbol.equals("Herz")) {
			if (wert == 13)
				image.setImageResource(R.drawable.herz_koenig);
			else if (wert == 12)
				image.setImageResource(R.drawable.herz_dame);
			else if (wert == 11)
				image.setImageResource(R.drawable.herz_bube);
			else if (wert == 10)
				image.setImageResource(R.drawable.herz10);
			else if (wert == 9)
				image.setImageResource(R.drawable.herz9);
			else if (wert == 8)
				image.setImageResource(R.drawable.herz8);
			else if (wert == 7)
				image.setImageResource(R.drawable.herz7);
			else if (wert == 6)
				image.setImageResource(R.drawable.herz6);
			else if (wert == 5)
				image.setImageResource(R.drawable.herz5);
			else if (wert == 4)
				image.setImageResource(R.drawable.herz4);
			else if (wert == 3)
				image.setImageResource(R.drawable.herz3);
			else if (wert == 2)
				image.setImageResource(R.drawable.herz2);
			else if (wert == 1)
				image.setImageResource(R.drawable.herzass);
		}
		else if (symbol.equals("Kreuz")) {
			if (wert == 13)
				image.setImageResource(R.drawable.kreuz_koenig);
			else if (wert == 12)
				image.setImageResource(R.drawable.kreuz_dame);
			else if (wert == 11)
				image.setImageResource(R.drawable.kreuz_bube);
			else if (wert == 10)
				image.setImageResource(R.drawable.kreuz10);
			else if (wert == 9)
				image.setImageResource(R.drawable.kreuz9);
			else if (wert == 8)
				image.setImageResource(R.drawable.kreuz8);
			else if (wert == 7)
				image.setImageResource(R.drawable.kreuz7);
			else if (wert == 6)
				image.setImageResource(R.drawable.kreuz6);
			else if (wert == 5)
				image.setImageResource(R.drawable.kreuz5);
			else if (wert == 4)
				image.setImageResource(R.drawable.kreuz4);
			else if (wert == 3)
				image.setImageResource(R.drawable.kreuz3);
			else if (wert == 2)
				image.setImageResource(R.drawable.kreuz2);
			else if (wert == 1)
				image.setImageResource(R.drawable.kreuzass);
		}
		else
			image.setImageResource(R.drawable.close);
	}
}
