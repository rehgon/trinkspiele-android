package com.google.code.trinkspiele;

import java.util.ArrayList;

public class Spieler {

	private static int welcherSpielerAlsNaechstes = 0;
	private static int aktuellerSpielerIndex = 0;
	private static ArrayList<String> spielerNameListe = new ArrayList<String>();
	
	public static int getWelcherSpielerAlsNaechstes() {
		return welcherSpielerAlsNaechstes;
	}	
	public static void setWelcherSpielerAlsNaechstes(int spielerIndex) {
		welcherSpielerAlsNaechstes = spielerIndex;
	}
	public static String getAktuellerSpieler() {
		return spielerNameListe.get(aktuellerSpielerIndex);
	}
	public static int getAktuellerSpielerIndex() {
		return aktuellerSpielerIndex;
	}
	public static void setAktuellerSpielerIndex(int spielerIndex) {
		aktuellerSpielerIndex = spielerIndex;
	}
	public static ArrayList<String> getSpielerNameArrayList() {
		return spielerNameListe;
	}
	public static String getSpielerName(int spielerIndex) {
		return spielerNameListe.get(spielerIndex);
	}
	public static void setSpielerName(ArrayList<String> namensListe) {
		spielerNameListe = namensListe;
	}
	public static void setSpielerName(String name) {
		spielerNameListe.add(name);
	}
	public static void incrementAktuellerSpieler() {
		if (aktuellerSpielerIndex == (spielerNameListe.size() - 1))
			aktuellerSpielerIndex = 0;
		else
			aktuellerSpielerIndex++;
	}
	
	public static void spielerHinzufuegen(String name) {
		spielerNameListe.add(name);
	}
	
	public static String getVorigerSpieler() {
		if (getAktuellerSpielerIndex() != 0)
			return getSpielerName(getAktuellerSpielerIndex() - 1);
		else
			return getSpielerName(spielerNameListe.size());	
	}	
	public static String getNaechsterSpieler() {
		if (getAktuellerSpielerIndex() == (spielerNameListe.size() - 1))
			return getSpielerName(0);
		else {
			return getSpielerName(getAktuellerSpielerIndex() + 1);
		}
	}
	
	public static String naechsterSpieler() {
		int tmp = welcherSpielerAlsNaechstes; //tmp speichert den Wert von "welcherSpieler" um diesen dann unveraendert returnen zu kaennen
		if (welcherSpielerAlsNaechstes == spielerNameListe.size() - 1)
			welcherSpielerAlsNaechstes = 0;
		else
			welcherSpielerAlsNaechstes++;
		return (spielerNameListe.get(tmp) + ":");
	}

	public static void spielerSetzen() {
		spielerNameListe.add("Remo");
		spielerNameListe.add("Raffi");
		spielerNameListe.add("Tanguy");
		spielerNameListe.add("Amo");
	}
	
	public static String[] convertArrayListToArray() {
		String[] spielerNameArray = new String[spielerNameListe.size()];
		for (int i = 0; i < spielerNameListe.size(); i++) {
			spielerNameArray[i] = spielerNameListe.get(i);
		}
		return spielerNameArray;
	}
}



