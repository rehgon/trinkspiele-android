package com.google.code.assrennen;


import java.util.ArrayList;
import java.util.Random;

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
  
}
