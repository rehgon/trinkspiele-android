package com.google.code.woody;

import java.util.Random;

import android.content.Context;

import com.google.code.trinkspiele.R;
import com.google.code.trinkspiele.Spieler;
import com.google.code.trinkspiele.Wuerfelspiel;

public class Woody extends Wuerfelspiel {

	private String werIstWoody;
	private boolean neuerWoody;
	private Context context;

	public Woody(Context context) {
		super();
		werIstWoody = "";
		neuerWoody = false;
		this.context = context;
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
		
		String WoodyMussTrinken = 	context.getString(R.string.woody_Der_woody) + "(" + getWerIstWoody() +
									") " + context.getString(R.string.woody_muss_trinken);
		
		String nichtsPassiert = 	context.getString(R.string.woody_nichts_passiert)+ ", " + context.getString(R.string.woody___nur_eng__its) + 
									Spieler.getNaechsterSpieler() + context.getString(R.string.woody_ist_dran_mit_wuerfeln);
		
		String neuerWoody = 		Spieler.getAktuellerSpieler() + " " + context.getString(R.string.woody_darf_einen_neuen_woody_waehlen);
		
		String pasch = 				super.getWuerfelZahl(0) + context.getString(R.string.woody_er_pasch) + ", " + Spieler.getAktuellerSpieler() + " " + context.getString(R.string.woody_darf) +
									" " + super.getWuerfelZahl(0) + " " + context.getString(R.string.woody_schluecke_verteilen);

		switch (ergebnis) {
		case 2:
			ausgabe = super.getWuerfelZahl(0) + context.getString(R.string.woody_er_pasch) +" " + Spieler.getAktuellerSpieler() +
				" " + context.getString(R.string.woody_darf_einen_schluck_verteilen);
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
					ausgabe = getWuerfelZahl(0) + context.getString(R.string.woody_er_pasch) + ", " + 
					Spieler.getAktuellerSpieler() + " " + context.getString(R.string.woody_darf) + " " +
					getWuerfelZahl(0) + " " + context.getString(R.string.woody_schluecke_verteilen) + " " + context.getString(R.string.woody_und)+ " " + neuerWoody;
				} else
					ausgabe = getWuerfelZahl(0) + context.getString(R.string.woody_er_pasch) + ", " + 
						Spieler.getAktuellerSpieler() + " " + context.getString(R.string.woody_darf) + " " +
						getWuerfelZahl(0) + " " + context.getString(R.string.woody_schluecke_verteilen) + " " + context.getString(R.string.woody_und)+ 
						" " + context.getString(R.string.woody_der_woody)+ "(" + getWerIstWoody() + ") " + context.getString(R.string.woody_muss_trinken);
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
					ausgabe = Spieler.getAktuellerSpieler() + context.getString(R.string.woody_s_linker_nachbar) + "(" + Spieler.getVorigerSpieler() + ") " +
					context.getString(R.string.woody_und) + " " + context.getString(R.string.woody_der_woody) + "(" + werIstWoody + ") "  + context.getString(R.string.woody_muessen_trinken);
				}
			} else
				ausgabe = Spieler.getAktuellerSpieler() + context.getString(R.string.woody_s_linker_nachbar) + "(" + Spieler.getVorigerSpieler() + ") " + context.getString(R.string.woody_muss_trinken);
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
					ausgabe = Spieler.getAktuellerSpieler() + context.getString(R.string.woody_s_linker_nachbar) + "(" + Spieler.getNaechsterSpieler() + ") " + context.getString(R.string.woody_und) + " " + context.getString(R.string.woody_der_woody) +
							"(" + getWerIstWoody() + ") " + context.getString(R.string.woody_muessen_trinken);
			} else
				ausgabe = Spieler.getAktuellerSpieler() + context.getString(R.string.woody_s_rechter_nachbar) + "(" + Spieler.getNaechsterSpieler() + ") " + context.getString(R.string.woody_muss_trinken);
			break;
		case 10:
			if (getWuerfelZahl(0) == 5 && getWuerfelZahl(1) == 5)
				ausgabe = getWuerfelZahl(0) + context.getString(R.string.woody_er_pasch) + ", " + Spieler.getAktuellerSpieler() + " " + context.getString(R.string.woody_darf) + " " +
				getWuerfelZahl(0) + " " + context.getString(R.string.woody_schluecke_verteilen) + ", " + context.getString(R.string.woody_und) + " " +  context.getString(R.string.woody_alle_nehmen_einen_Schluck);
			else
				ausgabe = context.getString(R.string.woody_alle_nehmen_einen_Schluck);
			break;
		case 11:
			ausgabe = nichtsPassiert;
			Spieler.incrementAktuellerSpieler();
			break;
		case 12:
			ausgabe = context.getString(R.string.woody_jackpot) + " " + Spieler.getAktuellerSpieler() + " " + context.getString(R.string.woody_darf) + " " + getErgebnis() + " " +context.getString(R.string.woody_schluecke_verteilen);
			break;
		default:
			ausgabe = context.getString(R.string.kritischer_fehler);
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
		return context.getString(R.string.woody_help_message);
	}
}
