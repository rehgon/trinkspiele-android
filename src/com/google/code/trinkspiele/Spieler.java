package com.google.code.trinkspiele;

import java.util.ArrayList;

public class Spieler {

	private String nameDesSpiels;
	private int welcherSpielerAlsNaechstes;
	private int aktuellerSpielerIndex;
	private ArrayList<String> spielerName;
	
	public Spieler(String nameDesSpiels) {
		this.nameDesSpiels = nameDesSpiels;
		welcherSpielerAlsNaechstes = 0;
		aktuellerSpielerIndex = 0;
		spielerName = new ArrayList<String>();
	}
	
	public String getNameDesSpiels() {
		return nameDesSpiels;
	}
	public void setNameDesSpiels(String name) {
		this.nameDesSpiels = name;
	}
	public int getWelcherSpielerAlsNaechstes() {
		return welcherSpielerAlsNaechstes;
	}	
	public void setWelcherSpielerAlsNaechstes(int spielerIndex) {
		this.welcherSpielerAlsNaechstes = spielerIndex;
	}
	public String getAktuellerSpieler() {
		return getSpielerName(getAktuellerSpielerIndex());
	}
	public int getAktuellerSpielerIndex() {
		return aktuellerSpielerIndex;
	}
	public void setAktuellerSpielerIndex(int spielerIndex) {
		this.aktuellerSpielerIndex = spielerIndex;
	}
	public ArrayList<String> getSpielerName() {
		return spielerName;
	}
	public String getSpielerName(int spielerIndex) {
		return spielerName.get(spielerIndex);
	}
	public void setSpielerName(ArrayList<String> namensListe) {
		this.spielerName = namensListe;
	}
	public void setSpielerName(String name) {
		spielerName.add(name);
	}
	public void incrementAktuellerSpieler() {
		if (aktuellerSpielerIndex == (spielerName.size() - 1))
			this.aktuellerSpielerIndex = 0;
		else
			aktuellerSpielerIndex++;
	}
	
	public void spielerHinzufuegen(String name) {
		spielerName.add(name);
	}
	
	public String getVorigerSpieler() {
		if (getAktuellerSpielerIndex() != 0)
			return getSpielerName(getAktuellerSpielerIndex() - 1);
		else
			return getSpielerName(getSpielerName().size());	
	}	
	public String getNaechsterSpieler() {
		if (getAktuellerSpielerIndex() == (getSpielerName().size() - 1))
			return getSpielerName(0);
		else {
			return getSpielerName(getAktuellerSpielerIndex() + 1);
		}
	}
	
	public String naechsterSpieler() {
		int tmp = welcherSpielerAlsNaechstes; //tmp speichert den Wert von "welcherSpieler" um diesen dann unveraendert returnen zu kaennen
		if (welcherSpielerAlsNaechstes == getSpielerName().size() - 1)
			welcherSpielerAlsNaechstes = 0;
		else
			welcherSpielerAlsNaechstes++;
		return (getSpielerName().get(tmp) + ":");
	}

	public void spielerSetzen() {
		spielerName.add("Remo");
		spielerName.add("Raffi");
	}
	
	public String[] convertArrayListToArray() {
		String[] spielerNameArray = new String[spielerName.size()];
		for (int i = 0; i < spielerName.size(); i++) {
			spielerNameArray[i] = spielerName.get(i);
		}
		return spielerNameArray;
	}
}



