package com.google.code.meier;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;

import com.google.code.trinkspiele.Wuerfelspiel;

public class Meier extends Wuerfelspiel {
	public static final String TAG = "Meier";

	private int wuerfel1, wuerfel2, ergebnis, vorigeZahl, gelogeneZahl;
	private Random zufallsZahl = new Random();
	private boolean glaubtDassGelogen;

	public Meier(Context context) {
		super(context);
		wuerfel1 = 0;
		wuerfel2 = 0;
		ergebnis = 0;
		vorigeZahl = 0;
		gelogeneZahl = 0;
		glaubtDassGelogen = false;
	}

	public int getErgebnis() {
		return ergebnis;
	}

	public void setErgebnis(int ergebnis) {
		this.ergebnis = ergebnis;
	}

	public int getVorigeZahl() {
		return vorigeZahl;
	}

	public void setVorigeZahl(int vorigeZahl) {
		this.vorigeZahl = vorigeZahl;
	}

	public int getGelogeneZahl() {
		return gelogeneZahl;
	}

	public void setGelogeneZahl(int zahl) {
		gelogeneZahl = zahl;
	}

	public boolean getGlaubtDassGelogen() {
		return glaubtDassGelogen;
	}

	public void setGlaubtDassGelogen(boolean glaubtDassGelogen) {
		this.glaubtDassGelogen = glaubtDassGelogen;
	}

	public void wuerfeln() {
		vorigeZahl = ergebnis;
		wuerfel1 = wuerfelzahlGenerieren();
		wuerfel2 = wuerfelzahlGenerieren();
		if (wuerfel1 >= wuerfel2)
			ergebnis = (wuerfel1 * 10) + wuerfel2;
		else
			ergebnis = (wuerfel2 * 10) + wuerfel1;
	}

	public boolean hatGelogen() {
		if (!istHoeher(ergebnis, this.vorigeZahl))
			return true;
		else
			return false;
	}

	public boolean istHoeher(int zahl1, int zahl2) {
		if (istPaschOderMeier(zahl1) == true
				&& istPaschOderMeier(zahl2) == true) { // 2x Pasch
			if (zahl2 == 21)
				return false;
			else if (zahl1 == 21) // if Ergebnis == Meier
				return true;
			else if (zahl1 > zahl2)
				return true;
			else
				// ergebnis <= vorigeZahl
				return false;
		} else if (istPaschOderMeier(zahl1) == true
				&& istPaschOderMeier(zahl2) == false)
			return true;
		else if (istPaschOderMeier(zahl1) == false
				&& istPaschOderMeier(zahl2) == true)
			return false;
		else {
			if (zahl1 > zahl2)
				return true;
			else
				return false;
		}
	}

	private int wuerfelzahlGenerieren() {
		return zufallsZahl.nextInt(6) + 1;
	}

	public boolean istPaschOderMeier(int zahl) {
		if (zahl == 21)
			return true;
		else if ((zahl / 10) == (zahl % 10))
			return true;
		else
			return false;
	}

	public String[] moeglicheLuegenZahlen(int startZahl) {

		ArrayList<Integer> numbersArrayList = new ArrayList<Integer>();

		if (startZahl == 0) {
			startZahl = getErgebnis();
		}
		// Falls Zahl = kein Pasch und kein Meier oder erster Wurf
		else if (((startZahl % 10) != (startZahl / 10))) {

			for (int i = startZahl + 1; i <= 65; i++) {
				if (((i % 10) < (i / 10)) && (i % 10 != 0) && (i != 21))
					numbersArrayList.add(i);
			}
			for (int i = 11; i <= 66; i++) {
				if (i % 10 == i / 10)
					numbersArrayList.add(i);
			}
		}
		// Falls Zahl = Pasch
		else {
			// Falls jemand gleich zu Beginn lügen möchte && er einen Pasch hat
			int index = getVorigeZahl();
			if (index == 0)
				index = getErgebnis();

			for (int i = index + 11; i <= 66; i++) {
				if (i % 10 == i / 10)
					numbersArrayList.add(i);
			}
		}
		numbersArrayList.add(21);

		String[] numbersArrayConvertedToCharSequence = new String[numbersArrayList
				.size()];
		// Charsequence Array setzen und zurückgeben
		for (int i = 0; i < numbersArrayConvertedToCharSequence.length; i++) {
			numbersArrayConvertedToCharSequence[i] = numbersArrayList.get(i)
					+ "";
		}

		return numbersArrayConvertedToCharSequence;
	}
}
