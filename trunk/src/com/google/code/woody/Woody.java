package com.google.code.woody;

import java.util.Random;

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
		String nichtsPassiert = "Nichts passiert, " + Spieler.getNaechsterSpieler() + " ist dran";
		String neuerWoody = Spieler.getAktuellerSpieler() + " darf einen neuen Woody wählen";
		String pasch = super.getWuerfelZahl(0) + "er Pasch, " + Spieler.getAktuellerSpieler() + " darf "
				+ super.getWuerfelZahl(0) + " Schlücke verteilen";

		switch (ergebnis) {
		case 2:
			ausgabe = super.getWuerfelZahl(0) + "er Pasch, " + Spieler.getAktuellerSpieler() +
				" darf 1 Schluck verteilen";
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
					ausgabe = "3er Pasch, " + Spieler.getAktuellerSpieler() + " darf " +
					"3 Schlücke verteilen, und " + neuerWoody;
				} else
					ausgabe = "3er Pasch, " + Spieler.getAktuellerSpieler() + " darf 3 Schlücke verteilen und der Woody(" + getWerIstWoody()
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
					ausgabe = Spieler.getAktuellerSpieler() +"'s linker Nachbar(" + Spieler.getVorigerSpieler() + ") und der Woody("
							+ werIstWoody + ") müssen trinken";
				}
			} else
				ausgabe = Spieler.getAktuellerSpieler() +"'s linker Nachbar(" + Spieler.getVorigerSpieler() + ") muss einen trinken";
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
					ausgabe = Spieler.getAktuellerSpieler() + "'s rechter Nachbar(" + Spieler.getNaechsterSpieler() + ") und der Woody("
							+ getWerIstWoody() + ") müssen trinken";
			} else
				ausgabe = Spieler.getAktuellerSpieler() + "'s rechter Nachbar(" + Spieler.getNaechsterSpieler() + ") muss einen trinken";
			break;
		case 10:
			if (getWuerfelZahl(0) == 5 && getWuerfelZahl(1) == 5)
				ausgabe = "5er Pasch, " + Spieler.getAktuellerSpieler() + " darf " +
				" 5 Schlücke verteilen, und alle nehmen einen Schluck... Cheers!!!";
			else
				ausgabe = "Alle nehmen einen Schluck... Cheers!!!";
			break;
		case 11:
			ausgabe = nichtsPassiert;
			Spieler.incrementAktuellerSpieler();
			break;
		case 12:
			ausgabe = "JACKPOT!!!, " + Spieler.getAktuellerSpieler() + " darf 12 Schlücke verteilen";
			break;
		default:
			ausgabe = "Critical Error!!!";
			break;
		}
		return ausgabe;
	}
	
	public String spielerAmAnfangBestimmen() {
		Random generator = new Random();
		int spielerIndex = generator.nextInt(Spieler.getSpielerNameArrayList().size());
		String welcherSpieler = Spieler.getSpielerNameArrayList().get(spielerIndex);
		return welcherSpieler;
	}
	
	//Setzt den Text für die Spielerklärung
	public String getHelpMessage() {
		String s = 
			"Es wird reihum, offen gewürfelt. " +
			"Zu Beginn muss sich jemand freiwillig zum Woody ernennen. " + 
			"Der Woody trinkt immer wenn eine 3 in einem Würfel vorkommt oder wenn die Summe 3 ergibt. " +
			"Wenn der Woody selbst eine 3 würfelt, darf er einen neuen Woody wählen. " +
			"Der Woody kann auch jederzeit über das Optionsmenü geändert werden. " +
			"Hat ein Spieler einen Pasch, so darf er die Menge an Schlücken verteilen die auf einem Würfel steht. " +
			"Eine Ausnahme besteht darin, wenn der Spieler zwei sechsen würfelt, dann darf er die Summe aus beiden Würfeln verteilen. " +
			"Hat ein Spieler die Summe 7 gewürfelt, muss sein linker Nachbar trinken, bei 9 der rechte Nachbar. " +
			"Diese Möglichkeiten sind kummulierbar (Bsp.: wenn jemand 3 & 4 würfelt muss der linke Nachbar und der Woody trinken.";
		return s;
	}
}
