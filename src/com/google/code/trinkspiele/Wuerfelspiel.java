package com.google.code.trinkspiele;

import java.util.Random;

import android.content.Context;
import android.widget.ImageView;

public abstract class Wuerfelspiel extends Spiel {
	
	private Random wuerfelGenerator;
	private int[] wuerfelZahl;
	private int ergebnis;
	
	int[] diceDrawablesLocations = { 
			R.drawable.delete, 
			R.drawable.w1, 
			R.drawable.w2, 
			R.drawable.w3, 
			R.drawable.w4, 
			R.drawable.w5,
			R.drawable.w6
	};
	
	
	public Wuerfelspiel(Context context)
	{
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
	
	public void paint(Wuerfelspiel spiel, ImageView image, int wuerfelZahl) {
		try {
			image.setImageResource(diceDrawablesLocations[wuerfelZahl]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void paintBackground(Wuerfelspiel spiel, ImageView image, int wuerfelZahl) {
		try {
			image.setBackgroundResource(diceDrawablesLocations[wuerfelZahl]);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	//Wird später von den einzelnen Spielklassen überschrieben
	public String getHelpMessage() {
		return "";
	}
}

