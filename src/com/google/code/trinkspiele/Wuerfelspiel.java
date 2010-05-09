package com.google.code.trinkspiele;

import java.util.Random;

import android.widget.ImageView;

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
	
	public void paint(Wuerfelspiel spiel, ImageView image, int welcherWuerfel) {

		if (spiel.getWuerfelZahl(welcherWuerfel) == 1)
			image.setImageResource(R.drawable.w1);
		else if (spiel.getWuerfelZahl(welcherWuerfel) == 2)
			image.setImageResource(R.drawable.w2);
		else if (spiel.getWuerfelZahl(welcherWuerfel) == 3)
			image.setImageResource(R.drawable.w3);
		else if (spiel.getWuerfelZahl(welcherWuerfel) == 4)
			image.setImageResource(R.drawable.w4);
		else if (spiel.getWuerfelZahl(welcherWuerfel) == 5)
			image.setImageResource(R.drawable.w5);
		else if (spiel.getWuerfelZahl(welcherWuerfel) == 6)
			image.setImageResource(R.drawable.w6);
	}
}

