package com.google.code.woody;

import java.util.Random;

public abstract class Wuerfelspiel {
	
	private Random wuerfelGenerator;
	private int[] wuerfelZahl;
	private int ergebnis;
	
	public Wuerfelspiel() {
		wuerfelGenerator = new Random();
		wuerfelZahl = new int[10];
		ergebnis = 0;
	}

	public int getWuerfelZahl(int welcherWuerfel) {
		return wuerfelZahl[welcherWuerfel];
	}
	public void setWuerfelZahl(int wert, int welcherWuerfel) {
			wuerfelZahl[welcherWuerfel] = wert;
	}
	public int getErgebnis() {
		return ergebnis;
	}
	public void setErgebnis(int ergebnis) {
		this.ergebnis = ergebnis;
	}
	
	public void wuerfeln(int anzahlWuerfel) {
		ergebnis = 0;
		wuerfelZahl = new int[anzahlWuerfel];
		for (int i = 0; i < wuerfelZahl.length; i++) {
			wuerfelZahl[i] = wuerfelzahlGenerieren();
			ergebnis += wuerfelZahl[i];
		}
	}
	private int wuerfelzahlGenerieren() {
		return wuerfelGenerator.nextInt(6) + 1;
	}

	public boolean istGerade(int wert) {
		if (wert % 2 == 0)
			return true;
		else
			return false;
	}
}

