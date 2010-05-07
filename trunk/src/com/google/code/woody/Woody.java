package com.google.code.woody;

import com.google.code.trinkspiele.Spieler;
import com.google.code.trinkspiele.Wuerfelspiel;

public class Woody extends Wuerfelspiel {

	private String werIstWoody;
	private boolean neuerWoody;

	public Woody() {
		super();
		werIstWoody = "";
		neuerWoody = false;
	}

	public String getWerIstWoody() {
		return werIstWoody;
	}
	public void setWerIstWoody(int index) {
		werIstWoody = Spieler.getSpielerName(index);
	}
	public boolean getNeuerWoody() {
		return neuerWoody;
	}
	public void setNeuerWoody(boolean neuerWoody) {
		this.neuerWoody = neuerWoody;
	}

	// Wertet den Wurf aus und liefert die Ausgabe als String zurück
	public String auswerten(int ergebnis) {

		String ausgabe = "";
		String WoodyMussTrinken = "Der Woody(" + getWerIstWoody()
				+ ") muss trinken";
		String nichtsPassiert = "Nichts passiert, du musst weitergeben";
		String neuerWoody = "Es gibt einen neuen Woody";
		String pasch = super.getWuerfelZahl(0) + "er Pasch, du darfst "
				+ super.getWuerfelZahl(0) + " Schlücke verteilen";

		switch (ergebnis) {
		case 2:
			ausgabe = pasch;
			break;
		case 3:
			if (Spieler.getAktuellerSpieler().equals(werIstWoody)) {
				setNeuerWoody(true);
				ausgabe = neuerWoody;
			} else
				ausgabe = WoodyMussTrinken;
			break;
		case 4:
			if (getWuerfelZahl(0) == 3 || getWuerfelZahl(1) == 3) {
				if (Spieler.getAktuellerSpieler().equals(werIstWoody)) {
					setNeuerWoody(true);
					ausgabe = neuerWoody;
				} else {
					ausgabe = WoodyMussTrinken;
				}
			} else {
				ausgabe = pasch;
			}
			break;
		case 5:
			if (getWuerfelZahl(0) == 3 || getWuerfelZahl(1) == 3) {
				if (Spieler.getAktuellerSpieler().equals(werIstWoody)) {
					setNeuerWoody(true);
					ausgabe = neuerWoody;
				} else
					ausgabe = WoodyMussTrinken;
			} else {
				ausgabe = nichtsPassiert;
				Spieler.incrementAktuellerSpieler();
			}
			break;
		case 6:
			if (getWuerfelZahl(0) == 3 && getWuerfelZahl(1) == 3) {
				if (Spieler.getAktuellerSpieler().equals(werIstWoody)) {
					ausgabe = pasch;
					setNeuerWoody(true);
					ausgabe = "3er Pasch, und es gibt einen neuen Woody";
				} else
					ausgabe = "3er Pasch, und der Woody(" + getWerIstWoody()
							+ ") muss einen trinken";
			} else if (getWuerfelZahl(0) == 3 || getWuerfelZahl(1) == 3) {
				if (Spieler.getAktuellerSpieler().equals(werIstWoody)) {
					setNeuerWoody(true);
					ausgabe = neuerWoody;
				} else
					ausgabe = WoodyMussTrinken;
			} else {
				ausgabe = nichtsPassiert;
				Spieler.incrementAktuellerSpieler();
			}
			break;
		case 7:
			if (getWuerfelZahl(0) == 3 || getWuerfelZahl(1) == 3) {
				if (Spieler.getAktuellerSpieler().equals(werIstWoody)) {
					setNeuerWoody(true);
					ausgabe = neuerWoody;
				} else {
					ausgabe = "Dein linker Nachbar und der Woody("
							+ werIstWoody + ") müssen trinken";
				}
			} else
				ausgabe = "Dein linker Nachbar muss einen trinken";
			break;
		case 8:
			if (getWuerfelZahl(0) == 3 || getWuerfelZahl(1) == 3) {
				if (Spieler.getAktuellerSpieler().equals(werIstWoody)) {
					setNeuerWoody(true);
					ausgabe = neuerWoody;
				} else
					ausgabe = WoodyMussTrinken;
			} else if (getWuerfelZahl(0) == 4 && getWuerfelZahl(1) == 4)
				ausgabe = pasch;
			else {
				ausgabe = nichtsPassiert;
				Spieler.incrementAktuellerSpieler();
			}
			break;
		case 9:
			if (getWuerfelZahl(0) == 3 || getWuerfelZahl(1) == 3) {
				if (Spieler.getAktuellerSpieler().equals(werIstWoody)) {
					setNeuerWoody(true);
					ausgabe = neuerWoody;
				} else
					ausgabe = "Dein rechter Nachbar und der Woody("
							+ getWerIstWoody() + ") müssen trinken";
			} else
				ausgabe = "Dein rechter Nachbar muss einen trinken";
			break;
		case 10:
			if (getWuerfelZahl(0) == 5 && getWuerfelZahl(1) == 5)
				ausgabe = "5er Pasch, und alle nehmen einen Schluck";
			else
				ausgabe = "Alle nehmen einen Schluck... Cheers!!!";
			break;
		case 11:
			ausgabe = nichtsPassiert;
			Spieler.incrementAktuellerSpieler();
			break;
		case 12:
			ausgabe = "JACKPOT!!!, du darfst 12 Schlücke verteilen";
			break;
		default:
			ausgabe = "Critical Error!!!";
			break;
		}
		return ausgabe;
	}
}
